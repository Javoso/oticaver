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
import model.Profissao;

public class ProfissaoDAO {

	private ProfissaoDAO() {
	}
	
	
	public static Profissao getById(Integer id) {
		try {
			return GenericDao.findById(Profissao.class, id);
		} catch (ObjetoNaoEncontradoHibernateException e) {
			e.printStackTrace();
			return null;
		} catch (SessaoNaoEncontradaParaEntidadeFornecidaException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static LazyEntityDataModel<Profissao> profissaosLazy(Profissao profissao) {
		List<Criterion> restrictions = new ArrayList<Criterion>();

		if (profissao.getNome() != null && !profissao.getNome().isEmpty()) {
			restrictions.add(Restrictions.like("nome", profissao.getNome(), MatchMode.ANYWHERE));
		}
		return new LazyEntityDataModel<Profissao>(Profissao.class, null, null, restrictions, null);

	}

	private static StringBuilder dadosFiltro = new StringBuilder();

	public static List<Profissao> profissaos(Profissao profissao) throws SessaoNaoEncontradaParaEntidadeFornecidaException {
		List<Criterion> restrictions = new ArrayList<Criterion>();

		if (profissao.getNome() != null && !profissao.getNome().isEmpty()) {
			restrictions.add(Restrictions.like("nome", profissao.getNome(), MatchMode.ANYWHERE));

			dadosFiltro.append("NOME: " + profissao.getNome());
		}
		return GenericDao.findAllByAttributesRestrictions(Profissao.class, "nome", true, restrictions);
	}
	
	public static List<Profissao> profissaos(String profissao) throws SessaoNaoEncontradaParaEntidadeFornecidaException {

		List<Criterion> restrictions = new ArrayList<Criterion>();

		if (profissao != null && !profissao.isEmpty()) {
			restrictions.add(Restrictions.like("nome", profissao,MatchMode.ANYWHERE));
		}
		restrictions.add(Restrictions.eq("status", true));
		return GenericDao.findAllByAttributesRestrictions(Profissao.class, "nome", true, restrictions);
	}

	public static List<Profissao> profissaos() throws SessaoNaoEncontradaParaEntidadeFornecidaException {
		ArrayList<Criterion> restricions = new ArrayList<Criterion>();
		restricions.add(Restrictions.eq("status", true));
		return GenericDao.findAllByAttributesRestrictions(Profissao.class, "nome", true, restricions);
	}

	public static StringBuilder getDadosFiltro() {
		return dadosFiltro;
	}

}
