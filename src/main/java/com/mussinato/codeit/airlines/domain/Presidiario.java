package com.mussinato.codeit.airlines.domain;

public class Presidiario extends Pessoa {
	
	public Presidiario(String nome) {
		super(nome);
	}

	@Override
	public boolean podeFicarSozinha() {
		return false;
	}
}
