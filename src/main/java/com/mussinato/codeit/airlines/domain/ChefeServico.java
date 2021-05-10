package com.mussinato.codeit.airlines.domain;

public class ChefeServico extends Pessoa {
	
	public ChefeServico(String nome) {
		super(nome);
	}
	
	@Override
	public boolean isMotorista() {
		return true;
	}
	
	@Override
	public boolean podeTransportar(Pessoa pessoa) {
		return pessoa instanceof Comissaria || pessoa instanceof Piloto;
	}
}
