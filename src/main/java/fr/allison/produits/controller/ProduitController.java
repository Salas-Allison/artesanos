package fr.allison.produits.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import fr.allison.produits.dao.ProduitRepository;
import fr.allison.produits.entity.Produit;

import java.util.Collection;

/**
 * Classe de mapping des URLs liées aux fonctionnalités des produits.
 */
@Controller
public class ProduitController
{
    // Ne pas injecter avec @Autowired sinon le code ne sera pas utilisable en dehors de Spring Boot.
    // Utiliser plutôt l'injection @Autowired avec le constructeur.
    private final ProduitRepository produitDao;

    /**
     * Constructeur.
     *
     * @param pproduitDao le DAO d'accès aux données des produits.
     */
    @Autowired
    public ProduitController(ProduitRepository pproduitDao)
    {
        this.produitDao = pproduitDao;
    }

    /**
     * Retourne la page qui liste les produits. Cette page contient également une carte interactive.
     * <p>
     *
     * @param model Le modèle utilisé par Thymeleaf pour faire le lien avec la page HTML.
     * @param sort  Le paramètre sur lequel on veut trier les données.
     * @return Le nom de la vue associée à la page demandée.
     */
    @GetMapping(path = "/produits")
    public String getproduits(
            @RequestParam(required = false, defaultValue = "prix") String sort,
            @RequestParam(required = false) String order,
            Model model)
    {
        Sort.Order o = "desc".equalsIgnoreCase(order) ? Sort.Order.desc(sort) : Sort.Order.asc(sort);
        Collection<Produit> produits = produitDao.findAll(Sort.by(o));

        // Pour inverser le tri la prochaine fois qu'on clique, on modifie la variable "order"
        // avant de l'injecter dans le modèle
        order = "asc".equalsIgnoreCase(order) ? "desc" : "asc";
        model.addAttribute("produits", produits);
        model.addAttribute("order", order);
        model.addAttribute("sort", sort);
        return "produits";
    }

    /**
     * Retourne la page qui permet de saisir les informations d'un nouveau produit.
     *
     * @param model Le modèle utilisé par Thymeleaf pour faire le lien avec la page HTML.
     * @return Le nom de la vue associée à la page demandée.
     */
    @GetMapping(path = "/ajouter-produit")
    public String formulaireAjouterproduit(Model model)
    {
        model.addAttribute("produit", new Produit());
        return "ajouter-produit";
    }

    /**
     * Enregistre les informations d'un nouveau produit.
     *
     * @param produit   Le nouveau produit à enregistrer.
     * @param validation Les résultats de validation des données du formulaire.
     * @return La vue de la page d'accueil si tout s'est bien passé, sinon on reste sur la même page.
     */
    @PostMapping(path = "/ajouter-produit")
    public String actionAjouterproduit(
            @ModelAttribute("produit") @Valid Produit produit, BindingResult validation, Model model)
    {
        model.addAttribute("produit", produit);
        model.addAttribute("produitId", produit.getId());

        if (!validation.hasErrors())
        {
            produitDao.save(produit);
            return "redirect:/produits";
        }
        else
        {
            return "ajouter-produit";
        }
    }

    /**
     * Retourne le formulaire d'édition d'un produit.
     *
     * @param produitId Identifiant du produit, passé dans un paramètre de l'URL .
     * @param model      Modèle Thymeleaf.
     * @return Le formulaire d'édition d'un produit.
     */
    @GetMapping(path = "/editer-produit")
    public String formulaireEditerproduit(@RequestParam Long produitId, Model model)
    {
        model.addAttribute("produit", produitDao.findById(produitId));
        return "editer-produit";
    }

    /**
     * Enregistre les informations d'un produit.
     *
     * @param produit   Le produit à enregistrer.
     * @param validation Le résultat de validation du produit par Spring Validation.
     * @param model      Le modèle Thymeleaf (le type RedirectAttributes permet de rajouter des attributs dans l'URL).
     * @return La page d'accueil si tout s'est bien passé.
     */
    @PostMapping(path = "/editer-produit")
    public String actionEditerproduit(
            @ModelAttribute @Valid Produit produit, BindingResult validation, RedirectAttributes model)
    {
        model.addAttribute("produit", produit);
        model.addAttribute("produitId", produit.getId());

        if (!validation.hasErrors())
        {
            produitDao.save(produit);
            return "redirect:/produits";
        }
        else
        {
            return "editer-produit";
        }
    }

}
