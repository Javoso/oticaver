package exception;

import java.io.Serializable;

public class UsuarioException extends Exception implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7387633556350894258L;

	public UsuarioException(String msg) {
		super(msg);
	}
}
