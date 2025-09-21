import React, { useState } from "react";
import MediaLibraryForm from "./MediaLibraryForm";

export default {
    title: "Organisms/MediaLibraryForm",
    component: MediaLibraryForm,
};

const mockFiles = [
    {
        name: "SKU123_manual.pdf",
        type: "PDF",
        size: "1.2 MB",
        date: "2025-09-01",
        onDownload: (name) => alert(`Pobieranie: ${name}`),
        checkbox: <input type="checkbox" />,
    },
    {
        name: "SKU123_main_1.jpg",
        type: "JPG",
        size: "850 KB",
        date: "2025-09-01",
        onDownload: (name) => alert(`Pobieranie: ${name}`),
        checkbox: <input type="checkbox" />,
    },
    {
        name: "SKU456_campaign.mp4",
        type: "MP4",
        size: "12 MB",
        date: "2025-08-15",
        onDownload: (name) => alert(`Pobieranie: ${name}`),
        checkbox: <input type="checkbox" />,
    },
];

const sortOptions = [
    { value: "date", label: "Data dodania" },
    { value: "size", label: "Rozmiar" },
    { value: "type", label: "Typ pliku" },
];

export const Default = () => {
    const [searchValue, setSearchValue] = useState("");
    const [sortValue, setSortValue] = useState("date");

    return (
        <MediaLibraryForm
            title="Biblioteka materiałów"
            searchValue={searchValue}
            onSearchChange={(e) => setSearchValue(e.target.value)}
            sortValue={sortValue}
            onSortChange={(e) => setSortValue(e.target.value)}
            sortOptions={sortOptions}
            fileList={mockFiles}
            onDownloadSelected={() => alert("Pobieranie zaznaczonych plików")}
            footerNote="Wyświetlane pliki są aktualne na dzień 21.09.2025"
        />
    );
};
