package model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Type;

import constants.CategoriaGrupoProduto;

@Entity
@Table(name="grupo_produto")
public class GrupoProduto extends EntidadeGenerica  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3438150222836033248L;

	@Column(name = "status")
	@Type(type = "true_false")
	private Boolean status = true;

	@Column(name = "nome")
	private String nome;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "categoria_grupo")
	private CategoriaGrupoProduto categoriaGrupoProduto; 
	
	@Column(name = "observacao", columnDefinition = "text")
	private String observacao;
	
	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getNome() {
		if(nome == null) {
			nome = "";
		}
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public CategoriaGrupoProduto getCategoriaGrupoProduto() {
		return categoriaGrupoProduto;
	}

	public void setCategoriaGrupoProduto(CategoriaGrupoProduto categoriaGrupoProduto) {
		this.categoriaGrupoProduto = categoriaGrupoProduto;
	}
	
	

}