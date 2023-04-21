package util;

import java.util.List;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

import bo.ConfiguracaoEmailBO;
import br.hibernateutil.exception.ObjetoNaoEncontradoHibernateException;
import br.hibernateutil.exception.SessaoNaoEncontradaParaEntidadeFornecidaException;
import model.ConfiguracaoEmail;


public class EmailUtil {

	public static HtmlEmail configEmail = new HtmlEmail();

	public static void enviarEmail(String assunto, String mensagem, List<String> listaDeEmails) {

		try {
			
			ConfiguracaoEmail config = ConfiguracaoEmailBO.getLast();
			
			configEmail.setHostName(config.getHostname());

			configEmail.setSmtpPort(config.getPort());

			configEmail.setFrom(config.getUsername(), config.getUsername());

			configEmail.setSubject(assunto);

			configEmail.setHtmlMsg(mensagem);

			configEmail.setSSL(config.getSsl());
			
			configEmail.setSubject(assunto);

			configEmail.setHtmlMsg(mensagem);

			configEmail.setSSL(true);
			
			configEmail.setAuthentication(config.getUsername(), config.getPassword());

			for (String email : listaDeEmails) {
				configEmail.addTo(email);
			}

			configEmail.send();
			System.out.println("E-mail enviado com sucesso!");
			configEmail = new HtmlEmail();
		} catch (EmailException e) {
			e.printStackTrace();
		} catch (ObjetoNaoEncontradoHibernateException e) {
			e.printStackTrace();
		} catch (SessaoNaoEncontradaParaEntidadeFornecidaException e) {
			e.printStackTrace();
		}
	}

}
