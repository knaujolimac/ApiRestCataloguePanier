package co.catalogue.prod.rest;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import co.catalogue.prod.rest.modele.Produit;
import co.catalogue.prod.rest.modele.TypeProduit;
import co.catalogue.prod.rest.referentiel.ProduitReferentiel;
import co.catalogue.prod.rest.referentiel.TypeProduitReferentiel;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author Camilo Chaparro
 *
 */
@Slf4j
@SpringBootApplication
public class ApiCatalogueProdServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiCatalogueProdServicesApplication.class, args);

	}

	@Bean
	public CommandLineRunner chargerDesDonnes(ProduitReferentiel prodRef, TypeProduitReferentiel typeRef) {
		return new CommandLineRunner() {

			// On va creer les dates necesaires de l'api
			@Override
			public void run(String... args) throws Exception {
				// On va ajouter les types de produits d'abord
				log.info("On va ajouter les types de produits d'abord!!");
				TypeProduit typeTechn = null;
				TypeProduit typeVet = null;
				TypeProduit typeApp = null;
				TypeProduit typeChau = null;
				typeTechn = typeRef.save(new TypeProduit("technologique", 1));
				typeVet = typeRef.save(new TypeProduit("vêtements", 1));
				typeApp = typeRef.save(new TypeProduit("appareil ménager", 1));
				typeChau = typeRef.save(new TypeProduit("chaussures", 1));

				log.info("On va ajouter les produits associé aux typeProduits!!");
				prodRef.save(new Produit("computador", 100000.0, typeTechn,50));
				prodRef.save(new Produit("celular", 50000.0, typeTechn,35));
				prodRef.save(new Produit("tablet", 250000.0, typeTechn,15));
				prodRef.save(new Produit("televisor", 3500000.0, typeTechn,40));

				prodRef.save(new Produit("Pantalon", 89000.0, typeVet,60));
				prodRef.save(new Produit("Camiseta", 50000.0, typeVet,12));
				prodRef.save(new Produit("Cinturon", 35000.0, typeVet,5));
				prodRef.save(new Produit("Medias", 20000.0, typeVet,6));

				prodRef.save(new Produit("Sofa", 550000.0, typeApp,3));
				prodRef.save(new Produit("Cama", 1500000.0, typeApp,2));
				prodRef.save(new Produit("Cajonera", 550000.0, typeApp,6));
				prodRef.save(new Produit("Armario", 800000.0, typeApp,1));

				prodRef.save(new Produit("Zapatos", 250000.0, typeChau,15));
				prodRef.save(new Produit("Tenis", 300000.0, typeChau,9));
				prodRef.save(new Produit("Chanclas", 100000.0, typeVet,4));
			}

		};
	}

}
