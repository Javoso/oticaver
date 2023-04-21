package bo;

import java.util.List;

import br.hibernateutil.core.GenericDao;
import br.hibernateutil.exception.ObjetoNaoEncontradoHibernateException;
import br.hibernateutil.exception.SessaoNaoEncontradaParaEntidadeFornecidaException;
import br.hibernateutil.exception.ValidacaoHibernateException;
import br.hibernateutil.exception.ViolacaoChaveHibernateException;
import br.hibernateutil.paginacao.LazyEntityDataModel;
import dao.FuncaoDAO;
import exception.ObjetoExistenteException;
import model.Funcao;

public class FuncaoBO {

	private FuncaoBO() {
	}

	public static void save(Funcao funcao) throws ViolacaoChaveHibernateException, ValidacaoHibernateException,
			SessaoNaoEncontradaParaEntidadeFornecidaException, ObjetoExistenteException {
		validation(funcao);
		GenericDao.save(funcao);
	}

	public static void update(Funcao funcao)
			throws ViolacaoChaveHibernateException, ObjetoNaoEncontradoHibernateException, ValidacaoHibernateException,
			SessaoNaoEncontradaParaEntidadeFornecidaException, ObjetoExistenteException {
		validation(funcao);
		GenericDao.update(funcao);
	}

	public static LazyEntityDataModel<Funcao> funcaosLazy(Funcao funcao) {
		return FuncaoDAO.funcaosLazy(funcao);
	}

	public static List<Funcao> funcaos(Funcao funcao) throws SessaoNaoEncontradaParaEntidadeFornecidaException {
		return FuncaoDAO.funcaos(funcao);
	}

	public static List<Funcao> funcaosComplete(String funcaoName)
			throws SessaoNaoEncontradaParaEntidadeFornecidaException {
		return FuncaoDAO.funcoes(funcaoName);
	}

	public static List<Funcao> list() throws SessaoNaoEncontradaParaEntidadeFornecidaException {
		return FuncaoDAO.funcoes();
	}

	public static StringBuilder dadosFiltro() {
		return FuncaoDAO.getDadosFiltro();
	}
	
	public static void validation(Funcao funcao) throws SessaoNaoEncontradaParaEntidadeFornecidaException, ObjetoExistenteException {
		
//		Funcao l = GenericDao.findByAttribute(Funcao.class, "cnpj",funcao.getCnpj());
//
//		if (l != null) {
//			if (!l.equals(funcao)) {
//				throw new ObjetoExistenteException(
//						"Já existe funcao cadastrada com o CNPJ informado!");
//			}
//		}

	}
}
