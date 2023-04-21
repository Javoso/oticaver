package model;

public enum PerfilUser {

	ADMINISTRADOR("Administrador"),
	GERENTE("Gerente"),
	FINANCEIRO("Financeiro"),
	VENDEDOR("Vendedor"),
	;

	PerfilUser(String descricao) {
		this.descricao = descricao;
	}

	private String descricao;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
