package com.manuelfgj.igreja.entities.enuns;


public enum EstadoCivil {
	
	SOLTEIRO(1, "Solteiro"),
	CASADO(2,"Casado"),
	UNIAO_ESTAVEL(3, "União Estável"),
	VIUVO(4,"Viúvo"),
	DIVORCIADO(5, "Divorciado"),
	SEPARADO(6,"Separado");
	
	private int cod;
	private String descricao;
	
	private EstadoCivil(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static EstadoCivil toEnum(Integer cod) {
	
		if(cod == null) {
			return null;
		}
		for(EstadoCivil c : EstadoCivil.values()) {
			if(cod.equals(c.getCod())) {
				return c;
			}
		}
		
		throw new IllegalArgumentException("Id inválido: "+ cod);
	}
}
