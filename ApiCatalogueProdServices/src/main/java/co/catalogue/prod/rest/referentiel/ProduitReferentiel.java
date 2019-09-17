package co.catalogue.prod.rest.referentiel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.catalogue.prod.rest.modele.Produit;

/**
 * 
 * @author Camilo Chaparro
 *
 */
@Repository
public interface ProduitReferentiel  extends JpaRepository<Produit, Long>{
	
	/**
	 * Méthode qui retourne le detail d'un produit avec son id
	 * @param idProduit
	 * @return
	 */
	Produit findByIdProduit(Long idProduit);

}
