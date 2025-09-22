
const BASE_URL = "/api/v1/media";

// GET all media files
export const getAllMediaFiles = async () => {
    const res = await fetch(`${BASE_URL}`);
    if (!res.ok) throw new Error("Failed to fetch media files");
    const data = await res.json();
    return data.items || [];
};

// GET media file by ID
export const getMediaFileById = async (id) => {
    const res = await fetch(`${BASE_URL}/${id}`);
    if (!res.ok) throw new Error("Failed to fetch media file");
    return await res.json();
};

// GET media files sorted by type
export const getSortedByType = async () => {
    const res = await fetch(`${BASE_URL}/sorted-by-type`);
    if (!res.ok) throw new Error("Failed to fetch sorted media");
    const data = await res.json();
    return data.items || [];
};

// GET media files sorted by size
export const getSortedBySizeAsc = async () => {
    const res = await fetch(`${BASE_URL}/sorted-by-size-asc`);
    if (!res.ok) throw new Error("Failed to fetch sorted media");
    const data = await res.json();
    return data.items || [];
};

export const getSortedBySizeDesc = async () => {
    const res = await fetch(`${BASE_URL}/sorted-by-size-desc`);
    if (!res.ok) throw new Error("Failed to fetch sorted media");
    const data = await res.json();
    return data.items || [];
};

// GET media files sorted by date
export const getSortedByDateAsc = async () => {
    const res = await fetch(`${BASE_URL}/sorted-by-date-asc`);
    if (!res.ok) throw new Error("Failed to fetch sorted media");
    const data = await res.json();
    return data.items || [];
};

export const getSortedByDateDesc = async () => {
    const res = await fetch(`${BASE_URL}/sorted-by-date-desc`);
    if (!res.ok) throw new Error("Failed to fetch sorted media");
    const data = await res.json();
    return data.items || [];
};

// GET media files by SKU
export const searchMediaBySku = async (sku) => {
    const res = await fetch(`${BASE_URL}/search?sku=${encodeURIComponent(sku)}`);
    if (!res.ok) throw new Error("Failed to search media");
    const data = await res.json();
    return data.items || [];
};

// POST single file upload
export const uploadSingleFile = async (file) => {
    const formData = new FormData();
    formData.append("file", file);

    const res = await fetch(`${BASE_URL}/upload-file`, {
        method: "POST",
        body: formData,
    });

    if (!res.ok) throw new Error("Upload failed");
};

// POST multiple file upload
export const uploadMultipleFiles = async (files) => {
    const formData = new FormData();
    files.forEach((file) => formData.append("files", file));

    const res = await fetch(`${BASE_URL}/upload`, {
        method: "POST",
        body: formData,
    });

    if (!res.ok) throw new Error("Upload failed");
};
