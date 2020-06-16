package com.manuelfgj.igreja.entities.enuns;


public enum Sexo {
	
	MASCULINO(1, "Masculino"),
	FEMININO(2,"Feminino");
	
	private int cod;
	private String descricao;
	
	private Sexo(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static Sexo toEnum(Integer cod) {
	
		if(cod == null) {
			return null;
		}
		for(Sexo c : Sexo.values()) {
			if(cod.equals(c.getCod())) {
				return c;
			}
		}
		
		throw new IllegalArgumentException("Id inválido: "+ cod);
	}
}
