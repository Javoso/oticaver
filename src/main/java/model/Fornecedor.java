package model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import constants.TipoPessoa;

@Entity
@Table(name="fornecedor")
public class Fornecedor extends EntidadeGenerica  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3438150222836033248L;

	@Column(name = "status")
	@Type(type = "true_false")
	private Boolean status = true;

	@Column(name = "laboratorio")
	@Type(type = "true_false")
	private Boolean laboratorio = false;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "tipo_fornecedor")
	private TipoPessoa tipoFornecedor;
	
	@Column(name = "documento")
	private String documento;

	@Column(name = "razao_social")
	private String nome;

	@Column(name = "nome_fantasia")
	private String nomeFantasia;

	@Column(name = "cep")
	private String cep;
	
	@Column(name = "endereco")
	private String endereco;

	@Column(name = "numero")
	private String numero;
	
	@Column(name = "complemento")
	private String complemento;
	
	@Column(name = "bairro")
	private String bairro;
	
	@Column(name = "cidade")
	private String cidade;
	
	@Column(name = "observacao", columnDefinition = "text")
	private String observacao;

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Boolean getLaboratorio() {
		return laboratorio;
	}

	public void setLaboratorio(Boolean laboratorio) {
		this.laboratorio = laboratorio;
	}

	public TipoPessoa getTipoFornecedor() {
		return tipoFornecedor;
	}

	public void setTipoFornecedor(TipoPessoa tipoFornecedor) {
		this.tipoFornecedor = tipoFornecedor;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	
	
	
}