package com.mussinato.codeit.airlines.service;

import com.mussinato.codeit.airlines.domain.Aviao;
import com.mussinato.codeit.airlines.domain.Terminal;
import com.mussinato.codeit.airlines.domain.TransporteTerminal;

public class AeroportoService {
	private TransporteService transporteService = new TransporteService();
	
	public void transportarPessoasParaAviao(Terminal terminal, Aviao aviao) {
		TransporteTerminal veiculo = new TransporteTerminal();
		while (!terminal.getPessoas().isEmpty() || veiculo.getMotorista() != null) {
			if (veiculo.getMotorista() != null) {
				System.out.println("Motorista "+veiculo.getMotorista().getNome()+" voltando ao terminal");
			}
			
			veiculo = transporteService.buscarCarona(terminal, veiculo);
			
			System.out.println("Dirigindo ao avião");
			
			veiculo = transporteService.levarParaAviao(aviao, veiculo);
		}
		
	}
}
