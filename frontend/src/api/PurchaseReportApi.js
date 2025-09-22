export const createPurchaseReport = async ({ quarter }) => {
    const res = await fetch("/api/v1/purchase-report", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify({ quarter }),
    });

    if (!res.ok) throw new Error("Failed to create report");
    return await res.json();
};
