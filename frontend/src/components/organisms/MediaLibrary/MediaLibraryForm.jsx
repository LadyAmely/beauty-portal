import React from "react";
import MediaLibraryFormPropTypes from "./MediaLibraryForm.propTypes";
import InputGroup from "../../molecules/InputGroup/InputGroup";
import SelectGroup from "../../molecules/SelectGroup/SelectGroup";
import TopBar from "../../atoms/TopBar/TopBar";
import Footer from "../../atoms/Footer/Footer";
import "./MediaLibraryForm.scss";
import MediaFileGrid from "../../molecules/Media/MediaFileGrid/MediaFileGrid";

const MediaLibraryForm = ({
                              title,
                              searchValue,
                              onSearchChange,
                              sortValue,
                              onSortChange,
                              sortOptions,
                              fileList,
                              footerNote,
                          }) => {
    return (
        <div className="media-library-form">
            <h2>{title}</h2>
            <TopBar className="media-library-form__topbar">
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
            </TopBar>
            <MediaFileGrid fileList={fileList} />
            {footerNote && (
                <Footer className="media-library-form__footer">{footerNote}</Footer>
            )}
        </div>
    );
};

MediaLibraryForm.propTypes = MediaLibraryFormPropTypes;

export default MediaLibraryForm;
