package com.ProjetoBiblioteca.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ProjetoBiblioteca.excecao.Excecao;
import com.ProjetoBiblioteca.model.Autor;
import com.ProjetoBiblioteca.model.Livro;
import com.ProjetoBiblioteca.repository.AutorRepository;
import com.ProjetoBiblioteca.repository.LivroRepository;

@Service
@Transactional
public class LivroService {
	private final LivroRepository livroRepository;
	private final AutorRepository autorRepository;

	public LivroService(LivroRepository livroRepository, AutorRepository autorRepository) {
		this.livroRepository = livroRepository;
		this.autorRepository = autorRepository;
	}

	@Transactional(readOnly = true)
	public List<Livro> listar(String titulo) {
		return titulo == null || titulo.isBlank() ? livroRepository.findAll()
				: livroRepository.findByTituloContainingIgnoreCaseOrderByTituloAsc(titulo.trim());
	}

	@Transactional(readOnly = true)
	public Livro buscarPorId(Long id) {
		return livroRepository.findById(id).orElseThrow(() -> new Excecao("Livro não encontrado: " + id));
	}

	public Livro criar(Livro livro) {
		livro.setId(null);
		livro.setAutores(resolverAutores(livro.getAutores()));
		return livroRepository.save(livro);
	}

	public Livro atualizar(Long id, Livro dados) {
		Livro livro = buscarPorId(id);
		livro.setTitulo(dados.getTitulo());
		livro.setIsbn(dados.getIsbn());
		livro.setAnoPublicacao(dados.getAnoPublicacao());
		livro.setAutores(resolverAutores(dados.getAutores()));
		return livroRepository.save(livro);
	}

	public void excluir(Long id) {
		livroRepository.delete(buscarPorId(id));
	}

	private List<Autor> resolverAutores(List<Autor> autores) {
		if (autores == null) return List.of();
		return autores.stream().map(autor -> {
			if (autor.getId() == null) throw new Excecao("Informe o ID de cada autor do livro");
			return autorRepository.findById(autor.getId())
					.orElseThrow(() -> new Excecao("Autor não encontrado: " + autor.getId()));
		}).distinct().toList();
	}
}
