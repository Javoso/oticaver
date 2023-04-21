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
import model.Cliente;
import model.Loja;

public class ClienteDAO {

	private ClienteDAO() {
	}

	public static Cliente getById(Integer id) {
		try {
			return GenericDao.findById(Cliente.class, id);
		} catch (ObjetoNaoEncontradoHibernateException e) {
			e.printStackTrace();
			return null;
		} catch (SessaoNaoEncontradaParaEntidadeFornecidaException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static LazyEntityDataModel<Cliente> clienteesLazy(Cliente cliente) {
		List<Criterion> restrictions = new ArrayList<Criterion>();

		if (cliente.getNome() != null && !cliente.getNome().isEmpty()) {
			restrictions.add(Restrictions.like("nome", cliente.getNome(), MatchMode.ANYWHERE));
		}
		if (cliente.getLoja() != null) {
			restrictions.add(Restrictions.eq("loja", cliente.getLoja()));
		}
		return new LazyEntityDataModel<Cliente>(Cliente.class, null, null, restrictions, null);

	}

	private static StringBuilder dadosFiltro = new StringBuilder();

	public static List<Cliente> clientees(Cliente cliente)
			throws SessaoNaoEncontradaParaEntidadeFornecidaException {
		List<Criterion> restrictions = new ArrayList<Criterion>();

		if (cliente.getNome() != null && !cliente.getNome().isEmpty()) {
			restrictions.add(Restrictions.like("nome", cliente.getNome(), MatchMode.ANYWHERE));
		}
		if (cliente.getLoja() != null) {
			restrictions.add(Restrictions.eq("loja", cliente.getLoja()));
		}
		return GenericDao.findAllByAttributesRestrictions(Cliente.class, "loja", true, restrictions);
	}

	public static List<Cliente> verificarClienteImportacao(Loja loja, String codigoFortes)
			throws SessaoNaoEncontradaParaEntidadeFornecidaException {
		List<Criterion> restrictions = new ArrayList<Criterion>();
		restrictions.add(Restrictions.like("codigoFortes", codigoFortes));
		restrictions.add(Restrictions.eq("loja", loja));
		return GenericDao.findAllByAttributesRestrictions(Cliente.class, "nome", true, restrictions);
	}

	public static List<Cliente> clienteesPorLoja(Loja loja)
			throws SessaoNaoEncontradaParaEntidadeFornecidaException {
		List<Criterion> restrictions = new ArrayList<Criterion>();

		if(!loja.getNome().isEmpty()) {
			restrictions.add(Restrictions.eq("loja", loja));
		}
		return GenericDao.findAllByAttributesRestrictions(Cliente.class, "nome", true, restrictions);
	}
	
	public static LazyEntityDataModel<Cliente> clienteesDialogLazy(List<Cliente> clientees, Loja loja ) {
		List<Criterion> restrictions = new ArrayList<Criterion>();
		
		if(!loja.getNome().isEmpty()) {
			restrictions.add(Restrictions.eq("loja", loja));
		}

		if (!clientees.isEmpty()) {
			for (int i = 0; i < clientees.size(); i++) {
				restrictions.add(Restrictions.ne("id", clientees.get(i).getId()));
			}
		}

		return new LazyEntityDataModel<Cliente>(Cliente.class, null, null, restrictions, null);
	}

	public static List<Cliente> clienteesComplete(String nome)
			throws SessaoNaoEncontradaParaEntidadeFornecidaException {

		List<Criterion> restrictions = new ArrayList<Criterion>();
		restrictions.add(Restrictions.like("nome", nome, MatchMode.ANYWHERE));
		return GenericDao.findAllByAttributesRestrictions(Cliente.class, "nome", true, restrictions);
	}

	public static List<Cliente> clientees() throws SessaoNaoEncontradaParaEntidadeFornecidaException {
		ArrayList<Criterion> restricions = new ArrayList<Criterion>();
		restricions.add(Restrictions.eq("status", true));
		return GenericDao.findAllByAttributesRestrictions(Cliente.class, "nome", true, restricions);
	}

	public static StringBuilder getDadosFiltro() {
		return dadosFiltro;
	}

}
