package com.mussinato.codeit.airlines;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import com.mussinato.codeit.airlines.domain.ChefeServico;
import com.mussinato.codeit.airlines.domain.Comissaria;
import com.mussinato.codeit.airlines.domain.Oficial;
import com.mussinato.codeit.airlines.domain.Piloto;
import com.mussinato.codeit.airlines.domain.Policial;
import com.mussinato.codeit.airlines.domain.Presidiario;
import com.mussinato.codeit.airlines.domain.Terminal;
import com.mussinato.codeit.airlines.domain.TransporteTerminal;
import com.mussinato.codeit.airlines.service.TransporteService;

public class TransporteServiceTest {
	private TransporteService transporteService = new TransporteService();
	
	@Test
	public void buscarMotoristaECaronaComVeiculoVazio() {
		Piloto piloto = new Piloto("Piloto");
		Oficial oficial = new Oficial("Oficial");
		Comissaria comissaria = new Comissaria("Comissária");
		ChefeServico chefeServico = new ChefeServico("Chefe de Serviço");
		Policial policial = new Policial("Policial");
		Presidiario presidiario = new Presidiario("Presidiário");
		
		Terminal terminal = new Terminal(List.of(policial,chefeServico,piloto,oficial,comissaria,presidiario));
		TransporteTerminal veiculo = new TransporteTerminal();
		
		veiculo = transporteService.buscarCarona(terminal, veiculo);
		
		assertEquals(chefeServico, veiculo.getMotorista());
		assertEquals(comissaria, veiculo.getPassageiro());
		assertFalse(terminal.getPessoas().contains(chefeServico));
		assertFalse(terminal.getPessoas().contains(comissaria));
	}
	
	@Test
	public void buscarCaronaComMotorista() {
		Piloto piloto = new Piloto("Piloto");
		Oficial oficial = new Oficial("Oficial");
		Comissaria comissaria = new Comissaria("Comissária");
		ChefeServico chefeServico = new ChefeServico("Chefe de Serviço");
		Policial policial = new Policial("Policial");
		Presidiario presidiario = new Presidiario("Presidiário");
		
		Terminal terminal = new Terminal(List.of(piloto,oficial,comissaria,policial,presidiario));
		TransporteTerminal veiculo = new TransporteTerminal(chefeServico,null);
		
		veiculo = transporteService.buscarCarona(terminal, veiculo);
		
		assertEquals(piloto, veiculo.getMotorista());
		assertEquals(oficial, veiculo.getPassageiro());
		assertFalse(terminal.getPessoas().contains(piloto));
		assertFalse(terminal.getPessoas().contains(oficial));
		assertTrue(terminal.getPessoas().contains(chefeServico));
	}
	
	@Test
	public void buscarCaronaComTodosMotoristaNoTerminal() {
		Piloto piloto = new Piloto("Piloto");
		ChefeServico chefeServico = new ChefeServico("Chefe de Serviço");
		Policial policial = new Policial("Policial");
		Presidiario presidiario = new Presidiario("Presidiário");
		
		Terminal terminal = new Terminal(List.of(policial,presidiario,piloto));
		TransporteTerminal veiculo = new TransporteTerminal(chefeServico,null);
		
		veiculo = transporteService.buscarCarona(terminal, veiculo);
		
		assertEquals(piloto, veiculo.getMotorista());
		assertEquals(chefeServico, veiculo.getPassageiro());
		assertFalse(terminal.getPessoas().contains(piloto));
		assertFalse(terminal.getPessoas().contains(chefeServico));
		assertTrue(terminal.getPessoas().contains(policial));
	}
	
	@Test
	public void buscarCaronaComPolicialPresidiarioEOutroMotoristaNoTerminal() {
		ChefeServico chefeServico = new ChefeServico("Chefe de Serviço");
		Policial policial = new Policial("Policial");
		Presidiario presidiario = new Presidiario("Presidiário");
		
		Terminal terminal = new Terminal(List.of(policial,presidiario));
		TransporteTerminal veiculo = new TransporteTerminal(chefeServico,null);
		
		veiculo = transporteService.buscarCarona(terminal, veiculo);
		
		assertEquals(policial, veiculo.getMotorista());
		assertEquals(presidiario, veiculo.getPassageiro());
		assertFalse(terminal.getPessoas().contains(policial));
		assertFalse(terminal.getPessoas().contains(presidiario));
		assertTrue(terminal.getPessoas().contains(chefeServico));
	}
	
	@Test
	public void buscarCaronaComUmMotoristaNoTerminal() {
		Piloto piloto = new Piloto("Piloto");
		ChefeServico chefeServico = new ChefeServico("Chefe de Serviço");
		
		Terminal terminal = new Terminal(List.of(chefeServico));
		TransporteTerminal veiculo = new TransporteTerminal(piloto,null);
		
		veiculo = transporteService.buscarCarona(terminal, veiculo);
		
		assertEquals(chefeServico, veiculo.getMotorista());
		assertEquals(piloto, veiculo.getPassageiro());
		assertFalse(terminal.getPessoas().contains(chefeServico));
		assertFalse(terminal.getPessoas().contains(piloto));
		assertTrue(terminal.getPessoas().isEmpty());
	}
}
