package co.catalogue.prod.rest.exception;

import org.springframework.http.HttpStatus;

/**
 * 
 * @author Camilo Chaparro
 *
 */
public class CatalogueProdException extends Exception {
	private HttpStatus status;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CatalogueProdException(HttpStatus status, String message, Throwable cause) {
		super(message, cause);
		this.status = status;
	}

	public CatalogueProdException(HttpStatus status, String message) {
		super(message);
		this.status = status;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

}
