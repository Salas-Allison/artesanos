package fr.allison.produits;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Application de visualisation des produits bretons sur une carte interactive. L'application permet également de
 * référencer de nouveaux produits ou de modifier les produits existants.
 */
@SpringBootApplication
public class ProduitsApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(ProduitsApplication.class, args);
    }
}
