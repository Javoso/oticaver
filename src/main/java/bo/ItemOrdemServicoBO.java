package bo;

import java.util.List;

import br.hibernateutil.core.GenericDao;
import br.hibernateutil.exception.IntegridadeObjetoHibernateException;
import br.hibernateutil.exception.ListaVaziaException;
import br.hibernateutil.exception.ObjetoNaoEncontradoHibernateException;
import br.hibernateutil.exception.SessaoNaoEncontradaParaEntidadeFornecidaException;
import br.hibernateutil.exception.ValidacaoHibernateException;
import br.hibernateutil.exception.ViolacaoChaveHibernateException;
import dao.ItemOrdemServicoDAO;
import model.OrdemServico;
import model.ItemOrdemServico;

public class ItemOrdemServicoBO {

	private ItemOrdemServicoBO() {
	}

	public static ItemOrdemServicoBO getInstance() {
		return new ItemOrdemServicoBO();
	}

	public void save(ItemOrdemServico itemOrdemServicoMetaLoja)
			throws ViolacaoChaveHibernateException,
			ValidacaoHibernateException,
			SessaoNaoEncontradaParaEntidadeFornecidaException {
		GenericDao.save(itemOrdemServicoMetaLoja);
	}

	public void update(ItemOrdemServico itemOrdemServicoMetaLoja)
			throws ViolacaoChaveHibernateException,
			ObjetoNaoEncontradoHibernateException, ValidacaoHibernateException,
			SessaoNaoEncontradaParaEntidadeFornecidaException {
		GenericDao.update(itemOrdemServicoMetaLoja);
	}

	public void delete(ItemOrdemServico itemOrdemServicoMetaLoja)
			throws IntegridadeObjetoHibernateException,
			ObjetoNaoEncontradoHibernateException,
			SessaoNaoEncontradaParaEntidadeFornecidaException {
		GenericDao.delete(itemOrdemServicoMetaLoja);
	}

	public List<ItemOrdemServico> itemOrdemServicoMetaLojaPorOrdemServico(OrdemServico ordemServico)
			throws SessaoNaoEncontradaParaEntidadeFornecidaException {
		return ItemOrdemServicoDAO.getInstance().itemOrdemServicoMetaLojaPorOrdemServico(ordemServico);
	}

	public List<ItemOrdemServico> itens()
			throws SessaoNaoEncontradaParaEntidadeFornecidaException {
		return ItemOrdemServicoDAO.getInstance().itens();
	}
	
	public void mergeAll(List<ItemOrdemServico> itens)
			throws ViolacaoChaveHibernateException,
			ValidacaoHibernateException,
			SessaoNaoEncontradaParaEntidadeFornecidaException,
			ListaVaziaException, ObjetoNaoEncontradoHibernateException {
		GenericDao.mergeAll(itens);
	}

	public void updateAll(List<ItemOrdemServico> itens)
			throws ViolacaoChaveHibernateException,
			ValidacaoHibernateException,
			SessaoNaoEncontradaParaEntidadeFornecidaException,
			ListaVaziaException, ObjetoNaoEncontradoHibernateException {
		GenericDao.updateAll(itens);
	}

	public void deleteAll(List<ItemOrdemServico> itensAux)
			throws IntegridadeObjetoHibernateException,
			ObjetoNaoEncontradoHibernateException,
			SessaoNaoEncontradaParaEntidadeFornecidaException {
		synchronized (ItemOrdemServico.class) {
			for (int i = 0; i < itensAux.size(); i++) {
				GenericDao.delete(itensAux.get(i));
			}
		}
	}

}
