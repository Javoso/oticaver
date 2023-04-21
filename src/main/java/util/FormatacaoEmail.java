package util;

public class FormatacaoEmail {


	public static String templateEmail(String titulo, String corpo) {

		String email = "	<html>\r\n" + 
				"<body>\r\n" + 
				"<div style=\"width: 600px; border: 2px solid #555;border-radius: 4px;\">\r\n" + 
				"<div style=\"background-color: #555;width: 100%; padding: 20px 0px; text-align:center; color: #fff;font-size:20px;\">GIP - Gestão Integrada de Pessoas</div>\r\n" + 
				"<div style=\"width: calc(100% - 80px);padding: 40px;\"><h4></h4>\r\n" + 
				"<div style=\"border: 1px solid #bbb;padding: 20px;\">\r\n" + 
				"<h3> "+titulo+" </h3>\r\n" + 
				"<br/>\r\n" + 
				corpo +"\r\n" + 
				"<br/>\r\n" + 
				"<p style=\"color:#CD0000;font-weight: bold;\">\r\n" + 
				"Obs: Este é um e-mail automático, por favor, não responda!  \r\n" + 
				"<p/>\r\n" + 
				"</div></div>\r\n" + 
				"\r\n" + 
				"</div>\r\n" + 
				"</body>		\r\n" + 
				"</html>";

		return email;
	}

//	public static String distribuicaoLiberada(ControleMetaLoja controle) {
//		String msg = "Prezado(a), <b>" + controle.getColaboradorGerente().getNome() 
//	    + "</b>, a dsitribuição de metas para os colaboradores esta liberada. " 
//		+ "<br/><br/>"
//		+ " <ul>"
//		+ " <li><b>Operação: <b/> "+controle.getLoja().getOperacao().getNome()+"</li>"
//		+ " <li><b>Loja: <b/> "+controle.getLoja().getNome()+"</li>"
//		+ " <li><b>Competência: <b/> "+controle.getCompetencia()+"</li>"
//		+ " </ul>";
//		return msg;
//	}
}
