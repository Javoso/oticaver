package mb;

import static util.Message.addErrorMessage;
import static util.Message.addWarnMessage;
import static util.Message.setErrorMessage;
import static util.Message.setInfoMessage;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.FileUploadEvent;

import bo.UsuarioBO;
import br.hibernateutil.exception.ObjetoNaoEncontradoHibernateException;
import br.hibernateutil.exception.SessaoNaoEncontradaParaEntidadeFornecidaException;
import br.hibernateutil.exception.ValidacaoHibernateException;
import br.hibernateutil.exception.ViolacaoChaveHibernateException;
import br.hibernateutil.paginacao.LazyEntityDataModel;
import exception.UsuarioExistenteException;
import model.PerfilUser;
import model.Usuario;
import report.GenericReport;
import util.AfterRedirect;
import util.CriptografiaMD5;
import util.RecuperarObjetoViaRequisicao;

@ManagedBean
@ViewScoped
public class UsuarioMB implements Serializable {

	private static final long serialVersionUID = 8197346996386546555L;

	private final String CAD_EDIT = "/private/cadastro/cadastrarUsuario.xhtml";
	private final String LIST = "/private/lista/listarUsuario.xhtml?faces-redirect=true";
	private final String INICIO = "/private/dashboard/dashboard.xhtml?faces-redirect=true";

	private Usuario usuario = new Usuario();
	private Usuario usuarioFilter;
	private LazyEntityDataModel<Usuario> lazy;
	private List<Usuario> usuarios;

	@PostConstruct
	public void init() {

		Usuario aux = RecuperarObjetoViaRequisicao.getObjeto(Usuario.class, "id");
		usuario = aux != null ? aux : new Usuario();

		usuarioFilter = new Usuario();
		lazy = new LazyEntityDataModel<Usuario>(Usuario.class);
		usuarios = new ArrayList<Usuario>();
	}

