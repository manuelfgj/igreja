package com.manuelfgj.igreja.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.manuelfgj.igreja.entities.enuns.EstadoCivil;
import com.manuelfgj.igreja.entities.enuns.Sexo;

@Entity
public class Pessoa implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	@Column(unique = true)
	private String cpf;
	@Column(unique = true)
	private String celular;
	@Column(unique = true)
	private String email;
	@JsonFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataNasc;
	private Integer sexo;
	private Integer estadoCivil;
	@JoinColumn(name="endereco_id")
	@OneToOne(cascade = CascadeType.ALL)
	private Endereco endereco;
	@JsonIgnore
	@JoinColumn(name="grupo_id")
	@OneToMany(cascade = CascadeType.ALL)
	private List<Grupo> grupos = new ArrayList<>();
	
	public Pessoa() {
	}

	public Pessoa(Integer id, String nome, String cpf, String celular, String email, Date dataNasc, Sexo sexo, EstadoCivil estadoCivil) {
		super();
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.celular = celular;
		this.email = email;
		this.dataNasc = dataNasc;
		this.sexo = (sexo == null) ? null : sexo.getCod();
		this.estadoCivil = (estadoCivil == null) ? null : estadoCivil.getCod();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Sexo getSexo() {
		return Sexo.toEnum(sexo);
	}

	public void setGenero(Sexo sexo) {
		this.sexo = sexo.getCod();
	}

	public EstadoCivil getEstadoCivil() {
		return EstadoCivil.toEnum(estadoCivil);
	}

	public void setEstadoCivil(EstadoCivil estadoCivil) {
		this.estadoCivil = estadoCivil.getCod();
	}
	
	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public List<Grupo> getGrupos() {
		return grupos;
	}

	public void setGrupos(List<Grupo> grupos) {
		this.grupos = grupos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pessoa other = (Pessoa) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
