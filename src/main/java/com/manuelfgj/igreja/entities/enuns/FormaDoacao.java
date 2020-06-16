package com.manuelfgj.igreja.entities.enuns;


public enum FormaDoacao {
	
	DINHEIRO(1, "Dinheiro"),
	BOLETO(2,"Boleto"),
	CARTAO_DEBITO(3, "Cartão de débito"),
	CARTAO_CREDITO(4,"Cartão de crédito"),
	TRANSFERENCIA(5, "Transferência"),
	CHEQUE(6,"Cheque");
	
	private int cod;
	private String descricao;
	
	private FormaDoacao(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static FormaDoacao toEnum(Integer cod) {
	
		if(cod == null) {
			return null;
		}
		for(FormaDoacao c : FormaDoacao.values()) {
			if(cod.equals(c.getCod())) {
				return c;
			}
		}
		
		throw new IllegalArgumentException("Id inválido: "+ cod);
	}
}
