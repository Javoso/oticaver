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
import model.Loja;

public class LojaDAO {

	private LojaDAO() {
	}
	
	
	public static Loja getById(Integer id) {
		try {
			return GenericDao.findById(Loja.class, id);
		} catch (ObjetoNaoEncontradoHibernateException e) {
			e.printStackTrace();
			return null;
		} catch (SessaoNaoEncontradaParaEntidadeFornecidaException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static LazyEntityDataModel<Loja> lojasLazy(Loja loja) {
		List<Criterion> restrictions = new ArrayList<Criterion>();

		if (loja.getNome() != null && !loja.getNome().isEmpty()) {
			restrictions.add(Restrictions.like("nome", loja.getNome(), MatchMode.ANYWHERE));
		}
		return new LazyEntityDataModel<Loja>(Loja.class, null, null, restrictions, null);

	}

	private static StringBuilder dadosFiltro = new StringBuilder();

	public static List<Loja> lojas(Loja loja) throws SessaoNaoEncontradaParaEntidadeFornecidaException {
		List<Criterion> restrictions = new ArrayList<Criterion>();

		if (loja.getNome() != null && !loja.getNome().isEmpty()) {
			restrictions.add(Restrictions.like("nome", loja.getNome(), MatchMode.ANYWHERE));

			dadosFiltro.append("NOME: " + loja.getNome());
		}
		return GenericDao.findAllByAttributesRestrictions(Loja.class, "nome", true, restrictions);
	}
	
	public static Loja lojaPorCNPJ(String cnpj) throws SessaoNaoEncontradaParaEntidadeFornecidaException {
		return GenericDao.findByAttribute(Loja.class, "cnpj", cnpj);
	}

	public static List<Loja> lojas(String loja) throws SessaoNaoEncontradaParaEntidadeFornecidaException {

		List<Criterion> restrictions = new ArrayList<Criterion>();

		if (loja != null && !loja.isEmpty()) {
			restrictions.add(Restrictions.like("nome", loja,MatchMode.ANYWHERE));
		}
		restrictions.add(Restrictions.eq("status", true));
		return GenericDao.findAllByAttributesRestrictions(Loja.class, "nome", true, restrictions);
	}

	public static List<Loja> lojas() throws SessaoNaoEncontradaParaEntidadeFornecidaException {
		ArrayList<Criterion> restricions = new ArrayList<Criterion>();
		restricions.add(Restrictions.eq("status", true));
		return GenericDao.findAllByAttributesRestrictions(Loja.class, "nome", true, restricions);
	}

	public static StringBuilder getDadosFiltro() {
		return dadosFiltro;
	}

}
