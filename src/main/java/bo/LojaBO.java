package bo;

import java.util.List;

import br.hibernateutil.core.GenericDao;
import br.hibernateutil.exception.ObjetoNaoEncontradoHibernateException;
import br.hibernateutil.exception.SessaoNaoEncontradaParaEntidadeFornecidaException;
import br.hibernateutil.exception.ValidacaoHibernateException;
import br.hibernateutil.exception.ViolacaoChaveHibernateException;
import br.hibernateutil.paginacao.LazyEntityDataModel;
import dao.LojaDAO;
import exception.ObjetoExistenteException;
import model.Loja;

public class LojaBO {

	private LojaBO() {
	}

	public static void save(Loja loja) throws ViolacaoChaveHibernateException, ValidacaoHibernateException,
			SessaoNaoEncontradaParaEntidadeFornecidaException, ObjetoExistenteException {
		validation(loja);
		GenericDao.save(loja);
	}

	public static void update(Loja loja)
			throws ViolacaoChaveHibernateException, ObjetoNaoEncontradoHibernateException, ValidacaoHibernateException,
			SessaoNaoEncontradaParaEntidadeFornecidaException, ObjetoExistenteException {
		validation(loja);
		GenericDao.update(loja);
	}

	public static LazyEntityDataModel<Loja> lojasLazy(Loja loja) {
		return LojaDAO.lojasLazy(loja);
	}

	public static List<Loja> lojas(Loja loja) throws SessaoNaoEncontradaParaEntidadeFornecidaException {
		return LojaDAO.lojas(loja);
	}

	public static List<Loja> lojasComplete(String lojaName)
			throws SessaoNaoEncontradaParaEntidadeFornecidaException {
		return LojaDAO.lojas(lojaName);
	}

	public static List<Loja> list() throws SessaoNaoEncontradaParaEntidadeFornecidaException {
		return LojaDAO.lojas();
	}

	public static StringBuilder dadosFiltro() {
		return LojaDAO.getDadosFiltro();
	}
	
	public static Loja lojaPorCNPJ(String cnpj) throws SessaoNaoEncontradaParaEntidadeFornecidaException {
		return LojaDAO.lojaPorCNPJ(cnpj);
	}

	public static void validation(Loja loja) throws SessaoNaoEncontradaParaEntidadeFornecidaException, ObjetoExistenteException {
		
//		Loja l = GenericDao.findByAttribute(Loja.class, "cnpj",loja.getCnpj());
//
//		if (l != null) {
//			if (!l.equals(loja)) {
//				throw new ObjetoExistenteException(
//						"Já existe loja cadastrada com o CNPJ informado!");
//			}
//		}

	}
}
