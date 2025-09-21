import React from "react";
import Footer from "./Footer";

export default {
    title: "Atoms/Footer",
    component: Footer,
};

const Template = (args) => <Footer {...args} />;

export const Default = Template.bind({});
Default.args = {
    children: "© 2025 Beauty Portal. All rights reserved.",
};

export const WithNote = Template.bind({});
WithNote.args = {
    children: "Note: Only visible to admin users.",
    className: "footer--note",
};
