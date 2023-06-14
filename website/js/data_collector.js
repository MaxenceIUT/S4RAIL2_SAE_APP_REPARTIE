const getStationinformation = async () => {
  const response = await fetch(
    "https://transport.data.gouv.fr/gbfs/nancy/station_information.json"
  );
  //console.log("infos : ");
  return await response.json();
}

const getStationstatus = async () => {
    const response = await fetch(
        "https://transport.data.gouv.fr/gbfs/nancy/station_status.json"
    );
    //console.log("status : ");
    //console.log(await response.json());
    return await response.json();
}

const getIncidents = async () => {
    const response = await fetch(
        "https://carto.g-ny.org/data/cifs/cifs_waze_v2.json"
    );
    //console.log("incidents : ");
    //console.log(await response.json());
    return await response.json();
}

export default {
    getStationinformation : getStationinformation,
    getStationstatus : getStationstatus,
    getIncidents : getIncidents
};