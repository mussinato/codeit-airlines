package com.mussinato.codeit.airlines.domain;

public class Piloto extends Pessoa {
	
	public Piloto(String nome) {
		super(nome);
	}

	@Override
	public boolean isMotorista() {
		return true;
	}
	
	@Override
	public boolean podeTransportar(Pessoa pessoa) {
		return pessoa instanceof Oficial || pessoa instanceof ChefeServico;
	}
}
