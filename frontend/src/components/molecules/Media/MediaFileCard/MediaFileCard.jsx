import React from "react";
import Card from "../../../atoms/Card/Card";
import Button from "../../../atoms/Button/Button";

const MediaFileCard = ({ file }) => (
    <Card className="media-library-form__card">
        <div className="media-library-form__card-name">{file.name}</div>
        <div className="media-library-form__card-meta">
            {file.type} • {file.size} • {file.date}
        </div>
        <div className="media-library-form__card-actions">
            <Button label="Download" onClick={() => file.onDownload(file.name)} />
            {file.checkbox}
        </div>
    </Card>
);

export default MediaFileCard;
