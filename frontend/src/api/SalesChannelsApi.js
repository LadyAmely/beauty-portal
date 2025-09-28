import axios from 'axios';

export const getClientsByChannel = async (distributorId) => {
    const res = await fetch(`/api/v1/sales-channels/clients/${distributorId}/by-channel`);
    if (!res.ok) throw new Error("Failed to fetch clients by channel");
    return await res.json();
};

export const createQuarterReport = async(report)=>{
    try{
        const response = await axios.post(
            "api/v1/sales-channels/quarter-reports",
            report,
            {
                    headers: {
                        "Content-Type":"application/json"
                    }
                }
        );
        return response.data;
    }catch(err) {
        throw err;
    }
}


