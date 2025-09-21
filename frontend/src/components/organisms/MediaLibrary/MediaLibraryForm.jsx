import React from "react";
import MediaLibraryFormPropTypes from "./MediaLibraryForm.propTypes";
import InputGroup from "../../molecules/InputGroup/InputGroup";
import SelectGroup from "../../molecules/SelectGroup/SelectGroup";
import Card from "../../atoms/Card/Card";
import Button from "../../atoms/Button/Button";
import "./MediaLibraryForm.scss";

const MediaLibraryForm = ({
                              title,
                              searchValue,
                              onSearchChange,
                              sortValue,
                              onSortChange,
                              sortOptions,
                              fileList,
                              onDownloadSelected,
                              footerNote,
                          }) => {
    return (
        <div className="media-library-form">
            <h2>{title}</h2>

            <div className="media-library-form__topbar">
                <InputGroup
                    label="Search by SKU"
                    name="skuSearch"
                    value={searchValue}
                    onChange={onSearchChange}
                    placeholder="e.g. SKU123"
                />

                <SelectGroup
                    label="Sort by"
                    name="sort"
                    options={sortOptions}
                    value={sortValue}
                    onChange={onSortChange}
                />

                <Button label="Download selected" onClick={onDownloadSelected} />
            </div>

            <div className="media-library-form__grid">
                {fileList && fileList.length > 0 ? (
                    fileList.map((file, idx) => (
                        <Card key={idx} className="media-library-form__card">
                            <div className="media-library-form__card-name">{file.name}</div>
                            <div className="media-library-form__card-meta">
                                {file.type} • {file.size} • {file.date}
                            </div>
                            <div className="media-library-form__card-actions">
                                <Button
                                    label="Download"
                                    onClick={() => file.onDownload(file.name)}
                                />
                                {file.checkbox}
                            </div>
                        </Card>
                    ))
                ) : (
                    <p>No files to display</p>
                )}
            </div>

            {footerNote && (
                <div className="media-library-form__footer">{footerNote}</div>
            )}
        </div>
    );
};

MediaLibraryForm.propTypes = MediaLibraryFormPropTypes;

export default MediaLibraryForm;
