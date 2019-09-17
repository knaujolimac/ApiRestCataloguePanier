package co.catalogue.prod.rest.referentiel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.catalogue.prod.rest.modele.TypeProduit;

/**
 * 
 * @author Camilo Chaparro
 *
 */
@Repository
public interface TypeProduitReferentiel extends JpaRepository<TypeProduit, Long> {

}
