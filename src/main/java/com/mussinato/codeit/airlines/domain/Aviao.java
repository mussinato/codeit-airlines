package com.mussinato.codeit.airlines.domain;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

@Getter
public class Aviao {
	private List<Pessoa> passageiros = new ArrayList<Pessoa>();
}
