package com.mussinato.codeit.airlines;

import java.util.List;

import com.mussinato.codeit.airlines.domain.Aviao;
import com.mussinato.codeit.airlines.domain.ChefeServico;
import com.mussinato.codeit.airlines.domain.Comissaria;
import com.mussinato.codeit.airlines.domain.Oficial;
import com.mussinato.codeit.airlines.domain.Pessoa;
import com.mussinato.codeit.airlines.domain.Piloto;
import com.mussinato.codeit.airlines.domain.Policial;
import com.mussinato.codeit.airlines.domain.Presidiario;
import com.mussinato.codeit.airlines.domain.Terminal;
import com.mussinato.codeit.airlines.service.AeroportoService;

public class Application {

	public static void main(String[] args) {
		List<Pessoa> pessoasNoTerminal = List.of(new Piloto("Piloto"),
				new Oficial("Oficial1"),
				new Oficial("Oficial2"),
				new ChefeServico("Chefe de Servi�o"),
				new Comissaria("Comiss�ria1"),
				new Comissaria("Comiss�ria2"),
				new Policial("Policial"),
				new Presidiario("Presidi�rio"));
		
		Terminal terminal = new Terminal(pessoasNoTerminal);
		Aviao aviao = new Aviao();
		
		new AeroportoService().transportarPessoasParaAviao(terminal, aviao);
	}

}
