package co.catalogue.prod.rest.modele.dto;

import lombok.Data;

/**
 * 
 * @author Camilo Chaparro
 *
 */
@Data
public class PanierAcheteDTO {
	private Long idPanierAchete;
	private Long idProduit;
	private String nomProduit;
	private int cantite;
	private Double prixProduit;
	private Double prixTotal;

	public PanierAcheteDTO(Long idPanierAchete, Long idProduit, String nomProduit, int cantite, Double prixProduit) {
		super();
		this.idPanierAchete = idPanierAchete;
		this.idProduit = idProduit;
		this.nomProduit = nomProduit;
		this.cantite = cantite;
		this.prixProduit = prixProduit;
		this.prixTotal = cantite * prixProduit;
	}

}
