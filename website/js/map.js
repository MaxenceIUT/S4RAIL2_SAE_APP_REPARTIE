import provider from "./data-provider.js";
import mapUtils from "./map-utils.js";

// Loading Leaflet Map and getting the reference to it, getting the icons
mapUtils.initMap();
const map = mapUtils.getMap();
const { etablissementIcon, veloIcon, dangerIcon, restaurantIcon } = mapUtils;

// Fetching etablissements data
provider
  .getEtablissements()
  .then((etablissementsData) => addEtablissementsMarkers(etablissementsData))
  .catch((error) => {
    alert("Impossible de récupérer les données sur les principaux établissements de l'enseignement supérieur");
    console.error("Impossible de récupérer les données sur les principaux établissements de l'enseignement supérieur", error);
  });

// Fetching restaurants data
provider
  .getGoodFoodRestaurants()
  .then((restaurantData) => addRestaurantsMarkers(restaurantData))
  .catch((error) => {
    alert("Impossible de récupérer les données sur les restaurants");
    console.error("Impossible de récupérer les données sur les restaurants", error);
  });

// Fetching incidents data
provider
  .getIncidents()
  .then((incidentsData) => addIncidentsMarkers(incidentsData))
  .catch((error) => {
    alert("Impossible de récupérer les données sur les incidents");
    console.error("Impossible de récupérer les données sur les incidents", error);
  });

// Fetching stations data
provider
  .getStationInformation()
  .then((stationsInfoData) => {
    provider
      .getStationStatus()
      .then((stationsStatusData) =>
        addStationsMarkers(stationsInfoData, stationsStatusData)
      )
      .catch((error) => {
        alert("Impossible de récupérer les données sur les stations");
        console.error("Impossible de récupérer les données sur les stations", error);
      });
  })
  .catch((error) => {
    alert("Impossible de récupérer les données sur les stations");
    console.error("Impossible de récupérer les données sur les stations", error);
  });

// Using the data to display markers on the map
function addEtablissementsMarkers(etablissementsData) {
  if (Array.isArray(etablissementsData)) {
    etablissementsData.forEach((etablissement) => {
      if (etablissement.fields.coordonnees)
        L.marker(
          [
            etablissement.fields.coordonnees[0],
            etablissement.fields.coordonnees[1],
          ],
          { icon: etablissementIcon }
        )
          .addTo(map)
          .bindPopup(
            `<b>${etablissement.fields.uo_lib_officiel}</b><br> Académie : ${etablissement.fields.aca_nom}`
          );
    });
    return true;
  }
  return false;
}

function addStationsMarkers(stationsInfoData, stationsStatusData) {
  if (stationsInfoData?.data?.stations && stationsStatusData?.data?.stations) {
    stationsInfoData.data.stations.forEach((station) => {
      const { station_id } = station;
      const stationStatus = stationsStatusData.data.stations.find(
        (station) => station.station_id === station_id
      );
      L.marker([station.lat, station.lon], { icon: veloIcon }).addTo(map)
        .bindPopup(`Station ${station.name}<br> ${station.address}<br>${stationStatus.num_bikes_available} vélos disponibles<br>
        ${stationStatus.num_docks_available} places de parking libres sur la station`);
    });
    return true;
  }
  return false;
}

function addIncidentsMarkers(incidentsData) {
  if (incidentsData?.incidents && incidentsData.incidents.length > 0) {
    incidentsData.incidents.forEach((incident) => {
      const longLat = incident.location.polyline.split(" ");
      L.marker([longLat[0], longLat[1]], { icon: dangerIcon })
        .addTo(map)
        .bindPopup(
          `${incident.short_description}<br> Fin estimée : ${
            incident.endtime.split("T")[0]
          }`
        );
    });
    return true;
  }
  return false;
}

function addRestaurantsMarkers(restaurantData) {
  if (Array.isArray(restaurantData)) {
    restaurantData.forEach((resto) => {
      const marker = L.marker([resto.latitude, resto.longitude], {
        icon: restaurantIcon,
      })
        .addTo(map)
        .bindPopup(
          `${resto.nom}<br>
      <div id="form">
          <label for="nom">Nom :</label>
          <input type="text" id="nom">
          <br>
          <label for="prenom">Prenom :</label>
          <input type="text" id="prenom">
          <br>
          <label for="nbInv">Nombre d'invité :</label>
          <input type="number" id="nbInv">
          <br>
          <label for="tel">Telephone :</label>
          <input type="tel" id="tel">
          <br>
          <label for="date">Date de réservation :</label>
          <input type="datetime-local" id="date">
          <br>
          <button id="bouttonReserve" type="submit" data-nomResto="${resto.nom}">Un simple bouton</button>
      </div>`
        );

      marker.on("popupopen", (e) => {
        const popup = e.popup;
        const button = popup.getElement().querySelector("#bouttonReserve");
        button.addEventListener("click", (event) => {
          let nom = document.getElementById("nom").value;
          let prenom = document.getElementById("prenom").value;
          let nbInv = document.getElementById("nbInv").value;
          let tel = document.getElementById("tel").value;
          let nomResto = event.target.getAttribute("data-nomResto");
          let date = document.getElementById("date").value;

          if (nom !== "" && prenom !== "" && nbInv != null && tel !== "") {
            provider.reserverTable(nom, prenom, nbInv, tel, nomResto, date);
          } else {
            console.log("Paramètres incomplets");
          }
        });
      });
    });
    return true;
  }
  return false;
}
