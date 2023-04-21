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
import model.Unidade;

public class UnidadeDAO {

	private UnidadeDAO() {
	}

	public static Unidade getById(Integer id) {
		try {
			return GenericDao.findById(Unidade.class, id);
		} catch (ObjetoNaoEncontradoHibernateException e) {
			e.printStackTrace();
			return null;
		} catch (SessaoNaoEncontradaParaEntidadeFornecidaException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static LazyEntityDataModel<Unidade> unidadesLazy(Unidade unidade) {
		List<Criterion> restrictions = new ArrayList<Criterion>();

		if (unidade.getNome() != null && !unidade.getNome().isEmpty()) {
			restrictions.add(Restrictions.like("nome", unidade.getNome(), MatchMode.ANYWHERE));
		}
		if (unidade.getSigla() != null && !unidade.getSigla().isEmpty()) {
			restrictions.add(Restrictions.like("sigla", unidade.getSigla(), MatchMode.ANYWHERE));
		}
		return new LazyEntityDataModel<Unidade>(Unidade.class, null, null, restrictions, null);

	}

	private static StringBuilder dadosFiltro = new StringBuilder();

	public static List<Unidade> unidades(Unidade unidade) throws SessaoNaoEncontradaParaEntidadeFornecidaException {
		List<Criterion> restrictions = new ArrayList<Criterion>();

		if (unidade.getNome() != null && !unidade.getNome().isEmpty()) {
			restrictions.add(Restrictions.like("nome", unidade.getNome(), MatchMode.ANYWHERE));

			dadosFiltro.append("NOME: " + unidade.getNome());
		}
		if (unidade.getSigla() != null && !unidade.getSigla().isEmpty()) {
			restrictions.add(Restrictions.like("sigla", unidade.getSigla(), MatchMode.ANYWHERE));
		}
		return GenericDao.findAllByAttributesRestrictions(Unidade.class, "nome", true, restrictions);
	}

	public static List<Unidade> unidades(String unidade) throws SessaoNaoEncontradaParaEntidadeFornecidaException {

		List<Criterion> restrictions = new ArrayList<Criterion>();

		if (unidade != null && !unidade.isEmpty()) {
			restrictions.add(Restrictions.like("nome", unidade, MatchMode.ANYWHERE));
		}
		restrictions.add(Restrictions.eq("status", true));
		return GenericDao.findAllByAttributesRestrictions(Unidade.class, "nome", true, restrictions);
	}

	public static List<Unidade> unidades() throws SessaoNaoEncontradaParaEntidadeFornecidaException {
		ArrayList<Criterion> restricions = new ArrayList<Criterion>();
		restricions.add(Restrictions.eq("status", true));
		return GenericDao.findAllByAttributesRestrictions(Unidade.class, "nome", true, restricions);
	}

	public static StringBuilder getDadosFiltro() {
		return dadosFiltro;
	}

}
