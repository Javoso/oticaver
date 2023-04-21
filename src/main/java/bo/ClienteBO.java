package bo;

import java.util.List;

import br.hibernateutil.core.GenericDao;
import br.hibernateutil.exception.ListaVaziaException;
import br.hibernateutil.exception.ObjetoNaoEncontradoHibernateException;
import br.hibernateutil.exception.SessaoNaoEncontradaParaEntidadeFornecidaException;
import br.hibernateutil.exception.ValidacaoHibernateException;
import br.hibernateutil.exception.ViolacaoChaveHibernateException;
import br.hibernateutil.paginacao.LazyEntityDataModel;
import dao.ClienteDAO;
import model.Cliente;
import model.Loja;

public class ClienteBO {

	private ClienteBO() {
	}

	public static void save(Cliente cliente) throws ViolacaoChaveHibernateException, ValidacaoHibernateException,
			SessaoNaoEncontradaParaEntidadeFornecidaException {
		validation(cliente);
		GenericDao.save(cliente);
	}

	public static void update(Cliente cliente)
			throws ViolacaoChaveHibernateException, ObjetoNaoEncontradoHibernateException, ValidacaoHibernateException,
			SessaoNaoEncontradaParaEntidadeFornecidaException {
		validation(cliente);
		GenericDao.update(cliente);
	}

	public static LazyEntityDataModel<Cliente> clienteesLazy(Cliente cliente) {
		return ClienteDAO.clienteesLazy(cliente);
	}

	public static LazyEntityDataModel<Cliente> clienteesDialogLazy(List<Cliente> clientees, Loja loja) {
		return ClienteDAO.clienteesDialogLazy(clientees, loja);
	}

	public static List<Cliente> clientees(Cliente cliente) throws SessaoNaoEncontradaParaEntidadeFornecidaException {
		return ClienteDAO.clientees(cliente);
	}

	public static List<Cliente> clienteesComplete(String nome) throws SessaoNaoEncontradaParaEntidadeFornecidaException {
		return ClienteDAO.clienteesComplete(nome);
	}

	public static List<Cliente> clienteesPorLoja(Loja loja) throws SessaoNaoEncontradaParaEntidadeFornecidaException {
		return ClienteDAO.clienteesPorLoja(loja);
	}

	public static List<Cliente> verificarClienteImportacao(Loja loja, String codigoFortes) throws SessaoNaoEncontradaParaEntidadeFornecidaException {
		return ClienteDAO.verificarClienteImportacao(loja, codigoFortes);
	}

	public static List<Cliente> list() throws SessaoNaoEncontradaParaEntidadeFornecidaException {
		return ClienteDAO.clientees();
	}

	public static StringBuilder dadosFiltro() {
		return ClienteDAO.getDadosFiltro();
	}
	
	public static void mergeAll(List<Cliente> despesaSessenta)
			throws ViolacaoChaveHibernateException,
			ValidacaoHibernateException,
			SessaoNaoEncontradaParaEntidadeFornecidaException,
			ListaVaziaException, ObjetoNaoEncontradoHibernateException {
		GenericDao.mergeAll(despesaSessenta);
	}

	public static void validation(Cliente cliente) {

	}
}
