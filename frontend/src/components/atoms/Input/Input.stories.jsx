import React, { useState } from 'react';
import Input from './Input';

export default {
    title: 'Atoms/Input',
    component: Input,
    argTypes: {
        type: {
            control: 'text',
            description: 'Typ pola (np. text, password, email)',
        },
        placeholder: {
            control: 'text',
            description: 'Tekst zastępczy',
        },
        disabled: {
            control: 'boolean',
            description: 'Czy pole jest nieaktywne',
        },
        value: {
            control: 'text',
            description: 'Wartość pola',
        },
    },
};

const Template = (args) => {
    const [value, setValue] = useState(args.value || '');

    return (
        <Input
            {...args}
            value={value}
            onChange={(e) => setValue(e.target.value)}
        />
    );
};

export const Default = Template.bind({});
Default.args = {
    type: 'text',
    placeholder: 'Wpisz coś...',
    disabled: false,
};

export const Disabled = Template.bind({});
Disabled.args = {
    type: 'text',
    placeholder: 'Nieaktywne pole',
    disabled: true,
    value: 'Zablokowane',
};

export const Password = Template.bind({});
Password.args = {
    type: 'password',
    placeholder: 'Hasło',
};

export const Email = Template.bind({});
Email.args = {
    type: 'email',
    placeholder: 'Twój email',
};
