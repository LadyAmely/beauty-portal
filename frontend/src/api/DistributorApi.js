import axios from 'axios';

export const createDistributor = async(distributor)=>{
    try{
        const response = await axios.post(
            "/api/v1/sales-channels/distributors",
            distributor,
            {
                headers:{
                    "Content-Type": "application/json"
                }
            }
        );
        return response.data;
    }catch(error){
        throw error;
    }
}