package com.ProjetoBiblioteca.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ProjetoBiblioteca.excecao.Excecao;
import com.ProjetoBiblioteca.model.Autor;
import com.ProjetoBiblioteca.repository.AutorRepository;

@Service
@Transactional
public class AutorService {
	private final AutorRepository autorRepository;

	public AutorService(AutorRepository autorRepository) {
		this.autorRepository = autorRepository;
	}

	@Transactional(readOnly = true)
	public List<Autor> listar(String nome) {
		if (nome == null || nome.isBlank()) return autorRepository.findAll();
		return autorRepository.findByNomeContainingIgnoreCaseOrderByNomeAsc(nome.trim());
	}

	@Transactional(readOnly = true)
	public Autor buscarPorId(Long id) {
		return autorRepository.findById(id)
				.orElseThrow(() -> new Excecao("Autor não encontrado: " + id));
	}

	public Autor criar(Autor autor) {
		autor.setId(null);
		return autorRepository.save(autor);
	}

	public Autor atualizar(Long id, Autor dados) {
		Autor autor = buscarPorId(id);
		autor.setNome(dados.getNome());
		autor.setNacionalidade(dados.getNacionalidade());
		autor.setBiografia(dados.getBiografia());
		return autorRepository.save(autor);
	}

	public void excluir(Long id) {
		Autor autor = buscarPorId(id);
		autorRepository.delete(autor);
	}
}
