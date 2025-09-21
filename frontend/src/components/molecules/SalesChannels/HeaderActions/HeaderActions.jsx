import React from "react";
import SelectGroup from "../../SelectGroup/SelectGroup";
import {Button} from "../../../../stories/Button";

const HeaderActions = ({
                                        periodOptions,
                                        selectedPeriod,
                                        onPeriodChange,
                                        onImport,
                                    }) => (
    <>
        <SelectGroup
            label="Period"
            name="period"
            options={periodOptions}
            value={selectedPeriod}
            onChange={onPeriodChange}
        />
        <Button label="Import data" onClick={onImport} />
    </>
);

export default HeaderActions;
