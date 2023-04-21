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
import model.Usuario;

public class UsuarioDAO {

	private UsuarioDAO() {
	}

	public static UsuarioDAO getInstance() {
		return new UsuarioDAO();
	}

	public Usuario getById(Integer id) {
		try {
			return GenericDao.findById(Usuario.class, id);
		} catch (ObjetoNaoEncontradoHibernateException e) {
			e.printStackTrace();
			return null;
		} catch (SessaoNaoEncontradaParaEntidadeFornecidaException e) {
			e.printStackTrace();
			return null;
		}
	}

	public LazyEntityDataModel<Usuario> usuariosLazy(Usuario usuario) {
		List<Criterion> restrictions = new ArrayList<Criterion>();

		if (usuario.getNome() != null && !usuario.getNome().isEmpty()) {
			restrictions.add(Restrictions.like("nome", usuario.getNome(), MatchMode.ANYWHERE));
		}
		if (usuario.getPerfilUser() != null) {
			restrictions.add(Restrictions.eq("perfilUser", usuario.getPerfilUser()));
		}
		if (usuario.getLoja() != null) {
			restrictions.add(Restrictions.eq("loja", usuario.getLoja()));
		}
		return new LazyEntityDataModel<Usuario>(Usuario.class, null, null, restrictions, null);
	}

	private StringBuilder dadosFiltro = new StringBuilder();

	public List<Usuario> usuarios(Usuario usuario) throws SessaoNaoEncontradaParaEntidadeFornecidaException {
		List<Criterion> restrictions = new ArrayList<Criterion>();

		if (usuario.getNome() != null && !usuario.getNome().isEmpty()) {
			restrictions.add(Restrictions.like("nome", usuario.getNome(), MatchMode.ANYWHERE));
		}
		if (usuario.getPerfilUser() != null) {
			restrictions.add(Restrictions.eq("perfilUser", usuario.getPerfilUser()));
		}
		if (usuario.getLoja() != null) {
			restrictions.add(Restrictions.eq("loja", usuario.getLoja()));
		}
		return GenericDao.findAllByAttributesRestrictions(Usuario.class, "nome", true, restrictions);
	}

	public List<Usuario> usuarios() throws SessaoNaoEncontradaParaEntidadeFornecidaException {
		ArrayList<Criterion> restricions = new ArrayList<Criterion>();
		restricions.add(Restrictions.eq("status", true));
		return GenericDao.findAllByAttributesRestrictions(Usuario.class, "nome", true, restricions);
	}

	public StringBuilder getDadosFiltro() {
		return dadosFiltro;
	}

	public void setDadosFiltro(StringBuilder dadosFiltro) {
		this.dadosFiltro = dadosFiltro;
	}

}
