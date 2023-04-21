package exception;

import java.io.Serializable;

public class ValorInvalidoException extends Exception implements Serializable {
	 
	private static final long serialVersionUID = -8922605396441669876L;

	public ValorInvalidoException(String msg){
		super(msg);
	}

}
