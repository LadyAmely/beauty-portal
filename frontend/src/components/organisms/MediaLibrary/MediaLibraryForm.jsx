import React from "react";
import MediaLibraryFormPropTypes from "./MediaLibraryForm.propTypes";
import SelectGroup from "../../molecules/SelectGroup/SelectGroup";
import TopBar from "../../atoms/TopBar/TopBar";
import Footer from "../../atoms/Footer/Footer";
import "./MediaLibraryForm.scss";
import MediaFileGrid from "../../molecules/Media/MediaFileGrid/MediaFileGrid";
import TableToolbar from "../../molecules/TableToolbar/TableToolbar";
import SearchBar from "../../atoms/SearchBar/SearchBar";

const MediaLibraryForm = ({
                              title,
                              sortValue,
                              onSortChange,
                              sortOptions,
                              fileList,
                              footerNote,
                          }) => {
    return (
        <div className="media-library-form">
            <h2>{title}</h2>
            <TableToolbar title="Media Library" />
            <section className="purchase-report-form__table-section">
                <TopBar className="media-library-form__topbar">
                    <SearchBar value="" />
                </TopBar>
            </section>
            <MediaFileGrid fileList={fileList} />
            {footerNote && (
                <Footer className="media-library-form__footer">{footerNote}</Footer>
            )}
        </div>
    );
};

MediaLibraryForm.propTypes = MediaLibraryFormPropTypes;

export default MediaLibraryForm;
