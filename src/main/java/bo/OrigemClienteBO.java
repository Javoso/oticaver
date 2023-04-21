package bo;

import java.util.List;

import br.hibernateutil.core.GenericDao;
import br.hibernateutil.exception.ObjetoNaoEncontradoHibernateException;
import br.hibernateutil.exception.SessaoNaoEncontradaParaEntidadeFornecidaException;
import br.hibernateutil.exception.ValidacaoHibernateException;
import br.hibernateutil.exception.ViolacaoChaveHibernateException;
import br.hibernateutil.paginacao.LazyEntityDataModel;
import dao.OrigemClienteDAO;
import exception.ObjetoExistenteException;
import model.OrigemCliente;

public class OrigemClienteBO {

	private OrigemClienteBO() {
	}

	public static void save(OrigemCliente origemCliente) throws ViolacaoChaveHibernateException, ValidacaoHibernateException,
			SessaoNaoEncontradaParaEntidadeFornecidaException, ObjetoExistenteException {
		validation(origemCliente);
		GenericDao.save(origemCliente);
	}

	public static void update(OrigemCliente origemCliente)
			throws ViolacaoChaveHibernateException, ObjetoNaoEncontradoHibernateException, ValidacaoHibernateException,
			SessaoNaoEncontradaParaEntidadeFornecidaException, ObjetoExistenteException {
		validation(origemCliente);
		GenericDao.update(origemCliente);
	}

	public static LazyEntityDataModel<OrigemCliente> origemClientesLazy(OrigemCliente origemCliente) {
		return OrigemClienteDAO.origemClientesLazy(origemCliente);
	}

	public static List<OrigemCliente> origemClientes(OrigemCliente origemCliente) throws SessaoNaoEncontradaParaEntidadeFornecidaException {
		return OrigemClienteDAO.origemClientes(origemCliente);
	}

	public static List<OrigemCliente> origemClientesComplete(String origemClienteName)
			throws SessaoNaoEncontradaParaEntidadeFornecidaException {
		return OrigemClienteDAO.origemClientes(origemClienteName);
	}

	public static List<OrigemCliente> list() throws SessaoNaoEncontradaParaEntidadeFornecidaException {
		return OrigemClienteDAO.origemClientes();
	}

	public static StringBuilder dadosFiltro() {
		return OrigemClienteDAO.getDadosFiltro();
	}
	
	public static void validation(OrigemCliente origemCliente) throws SessaoNaoEncontradaParaEntidadeFornecidaException, ObjetoExistenteException {
		
//		OrigemCliente l = GenericDao.findByAttribute(OrigemCliente.class, "cnpj",origemCliente.getCnpj());
//
//		if (l != null) {
//			if (!l.equals(origemCliente)) {
//				throw new ObjetoExistenteException(
//						"Já existe origemCliente cadastrada com o CNPJ informado!");
//			}
//		}

	}
}
