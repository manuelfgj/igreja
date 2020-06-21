package com.manuelfgj.igreja.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.manuelfgj.igreja.entities.Doacao;
import com.manuelfgj.igreja.entities.enuns.FormaDoacao;
import com.manuelfgj.igreja.entities.enuns.TipoDoacao;

public class DoacaoDTO  implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Double valor;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	@Temporal(TemporalType.TIMESTAMP)
	private Date data;
	
	private Integer tipo;	
	private Integer forma;
	
	
	public DoacaoDTO() {
	}

	public DoacaoDTO(Doacao obj) {
		this.id = obj.getId();
		this.data = obj.getData();
		this.tipo = (obj.getTipo() == null) ? null : obj.getTipo().getCod();
		this.forma = (obj.getForma() == null) ? null : obj.getForma().getCod();
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
}
