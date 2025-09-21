import React from "react";
import MediaFileCard from "../MediaFileCard/MediaFileCard";

const MediaFileGrid = ({ fileList }) => (
    <div className="media-library-form__grid">
        {fileList && fileList.length > 0 ? (
            fileList.map((file, idx) => <MediaFileCard key={idx} file={file} />)
        ) : (
            <p>No files to display</p>
        )}
    </div>
);

export default MediaFileGrid;
