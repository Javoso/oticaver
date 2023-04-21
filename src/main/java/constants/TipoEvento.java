package constants;

public enum TipoEvento {

	/* TIPO DE EVENTO - CSS */
    PADRAO("Padrão", ""),
    URGENTE("Urgente", "urgente"),
    CANCELADO("Cancelado", "cancelado");

    private final String descricao;
    private final String css;

    private TipoEvento(String descricao, String css) {
        this.css = css;
        this.descricao = descricao;
    }

    public String getCss() {
        return css;
    }

    public String getDescricao() {
        return descricao;
    }
    
}
