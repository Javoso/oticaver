package exception;

import java.io.Serializable;

public class EmpresaNaoCadastradaException extends Exception implements Serializable {
	 
	private static final long serialVersionUID = -8922605396441669876L;

	public EmpresaNaoCadastradaException(String msg){
		super(msg);
	}

}
