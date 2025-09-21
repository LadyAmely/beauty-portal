import React, { useState } from "react";
import MediaLibraryForm from "../../organisms/MediaLibrary/MediaLibraryForm";

const MediaLibraryTemplate = () => {
    const [selectedCategory, setSelectedCategory] = useState("Images");

    const categoryOptions = [
        { value: "Images", label: "Images" },
        { value: "Documents", label: "Documents" },
        { value: "Videos", label: "Videos" },
        { value: "Audio", label: "Audio" },
    ];

    const mediaItems = [
        {
            id: "img-001",
            name: "Product Banner",
            type: "image",
            url: "/media/product-banner.jpg",
            uploadedAt: "2025-09-15",
        },
        {
            id: "doc-002",
            name: "Brand Guidelines",
            type: "document",
            url: "/media/brand-guidelines.pdf",
            uploadedAt: "2025-09-10",
        },
        {
            id: "vid-003",
            name: "Promo Video",
            type: "video",
            url: "/media/promo.mp4",
            uploadedAt: "2025-09-05",
        },
    ];

    const handleUpload = () => {
        alert("Uploading new media...");
    };

    const handlePreview = (item) => {
        alert(`Previewing: ${item.name}`);
    };

    const handleDelete = (itemId) => {
        alert(`Deleting item: ${itemId}`);
    };

    return (
        <div className="media-library-template">
            <MediaLibraryForm
                categoryOptions={categoryOptions}
                selectedCategory={selectedCategory}
                onCategoryChange={(e) => setSelectedCategory(e.target.value)}
                mediaItems={mediaItems.filter((item) =>
                    item.type.toLowerCase().includes(selectedCategory.toLowerCase())
                )}
                onUpload={handleUpload}
                onPreview={handlePreview}
                onDelete={handleDelete}
            />
        </div>
    );
};

export default MediaLibraryTemplate;
