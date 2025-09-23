import SalesChannelsPage from "../SalesChannels/SalesChannelsPage";
import PurchaseReportPage from "../PurchaseReport/PurchaseReportPage";
import MediaLibraryPage from "../MediaLibrary/MediaLibraryPage";

export const Home =()=>{
    return(
        <div>
            <SalesChannelsPage/>
            <PurchaseReportPage/>
            <MediaLibraryPage/>
        </div>
    );
}