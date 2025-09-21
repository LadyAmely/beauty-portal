
export const getClientsByChannel = async (distributorId) => {
    const res = await fetch(`/api/v1/sales-channels/clients/${distributorId}/by-channel`);
    if (!res.ok) throw new Error("Failed to fetch clients by channel");
    return await res.json();
};

export const createQuarterReport = async (payload) => {
    const res = await fetch(`/api/v1/sales-channels/quarter-reports`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(payload),
    });
    if (!res.ok) throw new Error("Failed to create quarter report");
    return await res.json();
};

