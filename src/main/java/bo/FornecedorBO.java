package bo;

import java.util.List;

import br.hibernateutil.core.GenericDao;
import br.hibernateutil.exception.ListaVaziaException;
import br.hibernateutil.exception.ObjetoNaoEncontradoHibernateException;
import br.hibernateutil.exception.SessaoNaoEncontradaParaEntidadeFornecidaException;
import br.hibernateutil.exception.ValidacaoHibernateException;
import br.hibernateutil.exception.ViolacaoChaveHibernateException;
import br.hibernateutil.paginacao.LazyEntityDataModel;
import dao.FornecedorDAO;
import model.Fornecedor;
import model.Loja;

public class FornecedorBO {

	private FornecedorBO() {
	}

	public static void save(Fornecedor fornecedor) throws ViolacaoChaveHibernateException, ValidacaoHibernateException,
			SessaoNaoEncontradaParaEntidadeFornecidaException {
		validation(fornecedor);
		GenericDao.save(fornecedor);
	}

	public static void update(Fornecedor fornecedor)
			throws ViolacaoChaveHibernateException, ObjetoNaoEncontradoHibernateException, ValidacaoHibernateException,
			SessaoNaoEncontradaParaEntidadeFornecidaException {
		validation(fornecedor);
		GenericDao.update(fornecedor);
	}

	public static LazyEntityDataModel<Fornecedor> fornecedoresLazy(Fornecedor fornecedor) {
		return FornecedorDAO.fornecedoresLazy(fornecedor);
	}

	public static LazyEntityDataModel<Fornecedor> fornecedoresDialogLazy(List<Fornecedor> fornecedores, Loja loja) {
		return FornecedorDAO.fornecedoresDialogLazy(fornecedores, loja);
	}

	public static List<Fornecedor> fornecedores(Fornecedor fornecedor) throws SessaoNaoEncontradaParaEntidadeFornecidaException {
		return FornecedorDAO.fornecedores(fornecedor);
	}

	public static List<Fornecedor> fornecedoresComplete(String nome) throws SessaoNaoEncontradaParaEntidadeFornecidaException {
		return FornecedorDAO.fornecedoresComplete(nome);
	}

	public static List<Fornecedor> verificarFornecedorImportacao(Loja loja, String codigoFortes) throws SessaoNaoEncontradaParaEntidadeFornecidaException {
		return FornecedorDAO.verificarFornecedorImportacao(loja, codigoFortes);
	}

	public static List<Fornecedor> list() throws SessaoNaoEncontradaParaEntidadeFornecidaException {
		return FornecedorDAO.fornecedores();
	}

	public static StringBuilder dadosFiltro() {
		return FornecedorDAO.getDadosFiltro();
	}
	
	public static void mergeAll(List<Fornecedor> despesaSessenta)
			throws ViolacaoChaveHibernateException,
			ValidacaoHibernateException,
			SessaoNaoEncontradaParaEntidadeFornecidaException,
			ListaVaziaException, ObjetoNaoEncontradoHibernateException {
		GenericDao.mergeAll(despesaSessenta);
	}

	public static void validation(Fornecedor fornecedor) {

	}
}
