package co.catalogue.prod.rest.modele;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;

import lombok.Data;

/**
 * 
 * @author Camilo Chaparro
 *
 */
@Data
@Entity
public class PanierAchete implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idPanierAchete;
	@ManyToOne
	private Produit idProduit;
	private int cantite;
	private Date dateCreation;
	private Date dateModification;

	public PanierAchete() {

	}

	public PanierAchete(Produit idProduit, int cantite) {
		super();
		this.idProduit = idProduit;
		this.cantite = cantite;
	}

	public PanierAchete(Produit idProduit, int cantite, Date dateModification) {
		super();
		this.idProduit = idProduit;
		this.cantite = cantite;
		this.dateModification = dateModification;
	}

	@PrePersist
	void dateCreation() {
		this.dateCreation = new Date();
	}

}
