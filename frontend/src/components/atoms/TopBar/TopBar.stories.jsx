import React from "react";
import TopBar from "./TopBar";
import InputGroup from "../../molecules/InputGroup/InputGroup";
import SelectGroup from "../../molecules/SelectGroup/SelectGroup";
import Button from "../Button/Button";

export default {
    title: "Atoms/TopBar",
    component: TopBar,
};

const Template = (args) => <TopBar {...args} />;

export const Default = Template.bind({});
Default.args = {
    children: (
        <>
            <InputGroup
                label="Search"
                name="search"
                value=""
                onChange={() => {}}
                placeholder="Search something..."
            />
            <SelectGroup
                label="Sort"
                name="sort"
                options={[
                    { value: "name", label: "Name" },
                    { value: "date", label: "Date" },
                ]}
                value="name"
                onChange={() => {}}
            />
            <Button label="Apply" onClick={() => {}} />
        </>
    ),
};

export const Compact = Template.bind({});
Compact.args = {
    children: (
        <>
            <InputGroup
                label="SKU"
                name="sku"
                value=""
                onChange={() => {}}
                placeholder="e.g. SKU123"
            />
            <Button label="Download" onClick={() => {}} />
        </>
    ),
    className: "topbar--compact",
};
