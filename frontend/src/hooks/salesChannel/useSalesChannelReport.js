import {useState, useEffect, use} from "react";
import {createQuarterReport, getClientsByChannel} from "../../api/SalesChannelsApi";

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

export const useSalesChannelForm =()=>{

    const [order, setOrder] = useState("");
    const [salesChannel, setSalesChannel] = useState("");
    const [newClients, setNewClients] = useState("");
    const [month, setMonth] = useState("");
    const [exchangeRate, setExchangeRate] = useState("");
    const [unitSold, setUnitSold] = useState("");
    const [salesValueEUR, setSalesValueEUR] = useState("");
    const [salesValuePLN, setSalesValuePLN] = useState("");
    const [loading, setLoading]= useState(false);
    const [error, setError] = useState(null);

    const handleSubmit = async(e)=>{
        e.preventDefault();
        setError(null);
        try{
            setLoading(true);
            await(createQuarterReport({
                order,
                salesChannel,
                newClients,
                month,
                exchangeRate,
                unitSold,
                salesValueEUR,
                salesValuePLN
            }))
        }catch(error){
            setError("");
        }finally{
            setLoading(false);
        }
    };
  return {
      order, setOrder,
      salesChannel, setSalesChannel,
      newClients, setNewClients,
      month, setMonth,
      exchangeRate, setExchangeRate,
      unitSold, setUnitSold,
      salesValueEUR, setSalesValueEUR,
      salesValuePLN, setSalesValuePLN,
      error, setError,
      loading, setLoading,
      handleSubmit
  };
};
