package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import br.hibernateutil.core.GenericDao;
import br.hibernateutil.exception.SessaoNaoEncontradaParaEntidadeFornecidaException;
import model.OrdemServico;
import model.ReceitaOrdemServico;

public class ReceitaOrdemServicoDAO {

	private ReceitaOrdemServicoDAO() {
	}

	public static ReceitaOrdemServicoDAO getInstance() {
		return new ReceitaOrdemServicoDAO();
	}

	public List<ReceitaOrdemServico> receitaOrdemServicoMetaLojaPorOrdemServico(OrdemServico ordemServico)
			throws SessaoNaoEncontradaParaEntidadeFornecidaException {

		List<Criterion> restrictions = new ArrayList<Criterion>();

		if (ordemServico != null) {
			restrictions.add(Restrictions.eq("ordemServico", ordemServico));
		}
		return GenericDao.findAllByAttributesRestrictions(ReceitaOrdemServico.class,
				"dataCadastro", false, restrictions);
	}
	
	public List<ReceitaOrdemServico> itens() throws SessaoNaoEncontradaParaEntidadeFornecidaException{
		ArrayList<Criterion> restricions = new ArrayList<Criterion>();
		return GenericDao.findAllByAttributesRestrictions(ReceitaOrdemServico.class, null, true, restricions);
	}
	
}