	public String save() {
		try {
			usuario.setSenha(CriptografiaMD5.cifrar(usuario.getSenha()));
			UsuarioBO.getInstance().save(usuario);
			setInfoMessage("Cadastrado com sucesso!", "Usuario " + usuario.getNome());
		} catch (ViolacaoChaveHibernateException e) {
			addErrorMessage("Formulario", "erro.salvar.entidade.campo.existente", "");
			e.printStackTrace();
		} catch (ValidacaoHibernateException e) {
			setErrorMessage("Erro!", e.getMessage());
			e.printStackTrace();
		} catch (SessaoNaoEncontradaParaEntidadeFornecidaException e) {
			setErrorMessage("Erro!", e.getMessage());
			e.printStackTrace();
		} catch (UsuarioExistenteException e) {
			setErrorMessage("Erro!", e.getMessage());
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		usuario = new Usuario();
		AfterRedirect.manterMensagem();
		return LIST;
	}

	public String update() {
		try {
			if (usuario.isAlterarSenha()) {
				usuario.setSenha(CriptografiaMD5.cifrar(usuario.getSenha()));
			}
			UsuarioBO.getInstance().update(usuario);
			setInfoMessage("Atualizado com sucesso!", "Usuario " + usuario.getNome());
		} catch (ViolacaoChaveHibernateException e) {
			addErrorMessage("Formulario", "erro.salvar.entidade.campo.existente", "");
			e.printStackTrace();
		} catch (ObjetoNaoEncontradoHibernateException e) {
			setErrorMessage("Erro!", e.getMessage());
			e.printStackTrace();
		} catch (ValidacaoHibernateException e) {
			setErrorMessage("Erro!", e.getMessage());
			e.printStackTrace();
		} catch (SessaoNaoEncontradaParaEntidadeFornecidaException e) {
			setErrorMessage("Erro!", e.getMessage());
			e.printStackTrace();
		} catch (UsuarioExistenteException e) {
			setErrorMessage("Erro!", e.getMessage());
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			setErrorMessage("Erro!", e.getMessage());
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			setErrorMessage("Erro!", e.getMessage());
			e.printStackTrace();
		}
		usuario = new Usuario();
		AfterRedirect.manterMensagem();
		return LIST;
	}

	public String updateSenha() {
		try {
			usuario.setSenha(CriptografiaMD5.cifrar(usuario.getSenha()));
			usuario.setPrimeiroAcesso(false);
			UsuarioBO.getInstance().update(usuario);
			setInfoMessage("Atualizado com sucesso!", "Usuario " + usuario.getNome());
		} catch (ViolacaoChaveHibernateException e) {
			addErrorMessage("Formulario", "erro.salvar.entidade.campo.existente", "");
			e.printStackTrace();
		} catch (ObjetoNaoEncontradoHibernateException e) {
			setErrorMessage("Erro!", e.getMessage());
			e.printStackTrace();
		} catch (ValidacaoHibernateException e) {
			setErrorMessage("Erro!", e.getMessage());
			e.printStackTrace();
		} catch (SessaoNaoEncontradaParaEntidadeFornecidaException e) {
			setErrorMessage("Erro!", e.getMessage());
			e.printStackTrace();
		} catch (UsuarioExistenteException e) {
			setErrorMessage("Erro!", e.getMessage());
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			setErrorMessage("Erro!", e.getMessage());
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			setErrorMessage("Erro!", e.getMessage());
			e.printStackTrace();
		}
		usuario = new Usuario();
		AfterRedirect.manterMensagem();
		return INICIO;
	}

	public String updateStatus() {
		try {

			if (usuario.getStatus()) {
				usuario.setStatus(false);
				UsuarioBO.getInstance().update(usuario);
			} else {
				usuario.setStatus(true);
				UsuarioBO.getInstance().update(usuario);
			}

		} catch (ViolacaoChaveHibernateException e) {
			setErrorMessage("Erro!", e.getMessage());
			e.printStackTrace();
		} catch (ObjetoNaoEncontradoHibernateException e) {
			setErrorMessage("Erro!", e.getMessage());
			e.printStackTrace();
		} catch (ValidacaoHibernateException e) {
			setErrorMessage("Erro!", e.getMessage());
			e.printStackTrace();
		} catch (SessaoNaoEncontradaParaEntidadeFornecidaException e) {
			setErrorMessage("Erro!", e.getMessage());
			e.printStackTrace();
		} catch (UsuarioExistenteException e) {
			setErrorMessage("Erro!", e.getMessage());
			e.printStackTrace();
		}

		String status = usuario.getStatus() ? "Ativo" : "Inativo";

		setInfoMessage("Status alterado com sucesso!", "A usuario est�: " + status);

		return null;
	}

	public void uploadFile(FileUploadEvent event) {
		try {
			this.usuario.setExtensao_foto(getExtensao(event.getFile().getFileName()));
			this.usuario.setFoto(event.getFile().getContents());
			FacesMessage message = new FacesMessage("Enviado com sucesso!", event.getFile().getFileName());
			FacesContext.getCurrentInstance().addMessage(null, message);
		} catch (Exception e) {
			e.printStackTrace();
			addWarnMessage("Erro: " + e.getMessage(), "");
		}
	}

	public String getExtensao(String nomeArquivo) {
		if (nomeArquivo.isEmpty() || nomeArquivo == null)
			return null;
		return nomeArquivo.substring(nomeArquivo.lastIndexOf("."), nomeArquivo.length()).toLowerCase();
	}

	public String gerarPDF() {

		Map<String, Object> mapa = new HashMap<String, Object>();

		try {
			List<Usuario> usuarios = UsuarioBO.getInstance().usuarios(usuarioFilter);
			// mapa.put("logo",
			// BuscaNoWebContent.buscaArquivo("/resources/images/amigos-full.jpg"));
			// mapa.put("filtro", UsuarioBO.getInstance().dadosFiltro());

			GenericReport.gerarPdfComJRBeanCollectionDataSource(mapa, usuarios, "usuarios", "Relatório de Usuários",
					true);
		} catch (SessaoNaoEncontradaParaEntidadeFornecidaException e) {
			e.printStackTrace();
		}
		return null;
	}

	public PerfilUser[] getPerfis() {
		return PerfilUser.values();
	}

	public String prepareUpdateUserLogado() {
		return "/private/cadastro/mudarSenha.xhtml";
	}

	public void clearUsuario() {
		usuario = new Usuario();
	}

	public String goToList() {
		return LIST;
	}

	public String prepareUpdate() {
		return CAD_EDIT;
	}

	public String prepareSave() {
		usuario = new Usuario();
		return CAD_EDIT;
	}

	public String list() {
		lazy = UsuarioBO.getInstance().usuariosLazy(usuarioFilter);
		return null;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public LazyEntityDataModel<Usuario> getLazy() {
		return lazy;
	}

	public void setLazy(LazyEntityDataModel<Usuario> lazy) {
		this.lazy = lazy;
	}

	public List<Usuario> getUsuarios() {
		try {
			usuarios = UsuarioBO.getInstance().list();
		} catch (SessaoNaoEncontradaParaEntidadeFornecidaException e) {
			e.printStackTrace();
		}
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Usuario getUsuarioFilter() {
		return usuarioFilter;
	}

	public void setUsuarioFilter(Usuario usuarioFilter) {
		this.usuarioFilter = usuarioFilter;
	}

}
