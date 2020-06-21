package com.manuelfgj.igreja.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.manuelfgj.igreja.entities.enuns.FormaDoacao;
import com.manuelfgj.igreja.entities.enuns.TipoDoacao;

@Entity
public class Doacao implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private Double valor;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	@Temporal(TemporalType.TIMESTAMP)
	private Date data;
	
	private Integer tipo;
	
	private Integer forma;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="pessoa_id")
	private Pessoa pessoa;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="comunidade_id")
	private Comunidade comunidade;

	public Doacao() {
	}

	public Doacao(Integer id, Double valor, Date data, TipoDoacao tipo, FormaDoacao forma, Pessoa pessoa, Comunidade comunidade) {
		super();
		this.id = id;
		this.valor = valor;
		this.data = data;
		this.tipo = (tipo == null) ? null : tipo.getCod();
		this.forma = (forma == null) ? null : forma.getCod();
		this.pessoa = pessoa;
		this.comunidade = comunidade;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public TipoDoacao getTipo() {
		return TipoDoacao.toEnum(tipo);
	}

	public void setTipo(TipoDoacao tipo) {
		this.tipo = tipo.getCod();
	}

	public FormaDoacao getForma() {
		return FormaDoacao.toEnum(forma);
	}

	public void setForma(FormaDoacao forma) {
		this.forma = forma.getCod();
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Comunidade getComunidade() {
		return comunidade;
	}

	public void setComunidade(Comunidade comunidade) {
		this.comunidade = comunidade;
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
		Doacao other = (Doacao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
