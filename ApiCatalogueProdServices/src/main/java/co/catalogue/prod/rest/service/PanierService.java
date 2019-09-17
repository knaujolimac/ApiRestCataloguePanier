package co.catalogue.prod.rest.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.catalogue.prod.rest.exception.CatalogueProdException;
import co.catalogue.prod.rest.modele.PanierAchete;
import co.catalogue.prod.rest.modele.Produit;
import co.catalogue.prod.rest.modele.dto.PanierAcheteDTO;
import co.catalogue.prod.rest.modele.dto.ProduitDTO;
import co.catalogue.prod.rest.referentiel.PanierAcheteReferentiel;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author Camilo Chaparro
 *
 */
@Slf4j
@Service
public class PanierService {

	@Autowired
	CatalogueService catService;

	@Autowired
	PanierAcheteReferentiel panierAchetRef;

	/**
	 * Méthode qui ajoute un produit du catalogue au panier
	 * 
	 * @param idProduit
	 * @return ProduitDTO
	 * @throws CatalogueProdException Si le produit n'existe pas au panier ou ll n'y
	 *                                a pas de produit disponible
	 */
	public ProduitDTO ajouterProduitPanier(Long idProduit) throws CatalogueProdException {
		ProduitDTO produitDTO = null;
		// On obtient la information du produit
		produitDTO = catService.getDetailProduitById(idProduit);

		// Si le produit n'existe pas au panier
		if (produitDTO == null) {
			log.debug("le produit avec l'identificateur nombre " + idProduit + " n'existe pas au catalogue");
			throw new CatalogueProdException(null,
					"le produit avec l'identificateur nombre " + idProduit + " n'existe pas au catalogue");
		}

		// S'll n'y a pas de produit disponible
		if (produitDTO.getCantProduitDispo() == 0) {
			log.debug("Il n'y a pas de produit disponible du catalogue, idProduit:" + idProduit);
			throw new CatalogueProdException(null, "Il n'y a pas de produit disponible du catalogue");
		}

		// Au cas contraier on va disminuer la cantité de produit disponible du
		// catalogue et ajoute le produit qui a été choisi au panier
		Produit produit = catService.getProduitById(idProduit);
		int cant = produit.getCantProduitDispo();

		PanierAchete panierAchete = null;
		panierAchete = panierAchetRef.findByIdProduit(produit);

		if (panierAchete == null) {
			panierAchete = new PanierAchete(produit, 1);
		} else {
			panierAchete.setCantite(panierAchete.getCantite() + 1);
			panierAchete.setDateModification(new Date());
		}
		// on ajoute ou modifie le produit du panier
		panierAchetRef.save(panierAchete);

		produit.setCantProduitDispo(--cant);
		catService.modifierProduit(produit);

		// On returne le detail du produit
		produitDTO = catService.getDetailProduitById(idProduit);

		return produitDTO;
	}

	/**
	 * Méthode qui enleve un produit du catalogue au panier
	 * 
	 * @param idProduit
	 * @return ProduitDTO
	 * @throws CatalogueProdException le produit avec l'identificateur du produit
	 *                                n'existe pas au panier
	 */
	public ProduitDTO enleverProduitPanier(Long idProduit) throws CatalogueProdException {
		ProduitDTO produitDTO = null;
		// On obtient la information du produit
		Produit produit = catService.getProduitById(idProduit);
		// Si le produit n'existe pas au panier
		if (produit == null) {
			log.debug("le produit avec l'identificateur " + idProduit + " n'existe pas au catalogue");
			throw new CatalogueProdException(null,
					"le produit avec l'identificateur " + idProduit + " n'existe pas au catalogue");
		}

		PanierAchete produitPanier = panierAchetRef.findByIdProduit(produit);
		// Si le produit n'existe pas au panier
		if (produitPanier == null) {
			log.debug("le produit avec l'identificateur du produit " + idProduit + " n'existe pas au panier");
			throw new CatalogueProdException(null,
					"le produit avec l'identificateur du produit" + idProduit + " n'existe pas au panier");
		}

		// S'il y a plus ou deux produits au panier on modifie la cantite, au cas
		// contraire on l'enlever
		if (produitPanier.getCantite() > 1) {
			produitPanier.setCantite(produitPanier.getCantite() - 1);
			panierAchetRef.save(produitPanier);
		} else {
			panierAchetRef.deleteById(produitPanier.getIdPanierAchete());
		}

		// On va augmenter la cantité de produit disponible du catalogue
		int cant = produit.getCantProduitDispo();
		produit.setCantProduitDispo(++cant);
		catService.modifierProduit(produit);

		// On returne le detail du produit
		produitDTO = catService.getDetailProduitById(idProduit);

		return produitDTO;

	}

	/**
	 * Méthode qui retourne le contenu du panier
	 * @return
	 */
	public List<PanierAcheteDTO> getContenuProduitPanier() {

		List<PanierAcheteDTO> listPanier = panierAchetRef.findAll().stream()
				.map(panier -> new PanierAcheteDTO(panier.getIdPanierAchete(), panier.getIdProduit().getIdProduit(),
						panier.getIdProduit().getNomProduit(), panier.getCantite(),
						panier.getIdProduit().getPrixProduit()))
				.collect(Collectors.toList());
		return listPanier;
	}

}
