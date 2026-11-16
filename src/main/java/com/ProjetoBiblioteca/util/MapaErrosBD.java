package com.ProjetoBiblioteca.util;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MapaErrosBD {
	@Autowired
	Conversor conversor;
	
	private HashMap<String, String> mapaErros;
	
	public MapaErrosBD() {
		mapaErros = new HashMap<>();
		mapaErros.put("data_saida", "Data em formato inválido");
	}
	
	public String getErros(String erro) {
		return mapaErros.get(conversor.converter(erro));
	}
}
