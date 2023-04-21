package acesso;

import static util.Message.addWarnMessage;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.FileUploadEvent;

import model.Usuario;

@ManagedBean
@SessionScoped
public class EscopoSessaoBean implements Serializable {

	private static final long serialVersionUID = -1361342408456177486L;

	@ManagedProperty(value = "#{usuarioLogado}")
	private Usuario usuarioLogado;

	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	public String getPaginaInicial() {
		return "/private/dashboard/dashboard.xhtml?faces-redirect=true";
	}

	public void uploadFile(FileUploadEvent event) {
		try {
			this.usuarioLogado.setExtensao_foto(getExtensao(event.getFile().getFileName()));
			this.usuarioLogado.setFoto(event.getFile().getContents());
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

}