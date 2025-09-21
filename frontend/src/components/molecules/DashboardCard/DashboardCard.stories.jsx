import React from "react";
import DashboardCard from "./DashboardCard";

export default {
    title: "Molecules/DashboardCard",
    component: DashboardCard,
};

const dummyChart = (
    <div
        style={{
            width: "100%",
            height: "100px",
            background: "linear-gradient(to right, #ffe3e3, #ffbdbd)",
            borderRadius: "6px",
        }}
    >
    </div>
);

export const Default = () => (
    <DashboardCard
        title="Sprzedaż Q3"
        value="125 000 PLN"
        chart={dummyChart}
    />
);

export const TrendUp = () => (
    <DashboardCard
        title="Sprzedaż vs Q2"
        value="+12%"
        trend={{ label: "Wzrost", status: "up", icon: "📈" }}
        chart={dummyChart}
    />
);

export const TrendDown = () => (
    <DashboardCard
        title="Sprzedaż vs Budżet"
        value="-8%"
        trend={{ label: "Spadek", status: "down", icon: "📉" }}
        chart={dummyChart}
    />
);

export const TrendNeutral = () => (
    <DashboardCard
        title="Sprzedaż vs Rok ubiegły"
        value="0%"
        trend={{ label: "Bez zmian", status: "neutral", icon: "➖" }}
        chart={dummyChart}
    />
);
