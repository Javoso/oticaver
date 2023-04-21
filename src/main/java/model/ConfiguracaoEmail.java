package model;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;

import util.AES;

@Entity
@Table(name = "configuracao_email")
public class ConfiguracaoEmail extends EntidadeGenerica {

	private static final long serialVersionUID = 3005935148064984790L;

	@Column(name = "hostname")
	private String hostname; // smtp.gmail.com

	@Column(name = "porta")
	private Integer port; // 587

	@Column(name = "usa_ssl")
	@Type(type = "true_false")
	private Boolean ssl; // true

	@Column(name = "email")
	private String username; // EMAIL@SERVER.COM

	@Column(name = "senha")
	private String password; // SUASENHA

	// GETTERS AND SETTERS

	@Transient
	public boolean isNova() {
		return getId() == null;
	}

	@Transient
	public boolean isCadastrada() {
		return !isNova();
	}

	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	public Boolean getSsl() {
		return ssl;
	}

	public void setSsl(Boolean ssl) {
		this.ssl = ssl;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void criptografarSenha() {
		if (isNotBlank(password)) {
			password = new AES().codificar(password);
		}
	}
}
