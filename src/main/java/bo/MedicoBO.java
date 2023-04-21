package bo;

import java.util.List;

import br.hibernateutil.core.GenericDao;
import br.hibernateutil.exception.ObjetoNaoEncontradoHibernateException;
import br.hibernateutil.exception.SessaoNaoEncontradaParaEntidadeFornecidaException;
import br.hibernateutil.exception.ValidacaoHibernateException;
import br.hibernateutil.exception.ViolacaoChaveHibernateException;
import br.hibernateutil.paginacao.LazyEntityDataModel;
import dao.MedicoDAO;
import exception.ObjetoExistenteException;
import model.Medico;

public class MedicoBO {

	private MedicoBO() {
	}

	public static void save(Medico medico) throws ViolacaoChaveHibernateException, ValidacaoHibernateException,
			SessaoNaoEncontradaParaEntidadeFornecidaException, ObjetoExistenteException {
		validation(medico);
		GenericDao.save(medico);
	}

	public static void update(Medico medico)
			throws ViolacaoChaveHibernateException, ObjetoNaoEncontradoHibernateException, ValidacaoHibernateException,
			SessaoNaoEncontradaParaEntidadeFornecidaException, ObjetoExistenteException {
		validation(medico);
		GenericDao.update(medico);
	}

	public static LazyEntityDataModel<Medico> medicosLazy(Medico medico) {
		return MedicoDAO.medicosLazy(medico);
	}

	public static List<Medico> medicos(Medico medico) throws SessaoNaoEncontradaParaEntidadeFornecidaException {
		return MedicoDAO.medicos(medico);
	}

	public static List<Medico> medicosComplete(String medicoName)
			throws SessaoNaoEncontradaParaEntidadeFornecidaException {
		return MedicoDAO.medicos(medicoName);
	}

	public static List<Medico> list() throws SessaoNaoEncontradaParaEntidadeFornecidaException {
		return MedicoDAO.medicos();
	}

	public static StringBuilder dadosFiltro() {
		return MedicoDAO.getDadosFiltro();
	}
	
	public static void validation(Medico medico) throws SessaoNaoEncontradaParaEntidadeFornecidaException, ObjetoExistenteException {
		
//		Medico l = GenericDao.findByAttribute(Medico.class, "cnpj",medico.getCnpj());
//
//		if (l != null) {
//			if (!l.equals(medico)) {
//				throw new ObjetoExistenteException(
//						"Já existe medico cadastrada com o CNPJ informado!");
//			}
//		}

	}
}
