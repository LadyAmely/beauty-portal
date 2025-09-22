import { useState, useEffect } from "react";
import {
    fetchMediaItems,
    uploadMediaFile,
    deleteMediaFile,
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

    useEffect(() => {
        const loadMedia = async () => {
            try {
                const items = await fetchMediaItems();
                setMediaItems(items);
            } catch (err) {
                console.error("Failed to load media", err);
            } finally {
                setLoading(false);
            }
        };

        loadMedia();
    }, []);

    const handleUpload = async () => {
        const input = document.createElement("input");
        input.type = "file";
        input.onchange = async (e) => {
            const file = e.target.files[0];
            if (file) {
                await uploadMediaFile(file);
                const updated = await fetchMediaItems();
                setMediaItems(updated);
            }
        };
        input.click();
    };

    const handlePreview = (item) => {
        window.open(item.url, "_blank");
    };

    const handleDelete = async (itemId) => {
        await deleteMediaFile(itemId);
        const updated = await fetchMediaItems();
        setMediaItems(updated);
    };

    const filteredItems = mediaItems.filter((item) =>
        item.type.toLowerCase().includes(selectedCategory.toLowerCase())
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
