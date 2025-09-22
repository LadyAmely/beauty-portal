import PropTypes from "prop-types";

const MediaLibraryFormPropTypes = {
    title: PropTypes.string,
    searchInput: PropTypes.node,
    sortSelect: PropTypes.node,
    fileList: PropTypes.arrayOf(
        PropTypes.shape({
            name: PropTypes.string.isRequired,
            type: PropTypes.string,
            size: PropTypes.string,
            date: PropTypes.string,
            onDownload: PropTypes.func.isRequired,
            checkbox: PropTypes.node,
        })
    ),
    onDownloadSelected: PropTypes.func,
    footerNote: PropTypes.node,
};

export default MediaLibraryFormPropTypes;
