package com.mussinato.codeit.airlines.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Terminal {
	private List<Pessoa> pessoas;
}
