package bo;

import java.util.List;

import br.hibernateutil.core.GenericDao;
import br.hibernateutil.exception.IntegridadeObjetoHibernateException;
import br.hibernateutil.exception.ListaVaziaException;
import br.hibernateutil.exception.ObjetoNaoEncontradoHibernateException;
import br.hibernateutil.exception.SessaoNaoEncontradaParaEntidadeFornecidaException;
import br.hibernateutil.exception.ValidacaoHibernateException;
import br.hibernateutil.exception.ViolacaoChaveHibernateException;
import dao.ReceitaOrdemServicoDAO;
import model.OrdemServico;
import model.ReceitaOrdemServico;

public class ReceitaOrdemServicoBO {

	private ReceitaOrdemServicoBO() {
	}

	public static ReceitaOrdemServicoBO getInstance() {
		return new ReceitaOrdemServicoBO();
	}

	public void save(ReceitaOrdemServico receitaOrdemServicoMetaLoja)
			throws ViolacaoChaveHibernateException,
			ValidacaoHibernateException,
			SessaoNaoEncontradaParaEntidadeFornecidaException {
		GenericDao.save(receitaOrdemServicoMetaLoja);
	}

	public void update(ReceitaOrdemServico receitaOrdemServicoMetaLoja)
			throws ViolacaoChaveHibernateException,
			ObjetoNaoEncontradoHibernateException, ValidacaoHibernateException,
			SessaoNaoEncontradaParaEntidadeFornecidaException {
		GenericDao.update(receitaOrdemServicoMetaLoja);
	}

	public void delete(ReceitaOrdemServico receitaOrdemServicoMetaLoja)
			throws IntegridadeObjetoHibernateException,
			ObjetoNaoEncontradoHibernateException,
			SessaoNaoEncontradaParaEntidadeFornecidaException {
		GenericDao.delete(receitaOrdemServicoMetaLoja);
	}

	public List<ReceitaOrdemServico> receitaOrdemServicoMetaLojaPorOrdemServico(OrdemServico ordemServico)
			throws SessaoNaoEncontradaParaEntidadeFornecidaException {
		return ReceitaOrdemServicoDAO.getInstance().receitaOrdemServicoMetaLojaPorOrdemServico(ordemServico);
	}

	public List<ReceitaOrdemServico> itens()
			throws SessaoNaoEncontradaParaEntidadeFornecidaException {
		return ReceitaOrdemServicoDAO.getInstance().itens();
	}
	
	public void mergeAll(List<ReceitaOrdemServico> itens)
			throws ViolacaoChaveHibernateException,
			ValidacaoHibernateException,
			SessaoNaoEncontradaParaEntidadeFornecidaException,
			ListaVaziaException, ObjetoNaoEncontradoHibernateException {
		GenericDao.mergeAll(itens);
	}

	public void updateAll(List<ReceitaOrdemServico> itens)
			throws ViolacaoChaveHibernateException,
			ValidacaoHibernateException,
			SessaoNaoEncontradaParaEntidadeFornecidaException,
			ListaVaziaException, ObjetoNaoEncontradoHibernateException {
		GenericDao.updateAll(itens);
	}

	public void deleteAll(List<ReceitaOrdemServico> itensAux)
			throws IntegridadeObjetoHibernateException,
			ObjetoNaoEncontradoHibernateException,
			SessaoNaoEncontradaParaEntidadeFornecidaException {
		synchronized (ReceitaOrdemServico.class) {
			for (int i = 0; i < itensAux.size(); i++) {
				GenericDao.delete(itensAux.get(i));
			}
		}
	}

}
