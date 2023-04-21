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
import model.Produto;
import model.Loja;

public class ProdutoDAO {

	private ProdutoDAO() {
	}

	public static Produto getById(Integer id) {
		try {
			return GenericDao.findById(Produto.class, id);
		} catch (ObjetoNaoEncontradoHibernateException e) {
			e.printStackTrace();
			return null;
		} catch (SessaoNaoEncontradaParaEntidadeFornecidaException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static LazyEntityDataModel<Produto> produtoesLazy(Produto produto) {
		List<Criterion> restrictions = new ArrayList<Criterion>();

		if (produto.getNome() != null && !produto.getNome().isEmpty()) {
			restrictions.add(Restrictions.like("nome", produto.getNome(), MatchMode.ANYWHERE));
		}
		return new LazyEntityDataModel<Produto>(Produto.class, null, null, restrictions, null);

	}

	private static StringBuilder dadosFiltro = new StringBuilder();

	public static List<Produto> produtoes(Produto produto)
			throws SessaoNaoEncontradaParaEntidadeFornecidaException {
		List<Criterion> restrictions = new ArrayList<Criterion>();

		if (produto.getNome() != null && !produto.getNome().isEmpty()) {
			restrictions.add(Restrictions.like("nome", produto.getNome(), MatchMode.ANYWHERE));
		}
		return GenericDao.findAllByAttributesRestrictions(Produto.class, null, true, restrictions);
	}

	public static List<Produto> verificarProdutoImportacao(Loja loja, String codigoFortes)
			throws SessaoNaoEncontradaParaEntidadeFornecidaException {
		List<Criterion> restrictions = new ArrayList<Criterion>();
		restrictions.add(Restrictions.like("codigoFortes", codigoFortes));
		return GenericDao.findAllByAttributesRestrictions(Produto.class, "nome", true, restrictions);
	}

	public static LazyEntityDataModel<Produto> produtoesDialogLazy(List<Produto> produtoes, Loja loja ) {
		List<Criterion> restrictions = new ArrayList<Criterion>();
		
		if (!produtoes.isEmpty()) {
			for (int i = 0; i < produtoes.size(); i++) {
				restrictions.add(Restrictions.ne("id", produtoes.get(i).getId()));
			}
		}

		return new LazyEntityDataModel<Produto>(Produto.class, null, null, restrictions, null);
	}

	public static List<Produto> produtoesComplete(String nome)
			throws SessaoNaoEncontradaParaEntidadeFornecidaException {

		List<Criterion> restrictions = new ArrayList<Criterion>();
		restrictions.add(Restrictions.like("nome", nome, MatchMode.ANYWHERE));
		return GenericDao.findAllByAttributesRestrictions(Produto.class, "nome", true, restrictions);
	}

	public static List<Produto> produtoes() throws SessaoNaoEncontradaParaEntidadeFornecidaException {
		ArrayList<Criterion> restricions = new ArrayList<Criterion>();
		restricions.add(Restrictions.eq("status", true));
		return GenericDao.findAllByAttributesRestrictions(Produto.class, "nome", true, restricions);
	}

	public static StringBuilder getDadosFiltro() {
		return dadosFiltro;
	}

}
