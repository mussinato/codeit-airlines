package com.mussinato.codeit.airlines.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class TransporteTerminal {
	private Pessoa motorista;
	private Pessoa passageiro;
}
