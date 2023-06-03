let buttonAjouterproduit = document.getElementById("btn-ajouter-produit");
buttonAjouterproduit.addEventListener("click", ajouterproduit);

let buttonsEditproduit = document.querySelectorAll(".action-edit-produit");
buttonsEditproduit.forEach(b => b.addEventListener("click", event => editerproduit(event)));

/**
 * Lien vers la page de création d'un nouveau produit.
 * @param {Event} event
 */
function ajouterproduit(event) {
    document.location.href = "/ajouter-produit";
}

/**
 * Lien vers la page de modification d'un nouveau produit.
 * @param {Event} event
 */
function editerproduit(event) {
    const produitId = event.target.attributes['idproduit'].nodeValue;
    document.location.href = `/editer-produit?produitId=${produitId}`;
}
