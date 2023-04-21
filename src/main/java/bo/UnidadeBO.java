package bo;

import java.util.List;

import br.hibernateutil.core.GenericDao;
import br.hibernateutil.exception.ObjetoNaoEncontradoHibernateException;
import br.hibernateutil.exception.SessaoNaoEncontradaParaEntidadeFornecidaException;
import br.hibernateutil.exception.ValidacaoHibernateException;
import br.hibernateutil.exception.ViolacaoChaveHibernateException;
import br.hibernateutil.paginacao.LazyEntityDataModel;
import dao.UnidadeDAO;
import exception.ObjetoExistenteException;
import model.Unidade;

public class UnidadeBO {

	private UnidadeBO() {
	}

	public static void save(Unidade unidade) throws ViolacaoChaveHibernateException, ValidacaoHibernateException,
			SessaoNaoEncontradaParaEntidadeFornecidaException, ObjetoExistenteException {
		validation(unidade);
		GenericDao.save(unidade);
	}

	public static void update(Unidade unidade)
			throws ViolacaoChaveHibernateException, ObjetoNaoEncontradoHibernateException, ValidacaoHibernateException,
			SessaoNaoEncontradaParaEntidadeFornecidaException, ObjetoExistenteException {
		validation(unidade);
		GenericDao.update(unidade);
	}

	public static LazyEntityDataModel<Unidade> unidadesLazy(Unidade unidade) {
		return UnidadeDAO.unidadesLazy(unidade);
	}

	public static List<Unidade> unidades(Unidade unidade) throws SessaoNaoEncontradaParaEntidadeFornecidaException {
		return UnidadeDAO.unidades(unidade);
	}

	public static List<Unidade> unidadesComplete(String unidadeName)
			throws SessaoNaoEncontradaParaEntidadeFornecidaException {
		return UnidadeDAO.unidades(unidadeName);
	}

	public static List<Unidade> list() throws SessaoNaoEncontradaParaEntidadeFornecidaException {
		return UnidadeDAO.unidades();
	}

	public static StringBuilder dadosFiltro() {
		return UnidadeDAO.getDadosFiltro();
	}
	
	public static void validation(Unidade unidade) throws SessaoNaoEncontradaParaEntidadeFornecidaException, ObjetoExistenteException {
		
//		Unidade l = GenericDao.findByAttribute(Unidade.class, "cnpj",unidade.getCnpj());
//
//		if (l != null) {
//			if (!l.equals(unidade)) {
//				throw new ObjetoExistenteException(
//						"Já existe unidade cadastrada com o CNPJ informado!");
//			}
//		}

	}
}
