let buttonCancel = document.querySelector("#btn-cancel");
buttonCancel.addEventListener("click", homepage);

/**
 * Lien vers la page de modification d'un nouveau produit.
 * @param {Event} event
 */
function homepage(event) {
    console.log("salut");
    document.location.href = "/produits";
}