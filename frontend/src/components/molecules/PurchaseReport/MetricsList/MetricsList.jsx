import React from "react";
import DataRow from "../../DataRow/DataRow";

const MetricsList = ({ metrics }) => (
    <section className="purchase-report-form__summary">
        {metrics.map((metric, idx) => (
            <DataRow
                key={idx}
                label={metric.label}
                value={metric.value}
                status={metric.status}
            />
        ))}
    </section>
);

export default MetricsList;
