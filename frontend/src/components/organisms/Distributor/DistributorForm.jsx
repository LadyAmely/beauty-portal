import React from "react";
import PropTypes from "prop-types";
import Button from "../../atoms/Button/Button";
import Footer from "../../atoms/Footer/Footer";
import "./DistributorForm.scss";
import {useDistributorForm} from "../../../hooks/distributor/useDistributoForm";
import Input from "../../atoms/Input/Input";

const DistributorForm = ({ onSubmit }) => {
    const {
        code, setCode,
        name, setName,
        handleSubmit,
        loading,
        error
    } = useDistributorForm();

    return (
        <div className="distributor-form">
            <h2 className="distributor-form__title">Add New Distributor</h2>
            <form onSubmit={handleSubmit} className="distributor-form__fields">
                <div className="form-group">
                    <label htmlFor="code">Distributor Code</label>
                    <Input
                        type="text"
                        id="code"
                        value={code}
                        onChange={(e) => setCode(e.target.value)}
                        maxLength={64}
                        required
                        placeholder="e.g. DIST-PL-001"
                    />
                </div>
                <div className="form-group">
                    <label htmlFor="name">Distributor Name</label>
                    <Input
                        type="text"
                        id="name"
                        value={name}
                        onChange={(e) => setName(e.target.value)}
                        maxLength={255}
                        required
                        placeholder="e.g. Acme Distribution Sp. z o.o."
                    />
                </div>
            </form>
            {error && <p className="error">{error}</p>}
            <Button type="submit" variant="primary" className="distributor-form__submit-button" disabled={loading}>
                <i className="icon-plus" />
                {loading ? "Adding..." : "Add Distributor"}
            </Button>
            <Footer className="distributor-form__footer"/>
        </div>
    );
};

DistributorForm.propTypes = {
    onSubmit: PropTypes.func,
};

export default DistributorForm;
