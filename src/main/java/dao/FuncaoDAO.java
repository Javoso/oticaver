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
import model.Funcao;

public class FuncaoDAO {

	private FuncaoDAO() {
	}
	
	
	public static Funcao getById(Integer id) {
		try {
			return GenericDao.findById(Funcao.class, id);
		} catch (ObjetoNaoEncontradoHibernateException e) {
			e.printStackTrace();
			return null;
		} catch (SessaoNaoEncontradaParaEntidadeFornecidaException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static LazyEntityDataModel<Funcao> funcaosLazy(Funcao funcao) {
		List<Criterion> restrictions = new ArrayList<Criterion>();

		if (funcao.getNome() != null && !funcao.getNome().isEmpty()) {
			restrictions.add(Restrictions.like("nome", funcao.getNome(), MatchMode.ANYWHERE));
		}
		return new LazyEntityDataModel<Funcao>(Funcao.class, null, null, restrictions, null);

	}

	private static StringBuilder dadosFiltro = new StringBuilder();

	public static List<Funcao> funcaos(Funcao funcao) throws SessaoNaoEncontradaParaEntidadeFornecidaException {
		List<Criterion> restrictions = new ArrayList<Criterion>();

		if (funcao.getNome() != null && !funcao.getNome().isEmpty()) {
			restrictions.add(Restrictions.like("nome", funcao.getNome(), MatchMode.ANYWHERE));

			dadosFiltro.append("NOME: " + funcao.getNome());
		}
		return GenericDao.findAllByAttributesRestrictions(Funcao.class, "nome", true, restrictions);
	}
	
	public static List<Funcao> funcoes(String funcao) throws SessaoNaoEncontradaParaEntidadeFornecidaException {

		List<Criterion> restrictions = new ArrayList<Criterion>();

		if (funcao != null && !funcao.isEmpty()) {
			restrictions.add(Restrictions.like("nome", funcao,MatchMode.ANYWHERE));
		}
		restrictions.add(Restrictions.eq("status", true));
		return GenericDao.findAllByAttributesRestrictions(Funcao.class, "nome", true, restrictions);
	}

	public static List<Funcao> funcoes() throws SessaoNaoEncontradaParaEntidadeFornecidaException {
		ArrayList<Criterion> restricions = new ArrayList<Criterion>();
		restricions.add(Restrictions.eq("status", true));
		return GenericDao.findAllByAttributesRestrictions(Funcao.class, "nome", true, restricions);
	}

	public static StringBuilder getDadosFiltro() {
		return dadosFiltro;
	}

}
