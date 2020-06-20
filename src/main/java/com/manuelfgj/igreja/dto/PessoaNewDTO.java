package com.manuelfgj.igreja.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;

public class PessoaNewDTO  implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@NotEmpty(message = "Preechimento obrigatorio")
	@Length(min=5,max=80, message = "O tamanho deve ser entre 5 e 80 caracteres")
	private String nome;
	@CPF
	private String cpf;
	private String celular;
	@Email(message = "Email inválido")
	private String email;
	@JsonFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataNasc;
	private Integer sexo;
	private Integer estadoCivil;
	
	@NotEmpty(message = "Preechimento obrigatorio")
	private String logradouro;
	@NotEmpty(message = "Preechimento obrigatorio")
	private String numero;
	@NotEmpty(message = "Preechimento obrigatorio")
	private String bairro;
	private String complemento;
	@NotEmpty(message = "Preechimento obrigatorio")
	private String cep;
	private Integer cidadeId;
	
	private Integer comunidadeId;

	public PessoaNewDTO() {
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

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDataNasc() {
		return dataNasc;
	}

	public void setDataNasc(Date dataNasc) {
		this.dataNasc = dataNasc;
	}

	public Integer getSexo() {
		return sexo;
	}

	public void setSexo(Integer sexo) {
		this.sexo = sexo;
	}

	public Integer getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(Integer estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public Integer getCidadeId() {
		return cidadeId;
	}

	public void setCidadeId(Integer cidadeId) {
		this.cidadeId = cidadeId;
	}

	public Integer getComunidadeId() {
		return comunidadeId;
	}

	public void setComunidadeId(Integer comunidadeId) {
		this.comunidadeId = comunidadeId;
	}
	
}
