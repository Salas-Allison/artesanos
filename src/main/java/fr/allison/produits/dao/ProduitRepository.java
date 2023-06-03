package fr.allison.produits.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.allison.produits.entity.Produit;

/**
 * Repository des produits.
 */
@Repository
public interface ProduitRepository extends JpaRepository<Produit, Long>
{
}
