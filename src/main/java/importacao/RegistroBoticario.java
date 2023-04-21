package importacao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import model.EntidadeGenerica;

public class RegistroBoticario extends EntidadeGenerica  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3438150222836033248L;

	private Integer titulo;
	private Integer parcela;
	private Integer numeroPedido;
	private Integer numeroNF;
	private Date dataEmissaoNf;
	private Date dataPedido;
	private BigDecimal valorPedido = new BigDecimal(0);
	
	public Integer getTitulo() {
		return titulo;
	}
	public void setTitulo(Integer titulo) {
		this.titulo = titulo;
	}
	public Integer getParcela() {
		return parcela;
	}
	public void setParcela(Integer parcela) {
		this.parcela = parcela;
	}
	public Integer getNumeroPedido() {
		return numeroPedido;
	}
	public void setNumeroPedido(Integer numeroPedido) {
		this.numeroPedido = numeroPedido;
	}
	public Integer getNumeroNF() {
		return numeroNF;
	}
	public void setNumeroNF(Integer numeroNF) {
		this.numeroNF = numeroNF;
	}
	public Date getDataEmissaoNf() {
		return dataEmissaoNf;
	}
	public void setDataEmissaoNf(Date dataEmissaoNf) {
		this.dataEmissaoNf = dataEmissaoNf;
	}
	public Date getDataPedido() {
		return dataPedido;
	}
	public void setDataPedido(Date dataPedido) {
		this.dataPedido = dataPedido;
	}
	public BigDecimal getValorPedido() {
		return valorPedido;
	}
	public void setValorPedido(BigDecimal valorPedido) {
		this.valorPedido = valorPedido;
	}
	@Override
	public String toString() {
		return "RegistroBoticario [titulo=" + titulo + ", parcela=" + parcela + ", numeroPedido=" + numeroPedido
				+ ", numeroNF=" + numeroNF + ", dataEmissaoNf=" + dataEmissaoNf + ", dataPedido=" + dataPedido
				+ ", valorPedido=" + valorPedido + "]";
	}
	
}