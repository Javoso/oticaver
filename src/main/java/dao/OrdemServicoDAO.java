package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Criterion;

import br.hibernateutil.core.GenericDao;
import br.hibernateutil.exception.ObjetoNaoEncontradoHibernateException;
import br.hibernateutil.exception.SessaoNaoEncontradaParaEntidadeFornecidaException;
import br.hibernateutil.paginacao.LazyEntityDataModel;
import model.OrdemServico;

public class OrdemServicoDAO {

	private OrdemServicoDAO() {
	}
	
	
	public static OrdemServico getById(Integer id) {
		try {
			return GenericDao.findById(OrdemServico.class, id);
		} catch (ObjetoNaoEncontradoHibernateException e) {
			e.printStackTrace();
			return null;
		} catch (SessaoNaoEncontradaParaEntidadeFornecidaException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static LazyEntityDataModel<OrdemServico> ordemServicosLazy(OrdemServico ordemServico) {
		List<Criterion> restrictions = new ArrayList<Criterion>();

//		if (ordemServico.getNome() != null && !ordemServico.getNome().isEmpty()) {
//			restrictions.add(Restrictions.like("nome", ordemServico.getNome(), MatchMode.ANYWHERE));
//		}
		return new LazyEntityDataModel<OrdemServico>(OrdemServico.class, null, null, restrictions, null);

	}

	private static StringBuilder dadosFiltro = new StringBuilder();

	public static List<OrdemServico> ordemServicos(OrdemServico ordemServico) throws SessaoNaoEncontradaParaEntidadeFornecidaException {
		List<Criterion> restrictions = new ArrayList<Criterion>();

//		if (ordemServico.getNome() != null && !ordemServico.getNome().isEmpty()) {
//			restrictions.add(Restrictions.like("nome", ordemServico.getNome(), MatchMode.ANYWHERE));
//
//			dadosFiltro.append("NOME: " + ordemServico.getNome());
//		}
		return GenericDao.findAllByAttributesRestrictions(OrdemServico.class, "dataAbertura", true, restrictions);
	}
	
	public static List<OrdemServico> ordemServicos(String ordemServico) throws SessaoNaoEncontradaParaEntidadeFornecidaException {

		List<Criterion> restrictions = new ArrayList<Criterion>();
//
//		if (ordemServico != null && !ordemServico.isEmpty()) {
//			restrictions.add(Restrictions.like("nome", ordemServico,MatchMode.ANYWHERE));
//		}
		return GenericDao.findAllByAttributesRestrictions(OrdemServico.class, "dataAbertura", true, restrictions);
	}

	public static List<OrdemServico> ordemServicos() throws SessaoNaoEncontradaParaEntidadeFornecidaException {
		ArrayList<Criterion> restricions = new ArrayList<Criterion>();
		return GenericDao.findAllByAttributesRestrictions(OrdemServico.class, "dataAbertura", true, restricions);
	}

	public static StringBuilder getDadosFiltro() {
		return dadosFiltro;
	}

}
