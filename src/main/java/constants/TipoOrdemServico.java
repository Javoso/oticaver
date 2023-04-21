package constants;

public enum TipoOrdemServico {

	/* TIPO DE EVENTO - CSS */
    REDE_MASTER("Rede Master");

    private final String descricao;

    private TipoOrdemServico(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

}
