package fr.allison.produits.controller;

import fr.allison.produits.dao.ProduitRepository;
import fr.allison.produits.entity.Produit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collection;

/**
 * Classe de mapping des URLs générales.
 */
@Controller
public class WebController
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
    public WebController(ProduitRepository pproduitDao)
    {
        this.produitDao = pproduitDao;
    }
    @GetMapping(path="/")
    public String index(Model model){
        Sort.Order o = Sort.Order.desc("prix");
        Collection<Produit> produits = produitDao.findAll(Sort.by(o));

        model.addAttribute("produits", produits);

        return "index";
    }
}
