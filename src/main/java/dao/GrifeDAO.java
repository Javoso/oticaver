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
import model.Grife;

public class GrifeDAO {

	private GrifeDAO() {
	}
	
	
	public static Grife getById(Integer id) {
		try {
			return GenericDao.findById(Grife.class, id);
		} catch (ObjetoNaoEncontradoHibernateException e) {
			e.printStackTrace();
			return null;
		} catch (SessaoNaoEncontradaParaEntidadeFornecidaException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static LazyEntityDataModel<Grife> grifesLazy(Grife grife) {
		List<Criterion> restrictions = new ArrayList<Criterion>();

		if (grife.getNome() != null && !grife.getNome().isEmpty()) {
			restrictions.add(Restrictions.like("nome", grife.getNome(), MatchMode.ANYWHERE));
		}
		return new LazyEntityDataModel<Grife>(Grife.class, null, null, restrictions, null);

	}

	private static StringBuilder dadosFiltro = new StringBuilder();

	public static List<Grife> grifes(Grife grife) throws SessaoNaoEncontradaParaEntidadeFornecidaException {
		List<Criterion> restrictions = new ArrayList<Criterion>();

		if (grife.getNome() != null && !grife.getNome().isEmpty()) {
			restrictions.add(Restrictions.like("nome", grife.getNome(), MatchMode.ANYWHERE));

			dadosFiltro.append("NOME: " + grife.getNome());
		}
		return GenericDao.findAllByAttributesRestrictions(Grife.class, "nome", true, restrictions);
	}
	
	public static List<Grife> grifes(String grife) throws SessaoNaoEncontradaParaEntidadeFornecidaException {

		List<Criterion> restrictions = new ArrayList<Criterion>();

		if (grife != null && !grife.isEmpty()) {
			restrictions.add(Restrictions.like("nome", grife,MatchMode.ANYWHERE));
		}
		restrictions.add(Restrictions.eq("status", true));
		return GenericDao.findAllByAttributesRestrictions(Grife.class, "nome", true, restrictions);
	}

	public static List<Grife> grifes() throws SessaoNaoEncontradaParaEntidadeFornecidaException {
		ArrayList<Criterion> restricions = new ArrayList<Criterion>();
		restricions.add(Restrictions.eq("status", true));
		return GenericDao.findAllByAttributesRestrictions(Grife.class, "nome", true, restricions);
	}

	public static StringBuilder getDadosFiltro() {
		return dadosFiltro;
	}

}
