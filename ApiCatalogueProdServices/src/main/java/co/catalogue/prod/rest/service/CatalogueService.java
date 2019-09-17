package co.catalogue.prod.rest.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.catalogue.prod.rest.modele.Produit;
import co.catalogue.prod.rest.modele.dto.CatalogueProduitDTO;
import co.catalogue.prod.rest.modele.dto.ProduitDTO;
import co.catalogue.prod.rest.referentiel.ProduitReferentiel;

/**
 * 
 * @author Camilo Chaparro
 *
 */
@Service
public class CatalogueService {

	@Autowired
	ProduitReferentiel prodRef;

	/**
	 * Méthode qui retourne un catalogue de produits
	 * 
	 * @return
	 */
	public List<CatalogueProduitDTO> getCatalogueProduits() {

		List<CatalogueProduitDTO> listCatProd = prodRef.findAll().stream()
				.map(produit -> new CatalogueProduitDTO(produit.getIdProduit(), produit.getNomProduit(),
						produit.getTypeProduit().getNomType()))
				.collect(Collectors.toList());

		return listCatProd;
	}

	/**
	 * Méthode qui retourne le detail d'un produit avec son id
	 * 
	 * @param idProduit
	 * @return
	 */
	public ProduitDTO getDetailProduitById(Long idProduit) {
		ProduitDTO prodDTO = null;
		Produit prod = prodRef.findByIdProduit(idProduit);
		if (prod != null) {
			prodDTO = new ProduitDTO(prod.getIdProduit(), prod.getNomProduit(), prod.getPrixProduit(),
					prod.getTypeProduit().getNomType(), prod.getCantProduitDispo());
		}
		return prodDTO;
	}

	/**
	 * 
	 * @param idProduit
	 * @return
	 */
	public Produit getProduitById(Long idProduit) {
		Produit prod = prodRef.findByIdProduit(idProduit);
		return prod;
	}

	public void modifierProduit(Produit prod) {
		prodRef.save(prod);
	}

}
