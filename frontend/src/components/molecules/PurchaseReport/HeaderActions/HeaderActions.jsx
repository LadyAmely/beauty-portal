import React from "react";
import SelectGroup from "../../SelectGroup/SelectGroup";
import {Button} from "../../../../stories/Button";


const HeaderActions = ({ quarterOptions, selectedQuarter, onQuarterChange, onExport }) => (
    <>
        <SelectGroup
            label="Quarter"
            name="quarter"
            options={quarterOptions}
            value={selectedQuarter}
            onChange={onQuarterChange}
        />
        <Button label="Export report" onClick={onExport} />
    </>
);

export default HeaderActions;
