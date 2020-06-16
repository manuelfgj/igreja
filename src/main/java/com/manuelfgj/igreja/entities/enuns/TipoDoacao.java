package com.manuelfgj.igreja.entities.enuns;


public enum TipoDoacao {
	
	OFERTA(1, "Oferta"),
	DIZIMO(2,"Dízimo"),
	DOACAO(3, "Doação");
	
	private int cod;
	private String descricao;
	
	private TipoDoacao(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static TipoDoacao toEnum(Integer cod) {
	
		if(cod == null) {
			return null;
		}
		for(TipoDoacao c : TipoDoacao.values()) {
			if(cod.equals(c.getCod())) {
				return c;
			}
		}
		
		throw new IllegalArgumentException("Id inválido: "+ cod);
	}
}
