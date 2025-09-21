import React, { useState } from "react";
import SelectGroup from "./SelectGroup";

export default {
    title: "Molecules/SelectGroup",
    component: SelectGroup,
};

const options = [
    { value: "", label: "Wybierz kwartał" },
    { value: "Q1", label: "Q1" },
    { value: "Q2", label: "Q2" },
    { value: "Q3", label: "Q3" },
    { value: "Q4", label: "Q4" },
];

const Template = (args) => {
    const [value, setValue] = useState(args.value || "");

    return (
        <SelectGroup
            {...args}
            value={value}
            onChange={(e) => setValue(e.target.value)}
        />
    );
};

export const Default = Template.bind({});
Default.args = {
    label: "Kwartał",
    name: "quarter",
    options,
    hint: "Wybierz kwartał raportowy",
};

export const WithError = Template.bind({});
WithError.args = {
    label: "Kwartał",
    name: "quarter",
    options,
    error: "To pole jest wymagane",
};

export const WithSuccess = Template.bind({});
WithSuccess.args = {
    label: "Kwartał",
    name: "quarter",
    options,
    success: "Poprawnie wybrano kwartał",
};

export const Disabled = Template.bind({});
Disabled.args = {
    label: "Kwartał",
    name: "quarter",
    options,
    disabled: true,
    hint: "Nie można edytować w tym trybie",
};
