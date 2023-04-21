package acesso;

import static util.Message.addErrorMessage;
import static util.Message.addInfoMessage;
import static util.Message.addWarnMessage;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import bo.LoginBO;
import bo.UsuarioBO;
import br.hibernateutil.exception.NumeroAtributosDiferenteNumeroValoresException;
import br.hibernateutil.exception.SessaoNaoEncontradaParaEntidadeFornecidaException;
import model.Usuario;
import util.CriptografiaMD5;
import util.ManagedBeanHelper;
import util.RecuperaMensagemComFlash;

@ManagedBean
public class LoginBean implements Serializable {

	private static final long serialVersionUID = -8351262285681938218L;

	private String user;
	private String pass;
	
	private static final String MUDAR_SENHA = "/private/cadastro/mudarSenha.xhtml?faces-redirect=true"; 

	public String login() {

		try {
						
			invalidaSessaoAtiva();
			
			if(UsuarioBO.getInstance().list().isEmpty()){
				UsuarioBO.getInstance().criarPrimeiroUsuario();	
			}
			
			Usuario usuario = LoginBO.getInstance().login(getUser(),
					CriptografiaMD5.cifrar(getPass()));

			if (usuario == null) {
				addErrorMessage("Login ou senha incorretos","Login e/ou senha incorretos");
				return "/public/login.xhtml";
			}
			if (usuario.getStatus() == false) {
				addWarnMessage("Este usuário foi desativado!",
						"Entre em contato com o administrador!");
				return "/public/login.xhtml?faces-redirect=true";
			}
	    	if (usuario.getPrimeiroAcesso()) {
	    		ManagedBeanHelper.recuperaBean("escopoSessaoBean",
						EscopoSessaoBean.class).setUsuarioLogado(usuario);
				RecuperaMensagemComFlash.flashScope().setKeepMessages(true);
	    		return "/private/cadastro/mudarSenha.xhtml?faces-redirect=true";
			}else {
				ManagedBeanHelper.recuperaBean("escopoSessaoBean",
						EscopoSessaoBean.class).setUsuarioLogado(usuario);
				addInfoMessage("BEM VINDO! " + usuario.getNome(), "");
				RecuperaMensagemComFlash.flashScope().setKeepMessages(true);
				return "/private/dashboard/dashboard.xhtml?faces-redirect=true";
			}
			
		} catch (SessaoNaoEncontradaParaEntidadeFornecidaException e) {
			addErrorMessage("", "Usuario não encontrados: "+e.getMessage());
			return "/public/login.xhtml";
		} catch (NumeroAtributosDiferenteNumeroValoresException e) {
			addErrorMessage("", "Usuario não encontrados: "+e.getMessage());
			e.printStackTrace();
			return "/public/login.xhtml?faces-redirect=true";
		} catch (NoSuchAlgorithmException e) {
			addErrorMessage("", "Usuario não encontrados: "+e.getMessage());
			e.printStackTrace();
			return "/public/login.xhtml?faces-redirect=true";
		} catch (UnsupportedEncodingException e) {
			addErrorMessage("", "Usuario não encontrados: "+e.getMessage());
			e.printStackTrace();
			return "/public/login.xhtml?faces-redirect=true";
		}

	}
	
	public String mudarSenha() {
		return MUDAR_SENHA;
	}

	public String logout() {
		RecuperaMensagemComFlash.flashScope().setKeepMessages(true);
		ManagedBeanHelper.destroiBean("escopoSessaoBean",
				EscopoSessaoBean.class);

		((HttpSession) FacesContext.getCurrentInstance().getExternalContext()
				.getSession(false)).invalidate();

		addInfoMessage("Logout!", "Sucesso ao sair do sistema!");

		return "/public/login.xhtml?faces-redirect=true";
	}

	private void invalidaSessaoAtiva() {
		if (((HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(false)) != null) {

			((HttpSession) FacesContext.getCurrentInstance()
					.getExternalContext().getSession(false)).invalidate();
		}
	}
	
	public void invalidaSessao() {
		invalidaSessaoAtiva();
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getUser() {
		return user;
	}
	
	public void setUser(String user) {
		this.user = user;
	}
	
}