import React from "react";
import {useSalesChannelsController} from "../../hooks/salesChannel/useSalesChannelReport";
import SalesChannelsForm from "../../components/organisms/SalesChannels/SalesChannelsForm";
import DistributorForm from "../../components/organisms/Distributor/DistributorForm";
import {useDistributorForm} from "../../hooks/distributor/useDistributoForm";

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
    const {
        handleSubmit,
    } = useDistributorForm();

    return (
        <div className="select-channels-template">
            <DistributorForm onSubmit={handleSubmit}/>
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
