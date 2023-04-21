package mb;

import java.io.ByteArrayInputStream;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import dao.UsuarioDAO;

@ManagedBean
@RequestScoped
public class RecuperaImagemMB implements Serializable {

	private static final long serialVersionUID = 1L;

//	public StreamedContent getImage() {
//		FacesContext fc = FacesContext.getCurrentInstance();
//		if (fc.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
//			return new DefaultStreamedContent();
//		}
//		String ra = fc.getExternalContext().getRequestParameterMap().get("ra");
//		int tipo = Integer.valueOf(fc.getExternalContext().getRequestParameterMap().get("tipo"));
//		byte[] photoData = BuscaRegistrosDAO.getInstance().consultaPorParametros(ra, tipo);
//		return new DefaultStreamedContent(new ByteArrayInputStream(photoData));
//	}

//	public StreamedContent getImageColaborador() {
//		FacesContext fc = FacesContext.getCurrentInstance();
//		if (fc.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
//			return new DefaultStreamedContent();
//		}
//		String ra = fc.getExternalContext().getRequestParameterMap().get("ra");
//		byte[] photoData = BuscaRegistrosDAO.getInstance().consultaColaboradorPorMatricula(ra);
//		return new DefaultStreamedContent(new ByteArrayInputStream(photoData));
//	}
	
//	public StreamedContent getImageParceiro() {
//		FacesContext fc = FacesContext.getCurrentInstance();
//		if (fc.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
//			return new DefaultStreamedContent();
//		}
//
//		String cpf = fc.getExternalContext().getRequestParameterMap().get("cpf");
//		byte[] photoData = null;
//		photoData = BuscaRegistrosDAO.getInstance().consultaParceiroPorCpf(cpf).getFoto();
//		return new DefaultStreamedContent(new ByteArrayInputStream(photoData));
//	}
	
//	public StreamedContent getImageClienteRM() {
//		FacesContext fc = FacesContext.getCurrentInstance();
//		if (fc.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
//			return new DefaultStreamedContent();
//		}
//
//		String cpf = fc.getExternalContext().getRequestParameterMap().get("cpf");
//		byte[] photoData = null;
//		photoData = BuscaRegistrosDAO.getInstance().getImageCliente(cpf).getFoto();
//		return new DefaultStreamedContent(new ByteArrayInputStream(photoData));
//	}
//
//	public StreamedContent getImageClienteAcessoImg() {
//		FacesContext fc = FacesContext.getCurrentInstance();
//		if (fc.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
//			return new DefaultStreamedContent();
//		}
//
//		String cpf = fc.getExternalContext().getRequestParameterMap().get("cpf");
//		byte[] photoData = null;
//		ImagemClienteExterno aux = ImagemClienteDAO.getInstance().getByCpf(cpf);
//		if (aux != null) {
//			photoData = aux.getFoto();
//			return new DefaultStreamedContent(new ByteArrayInputStream(photoData));
//		}
//		return null;
//	}
	
	public StreamedContent getImage() {
		FacesContext fc = FacesContext.getCurrentInstance();
		if (fc.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
			return new DefaultStreamedContent();
		}
		Integer codImagem = Integer.valueOf(fc.getExternalContext().getRequestParameterMap().get("idUsuario"));
		byte[] photoData = UsuarioDAO.getInstance().getById(codImagem).getFoto();
		return new DefaultStreamedContent(new ByteArrayInputStream(photoData));
	}
	

}
