import React from "react";
import MediaLibraryForm from "../../organisms/MediaLibrary/MediaLibraryForm";
import {useMediaLibraryController} from "../../../hooks/media/useMediaLibrary";


const MediaLibraryTemplate = () => {
    const {
        categoryOptions,
        selectedCategory,
        setSelectedCategory,
        mediaItems,
        loading,
        onUpload,
        onPreview,
        onDelete,
    } = useMediaLibraryController();

    return (
        <div className="media-library-template">
            <MediaLibraryForm
                categoryOptions={categoryOptions}
                selectedCategory={selectedCategory}
                onCategoryChange={(e) => setSelectedCategory(e.target.value)}
                mediaItems={mediaItems}
                onUpload={onUpload}
                onPreview={onPreview}
                onDelete={onDelete}
                loading={loading}
            />
        </div>
    );
};

export default MediaLibraryTemplate;
