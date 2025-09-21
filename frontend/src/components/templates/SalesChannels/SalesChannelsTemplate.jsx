import React, { useState } from "react";
import SalesChannelsForm from "../../organisms/SalesChannels/SalesChannelsForm";

const SalesChannelsTemplate = () => {
    const [selectedChannel, setSelectedChannel] = useState("Professional");

    const channelOptions = [
        { value: "Professional", label: "Professional" },
        { value: "Pharmacy", label: "Pharmacy" },
        { value: "E-commerce B2C", label: "E-commerce B2C" },
        { value: "E-commerce B2B", label: "E-commerce B2B" },
        { value: "Third Party", label: "Third Party" },
        { value: "Other", label: "Other" },
    ];

    const channelData = {
        Professional: { sales: "€45,000", clients: "120", growth: "+8%" },
        Pharmacy: { sales: "€32,000", clients: "85", growth: "+5%" },
        "E-commerce B2C": { sales: "€18,500", clients: "210", growth: "+12%" },
        "E-commerce B2B": { sales: "€22,000", clients: "60", growth: "+3%" },
        "Third Party": { sales: "€9,800", clients: "30", growth: "-2%" },
        Other: { sales: "€3,200", clients: "15", growth: "0%" },
    };

    const handleExport = () => {
        alert(`Exporting data for ${selectedChannel}`);
    };

    const currentData = channelData[selectedChannel];

    return (
        <div className="select-channels-template">
            <SalesChannelsForm
                selectedChannel={selectedChannel}
                channelOptions={channelOptions}
                onChannelChange={(e) => setSelectedChannel(e.target.value)}
                onExport={handleExport}
                channelData={currentData}
            />
        </div>
    );
};

export default SalesChannelsTemplate;
