package model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;
import org.hibernate.annotations.Type;

@NamedQueries({
		@NamedQuery(name = "findByLoginAndSenha", query = "select u from Usuario u where u.login = :login and u.senha = :senha"), })

@Entity
@Table(name = "usuario")
public class Usuario extends EntidadeGenerica implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4236639865845743885L;

	private String login;

	private String senha;

	private Boolean status = true;

	@Type(type = "true_false")
	@Column(name = "primeiro_acesso")
	private Boolean primeiroAcesso = true;

	@Enumerated(EnumType.STRING)
	@Column(name = "perfil_user")
	private PerfilUser perfilUser;

	@Column(name = "nome")
	private String nome;

	@Column(name = "cpf")
	private String cpf;

	@Column(name = "email")
	private String email;

	@Lob
	@Basic(fetch = FetchType.LAZY)
	private byte[] foto;

	@Column(name = "extensao_arquivo")
	private String extensao_foto;

	@ManyToOne
	private Loja loja;
	
	@Transient
	private boolean alterarSenha = false;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public PerfilUser getPerfilUser() {
		return perfilUser;
	}

	public void setPerfilUser(PerfilUser perfilUser) {
		this.perfilUser = perfilUser;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Boolean getPrimeiroAcesso() {
		return primeiroAcesso;
	}

	public void setPrimeiroAcesso(Boolean primeiroAcesso) {
		this.primeiroAcesso = primeiroAcesso;
	}

	public Loja getLoja() {
		return loja;
	}

	public void setLoja(Loja loja) {
		this.loja = loja;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
	
	public boolean isAlterarSenha() {
		return alterarSenha;
	}

	public void setAlterarSenha(boolean alterarSenha) {
		this.alterarSenha = alterarSenha;
	}

	@Transient
	public boolean isAdministrador() {
		return this.perfilUser.equals(PerfilUser.ADMINISTRADOR);
	}
	@Transient
	public boolean isFinanceiro() {
		return this.perfilUser.equals(PerfilUser.FINANCEIRO);
	}
	
	@Transient
	public boolean isVendedor() {
		return this.perfilUser.equals(PerfilUser.VENDEDOR);
	}
	
	@Transient
	public boolean isGerente() {
		return this.perfilUser.equals(PerfilUser.GERENTE);
	}
	
	@Transient
	public boolean notGerente() {
		return isAdministrador() || isFinanceiro() || isVendedor();
	}

	@Transient
	public boolean notAdministrador() {
		return isGerente() || isFinanceiro() || isVendedor();
	}
	
	

}
