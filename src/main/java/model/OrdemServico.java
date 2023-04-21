package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Type;

import constants.StatusOrdemServico;
import constants.TipoOrdemServico;
import util.DataUtil;

@Entity
@Table(name = "ordem_servico")
public class OrdemServico extends EntidadeGenerica implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4983331588533261636L;

	@Column(name = "status")
	@Type(type = "true_false")
	private Boolean status = true;

	@Column(name = "numero_ordem_servico")
	private String numeroOrdemServico;

	@Column(name = "tipo_ordem_servico")
	@Enumerated(EnumType.STRING)
	private TipoOrdemServico tipoOrdemServico;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_abertura")
	private Date dataAbertura = new Date();

	@Column(name = "status_ordem_servico")
	@Enumerated(EnumType.STRING)
	private StatusOrdemServico statusOrdemServico;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "previsao_entrega")
	private Date previsaoEntrega = DataUtil.adicionarDias(3);

	@Column(name = "observacao", columnDefinition = "text")
	private String observacao;

	@ManyToOne
	private Loja loja;

	@ManyToOne
	private Colaborador colaborador;

	@ManyToOne
	private Cliente cliente;

	@OneToMany(mappedBy = "ordemServico", fetch = FetchType.LAZY)
	private List<ItemOrdemServico> itensOrdemServico = new ArrayList<ItemOrdemServico>();

	@OneToMany(mappedBy = "ordemServico", fetch = FetchType.LAZY)
	private List<ReceitaOrdemServico> receitasOrdemServico = new ArrayList<ReceitaOrdemServico>();
	
	@Column(name = "caixaGaveta")
	private String caixaGaveta;
	
	@Column(name = "local_montagem")
	private String localMontagem;
	
	@ManyToOne
	@JoinColumn(name = "laboratorio")
	private Loja laboratorio;
	
	@Column(name = "possui_receita")
	@Type(type = "true_false")
	private Boolean possuiReceita;
	
	@Column(name = "armacao_propria")
	@Type(type = "true_false")
	private Boolean armacaoPropria;
	
	@Column(name = "segue_armacao")
	@Type(type = "true_false")
	private Boolean segueArmacao;
	
	@Column(name = "tipo_lente")
	private String tipoLente;
	
	@Column(name = "material_lente")
	private String materialLente;
	
	@Column(name = "descricao_lente")
	private String descricaoLente;
	
	@Column(name = "coloracao_lente")
	private String coloracaoLente;
	
	

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getNumeroOrdemServico() {
		return numeroOrdemServico;
	}

	public void setNumeroOrdemServico(String numeroOrdemServico) {
		this.numeroOrdemServico = numeroOrdemServico;
	}

	public TipoOrdemServico getTipoOrdemServico() {
		return tipoOrdemServico;
	}

	public void setTipoOrdemServico(TipoOrdemServico tipoOrdemServico) {
		this.tipoOrdemServico = tipoOrdemServico;
	}

	public Date getDataAbertura() {
		return dataAbertura;
	}

	public void setDataAbertura(Date dataAbertura) {
		this.dataAbertura = dataAbertura;
	}

	public StatusOrdemServico getStatusOrdemServico() {
		return statusOrdemServico;
	}

	public void setStatusOrdemServico(StatusOrdemServico statusOrdemServico) {
		this.statusOrdemServico = statusOrdemServico;
	}

	public Date getPrevisaoEntrega() {
		return previsaoEntrega;
	}

	public void setPrevisaoEntrega(Date previsaoEntrega) {
		this.previsaoEntrega = previsaoEntrega;
	}

	public Loja getLoja() {
		return loja;
	}

	public void setLoja(Loja loja) {
		this.loja = loja;
	}

	public Colaborador getColaborador() {
		return colaborador;
	}

	public void setColaborador(Colaborador colaborador) {
		this.colaborador = colaborador;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public List<ItemOrdemServico> getItensOrdemServico() {
		return itensOrdemServico;
	}

	public void setItensOrdemServico(List<ItemOrdemServico> itensOrdemServico) {
		this.itensOrdemServico = itensOrdemServico;
	}
	
	public List<ReceitaOrdemServico> getReceitasOrdemServico() {
		return receitasOrdemServico;
	}
	
	public void setReceitasOrdemServico(List<ReceitaOrdemServico> receitasOrdemServico) {
		this.receitasOrdemServico = receitasOrdemServico;
	}

	public String getCaixaGaveta() {
		return caixaGaveta;
	}

	public void setCaixaGaveta(String caixaGaveta) {
		this.caixaGaveta = caixaGaveta;
	}

	public String getLocalMontagem() {
		return localMontagem;
	}

	public void setLocalMontagem(String localMontagem) {
		this.localMontagem = localMontagem;
	}

	public Loja getLaboratorio() {
		return laboratorio;
	}

	public void setLaboratorio(Loja laboratorio) {
		this.laboratorio = laboratorio;
	}

	public Boolean getPossuiReceita() {
		return possuiReceita;
	}

	public void setPossuiReceita(Boolean possuiReceita) {
		this.possuiReceita = possuiReceita;
	}

	public Boolean getArmacaoPropria() {
		return armacaoPropria;
	}

	public void setArmacaoPropria(Boolean armacaoPropria) {
		this.armacaoPropria = armacaoPropria;
	}

	public Boolean getSegueArmacao() {
		return segueArmacao;
	}

	public void setSegueArmacao(Boolean segueArmacao) {
		this.segueArmacao = segueArmacao;
	}

	public String getTipoLente() {
		return tipoLente;
	}

	public void setTipoLente(String tipoLente) {
		this.tipoLente = tipoLente;
	}

	public String getMaterialLente() {
		return materialLente;
	}

	public void setMaterialLente(String materialLente) {
		this.materialLente = materialLente;
	}

	public String getDescricaoLente() {
		return descricaoLente;
	}

	public void setDescricaoLente(String descricaoLente) {
		this.descricaoLente = descricaoLente;
	}

	public String getColoracaoLente() {
		return coloracaoLente;
	}

	public void setColoracaoLente(String coloracaoLente) {
		this.coloracaoLente = coloracaoLente;
	}

}
