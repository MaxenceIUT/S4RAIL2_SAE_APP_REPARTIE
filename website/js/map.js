import data_collector from "./data_collector.js";

var map = L.map('map').setView([48.68895663631991, 6.178244621722987], 14);

L.tileLayer('https://tile.openstreetmap.org/{z}/{x}/{y}.png', {
    maxZoom: 19,
    attribution: '&copy; <a href="http://www.openstreetmap.org/copyright">OpenStreetMap</a>'
}).addTo(map);

let stationsInfoData = await data_collector.getStationinformation();

let stationsStatusData = await data_collector.getStationstatus();

stationsInfoData.data.stations.forEach(station => {
    L.marker([station.lat, station.lon]).addTo(map)
        .bindPopup(`Station ${station.name}<br> ${station.address}<br>${station.capacity} places`)
});