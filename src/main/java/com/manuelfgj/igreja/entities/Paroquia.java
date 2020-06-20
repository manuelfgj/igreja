package com.manuelfgj.igreja.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Paroquia implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	private String cnpj;
	private String padroeiro;
	private String telefone;
	@JoinColumn(name="endereco_id")
	@OneToOne(cascade = CascadeType.ALL)
	private Endereco endereco;
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "diocese_id")
	private Diocese diocese;
	@JsonIgnore
	@OneToMany(mappedBy = "paroquia")
	private List<Comunidade> comunidades = new ArrayList<>();	
	
	public Paroquia() {
	}

	public Paroquia(Integer id, String nome, String cnpj, String telefone, String padroeiro, Diocese diocese) {
		super();
		this.id = id;
		this.nome = nome;
		this.cnpj = cnpj;
		this.telefone = telefone;
		this.padroeiro = padroeiro;
		this.diocese = diocese;
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

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	
	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public String getPadroeiro() {
		return padroeiro;
	}

	public void setPadroeiro(String padroeiro) {
		this.padroeiro = padroeiro;
	}
	
	public Endereco getEndereco() {
		return endereco;
	}
	
//	public Set<String> getTelefones() {
//		return telefones;
//	}
//
//	public void setTelefones(Set<String> telefones) {
//		this.telefones = telefones;
//	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	public Diocese getDiocese() {
		return diocese;
	}

	public void setDiocese(Diocese diocese) {
		this.diocese = diocese;
	}

	public List<Comunidade> getComunidades() {
		return comunidades;
	}

	public void setComunidades(List<Comunidade> comunidades) {
		this.comunidades = comunidades;
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
		Paroquia other = (Paroquia) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}	
	
}
