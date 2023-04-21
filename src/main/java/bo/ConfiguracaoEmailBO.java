package bo;

import java.util.List;

import org.primefaces.model.SortOrder;

import br.hibernateutil.core.GenericDao;
import br.hibernateutil.exception.ObjetoNaoEncontradoHibernateException;
import br.hibernateutil.exception.SessaoNaoEncontradaParaEntidadeFornecidaException;
import br.hibernateutil.exception.ValidacaoHibernateException;
import br.hibernateutil.exception.ViolacaoChaveHibernateException;
import model.ConfiguracaoEmail;

public class ConfiguracaoEmailBO {

	public static ConfiguracaoEmail getLast()
			throws ObjetoNaoEncontradoHibernateException, SessaoNaoEncontradaParaEntidadeFornecidaException {
		List<ConfiguracaoEmail> list = GenericDao.getListaByDemanda(ConfiguracaoEmail.class, 0, 1, null, null, "id",
				SortOrder.DESCENDING, null, null);
		ConfiguracaoEmail param = new ConfiguracaoEmail();
		if (list != null && !list.isEmpty()) {
			return param = list.get(0);
		}
		return param;
	}

	public static void save(ConfiguracaoEmail param) throws ViolacaoChaveHibernateException,
			ValidacaoHibernateException, SessaoNaoEncontradaParaEntidadeFornecidaException {
		GenericDao.save(param);
	}

	public static void update(ConfiguracaoEmail param)
			throws ViolacaoChaveHibernateException, ObjetoNaoEncontradoHibernateException, ValidacaoHibernateException,
			SessaoNaoEncontradaParaEntidadeFornecidaException {
		GenericDao.update(param);
	}

	private ConfiguracaoEmailBO() {
	}

}
