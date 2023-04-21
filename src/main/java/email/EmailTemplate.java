package email;

public class EmailTemplate {

	private static final String LOGO = "";
	private static final String CONTEXT = "http://localhost:8080/gip/public/login.xhtml"; // ex.: sysfisio.fcrs.edu.br
	
	private EmailTemplate() { }
	
	
	public static String mensagem(String conteudo) {
		return pageEmail(conteudo);
	}
	
	private static String pageEmail(String conteudo) {
		StringBuilder page = new StringBuilder();
		page.append("<head>").append("<style>" + getCSS() + "</style>").append("</head>")
				.append("<div class=\"container100\">").append("<div class=\"container\">")
				.append("<div class=\"logo\"><img src=\"" + LOGO + "\"/><div>").append("<div class=\"card\">")
				.append("<div class=\"title\">GIP - Gestão Integrada de Pessoas").append("</div>")
				.append("<div class=\"content\">" + conteudo).append("<br/><br/>Link: ")
				.append("<a href='http://" + CONTEXT + "' target=\"_blank\">").append("Acessar GIP")
				.append("</a>").append("</div>") // Fecha div content
				
				.append("<div class=\"footer\">")
				
				.append("<p>Rua Epitácio Pessoa, 122</p>")
				.append("<p>Centro, Iguatu/CE, 63500-044</p>")
				.append("<p>Fone: 0800 2808525 </p>")
				.append("</div></div></div></div>");

		return page.toString();
	}

	private static String getCSS() {
		StringBuilder css = new StringBuilder();
		css.append("p { margin: 0px }");
		css.append("a { text-decoration: none; color: #4285f4; }");
		css.append("a:hover { text-decoration: underline; }");
		css.append(".container100 { padding: 15px; background: #f2f2f2; }");
		css.append(".container { width: 80%; margin: 20px auto; }");
		css.append(".logo { text-align: center }");
		css.append(".card { background: #fff; margin-top: 20px; border-top: solid 5px #074866; border-radius: 3px; }");
		css.append(".title { color: #263238; margin: 0px 20px; font-size: 20px; border-bottom: solid 1px #ccc; padding: 15px; }");
		css.append(".content { font-size: 14px; color: #263238; text-align: left; padding: 20px; }");
		css.append(".footer { padding: 10px; font-size: 12px; color: #FFF; background: #555; }");
		css.append("@media (max-width: 640px) { .container { width: 95%; }}");

		return css.toString();
	}
}