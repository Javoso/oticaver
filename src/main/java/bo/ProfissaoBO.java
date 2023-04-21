package bo;

import java.util.List;

import br.hibernateutil.core.GenericDao;
import br.hibernateutil.exception.ObjetoNaoEncontradoHibernateException;
import br.hibernateutil.exception.SessaoNaoEncontradaParaEntidadeFornecidaException;
import br.hibernateutil.exception.ValidacaoHibernateException;
import br.hibernateutil.exception.ViolacaoChaveHibernateException;
import br.hibernateutil.paginacao.LazyEntityDataModel;
import dao.ProfissaoDAO;
import exception.ObjetoExistenteException;
import model.Profissao;

public class ProfissaoBO {

	private ProfissaoBO() {
	}

	public static void save(Profissao profissao) throws ViolacaoChaveHibernateException, ValidacaoHibernateException,
			SessaoNaoEncontradaParaEntidadeFornecidaException, ObjetoExistenteException {
		validation(profissao);
		GenericDao.save(profissao);
	}

	public static void update(Profissao profissao)
			throws ViolacaoChaveHibernateException, ObjetoNaoEncontradoHibernateException, ValidacaoHibernateException,
			SessaoNaoEncontradaParaEntidadeFornecidaException, ObjetoExistenteException {
		validation(profissao);
		GenericDao.update(profissao);
	}

	public static LazyEntityDataModel<Profissao> profissaosLazy(Profissao profissao) {
		return ProfissaoDAO.profissaosLazy(profissao);
	}

	public static List<Profissao> profissaos(Profissao profissao) throws SessaoNaoEncontradaParaEntidadeFornecidaException {
		return ProfissaoDAO.profissaos(profissao);
	}

	public static List<Profissao> profissaosComplete(String profissaoName)
			throws SessaoNaoEncontradaParaEntidadeFornecidaException {
		return ProfissaoDAO.profissaos(profissaoName);
	}

	public static List<Profissao> list() throws SessaoNaoEncontradaParaEntidadeFornecidaException {
		return ProfissaoDAO.profissaos();
	}

	public static StringBuilder dadosFiltro() {
		return ProfissaoDAO.getDadosFiltro();
	}
	
	public static void validation(Profissao profissao) throws SessaoNaoEncontradaParaEntidadeFornecidaException, ObjetoExistenteException {
		
//		Profissao l = GenericDao.findByAttribute(Profissao.class, "cnpj",profissao.getCnpj());
//
//		if (l != null) {
//			if (!l.equals(profissao)) {
//				throw new ObjetoExistenteException(
//						"Já existe profissao cadastrada com o CNPJ informado!");
//			}
//		}

	}
}
