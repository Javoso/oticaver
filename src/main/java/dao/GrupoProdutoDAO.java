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
import model.GrupoProduto;

public class GrupoProdutoDAO {

	private GrupoProdutoDAO() {
	}
	
	
	public static GrupoProduto getById(Integer id) {
		try {
			return GenericDao.findById(GrupoProduto.class, id);
		} catch (ObjetoNaoEncontradoHibernateException e) {
			e.printStackTrace();
			return null;
		} catch (SessaoNaoEncontradaParaEntidadeFornecidaException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static LazyEntityDataModel<GrupoProduto> grupoProdutosLazy(GrupoProduto grupoProduto) {
		List<Criterion> restrictions = new ArrayList<Criterion>();

		if (grupoProduto.getNome() != null && !grupoProduto.getNome().isEmpty()) {
			restrictions.add(Restrictions.like("nome", grupoProduto.getNome(), MatchMode.ANYWHERE));
		}
		if (grupoProduto.getCategoriaGrupoProduto() != null) {
			restrictions.add(Restrictions.eq("categoriaGrupoProduto", grupoProduto.getCategoriaGrupoProduto()));
		}
		return new LazyEntityDataModel<GrupoProduto>(GrupoProduto.class, null, null, restrictions, null);

	}

	private static StringBuilder dadosFiltro = new StringBuilder();

	public static List<GrupoProduto> grupoProdutos(GrupoProduto grupoProduto) throws SessaoNaoEncontradaParaEntidadeFornecidaException {
		List<Criterion> restrictions = new ArrayList<Criterion>();

		if (grupoProduto.getNome() != null && !grupoProduto.getNome().isEmpty()) {
			restrictions.add(Restrictions.like("nome", grupoProduto.getNome(), MatchMode.ANYWHERE));

			dadosFiltro.append("NOME: " + grupoProduto.getNome());
		}
		if (grupoProduto.getCategoriaGrupoProduto() != null) {
			restrictions.add(Restrictions.eq("categoriaGrupoProduto", grupoProduto.getCategoriaGrupoProduto()));
		}
		return GenericDao.findAllByAttributesRestrictions(GrupoProduto.class, "nome", true, restrictions);
	}
	
	public static List<GrupoProduto> grupos(String grupoProduto) throws SessaoNaoEncontradaParaEntidadeFornecidaException {

		List<Criterion> restrictions = new ArrayList<Criterion>();

		if (grupoProduto != null && !grupoProduto.isEmpty()) {
			restrictions.add(Restrictions.like("nome", grupoProduto,MatchMode.ANYWHERE));
		}
		restrictions.add(Restrictions.eq("status", true));
		return GenericDao.findAllByAttributesRestrictions(GrupoProduto.class, "nome", true, restrictions);
	}

	public static List<GrupoProduto> grupos() throws SessaoNaoEncontradaParaEntidadeFornecidaException {
		ArrayList<Criterion> restricions = new ArrayList<Criterion>();
		restricions.add(Restrictions.eq("status", true));
		return GenericDao.findAllByAttributesRestrictions(GrupoProduto.class, "nome", true, restricions);
	}

	public static StringBuilder getDadosFiltro() {
		return dadosFiltro;
	}

}
