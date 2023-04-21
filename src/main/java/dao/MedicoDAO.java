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
import model.Medico;

public class MedicoDAO {

	private MedicoDAO() {
	}
	
	
	public static Medico getById(Integer id) {
		try {
			return GenericDao.findById(Medico.class, id);
		} catch (ObjetoNaoEncontradoHibernateException e) {
			e.printStackTrace();
			return null;
		} catch (SessaoNaoEncontradaParaEntidadeFornecidaException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static LazyEntityDataModel<Medico> medicosLazy(Medico medico) {
		List<Criterion> restrictions = new ArrayList<Criterion>();

		if (medico.getNome() != null && !medico.getNome().isEmpty()) {
			restrictions.add(Restrictions.like("nome", medico.getNome(), MatchMode.ANYWHERE));
		}
		return new LazyEntityDataModel<Medico>(Medico.class, null, null, restrictions, null);

	}

	private static StringBuilder dadosFiltro = new StringBuilder();

	public static List<Medico> medicos(Medico medico) throws SessaoNaoEncontradaParaEntidadeFornecidaException {
		List<Criterion> restrictions = new ArrayList<Criterion>();

		if (medico.getNome() != null && !medico.getNome().isEmpty()) {
			restrictions.add(Restrictions.like("nome", medico.getNome(), MatchMode.ANYWHERE));

			dadosFiltro.append("NOME: " + medico.getNome());
		}
		return GenericDao.findAllByAttributesRestrictions(Medico.class, "nome", true, restrictions);
	}
	
	public static List<Medico> medicos(String medico) throws SessaoNaoEncontradaParaEntidadeFornecidaException {

		List<Criterion> restrictions = new ArrayList<Criterion>();

		if (medico != null && !medico.isEmpty()) {
			restrictions.add(Restrictions.like("nome", medico,MatchMode.ANYWHERE));
		}
		restrictions.add(Restrictions.eq("status", true));
		return GenericDao.findAllByAttributesRestrictions(Medico.class, "nome", true, restrictions);
	}

	public static List<Medico> medicos() throws SessaoNaoEncontradaParaEntidadeFornecidaException {
		ArrayList<Criterion> restricions = new ArrayList<Criterion>();
		restricions.add(Restrictions.eq("status", true));
		return GenericDao.findAllByAttributesRestrictions(Medico.class, "nome", true, restricions);
	}

	public static StringBuilder getDadosFiltro() {
		return dadosFiltro;
	}

}
