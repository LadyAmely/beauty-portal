// src/hooks/media/useMediaLibrary.js
import { useState, useEffect } from "react";
import {
    getAllMediaFiles,
    uploadSingleFile,
} from "../../api/MediaApi";

export const useMediaLibraryController = () => {
    const [selectedCategory, setSelectedCategory] = useState("Images");
    const [mediaItems, setMediaItems] = useState([]);
    const [loading, setLoading] = useState(true);

    const categoryOptions = [
        { value: "Images", label: "Images" },
        { value: "Documents", label: "Documents" },
        { value: "Videos", label: "Videos" },
        { value: "Audio", label: "Audio" },
    ];

    const loadMedia = async () => {
        try {
            const items = await getAllMediaFiles();
            setMediaItems(items);
        } catch (err) {
            console.error("Failed to load media", err);
        } finally {
            setLoading(false);
        }
    };

    useEffect(() => {
        loadMedia();
    }, []);

    const handleUpload = async () => {
        const input = document.createElement("input");
        input.type = "file";
        input.onchange = async (e) => {
            const file = e.target.files?.[0];
            if (!file) return;
            await uploadSingleFile(file);
            await loadMedia();
        };
        input.click();
    };

    const handlePreview = (item) => {
        window.open(item.url, "_blank");
    };

    const handleDelete = async (itemId) => {
        console.warn("deleteMediaFile niezaimplementowane w MediaApi");
    };

    const filteredItems = mediaItems.filter((item) =>
        item.type?.toLowerCase().includes(selectedCategory.toLowerCase())
    );

    return {
        categoryOptions,
        selectedCategory,
        setSelectedCategory,
        mediaItems: filteredItems,
        loading,
        onUpload: handleUpload,
        onPreview: handlePreview,
        onDelete: handleDelete,
    };
};
