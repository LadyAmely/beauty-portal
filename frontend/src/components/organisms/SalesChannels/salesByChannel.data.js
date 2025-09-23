// salesByChannel.data.js


export const channels = [
    "Professional",
    "Pharmacy",
    "E-commerce B2C",
    "E-commerce B2B",
    "Third Party",
    "Other",
];

export const salesByChannelHeaders = [
    "Sales Channel",
    "Month",
    "Number of Orders",
    "New Clients",
    "Units Sold",
    "Sales Value (PLN)",
    "Exchange Rate (NBP)",
    "Sales Value (EUR)"
];

export const salesByChannelRows = [
    ["Pharmacy", "September", 120, 12, 3000, 60000, 4.32, (60000 / 4.32).toFixed(2)],
    ["E-commerce", "September", 80, 8, 1500, 30000, 4.32, (30000 / 4.32).toFixed(2)]
];

export const inventoryHeaders = [
    "SKU",
    "Quantity",
    "Location",
    "Status Date",
    "Comments"
];

export const inventoryRows = [
    ["CREAM-50ML-WHITE", 120, "Kraków Warehouse", "2025-09-30", "Promotional item"],
    ["TONER-200ML-GREEN", 45, "Gdańsk Warehouse", "2025-09-30", "Approaching expiration date"]
];

export const clientHeaders = [
    "Client Name",
    "Sales Channel",
    "NIP",
    "Location",
    "Status",
    "Date Added",
    "Comments"
];

export const clientRows = [
    ["Apteka Zdrowie", "Pharmacy", "1234567890", "Kraków", "Active", "2025-09-01", "Monthly orders"],
    ["Online Store XYZ", "E-commerce B2C", "9876543210", "Warsaw", "New", "2025-09-15", "Retail only"],
    ["Gabinet DermaLux", "Professional", "4567891230", "Wrocław", "Suspended", "2025-08-20", "Pending reactivation"]
];

export const monthlySalesHeaders = [
    "SKU",
    "Month",
    "Quantity",
    "Value"
];

export const monthlySalesRows = [
    ["CREAM-50ML-WHITE", "September", 300, "6,000 PLN"],
    ["TONER-200ML-GREEN", "September", 150, "3,000 PLN"],
    ["SERUM-30ML-GOLD", "September", 80, "2,400 PLN"]
];


