package phaselistener;

import static util.Message.addErrorMessage;

import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpSession;

import acesso.EscopoSessaoBean;
import model.Usuario;
import util.ManagedBeanHelper;

public class LoginPhaseListener implements PhaseListener {

	private static final long serialVersionUID = 8403402696846718508L;

	/**
	 * Verifica se o usuario esta logado no sistema.
	 * 
	 * @param event
	 *            {@link PhaseEvent}
	 * @see PhaseListener#beforePhase(PhaseEvent)
	 */
	public void afterPhase(PhaseEvent event) {

		if (event.getFacesContext().getViewRoot().getViewId().startsWith("/public/login.xhtml.")) {
			HttpSession httpSession = (HttpSession) event.getFacesContext().getExternalContext().getSession(false);
			addErrorMessage("Atenção!", "O acesso foi expirado por conta de inatividade no sistema!");

			if (httpSession != null) {
				httpSession.invalidate();
			}
			navigateToView(event.getFacesContext(), "/public/login.xhtml");

		} else if (!event.getFacesContext().getViewRoot().getViewId().contains("/public") && ManagedBeanHelper
				.recuperaBean("escopoSessaoBean", EscopoSessaoBean.class).getUsuarioLogado() == null) {

			navigateToView(event.getFacesContext(), "/public/login.xhtml");
		}
	}

	/**
	 * Navega para um view.
	 * 
	 * @param context
	 *            Faces Context atrelado a requisizi��o atual.
	 * @param view
	 *            outcome para qual se deseja navegar
	 */
	private void navigateToView(FacesContext context, String view) {
		context.getApplication().getNavigationHandler().handleNavigation(context, null, view);
	}

	/**
	 * Metodo da interface PhaseListener. Nao utilizado.
	 * 
	 * @param event
	 *            {@link PhaseEvent}
	 * @see PhaseListener#beforePhase(PhaseEvent)
	 */
	public void beforePhase(PhaseEvent event) {
		Usuario usuarioLogado = ManagedBeanHelper.recuperaBean("escopoSessaoBean", EscopoSessaoBean.class)
				.getUsuarioLogado();

		if (usuarioLogado != null) {

			if (usuarioLogado.getPrimeiroAcesso() && usuarioLogado.getId() != null
					&& event.getPhaseId() == PhaseId.RENDER_RESPONSE) {
				navigateToView(FacesContext.getCurrentInstance(), "/private/cadastro/mudarSenha.xhtml");
			}
		}
	}

	/**
	 * A que fase queremos "escutar". No caso {@link PhaseId#RESTORE_VIEW}.
	 * 
	 * @return {@link PhaseId#RESTORE_VIEW}
	 * @see PhaseListener#getPhaseId()
	 */
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}
}
