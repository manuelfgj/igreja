package com.manuelfgj.igreja.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.manuelfgj.igreja.entities.Pessoa;

public class PessoaDTO  implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@NotEmpty(message = "Preechimento obrigatorio")
	@Length(min=5,max=80, message = "O tamanho deve ser entre 5 e 80 caracteres")
	private String nome;
	
	public PessoaDTO() {
	}

	public PessoaDTO(Pessoa obj) {
		this.id = obj.getId();
		this.nome = obj.getNome();
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
}
