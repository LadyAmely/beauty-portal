import React, { useState } from 'react';
import Select from './Select';

export default {
    title: 'Atoms/Select',
    component: Select,
    argTypes: {
        options: {
            control: 'object',
            description: 'Lista opcji do wyboru',
        },
        value: {
            control: 'text',
            description: 'Wybrana wartość',
        },
        disabled: {
            control: 'boolean',
            description: 'Czy select jest nieaktywny',
        },
        name: {
            control: 'text',
            description: 'Nazwa pola',
        },
        id: {
            control: 'text',
            description: 'Identyfikator pola',
        },
    },
};

const defaultOptions = [
    { value: '', label: 'Wybierz opcję' },
    { value: 'apple', label: 'Jabłko' },
    { value: 'banana', label: 'Banan' },
    { value: 'orange', label: 'Pomarańcza' },
];

const Template = (args) => {
    const [value, setValue] = useState(args.value || '');

    return (
        <Select
            {...args}
            value={value}
            onChange={(e) => setValue(e.target.value)}
        />
    );
};

export const Default = Template.bind({});
Default.args = {
    options: defaultOptions,
    value: '',
    disabled: false,
};

export const Preselected = Template.bind({});
Preselected.args = {
    options: defaultOptions,
    value: 'banana',
};

export const Disabled = Template.bind({});
Disabled.args = {
    options: defaultOptions,
    value: '',
    disabled: true,
};
