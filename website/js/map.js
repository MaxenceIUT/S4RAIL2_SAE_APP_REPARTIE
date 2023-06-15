import data_collector from "./data_collector.js";

var map = L.map('map').setView([48.68895663631991, 6.178244621722987], 14);

L.tileLayer('https://tile.openstreetmap.org/{z}/{x}/{y}.png', {
    maxZoom: 19,
    attribution: '&copy; <a href="http://www.openstreetmap.org/copyright">OpenStreetMap</a>'
}).addTo(map);

let stationsInfoData = await data_collector.getStationinformation();

let stationsStatusData = await data_collector.getStationstatus();

let incidentsData = await data_collector.getIncidents();


var veloIcon = L.icon({
    iconUrl: 'img/velo.png',
    iconSize:     [40, 40], // size of the icon
    iconAnchor:   [20, 20], // point of the icon which will correspond to marker's location
    popupAnchor:  [-3, -76] // point from which the popup should open relative to the iconAnchor
});


stationsInfoData.data.stations.forEach(station => {
    const { station_id } = station;
    const stationStatus = stationsStatusData.data.stations.find(station => station.station_id === station_id);
    L.marker([station.lat, station.lon], {icon:veloIcon}).addTo(map)
        .bindPopup(`Station ${station.name}<br> ${station.address}<br>${stationStatus.num_bikes_available} vélos disponibles<br>
        ${stationStatus.num_docks_available} places de parking libres sur la station`);
});


var dangerIcon = L.icon({
    iconUrl: 'img/danger.png',
    iconSize:     [50, 50], // size of the icon
    iconAnchor:   [25, 25], // point of the icon which will correspond to marker's location
    popupAnchor:  [-3, -76] // point from which the popup should open relative to the iconAnchor
});

incidentsData.incidents.forEach(incident => {
    const longLat = incident.location.polyline.split(' ');
    L.marker([longLat[0], longLat[1]],{icon:dangerIcon}).addTo(map)
        .bindPopup(`${incident.short_description}<br> Fin estimée : ${incident.endtime.split('T')[0]}`);
});
