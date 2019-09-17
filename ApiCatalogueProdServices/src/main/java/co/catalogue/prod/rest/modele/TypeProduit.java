package co.catalogue.prod.rest.modele;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

/**
 * 
 * @author Camilo Chaparro
 *
 */
@Data
@Entity
public class TypeProduit implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idType;
	private String nomType;
	private int etatType;
	
	public TypeProduit() {
		
	}

	public TypeProduit(String nomType, int etatType) {
		super();
		this.nomType = nomType;
		this.etatType = etatType;
	}

}
