package model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "produto")
public class Produto extends EntidadeGenerica implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3438150222836033248L;

	@Column(name = "status")
	@Type(type = "true_false")
	private Boolean status = true;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_cadastro")
	private Date dataCadastro;


	@Column(name = "nome")
	private String nome;

	@Column(name = "referencia")
	private String referencia;

	@Column(name = "codigo_gtin")
	private String codigoGTIN;
	
	@ManyToOne
	@JoinColumn(name = "unidade")
	private Unidade unidade;
	
	@ManyToOne
	@JoinColumn(name = "grupo_produto")
	private GrupoProduto grupoProduto;

	@ManyToOne
	@JoinColumn(name = "grife")
	private Grife grife;

	@ManyToOne
	@JoinColumn(name = "fornecedor")
	private Fornecedor fornecedor;

	@Column(name = "controlar_estoque")
	@Type(type = "true_false")
	private Boolean controlarEstoque = true;
	
	@Column(name = "venda_somente_os")
	@Type(type = "true_false")
	private Boolean vendaSomenteComOS = false;

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public String getCodigoGTIN() {
		return codigoGTIN;
	}

	public void setCodigoGTIN(String codigoGTIN) {
		this.codigoGTIN = codigoGTIN;
	}

	public Unidade getUnidade() {
		return unidade;
	}

	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}

	public GrupoProduto getGrupoProduto() {
		return grupoProduto;
	}

	public void setGrupoProduto(GrupoProduto grupoProduto) {
		this.grupoProduto = grupoProduto;
	}

	public Grife getGrife() {
		return grife;
	}

	public void setGrife(Grife grife) {
		this.grife = grife;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public Boolean getControlarEstoque() {
		return controlarEstoque;
	}

	public void setControlarEstoque(Boolean controlarEstoque) {
		this.controlarEstoque = controlarEstoque;
	}

	public Boolean getVendaSomenteComOS() {
		return vendaSomenteComOS;
	}

	public void setVendaSomenteComOS(Boolean vendaSomenteComOS) {
		this.vendaSomenteComOS = vendaSomenteComOS;
	}
	
}