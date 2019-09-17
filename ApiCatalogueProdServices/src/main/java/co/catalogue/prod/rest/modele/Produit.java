package co.catalogue.prod.rest.modele;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;

/**
 * 
 * @author Camilo Chaparro
 *
 */
@Data
@Entity
public class Produit implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idProduit;
	private String nomProduit;
	private Double prixProduit;
	private int cantProduitDispo;
	@ManyToOne
	private TypeProduit typeProduit;

	public Produit() {

	}

	public Produit(String nomProduit, Double prixProduit, TypeProduit typeProduit, int cantProduitDispo) {
		this.nomProduit = nomProduit;
		this.prixProduit = prixProduit;
		this.typeProduit = typeProduit;
		this.cantProduitDispo = cantProduitDispo;
	}
}
