package bo;

import java.util.List;

import br.hibernateutil.core.GenericDao;
import br.hibernateutil.exception.ListaVaziaException;
import br.hibernateutil.exception.ObjetoNaoEncontradoHibernateException;
import br.hibernateutil.exception.SessaoNaoEncontradaParaEntidadeFornecidaException;
import br.hibernateutil.exception.ValidacaoHibernateException;
import br.hibernateutil.exception.ViolacaoChaveHibernateException;
import br.hibernateutil.paginacao.LazyEntityDataModel;
import dao.ProdutoDAO;
import model.Loja;
import model.Produto;

public class ProdutoBO {

	private ProdutoBO() {
	}

	public static void save(Produto produto) throws ViolacaoChaveHibernateException, ValidacaoHibernateException,
			SessaoNaoEncontradaParaEntidadeFornecidaException {
		validation(produto);
		GenericDao.save(produto);
	}

	public static void update(Produto produto)
			throws ViolacaoChaveHibernateException, ObjetoNaoEncontradoHibernateException, ValidacaoHibernateException,
			SessaoNaoEncontradaParaEntidadeFornecidaException {
		validation(produto);
		GenericDao.update(produto);
	}

	public static LazyEntityDataModel<Produto> produtoesLazy(Produto produto) {
		return ProdutoDAO.produtoesLazy(produto);
	}

	public static LazyEntityDataModel<Produto> produtoesDialogLazy(List<Produto> produtoes, Loja loja) {
		return ProdutoDAO.produtoesDialogLazy(produtoes, loja);
	}

	public static List<Produto> produtoes(Produto produto) throws SessaoNaoEncontradaParaEntidadeFornecidaException {
		return ProdutoDAO.produtoes(produto);
	}

	public static List<Produto> produtoesComplete(String nome) throws SessaoNaoEncontradaParaEntidadeFornecidaException {
		return ProdutoDAO.produtoesComplete(nome);
	}

	public static List<Produto> verificarProdutoImportacao(Loja loja, String codigoFortes) throws SessaoNaoEncontradaParaEntidadeFornecidaException {
		return ProdutoDAO.verificarProdutoImportacao(loja, codigoFortes);
	}

	public static List<Produto> list() throws SessaoNaoEncontradaParaEntidadeFornecidaException {
		return ProdutoDAO.produtoes();
	}

	public static StringBuilder dadosFiltro() {
		return ProdutoDAO.getDadosFiltro();
	}
	
	public static void mergeAll(List<Produto> despesaSessenta)
			throws ViolacaoChaveHibernateException,
			ValidacaoHibernateException,
			SessaoNaoEncontradaParaEntidadeFornecidaException,
			ListaVaziaException, ObjetoNaoEncontradoHibernateException {
		GenericDao.mergeAll(despesaSessenta);
	}

	public static void validation(Produto produto) {

	}
}
