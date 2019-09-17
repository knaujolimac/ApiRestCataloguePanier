package co.catalogue.prod.rest.modele.dto;

import lombok.Data;

/**
 * 
 * @author Camilo Chaparro
 *
 */
@Data
public class ProduitDTO {
	private Long idProduit;
	private String nomProduit;
	private Double prixProduit;
	private String nomTypeProduit;
	private int cantProduitDispo;

	public ProduitDTO(Long idProduit, String nomProduit, Double prixProduit, String nomTypeProduit,
			int cantProduitDispo) {
		super();
		this.idProduit = idProduit;
		this.nomProduit = nomProduit;
		this.prixProduit = prixProduit;
		this.nomTypeProduit = nomTypeProduit;
		this.cantProduitDispo = cantProduitDispo;
	}

	public String toString() {
		return "idProduit " + idProduit + "\nnomProduit " + nomProduit + "\nprixProduit " + prixProduit
				+ "\nnomTypeProduit " + nomTypeProduit + "\ncantProduitDispo " + cantProduitDispo;
	}

}
