package constants;

public enum EtapaOrdemServico {

	/* TIPO DE EVENTO - CSS */
	CONFERENCIA("Conferencia"),
	DIGITACAO("Digitação"),
	EM_PRODUCAO("em Produção"),
	ENTREGUE("Entregue"),
	PRONTO("Pronto"),
    STANDY_BY("Standy By");

    private final String descricao;

    private EtapaOrdemServico(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

}
