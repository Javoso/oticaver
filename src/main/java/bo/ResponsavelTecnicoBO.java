package bo;

import java.util.List;

import br.hibernateutil.core.GenericDao;
import br.hibernateutil.exception.ListaVaziaException;
import br.hibernateutil.exception.ObjetoNaoEncontradoHibernateException;
import br.hibernateutil.exception.SessaoNaoEncontradaParaEntidadeFornecidaException;
import br.hibernateutil.exception.ValidacaoHibernateException;
import br.hibernateutil.exception.ViolacaoChaveHibernateException;
import br.hibernateutil.paginacao.LazyEntityDataModel;
import dao.ResponsavelTecnicoDAO;
import model.ResponsavelTecnico;
import model.Loja;

public class ResponsavelTecnicoBO {

	private ResponsavelTecnicoBO() {
	}

	public static void save(ResponsavelTecnico responsavelTecnico) throws ViolacaoChaveHibernateException, ValidacaoHibernateException,
			SessaoNaoEncontradaParaEntidadeFornecidaException {
		validation(responsavelTecnico);
		GenericDao.save(responsavelTecnico);
	}

	public static void update(ResponsavelTecnico responsavelTecnico)
			throws ViolacaoChaveHibernateException, ObjetoNaoEncontradoHibernateException, ValidacaoHibernateException,
			SessaoNaoEncontradaParaEntidadeFornecidaException {
		validation(responsavelTecnico);
		GenericDao.update(responsavelTecnico);
	}

	public static LazyEntityDataModel<ResponsavelTecnico> responsavelTecnicoesLazy(ResponsavelTecnico responsavelTecnico) {
		return ResponsavelTecnicoDAO.responsavelTecnicoesLazy(responsavelTecnico);
	}

	public static LazyEntityDataModel<ResponsavelTecnico> responsavelTecnicoesDialogLazy(List<ResponsavelTecnico> responsavelTecnicoes, Loja loja) {
		return ResponsavelTecnicoDAO.responsavelTecnicoesDialogLazy(responsavelTecnicoes, loja);
	}

	public static List<ResponsavelTecnico> responsavelTecnicoes(ResponsavelTecnico responsavelTecnico) throws SessaoNaoEncontradaParaEntidadeFornecidaException {
		return ResponsavelTecnicoDAO.responsavelTecnicoes(responsavelTecnico);
	}

	public static List<ResponsavelTecnico> responsavelTecnicoesComplete(String nome) throws SessaoNaoEncontradaParaEntidadeFornecidaException {
		return ResponsavelTecnicoDAO.responsavelTecnicoesComplete(nome);
	}

	public static List<ResponsavelTecnico> responsavelTecnicoesPorLoja(Loja loja) throws SessaoNaoEncontradaParaEntidadeFornecidaException {
		return ResponsavelTecnicoDAO.responsavelTecnicoesPorLoja(loja);
	}

	public static List<ResponsavelTecnico> verificarResponsavelTecnicoImportacao(Loja loja, String codigoFortes) throws SessaoNaoEncontradaParaEntidadeFornecidaException {
		return ResponsavelTecnicoDAO.verificarResponsavelTecnicoImportacao(loja, codigoFortes);
	}

	public static List<ResponsavelTecnico> list() throws SessaoNaoEncontradaParaEntidadeFornecidaException {
		return ResponsavelTecnicoDAO.responsavelTecnicoes();
	}

	public static StringBuilder dadosFiltro() {
		return ResponsavelTecnicoDAO.getDadosFiltro();
	}
	
	public static void mergeAll(List<ResponsavelTecnico> despesaSessenta)
			throws ViolacaoChaveHibernateException,
			ValidacaoHibernateException,
			SessaoNaoEncontradaParaEntidadeFornecidaException,
			ListaVaziaException, ObjetoNaoEncontradoHibernateException {
		GenericDao.mergeAll(despesaSessenta);
	}

	public static void validation(ResponsavelTecnico responsavelTecnico) {

	}
}
