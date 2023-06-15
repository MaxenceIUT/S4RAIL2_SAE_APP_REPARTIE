const getStationinformation = async () => {
    try {
        const response = await fetch(
            "https://transport.data.gouv.fr/gbfs/nancy/station_information.json"
        );
        if(response.ok){
            return await response.json();
        }
    }catch (e) {
        console.log(e.message);
    }
}

const getStationstatus = async () => {
    try {
        const response = await fetch(
            "https://transport.data.gouv.fr/gbfs/nancy/station_status.json"
        );
        if (response.ok) {
            return await response.json();
        }
    }catch (e) {
        console.log(e.message);
        }
}

const getIncidents = async () => {
    try {
        const response = await fetch(
            "https://carto.g-ny.org/data/cifs/cifs_waze_v2.json"
        );
        if (response.ok) {
            return await response.json();
        }
    } catch (e) {
        console.log(e.message);
    }

}

const getGoodfoodRestaurant = async () => {
    try {
        let response = await fetch("http://localhost:8080/api/goodfood/restaurant");
        if (response.ok) {
            return response.json();
        }
    } catch (e) {
        console.log(e.message);
    }
}

export default {
    getStationinformation : getStationinformation,
    getStationstatus : getStationstatus,
    getIncidents : getIncidents,
    getGoodfoodRestaurant : getGoodfoodRestaurant
};