package bo;

import java.util.List;

import br.hibernateutil.core.GenericDao;
import br.hibernateutil.exception.ListaVaziaException;
import br.hibernateutil.exception.ObjetoNaoEncontradoHibernateException;
import br.hibernateutil.exception.SessaoNaoEncontradaParaEntidadeFornecidaException;
import br.hibernateutil.exception.ValidacaoHibernateException;
import br.hibernateutil.exception.ViolacaoChaveHibernateException;
import br.hibernateutil.paginacao.LazyEntityDataModel;
import dao.ColaboradorDAO;
import model.Colaborador;
import model.Loja;

public class ColaboradorBO {

	private ColaboradorBO() {
	}

	public static void save(Colaborador colaborador) throws ViolacaoChaveHibernateException, ValidacaoHibernateException,
			SessaoNaoEncontradaParaEntidadeFornecidaException {
		validation(colaborador);
		GenericDao.save(colaborador);
	}

	public static void update(Colaborador colaborador)
			throws ViolacaoChaveHibernateException, ObjetoNaoEncontradoHibernateException, ValidacaoHibernateException,
			SessaoNaoEncontradaParaEntidadeFornecidaException {
		validation(colaborador);
		GenericDao.update(colaborador);
	}

	public static LazyEntityDataModel<Colaborador> colaboradoresLazy(Colaborador colaborador) {
		return ColaboradorDAO.colaboradoresLazy(colaborador);
	}

	public static LazyEntityDataModel<Colaborador> colaboradoresDialogLazy(List<Colaborador> colaboradores, Loja loja) {
		return ColaboradorDAO.colaboradoresDialogLazy(colaboradores, loja);
	}

	public static List<Colaborador> colaboradores(Colaborador colaborador) throws SessaoNaoEncontradaParaEntidadeFornecidaException {
		return ColaboradorDAO.colaboradores(colaborador);
	}

	public static List<Colaborador> colaboradoresComplete(String nome) throws SessaoNaoEncontradaParaEntidadeFornecidaException {
		return ColaboradorDAO.colaboradoresComplete(nome);
	}

	public static List<Colaborador> colaboradoresPorLoja(Loja loja) throws SessaoNaoEncontradaParaEntidadeFornecidaException {
		return ColaboradorDAO.colaboradoresPorLoja(loja);
	}

	public static List<Colaborador> verificarColaboradorImportacao(Loja loja, String codigoFortes) throws SessaoNaoEncontradaParaEntidadeFornecidaException {
		return ColaboradorDAO.verificarColaboradorImportacao(loja, codigoFortes);
	}

	public static List<Colaborador> list() throws SessaoNaoEncontradaParaEntidadeFornecidaException {
		return ColaboradorDAO.colaboradores();
	}

	public static StringBuilder dadosFiltro() {
		return ColaboradorDAO.getDadosFiltro();
	}
	
	public static void mergeAll(List<Colaborador> despesaSessenta)
			throws ViolacaoChaveHibernateException,
			ValidacaoHibernateException,
			SessaoNaoEncontradaParaEntidadeFornecidaException,
			ListaVaziaException, ObjetoNaoEncontradoHibernateException {
		GenericDao.mergeAll(despesaSessenta);
	}

	public static void validation(Colaborador colaborador) {

	}
}
