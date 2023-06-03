package fr.allison.produits;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import fr.allison.produits.dao.ProduitRepository;

@SpringBootTest
class produitsApplicationTests
{
    @Autowired
    private ProduitRepository produitDao;

    @Test
    void contextLoads()
    {
        Assertions.assertNotNull(produitDao);
    }

}
