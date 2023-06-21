import { API_URL } from "./config.js";

const fetchJson = async (url) => {
  try {
    const response = await fetch(url);
    if (response.ok) {
      return await response.json();
    }
  } catch (e) {
    throw e;
  }
};

const getStationInformation = async () => {
  return fetchJson(
    "https://transport.data.gouv.fr/gbfs/nancy/station_information.json"
  );
};

const getStationStatus = async () => {
  return fetchJson(
    "https://transport.data.gouv.fr/gbfs/nancy/station_status.json"
  );
};

const getIncidents = async () => {
  return fetchJson("https://carto.g-ny.org/data/cifs/cifs_waze_v2.json");
};

const getGoodFoodRestaurants = async () => {
  return fetchJson(`${API_URL}/api/goodfood/restaurant`);
};

const getEtablissements = async () => {
  return fetchJson(`${API_URL}/api/etablissements`);
};

const reserverTable = async (nom, prenom, nbInv, tel, nomResto) => {
  let parametres = new URLSearchParams();
  parametres.append("nom", nom);
  parametres.append("prenom", prenom);
  parametres.append("nbInv", nbInv);
  parametres.append("tel", tel);
  parametres.append("nomResto", nomResto);

  return fetch(`${API_URL}/api/goodfood/reserver`, {
    method: "POST",
    headers: {
      "Content-Type": "application/x-www-form-urlencoded",
    },
    body: parametres.toString(),
  });
};

const fetchAll = async () => {
  const stationsInfoData = await getStationInformation();
  const stationsStatusData = await getStationStatus();
  const incidentsData = await getIncidents();
  const restaurantData = await getGoodFoodRestaurants();
  const etablissementsData = await getEtablissements();
  return [
    stationsInfoData,
    stationsStatusData,
    incidentsData,
    restaurantData,
    etablissementsData,
  ];
};

export default {
  getEtablissements,
  getStationInformation,
  getStationStatus,
  getIncidents: getIncidents,
  getGoodFoodRestaurants,
  reserverTable,
  fetchAll,
};
