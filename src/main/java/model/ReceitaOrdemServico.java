package model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import util.DataUtil;

@Entity
@Table(name = "receita_ordem_Servico")
public class ReceitaOrdemServico extends EntidadeGenerica implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3289000089333348830L;
	
	@ManyToOne
	private Medico medico;
	
	@Column(name = "observacao", columnDefinition = "text")
	private String observacao;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_cadastro")
	private Date dataCadastro = new Date();
	
	@Transient
	private String dataCadastroTitulo;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_validade")
	private Date dataValidade;

	@ManyToOne
	@JoinColumn(name = "ordem_servico_id")
	private OrdemServico ordemServico;
	
	// Atributos Medição
	// Longe
	@Column(name = "l_od_esferico", scale = 2, precision = 9)
	private BigDecimal longeOdEsferico;
	
	@Column(name = "l_od_cilindrico", scale = 2, precision = 9)
	private BigDecimal longeOdCilindrico;
	
	@Column(name = "l_od_eixo", scale = 2, precision = 9)
	private BigDecimal longeOdEixo;
	
	@Column(name = "l_od_altura", scale = 2, precision = 9)
	private BigDecimal longeOdAltura;
	
	@Column(name = "l_od_dnp", scale = 2, precision = 9)
	private BigDecimal longeOdDnp;

	@Column(name = "l_oe_esferico", scale = 2, precision = 9)
	private BigDecimal longeOeEsferico;
	
	@Column(name = "l_oe_cilindrico", scale = 2, precision = 9)
	private BigDecimal longeOeCilindrico;
	
	@Column(name = "l_oe_eixo", scale = 2, precision = 9)
	private BigDecimal longeOeEixo;
	
	@Column(name = "l_oe_altura", scale = 2, precision = 9)
	private BigDecimal longeOeAltura;
	
	@Column(name = "l_oe_dnp", scale = 2, precision = 9)
	private BigDecimal longeOeDnp;
	
	// Perto	
	@Column(name = "p_od_esferico", scale = 2, precision = 9)
	private BigDecimal pertoOdEsferico;
	
	@Column(name = "p_od_cilindrico", scale = 2, precision = 9)
	private BigDecimal pertoOdCilindrico;
	
	@Column(name = "p_od_eixo", scale = 2, precision = 9)
	private BigDecimal pertoOdEixo;
	
	@Column(name = "p_od_altura", scale = 2, precision = 9)
	private BigDecimal pertoOdAltura;
	
	@Column(name = "p_od_dnp", scale = 2, precision = 9)
	private BigDecimal pertoOdDnp;

	@Column(name = "p_oe_esferico", scale = 2, precision = 9)
	private BigDecimal pertoOeEsferico;
	
	@Column(name = "p_oe_cilindrico", scale = 2, precision = 9)
	private BigDecimal pertoOeCilindrico;
	
	@Column(name = "p_oe_eixo", scale = 2, precision = 9)
	private BigDecimal pertoOeEixo;
	
	@Column(name = "p_oe_altura", scale = 2, precision = 9)
	private BigDecimal pertoOeAltura;
	
	@Column(name = "p_oe_dnp", scale = 2, precision = 9)
	private BigDecimal pertoOeDnp;

	@Column(name = "adicao", scale = 2, precision = 9)
	private BigDecimal adicao = new BigDecimal(0);

	@Lob
	@Basic(fetch = FetchType.LAZY)
	private byte[] foto;

	@Column(name = "extensao_arquivo")
	private String extensao_foto;

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Date getDataValidade() {
		return dataValidade;
	}

	public void setDataValidade(Date dataValidade) {
		this.dataValidade = dataValidade;
	}

	public OrdemServico getOrdemServico() {
		return ordemServico;
	}

	public void setOrdemServico(OrdemServico ordemServico) {
		this.ordemServico = ordemServico;
	}

	public BigDecimal getLongeOdEsferico() {
		if(longeOdEsferico == null) {
			longeOdEsferico = new BigDecimal(0);
		}
		return longeOdEsferico;
	}

	public void setLongeOdEsferico(BigDecimal longeOdEsferico) {
		this.longeOdEsferico = longeOdEsferico;
	}

	public BigDecimal getLongeOdCilindrico() {
		if(longeOdCilindrico == null) {
			longeOdCilindrico = new BigDecimal(0);
		}
		return longeOdCilindrico;
	}

	public void setLongeOdCilindrico(BigDecimal longeOdCilindrico) {
		this.longeOdCilindrico = longeOdCilindrico;
	}

	public BigDecimal getLongeOdEixo() {
		if(longeOdEixo == null) {
			longeOdEixo = new BigDecimal(0);
		}
		return longeOdEixo;
	}

	public void setLongeOdEixo(BigDecimal longeOdEixo) {
		this.longeOdEixo = longeOdEixo;
	}

	public BigDecimal getLongeOdAltura() {
		return longeOdAltura;
	}

	public void setLongeOdAltura(BigDecimal longeOdAltura) {
		this.longeOdAltura = longeOdAltura;
	}

	public BigDecimal getLongeOdDnp() {
		return longeOdDnp;
	}

	public void setLongeOdDnp(BigDecimal longeOdDnp) {
		this.longeOdDnp = longeOdDnp;
	}

	public BigDecimal getLongeOeEsferico() {
		if(longeOeEsferico==null) {
			longeOeEsferico = new BigDecimal(0);
		}
		return longeOeEsferico;
	}

	public void setLongeOeEsferico(BigDecimal longeOeEsferico) {
		this.longeOeEsferico = longeOeEsferico;
	}

	public BigDecimal getLongeOeCilindrico() {
		return longeOeCilindrico;
	}

	public void setLongeOeCilindrico(BigDecimal longeOeCilindrico) {
		this.longeOeCilindrico = longeOeCilindrico;
	}

	public BigDecimal getLongeOeEixo() {
		return longeOeEixo;
	}

	public void setLongeOeEixo(BigDecimal longeOeEixo) {
		this.longeOeEixo = longeOeEixo;
	}

	public BigDecimal getLongeOeAltura() {
		return longeOeAltura;
	}

	public void setLongeOeAltura(BigDecimal longeOeAltura) {
		this.longeOeAltura = longeOeAltura;
	}

	public BigDecimal getLongeOeDnp() {
		return longeOeDnp;
	}

	public void setLongeOeDnp(BigDecimal longeOeDnp) {
		this.longeOeDnp = longeOeDnp;
	}

	public BigDecimal getPertoOdEsferico() {
		if(pertoOdEsferico == null) {
			pertoOdEsferico = new BigDecimal(0);
		}
		pertoOdEsferico = adicao.add(getLongeOdEsferico());
		return pertoOdEsferico;
	}

	public void setPertoOdEsferico(BigDecimal pertoOdEsferico) {
		this.pertoOdEsferico = pertoOdEsferico;
	}

	public BigDecimal getPertoOdCilindrico() {
		if(pertoOdCilindrico == null) {
			pertoOdCilindrico = new BigDecimal(0);
		}
		pertoOdCilindrico = getLongeOdCilindrico();
		return pertoOdCilindrico;
	}

	public void setPertoOdCilindrico(BigDecimal pertoOdCilindrico) {
		this.pertoOdCilindrico = pertoOdCilindrico;
	}

	public BigDecimal getPertoOdEixo() {
		if(pertoOdEixo == null) {
			pertoOdEixo = new BigDecimal(0);
		}
		pertoOdEixo = getLongeOdEixo();
		return pertoOdEixo;
	}

	public void setPertoOdEixo(BigDecimal pertoOdEixo) {
		this.pertoOdEixo = pertoOdEixo;
	}

	public BigDecimal getPertoOdAltura() {
		return pertoOdAltura;
	}

	public void setPertoOdAltura(BigDecimal pertoOdAltura) {
		this.pertoOdAltura = pertoOdAltura;
	}

	public BigDecimal getPertoOdDnp() {
		return pertoOdDnp;
	}

	public void setPertoOdDnp(BigDecimal pertoOdDnp) {
		this.pertoOdDnp = pertoOdDnp;
	}

	public BigDecimal getPertoOeEsferico() {
		if(pertoOeEsferico == null) {
			pertoOeEsferico = new BigDecimal(0);
		}
		pertoOeEsferico = adicao.add(getLongeOeEsferico());
		return pertoOeEsferico;
	}

	public void setPertoOeEsferico(BigDecimal pertoOeEsferico) {
		this.pertoOeEsferico = pertoOeEsferico;
	}

	public BigDecimal getPertoOeCilindrico() {
		if(pertoOeCilindrico == null) {
			pertoOeCilindrico = new BigDecimal(0);
		}
		pertoOeCilindrico = getLongeOeCilindrico();
		return pertoOeCilindrico;
	}

	public void setPertoOeCilindrico(BigDecimal pertoOeCilindrico) {
		this.pertoOeCilindrico = pertoOeCilindrico;
	}

	public BigDecimal getPertoOeEixo() {
		if(pertoOeEixo == null) {
			pertoOeEixo = new BigDecimal(0);
		}
		pertoOeEixo = getLongeOeEixo();
		return pertoOeEixo;
	}

	public void setPertoOeEixo(BigDecimal pertoOeEixo) {
		this.pertoOeEixo = pertoOeEixo;
	}

	public BigDecimal getPertoOeAltura() {
		return pertoOeAltura;
	}

	public void setPertoOeAltura(BigDecimal pertoOeAltura) {
		this.pertoOeAltura = pertoOeAltura;
	}

	public BigDecimal getPertoOeDnp() {
		return pertoOeDnp;
	}

	public void setPertoOeDnp(BigDecimal pertoOeDnp) {
		this.pertoOeDnp = pertoOeDnp;
	}

	public BigDecimal getAdicao() {
		return adicao;
	}

	public void setAdicao(BigDecimal adicao) {
		this.adicao = adicao;
	}

	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	public String getExtensao_foto() {
		return extensao_foto;
	}

	public void setExtensao_foto(String extensao_foto) {
		this.extensao_foto = extensao_foto;
	}

	public String getDataCadastroTitulo() {
		dataCadastroTitulo = DataUtil.formatarDataHora(getDataCadastro());
		return dataCadastroTitulo;
	}

	public void setDataCadastroTitulo(String dataCadastroTitulo) {
		this.dataCadastroTitulo = dataCadastroTitulo;
	}
	
	
	
}