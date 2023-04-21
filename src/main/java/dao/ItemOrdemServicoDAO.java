package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import br.hibernateutil.core.GenericDao;
import br.hibernateutil.exception.SessaoNaoEncontradaParaEntidadeFornecidaException;
import model.OrdemServico;
import model.ItemOrdemServico;

public class ItemOrdemServicoDAO {

	private ItemOrdemServicoDAO() {
	}

	public static ItemOrdemServicoDAO getInstance() {
		return new ItemOrdemServicoDAO();
	}

	public List<ItemOrdemServico> itemOrdemServicoMetaLojaPorOrdemServico(OrdemServico ordemServico)
			throws SessaoNaoEncontradaParaEntidadeFornecidaException {

		List<Criterion> restrictions = new ArrayList<Criterion>();

		if (ordemServico != null) {
			restrictions.add(Restrictions.eq("ordemServico", ordemServico));
		}
		return GenericDao.findAllByAttributesRestrictions(ItemOrdemServico.class,
				null, true, restrictions);
	}
	
	public List<ItemOrdemServico> itens() throws SessaoNaoEncontradaParaEntidadeFornecidaException{
		ArrayList<Criterion> restricions = new ArrayList<Criterion>();
		return GenericDao.findAllByAttributesRestrictions(ItemOrdemServico.class, null, true, restricions);
	}
	
}
