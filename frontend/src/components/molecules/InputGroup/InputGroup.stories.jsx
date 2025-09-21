import React, { useState } from "react";
import InputGroup from "./InputGroup";

export default {
    title: "Molecules/InputGroup",
    component: InputGroup,
};

const Template = (args) => {
    const [value, setValue] = useState(args.value || "");

    return (
        <InputGroup
            {...args}
            value={value}
            onChange={(e) => setValue(e.target.value)}
        />
    );
};

export const Default = Template.bind({});
Default.args = {
    label: "Imię",
    name: "firstName",
    placeholder: "Wpisz imię",
};

export const WithHint = Template.bind({});
WithHint.args = {
    label: "Email",
    name: "email",
    placeholder: "np. jan.kowalski@example.com",
    hint: "Użyj firmowego adresu e-mail",
};

export const WithError = Template.bind({});
WithError.args = {
    label: "Numer telefonu",
    name: "phone",
    placeholder: "np. 123 456 789",
    error: "Nieprawidłowy format numeru",
};

export const WithSuccess = Template.bind({});
WithSuccess.args = {
    label: "Kod rabatowy",
    name: "promoCode",
    placeholder: "Wpisz kod",
    success: "Kod został zastosowany",
};

export const Disabled = Template.bind({});
Disabled.args = {
    label: "NIP",
    name: "nip",
    placeholder: "Nieaktywny",
    disabled: true,
    hint: "Pole nieedytowalne w tym trybie",
};
