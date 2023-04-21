package constants;

public enum TipoPessoa {

	/* TIPO DE EVENTO - CSS */
	CPF("Cpf","Pessoa Física"),
    CNPJ("Cnpj","Pessoa Jurídica");

    private final String descricao;
    private final String tipoDescricao;

    private TipoPessoa(String descricao, String tipoDescricao) {
        this.descricao = descricao;
        this.tipoDescricao = tipoDescricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getTipoDescricao() {
		return tipoDescricao;
	}
}
