import React from "react";
import { Routes, Route } from "react-router-dom";
import SalesChannelsPage from "./pages/SalesChannels/SalesChannelsPage";
import MediaLibraryPage from "./pages/MediaLibrary/MediaLibraryPage";
import PurchaseReportPage from "./pages/PurchaseReport/PurchaseReportPage";
import {Home} from "./pages/Home/Home";

function App() {
    return (
        <Routes>
            <Route path="/" element={<Home/>} />
            <Route path="/sales-channels" element={<SalesChannelsPage />} />
            <Route path="/media-library" element={<MediaLibraryPage />} />
            <Route path="/purchase-report" element={<PurchaseReportPage />} />
        </Routes>
    );
}

export default App;
