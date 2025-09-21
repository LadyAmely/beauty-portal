import React from 'react';
import Badge from './Badge';

export default {
    title: 'Atoms/Badge',
    component: Badge,
    argTypes: {
        label: {
            control: 'text',
            description: 'Tekst wyświetlany w badge',
        },
        variant: {
            control: { type: 'select' },
            options: ['default', 'success', 'warning', 'error', 'info'],
            description: 'Kolorystyka badge',
        },
        size: {
            control: { type: 'select' },
            options: ['small', 'medium', 'large'],
            description: 'Rozmiar badge',
        },
    },
};

const Template = (args) => <Badge {...args} />;

export const Default = Template.bind({});
Default.args = {
    label: 'Nowy',
    variant: 'default',
    size: 'medium',
};

export const Success = Template.bind({});
Success.args = {
    label: 'Zapisano',
    variant: 'success',
    size: 'medium',
};

export const Warning = Template.bind({});
Warning.args = {
    label: 'Uwaga',
    variant: 'warning',
    size: 'medium',
};

export const Error = Template.bind({});
Error.args = {
    label: 'Błąd',
    variant: 'error',
    size: 'medium',
};

export const Info = Template.bind({});
Info.args = {
    label: 'Informacja',
    variant: 'info',
    size: 'medium',
};

export const Small = Template.bind({});
Small.args = {
    label: 'Mini',
    variant: 'success',
    size: 'small',
};

export const Large = Template.bind({});
Large.args = {
    label: 'Duży',
    variant: 'info',
    size: 'large',
};
