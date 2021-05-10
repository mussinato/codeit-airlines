package com.mussinato.codeit.airlines.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public abstract class Pessoa {
	
	@Getter
	private String nome = "Sem Nome";
	
	public boolean isMotorista() {
		return false;
	};
	
	public boolean podeTransportar(Pessoa pessoa){
		return false;
	}
	
	public boolean podeSerCarona(){
		return true;
	}
	
	public boolean isPolicial() {
		return this instanceof Policial;
	}
	
	public boolean podeFicarSozinha() {
		return true;
	}
}
