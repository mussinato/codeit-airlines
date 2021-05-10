package com.mussinato.codeit.airlines.domain;

public class Policial extends Pessoa {
	
	public Policial(String nome) {
		super(nome);
	}
	
	@Override
	public boolean isMotorista() {
		return true;
	}
	
	@Override
	public boolean podeTransportar(Pessoa pessoa) {
		return pessoa instanceof Presidiario;
	}
	
	@Override
	public boolean podeSerCarona() {
		return false;
	}
}
