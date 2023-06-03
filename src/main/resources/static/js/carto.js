// La map Leaflet
let mymap = L.map('map').setView([-13.525008696446909, -71.96808927163082], 6);

// Ajout d'un layer sur la map pour afficher des tuiles avec les routes
L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
    tileSize: 512,
    zoomOffset: -1,
    maxZoom: 18
}).addTo(mymap);

/**
 * Ajoute un marqueur sur la carte.
 *
 * @param {number} lat Latitude du marqueur.
 * @param {number} lon Longitude du marqueur.
 * @param {string} name Le nom de l'élément à afficher dans la popup.
 * @param {string} ville La ville de l'élément à afficher dans la popup.
 */
function addMarkerOnMap(lat, lon, name, ville) {

    // On ajoute une marque aux coordonnées fournies en paramètre
    let marker = L.marker([lat, lon]).addTo(mymap);

    // Un popup qui s'affichera au-dessus du marqueur
    let popup = L.popup().setContent(`<h2>${name}</h2><h3>${ville}</h3>`);
    marker.bindPopup(popup);

    marker.addEventListener('click', (event) => {
        popup.openOn(mymap);
    });
}