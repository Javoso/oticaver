package bo;

import java.util.List;

import br.hibernateutil.core.GenericDao;
import br.hibernateutil.exception.ObjetoNaoEncontradoHibernateException;
import br.hibernateutil.exception.SessaoNaoEncontradaParaEntidadeFornecidaException;
import br.hibernateutil.exception.ValidacaoHibernateException;
import br.hibernateutil.exception.ViolacaoChaveHibernateException;
import br.hibernateutil.paginacao.LazyEntityDataModel;
import dao.GrupoProdutoDAO;
import exception.ObjetoExistenteException;
import model.GrupoProduto;

public class GrupoProdutoBO {

	private GrupoProdutoBO() {
	}

	public static void save(GrupoProduto grupoProduto) throws ViolacaoChaveHibernateException, ValidacaoHibernateException,
			SessaoNaoEncontradaParaEntidadeFornecidaException, ObjetoExistenteException {
		validation(grupoProduto);
		GenericDao.save(grupoProduto);
	}

	public static void update(GrupoProduto grupoProduto)
			throws ViolacaoChaveHibernateException, ObjetoNaoEncontradoHibernateException, ValidacaoHibernateException,
			SessaoNaoEncontradaParaEntidadeFornecidaException, ObjetoExistenteException {
		validation(grupoProduto);
		GenericDao.update(grupoProduto);
	}

	public static LazyEntityDataModel<GrupoProduto> grupoProdutosLazy(GrupoProduto grupoProduto) {
		return GrupoProdutoDAO.grupoProdutosLazy(grupoProduto);
	}

	public static List<GrupoProduto> grupoProdutos(GrupoProduto grupoProduto) throws SessaoNaoEncontradaParaEntidadeFornecidaException {
		return GrupoProdutoDAO.grupoProdutos(grupoProduto);
	}

	public static List<GrupoProduto> grupoProdutosComplete(String grupoProdutoName)
			throws SessaoNaoEncontradaParaEntidadeFornecidaException {
		return GrupoProdutoDAO.grupos(grupoProdutoName);
	}

	public static List<GrupoProduto> list() throws SessaoNaoEncontradaParaEntidadeFornecidaException {
		return GrupoProdutoDAO.grupos();
	}

	public static StringBuilder dadosFiltro() {
		return GrupoProdutoDAO.getDadosFiltro();
	}
	
	public static void validation(GrupoProduto grupoProduto) throws SessaoNaoEncontradaParaEntidadeFornecidaException, ObjetoExistenteException {
		
//		GrupoProduto l = GenericDao.findByAttribute(GrupoProduto.class, "cnpj",grupoProduto.getCnpj());
//
//		if (l != null) {
//			if (!l.equals(grupoProduto)) {
//				throw new ObjetoExistenteException(
//						"Já existe grupoProduto cadastrada com o CNPJ informado!");
//			}
//		}

	}
}
