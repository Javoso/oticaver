package constants;

public enum CategoriaGrupoProduto {

	/* TIPO DE EVENTO - CSS */
	PRODUTO("Produto"),
    SERVICO("Serviço");

    private final String descricao;

    private CategoriaGrupoProduto(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
    
}
