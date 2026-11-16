package com.ProjetoBiblioteca.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ProjetoBiblioteca.model.Livro;
import com.ProjetoBiblioteca.service.LivroService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/livros")
public class LivroController {
	private final LivroService livroService;

	public LivroController(LivroService livroService) {
		this.livroService = livroService;
	}

	@GetMapping
	public ResponseEntity<List<Livro>> listar(@RequestParam(required = false) String titulo) {
		return ResponseEntity.ok(livroService.listar(titulo));
	}

	@GetMapping("/{id}")
	public ResponseEntity<Livro> buscarPorId(@PathVariable Long id) {
		return ResponseEntity.ok(livroService.buscarPorId(id));
	}

	@PostMapping
	public ResponseEntity<Livro> criar(@Valid @RequestBody Livro livro) {
		return ResponseEntity.status(HttpStatus.CREATED).body(livroService.criar(livro));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Livro> atualizar(@PathVariable Long id, @Valid @RequestBody Livro livro) {
		return ResponseEntity.ok(livroService.atualizar(id, livro));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluir(@PathVariable Long id) {
		livroService.excluir(id);
		return ResponseEntity.noContent().build();
	}
}
