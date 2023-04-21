package bo;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import br.hibernateutil.core.GenericDao;
import br.hibernateutil.exception.ObjetoNaoEncontradoHibernateException;
import br.hibernateutil.exception.SessaoNaoEncontradaParaEntidadeFornecidaException;
import br.hibernateutil.exception.ValidacaoHibernateException;
import br.hibernateutil.exception.ViolacaoChaveHibernateException;
import br.hibernateutil.paginacao.LazyEntityDataModel;
import dao.UsuarioDAO;
import exception.UsuarioExistenteException;
import model.PerfilUser;
import model.Usuario;
import util.CriptografiaMD5;

public class UsuarioBO {

	public static UsuarioBO getInstance() {
		return new UsuarioBO();
	}

	public void save(Usuario usuario) throws ViolacaoChaveHibernateException,
			ValidacaoHibernateException,
			SessaoNaoEncontradaParaEntidadeFornecidaException,
			UsuarioExistenteException {
		validation(usuario);
		GenericDao.save(usuario);
	}

	public void update(Usuario usuario) throws ViolacaoChaveHibernateException,
			ObjetoNaoEncontradoHibernateException, ValidacaoHibernateException,
			SessaoNaoEncontradaParaEntidadeFornecidaException,
			UsuarioExistenteException {
		validation(usuario);
		GenericDao.update(usuario);
	}

	public LazyEntityDataModel<Usuario> usuariosLazy(Usuario usuario) {
		return UsuarioDAO.getInstance().usuariosLazy(usuario);
	}

	public List<Usuario> usuarios(Usuario usuario)
			throws SessaoNaoEncontradaParaEntidadeFornecidaException {
		return UsuarioDAO.getInstance().usuarios(usuario);
	}

	public List<Usuario> list()
			throws SessaoNaoEncontradaParaEntidadeFornecidaException {
		return UsuarioDAO.getInstance().usuarios();
	}

	public StringBuilder dadosFiltro() {
		return UsuarioDAO.getInstance().getDadosFiltro();
	}

	public Usuario find(Integer id)
			throws ObjetoNaoEncontradoHibernateException,
			SessaoNaoEncontradaParaEntidadeFornecidaException {
		return GenericDao.findById(Usuario.class, id);
	}
	
	public void criarPrimeiroUsuario() {
		try {

			Usuario user = new Usuario();
			
			user.setNome("Usuário Administrador");
			user.setCpf("11111111111");
			user.setLogin("admin");
			user.setSenha(CriptografiaMD5.cifrar("admin"));
			user.setPerfilUser(PerfilUser.ADMINISTRADOR);
			GenericDao.save(user);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ViolacaoChaveHibernateException e) {
			e.printStackTrace();
		} catch (ValidacaoHibernateException e) {
			e.printStackTrace();
		} catch (SessaoNaoEncontradaParaEntidadeFornecidaException e) {
			e.printStackTrace();
		}
	}

	public void validation(Usuario usuario) throws UsuarioExistenteException,
			SessaoNaoEncontradaParaEntidadeFornecidaException {

		Usuario user = GenericDao.findByAttribute(Usuario.class, "login",
				usuario.getLogin());

		if (user != null) {
			if (!user.equals(usuario)) {
				throw new UsuarioExistenteException(
						"Já existe um usuário com esse login!");
			}
		}
	}
}
