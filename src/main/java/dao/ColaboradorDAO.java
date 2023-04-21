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
import model.Colaborador;
import model.Loja;

public class ColaboradorDAO {

	private ColaboradorDAO() {
	}

	public static Colaborador getById(Integer id) {
		try {
			return GenericDao.findById(Colaborador.class, id);
		} catch (ObjetoNaoEncontradoHibernateException e) {
			e.printStackTrace();
			return null;
		} catch (SessaoNaoEncontradaParaEntidadeFornecidaException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static LazyEntityDataModel<Colaborador> colaboradoresLazy(Colaborador colaborador) {
		List<Criterion> restrictions = new ArrayList<Criterion>();

		if (colaborador.getNome() != null && !colaborador.getNome().isEmpty()) {
			restrictions.add(Restrictions.like("nome", colaborador.getNome(), MatchMode.ANYWHERE));
		}
		if (colaborador.getLoja() != null) {
			restrictions.add(Restrictions.eq("loja", colaborador.getLoja()));
		}
		return new LazyEntityDataModel<Colaborador>(Colaborador.class, null, null, restrictions, null);

	}

	private static StringBuilder dadosFiltro = new StringBuilder();

	public static List<Colaborador> colaboradores(Colaborador colaborador)
			throws SessaoNaoEncontradaParaEntidadeFornecidaException {
		List<Criterion> restrictions = new ArrayList<Criterion>();

		if (colaborador.getNome() != null && !colaborador.getNome().isEmpty()) {
			restrictions.add(Restrictions.like("nome", colaborador.getNome(), MatchMode.ANYWHERE));
		}
		if (colaborador.getLoja() != null) {
			restrictions.add(Restrictions.eq("loja", colaborador.getLoja()));
		}
		return GenericDao.findAllByAttributesRestrictions(Colaborador.class, "loja", true, restrictions);
	}

	public static List<Colaborador> verificarColaboradorImportacao(Loja loja, String codigoFortes)
			throws SessaoNaoEncontradaParaEntidadeFornecidaException {
		List<Criterion> restrictions = new ArrayList<Criterion>();
		restrictions.add(Restrictions.like("codigoFortes", codigoFortes));
		restrictions.add(Restrictions.eq("loja", loja));
		return GenericDao.findAllByAttributesRestrictions(Colaborador.class, "nome", true, restrictions);
	}

	public static List<Colaborador> colaboradoresPorLoja(Loja loja)
			throws SessaoNaoEncontradaParaEntidadeFornecidaException {
		List<Criterion> restrictions = new ArrayList<Criterion>();

		if(!loja.getNome().isEmpty()) {
			restrictions.add(Restrictions.eq("loja", loja));
		}
		return GenericDao.findAllByAttributesRestrictions(Colaborador.class, "nome", true, restrictions);
	}
	
	public static LazyEntityDataModel<Colaborador> colaboradoresDialogLazy(List<Colaborador> colaboradores, Loja loja ) {
		List<Criterion> restrictions = new ArrayList<Criterion>();
		
		if(!loja.getNome().isEmpty()) {
			restrictions.add(Restrictions.eq("loja", loja));
		}

		if (!colaboradores.isEmpty()) {
			for (int i = 0; i < colaboradores.size(); i++) {
				restrictions.add(Restrictions.ne("id", colaboradores.get(i).getId()));
			}
		}

		return new LazyEntityDataModel<Colaborador>(Colaborador.class, null, null, restrictions, null);
	}

	public static List<Colaborador> colaboradoresComplete(String nome)
			throws SessaoNaoEncontradaParaEntidadeFornecidaException {

		List<Criterion> restrictions = new ArrayList<Criterion>();
		restrictions.add(Restrictions.like("nome", nome, MatchMode.ANYWHERE));
		return GenericDao.findAllByAttributesRestrictions(Colaborador.class, "nome", true, restrictions);
	}

	public static List<Colaborador> colaboradores() throws SessaoNaoEncontradaParaEntidadeFornecidaException {
		ArrayList<Criterion> restricions = new ArrayList<Criterion>();
		restricions.add(Restrictions.eq("status", true));
		return GenericDao.findAllByAttributesRestrictions(Colaborador.class, "nome", true, restricions);
	}

	public static StringBuilder getDadosFiltro() {
		return dadosFiltro;
	}

}
