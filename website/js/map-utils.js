let map;

function initMap() {
  map = L.map("map").setView([48.68895663631991, 6.178244621722987], 14);
  L.tileLayer("https://tile.openstreetmap.org/{z}/{x}/{y}.png", {
    maxZoom: 19,
    attribution:
      '&copy; <a href="http://www.openstreetmap.org/copyright">OpenStreetMap</a>',
  }).addTo(map);
}

const getMap = () => map;

const etablissementIcon = L.icon({
  iconUrl: "img/school.png",
  iconSize: [40, 40],
  iconAnchor: [20, 20],
  popupAnchor: [0, -10],
});

const veloIcon = L.icon({
  iconUrl: "img/velo.png",
  iconSize: [40, 40],
  iconAnchor: [20, 20],
  popupAnchor: [0, -10],
});

const dangerIcon = L.icon({
  iconUrl: "img/danger.png",
  iconSize: [50, 50],
  iconAnchor: [25, 25],
  popupAnchor: [0, -10],
});

const restaurantIcon = L.icon({
  iconUrl: "img/resto.png",
  iconSize: [50, 50],
  iconAnchor: [25, 25],
  popupAnchor: [0, -10],
});

export default {
  getMap,
  initMap,
  etablissementIcon,
  veloIcon,
  dangerIcon,
  restaurantIcon,
};