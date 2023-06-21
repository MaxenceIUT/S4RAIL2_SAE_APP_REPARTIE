const getStationinformation = async () => {
    try {
        const response = await fetch(
            "https://transport.data.gouv.fr/gbfs/nancy/station_information.json"
        );
        if(response.ok){
            return await response.json();
        }
    }catch (e) {
        alert('Impossible de récupérer les informations des stations');
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
        alert('Impossible de récupérer le statut des stations');
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
        alert('Impossible de récupérer les données des incidents');
    }

}

const getGoodfoodRestaurant = async () => {
    try {
        let response = await fetch("http://localhost:8080/api/goodfood/restaurant");
        if (response.ok) {
            return response.json();
        }
    } catch (e) {
        alert('Impossible de récupérer les données des restaurants');
    }
}

const getEtablissements = async () => {
    try {
        let response = await fetch("http://localhost:8080/api/etablissements");
        if (response.ok) {
            return response.json();
        }
    } catch (e) {
        alert('Impossible de récupérer les données des établissements');
    }
}

export default {
    getEtablissements : getEtablissements,
    getStationinformation : getStationinformation,
    getStationstatus : getStationstatus,
    getIncidents : getIncidents,
    getGoodfoodRestaurant : getGoodfoodRestaurant
};