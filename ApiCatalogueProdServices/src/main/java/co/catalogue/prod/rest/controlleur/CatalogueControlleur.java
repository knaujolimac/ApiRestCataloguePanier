package co.catalogue.prod.rest.controlleur;

import java.util.List;

import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.catalogue.prod.rest.exception.CatalogueProdException;
import co.catalogue.prod.rest.modele.dto.CatalogueProduitDTO;
import co.catalogue.prod.rest.modele.dto.PanierAcheteDTO;
import co.catalogue.prod.rest.modele.dto.ProduitDTO;
import co.catalogue.prod.rest.service.CatalogueService;
import co.catalogue.prod.rest.service.PanierService;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author Camilo Chaparro
 *
 */
@Slf4j
@Validated
@RestController
@RequestMapping(produces = "application/json")
public class CatalogueControlleur {

	@Autowired
	private CatalogueService catalogueService;
	@Autowired
	private PanierService panierService;

	/**
	 * Operation qui retourne un catalogue de produits
	 * @return
	 * @throws CatalogueProdException
	 */
	@GetMapping(value = "/afficherCatalogueProduits")
	public ResponseEntity<List<CatalogueProduitDTO>> afficherCatalogueProduits() throws CatalogueProdException {
		try {

			List<CatalogueProduitDTO> listCatalogueProd = catalogueService.getCatalogueProduits();

			if (listCatalogueProd == null || listCatalogueProd.isEmpty()) {
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			} else {
				return new ResponseEntity<>(listCatalogueProd, HttpStatus.OK);
			}

		} catch (Exception e) {
			log.error("Une erreur s'est produite lors de la consommation de l'opération (afficherCatalogueProduits). " + e.toString());
			throw new CatalogueProdException(HttpStatus.EXPECTATION_FAILED,
					"Une erreur s'est produite lors de la consommation de l'opération afficherCatalogueProduits.", e);

		}
	}

	/**
	 * Operation qui retourne le detail d'un produit avec son id
	 * @param idProduit
	 * @return
	 */
	@GetMapping(value = "/afficherDetailProduit/produit/{idProduit}")
	public ResponseEntity<ProduitDTO> afficherDetailProduit(@PathVariable("idProduit") @Min(0) Long idProduit) {
		ProduitDTO prodDTO = catalogueService.getDetailProduitById(idProduit);

		if (prodDTO == null) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(prodDTO, HttpStatus.OK);
		}
	}

	/**
	 * Operation qui ajoute un produit du catalogue au panier
	 * @param idProduit
	 * @return
	 * @throws CatalogueProdException
	 */
	@PostMapping(value = "/ajouterProduitPanier/produit/{idProduit}")
	public ResponseEntity<ProduitDTO> ajouterProduitPanier(@PathVariable("idProduit") @Min(0) Long idProduit)
			throws CatalogueProdException {
		ProduitDTO prodDTO = null;
		try {
			prodDTO = panierService.ajouterProduitPanier(idProduit);

		} catch (CatalogueProdException e) {
			log.error(
					"Une erreur s'est produite lors de la consommation de l'opération  (ajouterProduitPanier). " + e.getMessage());
			throw new CatalogueProdException(HttpStatus.BAD_REQUEST,
					"Une erreur s'est produite lors de la consommation de l'opération ajouterProduitPanier : " + e.getMessage(), e);
		}
		return new ResponseEntity<>(prodDTO, HttpStatus.OK);
	}

	/**
	 * Operation qui enleve un produit du catalogue au panier
	 * @param idProduit
	 * @return
	 * @throws CatalogueProdException
	 */
	@DeleteMapping(value = "/enleverProduitPanier/produit/{idProduit}")
	public ResponseEntity<ProduitDTO> enleverProduitPanier(@PathVariable("idProduit") @Min(0) Long idProduit)
			throws CatalogueProdException {
		ProduitDTO prodDTO = null;
		try {
			prodDTO = panierService.enleverProduitPanier(idProduit);

		} catch (CatalogueProdException e) {
			log.error(
					"Une erreur s'est produite lors de la consommation de l'opération (enleverProduitPanier). " + e.getMessage());
			throw new CatalogueProdException(HttpStatus.BAD_REQUEST,
					"Une erreur s'est produite lors de la consommation de l'opération enleverProduitPanier: " + e.getMessage(), e);
		}
		return new ResponseEntity<>(prodDTO, HttpStatus.OK);
	}

	/**
	 * Operation qui retourne le contenu du panier
	 * @return
	 * @throws CatalogueProdException
	 */
	@GetMapping(value = "/afficherContenuPanier")
	public ResponseEntity<List<PanierAcheteDTO>> afficherContenuPanier() throws CatalogueProdException {
		try {

			List<PanierAcheteDTO> listPanier = panierService.getContenuProduitPanier();

			if (listPanier == null || listPanier.isEmpty()) {
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			} else {
				return new ResponseEntity<>(listPanier, HttpStatus.OK);
			}

		} catch (Exception e) {
			log.error("Une erreur s'est produite lors de la consommation (afficherContenuPanier). " + e.toString());
			throw new CatalogueProdException(HttpStatus.EXPECTATION_FAILED,
					"Une erreur s'est produite lors de la consommation afficherContenuPanier.", e);

		}
	}

}
