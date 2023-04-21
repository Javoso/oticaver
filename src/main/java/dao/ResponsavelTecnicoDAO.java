package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import br.hibernateutil.core.GenericDao;
import br.hibernateutil.exception.ObjetoNaoEncontradoHibernateException;
import br.hibernateutil.exception.SessaoNaoEncontradaParaEntidadeFornecidaException;
import br.hibernateutil.paginacao.LazyEntityDataModel;
import model.ResponsavelTecnico;
import model.Loja;

public class ResponsavelTecnicoDAO {

	private ResponsavelTecnicoDAO() {
	}

	public static ResponsavelTecnico getById(Integer id) {
		try {
			return GenericDao.findById(ResponsavelTecnico.class, id);
		} catch (ObjetoNaoEncontradoHibernateException e) {
			e.printStackTrace();
			return null;
		} catch (SessaoNaoEncontradaParaEntidadeFornecidaException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static LazyEntityDataModel<ResponsavelTecnico> responsavelTecnicoesLazy(ResponsavelTecnico responsavelTecnico) {
		List<Criterion> restrictions = new ArrayList<Criterion>();

		if (responsavelTecnico.getNome() != null && !responsavelTecnico.getNome().isEmpty()) {
			restrictions.add(Restrictions.like("nome", responsavelTecnico.getNome(), MatchMode.ANYWHERE));
		}
		if (responsavelTecnico.getLoja() != null) {
			restrictions.add(Restrictions.eq("loja", responsavelTecnico.getLoja()));
		}
		return new LazyEntityDataModel<ResponsavelTecnico>(ResponsavelTecnico.class, null, null, restrictions, null);

	}

	private static StringBuilder dadosFiltro = new StringBuilder();

	public static List<ResponsavelTecnico> responsavelTecnicoes(ResponsavelTecnico responsavelTecnico)
			throws SessaoNaoEncontradaParaEntidadeFornecidaException {
		List<Criterion> restrictions = new ArrayList<Criterion>();

		if (responsavelTecnico.getNome() != null && !responsavelTecnico.getNome().isEmpty()) {
			restrictions.add(Restrictions.like("nome", responsavelTecnico.getNome(), MatchMode.ANYWHERE));
		}
		if (responsavelTecnico.getLoja() != null) {
			restrictions.add(Restrictions.eq("loja", responsavelTecnico.getLoja()));
		}
		return GenericDao.findAllByAttributesRestrictions(ResponsavelTecnico.class, "loja", true, restrictions);
	}

	public static List<ResponsavelTecnico> verificarResponsavelTecnicoImportacao(Loja loja, String codigoFortes)
			throws SessaoNaoEncontradaParaEntidadeFornecidaException {
		List<Criterion> restrictions = new ArrayList<Criterion>();
		restrictions.add(Restrictions.like("codigoFortes", codigoFortes));
		restrictions.add(Restrictions.eq("loja", loja));
		return GenericDao.findAllByAttributesRestrictions(ResponsavelTecnico.class, "nome", true, restrictions);
	}

	public static List<ResponsavelTecnico> responsavelTecnicoesPorLoja(Loja loja)
			throws SessaoNaoEncontradaParaEntidadeFornecidaException {
		List<Criterion> restrictions = new ArrayList<Criterion>();

		if(!loja.getNome().isEmpty()) {
			restrictions.add(Restrictions.eq("loja", loja));
		}
		return GenericDao.findAllByAttributesRestrictions(ResponsavelTecnico.class, "nome", true, restrictions);
	}
	
	public static LazyEntityDataModel<ResponsavelTecnico> responsavelTecnicoesDialogLazy(List<ResponsavelTecnico> responsavelTecnicoes, Loja loja ) {
		List<Criterion> restrictions = new ArrayList<Criterion>();
		
		if(!loja.getNome().isEmpty()) {
			restrictions.add(Restrictions.eq("loja", loja));
		}

		if (!responsavelTecnicoes.isEmpty()) {
			for (int i = 0; i < responsavelTecnicoes.size(); i++) {
				restrictions.add(Restrictions.ne("id", responsavelTecnicoes.get(i).getId()));
			}
		}

		return new LazyEntityDataModel<ResponsavelTecnico>(ResponsavelTecnico.class, null, null, restrictions, null);
	}

	public static List<ResponsavelTecnico> responsavelTecnicoesComplete(String nome)
			throws SessaoNaoEncontradaParaEntidadeFornecidaException {

		List<Criterion> restrictions = new ArrayList<Criterion>();
		restrictions.add(Restrictions.like("nome", nome, MatchMode.ANYWHERE));
		return GenericDao.findAllByAttributesRestrictions(ResponsavelTecnico.class, "nome", true, restrictions);
	}

	public static List<ResponsavelTecnico> responsavelTecnicoes() throws SessaoNaoEncontradaParaEntidadeFornecidaException {
		ArrayList<Criterion> restricions = new ArrayList<Criterion>();
		restricions.add(Restrictions.eq("status", true));
		return GenericDao.findAllByAttributesRestrictions(ResponsavelTecnico.class, "nome", true, restricions);
	}

	public static StringBuilder getDadosFiltro() {
		return dadosFiltro;
	}

}
