import { useState, useEffect } from "react";
import {getClientsByChannel} from "../../api/SalesChannelsApi";

export const useSalesChannelsController = (distributorId) => {
    const [selectedChannel, setSelectedChannel] = useState("Professional");
    const [channelData, setChannelData] = useState({});
    const [loading, setLoading] = useState(true);

    const channelOptions = [
        { value: "Professional", label: "Professional" },
        { value: "Pharmacy", label: "Pharmacy" },
        { value: "E-commerce B2C", label: "E-commerce B2C" },
        { value: "E-commerce B2B", label: "E-commerce B2B" },
        { value: "Third Party", label: "Third Party" },
        { value: "Other", label: "Other" },
    ];

    useEffect(() => {
        const loadData = async () => {
            try {
                const response = await getClientsByChannel(distributorId);
                setChannelData(response.data || {});
            } catch (err) {
                console.error("Failed to load channel data", err);
            } finally {
                setLoading(false);
            }
        };

        loadData();
    }, [distributorId]);

    const handleExport = () => {
        alert(`Exporting data for ${selectedChannel}`);
    };

    return {
        selectedChannel,
        setSelectedChannel,
        channelOptions,
        channelData: channelData[selectedChannel] || {},
        loading,
        onExport: handleExport,
    };
};
