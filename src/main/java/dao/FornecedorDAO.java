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
import model.Fornecedor;
import model.Loja;

public class FornecedorDAO {

	private FornecedorDAO() {
	}

	public static Fornecedor getById(Integer id) {
		try {
			return GenericDao.findById(Fornecedor.class, id);
		} catch (ObjetoNaoEncontradoHibernateException e) {
			e.printStackTrace();
			return null;
		} catch (SessaoNaoEncontradaParaEntidadeFornecidaException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static LazyEntityDataModel<Fornecedor> fornecedoresLazy(Fornecedor fornecedor) {
		List<Criterion> restrictions = new ArrayList<Criterion>();

		if (fornecedor.getNome() != null && !fornecedor.getNome().isEmpty()) {
			restrictions.add(Restrictions.like("nome", fornecedor.getNome(), MatchMode.ANYWHERE));
		}
		return new LazyEntityDataModel<Fornecedor>(Fornecedor.class, null, null, restrictions, null);

	}

	private static StringBuilder dadosFiltro = new StringBuilder();

	public static List<Fornecedor> fornecedores(Fornecedor fornecedor)
			throws SessaoNaoEncontradaParaEntidadeFornecidaException {
		List<Criterion> restrictions = new ArrayList<Criterion>();

		if (fornecedor.getNome() != null && !fornecedor.getNome().isEmpty()) {
			restrictions.add(Restrictions.like("nome", fornecedor.getNome(), MatchMode.ANYWHERE));
		}
		return GenericDao.findAllByAttributesRestrictions(Fornecedor.class, "nome", true, restrictions);
	}

	public static List<Fornecedor> verificarFornecedorImportacao(Loja loja, String codigoFortes)
			throws SessaoNaoEncontradaParaEntidadeFornecidaException {
		List<Criterion> restrictions = new ArrayList<Criterion>();
		restrictions.add(Restrictions.like("codigoFortes", codigoFortes));
		return GenericDao.findAllByAttributesRestrictions(Fornecedor.class, "nome", true, restrictions);
	}

	public static LazyEntityDataModel<Fornecedor> fornecedoresDialogLazy(List<Fornecedor> fornecedores, Loja loja ) {
		List<Criterion> restrictions = new ArrayList<Criterion>();
		
		if (!fornecedores.isEmpty()) {
			for (int i = 0; i < fornecedores.size(); i++) {
				restrictions.add(Restrictions.ne("id", fornecedores.get(i).getId()));
			}
		}

		return new LazyEntityDataModel<Fornecedor>(Fornecedor.class, null, null, restrictions, null);
	}

	public static List<Fornecedor> fornecedoresComplete(String nome)
			throws SessaoNaoEncontradaParaEntidadeFornecidaException {

		List<Criterion> restrictions = new ArrayList<Criterion>();
		restrictions.add(Restrictions.like("nome", nome, MatchMode.ANYWHERE));
		return GenericDao.findAllByAttributesRestrictions(Fornecedor.class, "nome", true, restrictions);
	}

	public static List<Fornecedor> fornecedores() throws SessaoNaoEncontradaParaEntidadeFornecidaException {
		ArrayList<Criterion> restricions = new ArrayList<Criterion>();
		restricions.add(Restrictions.eq("status", true));
		return GenericDao.findAllByAttributesRestrictions(Fornecedor.class, "nome", true, restricions);
	}

	public static StringBuilder getDadosFiltro() {
		return dadosFiltro;
	}

}
