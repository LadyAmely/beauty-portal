import React from "react";
import SalesChannelsForm from "../../organisms/SalesChannels/SalesChannelsForm";
import {useSalesChannelsController} from "../../../hooks/salesChannel/useSalesChannelReport";

const SalesChannelsTemplate = () => {
    const distributorId = "b3f2a2c1-1234-4e56-9876-abcdef123456";
    const {
        selectedChannel,
        setSelectedChannel,
        channelOptions,
        channelData,
        loading,
        onExport,
    } = useSalesChannelsController(distributorId);

    return (
        <div className="select-channels-template">
            <SalesChannelsForm
                selectedChannel={selectedChannel}
                channelOptions={channelOptions}
                onChannelChange={(e) => setSelectedChannel(e.target.value)}
                onExport={onExport}
                channelData={channelData}
                loading={loading}
            />
        </div>
    );
};

export default SalesChannelsTemplate;
