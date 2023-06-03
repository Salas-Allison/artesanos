package fr.allison.produits;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import fr.allison.produits.dao.ProduitRepository;
import fr.allison.produits.entity.Produit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Ce composant charge des données initiales en base de données lorsqu'il n'en trouve aucune.
 */
@Component
public class DataLoader implements ApplicationRunner
{
    private final ProduitRepository mproduitRepository;

    @Autowired
    public DataLoader(ProduitRepository pproduitRepository)
    {
        this.mproduitRepository = pproduitRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception
    {
        if (mproduitRepository.count() == 0)
        {
            ClassPathResource resource = new ClassPathResource("static/json/produits.json");
            ObjectMapper objectMapper = JsonMapper.builder().addModule(new JavaTimeModule()).build();
            List<Produit> produits = objectMapper.readValue(resource.getInputStream(), new TypeReference<>()
            {
            });
            mproduitRepository.saveAll(produits);
        }
    }

}
