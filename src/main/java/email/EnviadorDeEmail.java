package email;

import java.io.File;
import java.util.List;

import org.apache.commons.mail.HtmlEmail;

import model.ConfiguracaoEmail;

public class EnviadorDeEmail {

	private static final String REMETENTE = "GIP - Gestão Integrada de Pessoas";
	
	private ModuloEmail modulo;

	public EnviadorDeEmail(ConfiguracaoEmail configuracaoEmail) {
		modulo = new ModuloEmail(new HtmlEmail(), configuracaoEmail);
	}

	public void enviar(Email email) {
		if (email.semDestinatario())
			throw new IllegalStateException("Selecione pelo menos um destinatário.");

		modulo.enviarEmail(email.getAssunto(), EmailTemplate.mensagem(email.getMensagem()), REMETENTE,
				email.getEmails(), true);
	}

	public void mensagem(String assunto, String mensagem, File arquivo, List<String> destinatarios) {
		modulo.enviarEmail(assunto, EmailTemplate.mensagem(mensagem), arquivo, "GIP Villarouca", destinatarios);
	}
}