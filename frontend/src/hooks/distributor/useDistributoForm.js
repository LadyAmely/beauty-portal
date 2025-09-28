import {createDistributor} from "../../api/DistributorApi";
import {useState} from "react";

export const useDistributorForm =()=>{

    const [name, setName] = useState("");
    const [code, setCode] = useState("");
    const [error, setError] = useState(null);
    const [loading, setLoading] = useState(false);

    const handleSubmit = async(e)=>{
        e.preventDefault();
        setError(null);
        if(!code || !name) return;
        try{
            setLoading(true);
            await(createDistributor({code, name}))
        }catch(err){
            setError("");
        }finally{
            setLoading(false);
        }
    };
    return {
        code,
        setCode,
        name,
        setName,
        handleSubmit,
        loading,
        error
    };
};