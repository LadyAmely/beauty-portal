import React from "react";
import DataRow from "./DataRow";

export default {
    title: "Molecules/DataRow",
    component: DataRow,
};

export const Default = () => (
    <DataRow label="Sprzedaż Q3" value="125 000 PLN" />
);

export const Success = () => (
    <DataRow label="Wynik vs Budżet" value="+12%" status="success" />
);

export const Error = () => (
    <DataRow label="Wynik vs Plan" value="-8%" status="error" />
);

export const Neutral = () => (
    <DataRow label="Zmiana rok do roku" value="0%" status="neutral" />
);

export const Highlight = () => (
    <DataRow label="Nowy klient" value="Delta Group" status="highlight" />
);
