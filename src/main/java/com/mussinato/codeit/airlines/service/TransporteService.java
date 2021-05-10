package com.mussinato.codeit.airlines.service;

import static java.util.function.Predicate.not;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;
import java.util.stream.Collectors;

import com.mussinato.codeit.airlines.domain.Aviao;
import com.mussinato.codeit.airlines.domain.Pessoa;
import com.mussinato.codeit.airlines.domain.Terminal;
import com.mussinato.codeit.airlines.domain.TransporteTerminal;

public class TransporteService {
	
	public ListIterator<Pessoa> buscarMotoristas(List<Pessoa> pessoasNoTerminal){
		return pessoasNoTerminal.stream()
				.filter(Pessoa::isMotorista)
				.filter(not(Pessoa::isPolicial))
				.collect(Collectors.toList())
				.listIterator();
	}
	
	public List<Pessoa> buscarCandidatosCarona(List<Pessoa> pessoasNoTerminal, Pessoa motorista){
		return pessoasNoTerminal.stream()
				.filter(Pessoa::podeSerCarona)
				.filter(motorista::podeTransportar)
				.collect(Collectors.toList());
	}
	
	public TransporteTerminal buscarCarona(Terminal terminal, TransporteTerminal veiculo) {
		List<Pessoa> pessoasNoTerminal = new ArrayList<Pessoa>(terminal.getPessoas());

		if (veiculo.getMotorista() != null) {
			System.out.println("Motorista " + veiculo.getMotorista().getNome() + " entrou no terminal");
			pessoasNoTerminal.add(veiculo.getMotorista());
			veiculo.setMotorista(null);
		}

		// transportar pessoas que n�o s�o motoristas e n�o s�o policial/presidi�rio
		ListIterator<Pessoa> motoristasMenosPolicialNoTerminal = buscarMotoristas(pessoasNoTerminal);

		while (motoristasMenosPolicialNoTerminal.hasNext()) {
			Pessoa proximoMotorista = motoristasMenosPolicialNoTerminal.next();

			Optional<Pessoa> proximoCarona = buscarCandidatosCarona(pessoasNoTerminal, proximoMotorista).stream()
					.filter(not(Pessoa::isMotorista))
					.findAny();

			if (proximoCarona.isPresent()) {
				System.out.println("O pr�ximo motorista � " + proximoMotorista.getNome());
				System.out.println("A pr�xima carona � " + proximoCarona.get().getNome());

				veiculo.setMotorista(proximoMotorista);
				veiculo.setPassageiro(proximoCarona.get());

				pessoasNoTerminal.remove(proximoMotorista);
				pessoasNoTerminal.remove(proximoCarona.get());
				terminal.setPessoas(pessoasNoTerminal);
				return veiculo;
			}
		}

		// transportar pessoas que s�o motoristas e n�o s�o policial/presidi�rio
		motoristasMenosPolicialNoTerminal = buscarMotoristas(pessoasNoTerminal);

		while (motoristasMenosPolicialNoTerminal.hasNext()) {
			Pessoa proximoMotorista = motoristasMenosPolicialNoTerminal.next();

			Optional<Pessoa> proximoCarona = buscarCandidatosCarona(pessoasNoTerminal, proximoMotorista).stream().findAny();

			if (proximoCarona.isPresent()) {
				System.out.println("O pr�ximo motorista � " + proximoMotorista.getNome());
				System.out.println("A pr�xima carona � " + proximoCarona.get().getNome());

				veiculo.setMotorista(proximoMotorista);
				veiculo.setPassageiro(proximoCarona.get());

				pessoasNoTerminal.remove(proximoMotorista);
				pessoasNoTerminal.remove(proximoCarona.get());
				terminal.setPessoas(pessoasNoTerminal);
				return veiculo;
			}
		}

		// transportar pessoas que s�o policial/presidi�rio
		ListIterator<Pessoa> motoristasPolicialNoTerminal = pessoasNoTerminal.stream()
				.filter(Pessoa::isMotorista)
				.filter(Pessoa::isPolicial)
				.collect(Collectors.toList()).listIterator();

		while (motoristasPolicialNoTerminal.hasNext()) {
			Pessoa proximoMotorista = motoristasPolicialNoTerminal.next();

			Optional<Pessoa> proximoCarona = buscarCandidatosCarona(pessoasNoTerminal, proximoMotorista).stream().findAny();

			if (proximoCarona.isPresent()) {
				System.out.println("O pr�ximo motorista � " + proximoMotorista.getNome());
				System.out.println("A pr�xima carona � " + proximoCarona.get().getNome());

				veiculo.setMotorista(proximoMotorista);
				veiculo.setPassageiro(proximoCarona.get());

				pessoasNoTerminal.remove(proximoMotorista);
				pessoasNoTerminal.remove(proximoCarona.get());
				terminal.setPessoas(pessoasNoTerminal);
				return veiculo;
			}
		}

		System.out.println("N�o possui mais nenhuma carona no terminal.");
		
		veiculo.setMotorista(pessoasNoTerminal.get(0));
		pessoasNoTerminal.remove(pessoasNoTerminal.get(0));

		terminal.setPessoas(pessoasNoTerminal);
		return veiculo;
	}

	public TransporteTerminal levarParaAviao(Aviao aviao, TransporteTerminal veiculo) {
		if (veiculo.getPassageiro() != null && !veiculo.getPassageiro().podeFicarSozinha()) {
			System.out.println("O motorista " + veiculo.getMotorista().getNome() + " precisou subir no avi�o pq o "
					+ veiculo.getPassageiro().getNome() + " n�o pode ficar sozinho");
			aviao.getPassageiros().add(veiculo.getMotorista());

			Pessoa motoristasNoAviao = aviao.getPassageiros().stream().filter(Pessoa::isMotorista)
					.filter(not(Pessoa::isPolicial)).findAny()
					.orElseThrow(() -> new RuntimeException("N�o existe nenhum motorista no avi�o"));

			aviao.getPassageiros().remove(motoristasNoAviao);
			veiculo.setMotorista(motoristasNoAviao);

			System.out.println("O " + motoristasNoAviao.getNome() + " que estava no avi�o, subiu no ve�culo para dirigir");
		}

		if (veiculo.getPassageiro() != null) {
			System.out.println("O " + veiculo.getPassageiro().getNome() + " subiu no avi�o");
			aviao.getPassageiros().add(veiculo.getPassageiro());
			veiculo.setPassageiro(null);
		} else {
			System.out.println("O " + veiculo.getMotorista().getNome() + " subiu no avi�o");
			aviao.getPassageiros().add(veiculo.getMotorista());
			veiculo.setMotorista(null);
		}

		return veiculo;
	}
}
