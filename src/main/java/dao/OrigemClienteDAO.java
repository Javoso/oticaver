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
import model.OrigemCliente;

public class OrigemClienteDAO {

	private OrigemClienteDAO() {
	}
	
	
	public static OrigemCliente getById(Integer id) {
		try {
			return GenericDao.findById(OrigemCliente.class, id);
		} catch (ObjetoNaoEncontradoHibernateException e) {
			e.printStackTrace();
			return null;
		} catch (SessaoNaoEncontradaParaEntidadeFornecidaException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static LazyEntityDataModel<OrigemCliente> origemClientesLazy(OrigemCliente origemCliente) {
		List<Criterion> restrictions = new ArrayList<Criterion>();

		if (origemCliente.getNome() != null && !origemCliente.getNome().isEmpty()) {
			restrictions.add(Restrictions.like("nome", origemCliente.getNome(), MatchMode.ANYWHERE));
		}
		return new LazyEntityDataModel<OrigemCliente>(OrigemCliente.class, null, null, restrictions, null);

	}

	private static StringBuilder dadosFiltro = new StringBuilder();

	public static List<OrigemCliente> origemClientes(OrigemCliente origemCliente) throws SessaoNaoEncontradaParaEntidadeFornecidaException {
		List<Criterion> restrictions = new ArrayList<Criterion>();

		if (origemCliente.getNome() != null && !origemCliente.getNome().isEmpty()) {
			restrictions.add(Restrictions.like("nome", origemCliente.getNome(), MatchMode.ANYWHERE));

			dadosFiltro.append("NOME: " + origemCliente.getNome());
		}
		return GenericDao.findAllByAttributesRestrictions(OrigemCliente.class, "nome", true, restrictions);
	}
	
	public static List<OrigemCliente> origemClientes(String origemCliente) throws SessaoNaoEncontradaParaEntidadeFornecidaException {

		List<Criterion> restrictions = new ArrayList<Criterion>();

		if (origemCliente != null && !origemCliente.isEmpty()) {
			restrictions.add(Restrictions.like("nome", origemCliente,MatchMode.ANYWHERE));
		}
		restrictions.add(Restrictions.eq("status", true));
		return GenericDao.findAllByAttributesRestrictions(OrigemCliente.class, "nome", true, restrictions);
	}

	public static List<OrigemCliente> origemClientes() throws SessaoNaoEncontradaParaEntidadeFornecidaException {
		ArrayList<Criterion> restricions = new ArrayList<Criterion>();
		restricions.add(Restrictions.eq("status", true));
		return GenericDao.findAllByAttributesRestrictions(OrigemCliente.class, "nome", true, restricions);
	}

	public static StringBuilder getDadosFiltro() {
		return dadosFiltro;
	}

}
