package bo;

import model.Usuario;
import br.hibernateutil.core.GenericDao;
import br.hibernateutil.exception.NumeroAtributosDiferenteNumeroValoresException;
import br.hibernateutil.exception.SessaoNaoEncontradaParaEntidadeFornecidaException;

public class LoginBO {

	public static LoginBO getInstance() {
			return new LoginBO();
	}

	private LoginBO() {

	}

	public Usuario login(String login, String senha) throws NumeroAtributosDiferenteNumeroValoresException, SessaoNaoEncontradaParaEntidadeFornecidaException{
		return (Usuario) GenericDao.findByNamedQueryWithParameters(Usuario.class, "findByLoginAndSenha", "login", login,"senha", senha);

	}
}
