import React, { useState } from "react";
import TableToolbar from "./TableToolbar";
import SelectGroup from "../SelectGroup/SelectGroup"; // dostosuj ścieżkę
import Button from "../../atoms/Button/Button"; // dostosuj ścieżkę

export default {
    title: "Molecules/TableToolbar",
    component: TableToolbar,
};

const headers = ["Dystrybutor", "Sprzedaż", "Budżet", "Status"];
const rows = [
    ["ABC Sp. z o.o.", "120 000 PLN", "130 000 PLN", "✔️"],
    ["XYZ S.A.", "95 000 PLN", "100 000 PLN", "⚠️"],
    ["Delta Group", "80 000 PLN", "85 000 PLN", "❌"],
];

const FilterSelect = () => {
    const [value, setValue] = useState("Q3");
    const options = [
        { value: "Q1", label: "Q1" },
        { value: "Q2", label: "Q2" },
        { value: "Q3", label: "Q3" },
        { value: "Q4", label: "Q4" },
    ];

    return (
        <SelectGroup
            label="Kwartał"
            name="quarter"
            options={options}
            value={value}
            onChange={(e) => setValue(e.target.value)}
        />
    );
};

const ActionButtons = () => (
    <>
        <Button label="Eksportuj" />
        <Button label="Dodaj" variant="secondary" />
    </>
);

export const Default = () => (
    <TableToolbar headers={headers} rows={rows} />
);

export const WithTitle = () => (
    <TableToolbar title="Raport sprzedaży" headers={headers} rows={rows} />
);

export const WithFilter = () => (
    <TableToolbar filter={<FilterSelect />} headers={headers} rows={rows} />
);

export const WithActions = () => (
    <TableToolbar actions={<ActionButtons />} headers={headers} rows={rows} />
);

export const FullToolbar = () => (
    <TableToolbar
        title="Raport sprzedaży"
        filter={<FilterSelect />}
        actions={<ActionButtons />}
        headers={headers}
        rows={rows}
    />
);
