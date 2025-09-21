import React from 'react';
import Card from './Card';

export default {
    title: 'Atoms/Card',
    component: Card,
    argTypes: {
        title: {
            control: 'text',
            description: 'Nagłówek karty',
        },
        content: {
            control: 'text',
            description: 'Główna treść karty',
        },
        footer: {
            control: 'text',
            description: 'Stopka karty',
        },
    },
};

const Template = (args) => <Card {...args} />;

export const Default = Template.bind({});
Default.args = {
    title: 'Profil użytkownika',
    content: 'To jest przykładowa treść karty. Możesz tu umieścić dowolne informacje.',
    footer: 'Ostatnia aktualizacja: dzisiaj',
};

export const WithoutTitle = Template.bind({});
WithoutTitle.args = {
    content: 'Karta bez nagłówka. Nadaje się np. do prostych komunikatów.',
    footer: 'Stopka nadal widoczna',
};

export const WithoutFooter = Template.bind({});
WithoutFooter.args = {
    title: 'Nagłówek karty',
    content: 'Brak stopki. Użyteczne np. w dashboardach.',
};

export const Minimal = Template.bind({});
Minimal.args = {
    content: 'Tylko treść. Najprostszy wariant karty.',
};
