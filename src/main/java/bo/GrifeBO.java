package bo;

import java.util.List;

import br.hibernateutil.core.GenericDao;
import br.hibernateutil.exception.ObjetoNaoEncontradoHibernateException;
import br.hibernateutil.exception.SessaoNaoEncontradaParaEntidadeFornecidaException;
import br.hibernateutil.exception.ValidacaoHibernateException;
import br.hibernateutil.exception.ViolacaoChaveHibernateException;
import br.hibernateutil.paginacao.LazyEntityDataModel;
import dao.GrifeDAO;
import exception.ObjetoExistenteException;
import model.Grife;

public class GrifeBO {

	private GrifeBO() {
	}

	public static void save(Grife grife) throws ViolacaoChaveHibernateException, ValidacaoHibernateException,
			SessaoNaoEncontradaParaEntidadeFornecidaException, ObjetoExistenteException {
		validation(grife);
		GenericDao.save(grife);
	}

	public static void update(Grife grife)
			throws ViolacaoChaveHibernateException, ObjetoNaoEncontradoHibernateException, ValidacaoHibernateException,
			SessaoNaoEncontradaParaEntidadeFornecidaException, ObjetoExistenteException {
		validation(grife);
		GenericDao.update(grife);
	}

	public static LazyEntityDataModel<Grife> grifesLazy(Grife grife) {
		return GrifeDAO.grifesLazy(grife);
	}

	public static List<Grife> grifes(Grife grife) throws SessaoNaoEncontradaParaEntidadeFornecidaException {
		return GrifeDAO.grifes(grife);
	}

	public static List<Grife> grifesComplete(String grifeName)
			throws SessaoNaoEncontradaParaEntidadeFornecidaException {
		return GrifeDAO.grifes(grifeName);
	}

	public static List<Grife> list() throws SessaoNaoEncontradaParaEntidadeFornecidaException {
		return GrifeDAO.grifes();
	}

	public static StringBuilder dadosFiltro() {
		return GrifeDAO.getDadosFiltro();
	}
	
	public static void validation(Grife grife) throws SessaoNaoEncontradaParaEntidadeFornecidaException, ObjetoExistenteException {
		
//		Grife l = GenericDao.findByAttribute(Grife.class, "cnpj",grife.getCnpj());
//
//		if (l != null) {
//			if (!l.equals(grife)) {
//				throw new ObjetoExistenteException(
//						"Já existe grife cadastrada com o CNPJ informado!");
//			}
//		}

	}
}
