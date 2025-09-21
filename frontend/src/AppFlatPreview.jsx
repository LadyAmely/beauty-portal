import React from "react";
import SalesChannelsPage from "./components/pages/SalesChannels/SalesChannelsPage";
import MediaLibraryPage from "./components/pages/MediaLibrary/MediaLibraryPage";
import PurchaseReportPage from "./components/pages/PurchaseReport/PurchaseReportPage";

const AppFlatPreview = () => {
    return (
        <div className="AppFlatPreview">
            <SalesChannelsPage />
            <MediaLibraryPage />
            <PurchaseReportPage />
        </div>
    );
};

export default AppFlatPreview;
