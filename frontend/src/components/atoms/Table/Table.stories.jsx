import React from 'react';
import Table from './Table';

export default {
    title: 'Atoms/Table',
    component: Table,
    argTypes: {
        headers: {
            control: 'array',
            description: 'Nagłówki kolumn',
        },
        rows: {
            control: 'object',
            description: 'Dane wierszy (tablica tablic)',
        },
    },
};

const Template = (args) => <Table {...args} />;

export const Default = Template.bind({});
Default.args = {
    headers: ['Imię', 'Nazwisko', 'Email'],
    rows: [
        ['Anna', 'Kowalska', 'anna@example.com'],
        ['Jan', 'Nowak', 'jan@example.com'],
        ['Maria', 'Wiśniewska', 'maria@example.com'],
    ],
};

export const Empty = Template.bind({});
Empty.args = {
    headers: ['Kolumna 1', 'Kolumna 2'],
    rows: [],
};

export const WithNumbers = Template.bind({});
WithNumbers.args = {
    headers: ['Produkt', 'Cena', 'Ilość'],
    rows: [
        ['Laptop', '4500 zł', 3],
        ['Monitor', '1200 zł', 5],
        ['Myszka', '80 zł', 10],
    ],
};
