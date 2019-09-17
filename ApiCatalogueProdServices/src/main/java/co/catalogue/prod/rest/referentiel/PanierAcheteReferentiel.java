package co.catalogue.prod.rest.referentiel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.catalogue.prod.rest.modele.PanierAchete;
import co.catalogue.prod.rest.modele.Produit;

/**
 * 
 * @author Camilo Chaparro
 *
 */
@Repository
public interface PanierAcheteReferentiel extends JpaRepository<PanierAchete, Long>{

	/**
	 * Méthode qui retourne le produit du panier avec l'id
	 * @param idProduit
	 * @return
	 */
	PanierAchete findByIdProduit(Produit idProduit);
}
