package constants;

public enum StatusOrdemServico {

	/* TIPO DE EVENTO - CSS */
    ABERTA("Aberta"),
    CANCELADA("Cancelada"),
    PERDA("Perda"),
    ENTREGUE("Entregue"),
    VENDIDA("Vendida"),
	NAO_VENDIDA("Não Vendida");

    private final String descricao;

    private StatusOrdemServico(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

}
