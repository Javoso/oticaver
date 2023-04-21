package bo;

import java.util.List;

import br.hibernateutil.core.GenericDao;
import br.hibernateutil.exception.ObjetoNaoEncontradoHibernateException;
import br.hibernateutil.exception.SessaoNaoEncontradaParaEntidadeFornecidaException;
import br.hibernateutil.exception.ValidacaoHibernateException;
import br.hibernateutil.exception.ViolacaoChaveHibernateException;
import br.hibernateutil.paginacao.LazyEntityDataModel;
import dao.OrdemServicoDAO;
import exception.ObjetoExistenteException;
import model.OrdemServico;

public class OrdemServicoBO {

	private OrdemServicoBO() {
	}

	public static void save(OrdemServico ordemServico) throws ViolacaoChaveHibernateException, ValidacaoHibernateException,
			SessaoNaoEncontradaParaEntidadeFornecidaException, ObjetoExistenteException {
		validation(ordemServico);
		GenericDao.save(ordemServico);
	}

	public static void update(OrdemServico ordemServico)
			throws ViolacaoChaveHibernateException, ObjetoNaoEncontradoHibernateException, ValidacaoHibernateException,
			SessaoNaoEncontradaParaEntidadeFornecidaException, ObjetoExistenteException {
		validation(ordemServico);
		GenericDao.update(ordemServico);
	}

	public static LazyEntityDataModel<OrdemServico> ordemServicosLazy(OrdemServico ordemServico) {
		return OrdemServicoDAO.ordemServicosLazy(ordemServico);
	}

	public static List<OrdemServico> ordemServicos(OrdemServico ordemServico) throws SessaoNaoEncontradaParaEntidadeFornecidaException {
		return OrdemServicoDAO.ordemServicos(ordemServico);
	}

	public static List<OrdemServico> ordemServicosComplete(String ordemServicoName)
			throws SessaoNaoEncontradaParaEntidadeFornecidaException {
		return OrdemServicoDAO.ordemServicos(ordemServicoName);
	}

	public static List<OrdemServico> list() throws SessaoNaoEncontradaParaEntidadeFornecidaException {
		return OrdemServicoDAO.ordemServicos();
	}

	public static StringBuilder dadosFiltro() {
		return OrdemServicoDAO.getDadosFiltro();
	}
	
	public static void validation(OrdemServico ordemServico) throws SessaoNaoEncontradaParaEntidadeFornecidaException, ObjetoExistenteException {
		
//		OrdemServico l = GenericDao.findByAttribute(OrdemServico.class, "cnpj",ordemServico.getCnpj());
//
//		if (l != null) {
//			if (!l.equals(ordemServico)) {
//				throw new ObjetoExistenteException(
//						"Já existe ordemServico cadastrada com o CNPJ informado!");
//			}
//		}

	}
}
