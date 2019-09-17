package co.catalogue.prod.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.catalogue.prod.rest.modele.Produit;
import co.catalogue.prod.rest.referentiel.ProduitReferentiel;

/**
 * 
 * @author Camilo Chaparro
 *
 */
@Service
public class ProduitService {

	@Autowired
	private ProduitReferentiel prodRef;

	/**
	 * Méthode qui crée un nouveau produit
	 * 
	 * @param produit
	 */
	public void creerProduit(Produit produit) {
		prodRef.save(produit);
	}

}
