package co.catalogue.prod.rest.modele.dto;

import lombok.Data;

/**
 * 
 * @author Camilo Chaparro
 *
 */
@Data
public class CatalogueProduitDTO {

	private Long idProduit;
	private String nomProduit;
	private String nomTypeProduit;

	public CatalogueProduitDTO(Long idProduit, String nomProduit, String nomTypeProduit) {
		super();
		this.idProduit = idProduit;
		this.nomProduit = nomProduit;
		this.nomTypeProduit = nomTypeProduit;
	}

}
