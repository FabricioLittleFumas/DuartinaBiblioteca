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

import com.ProjetoBiblioteca.model.Autor;
import com.ProjetoBiblioteca.service.AutorService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/autores")
public class AutorController {
	private final AutorService autorService;

	public AutorController(AutorService autorService) {
		this.autorService = autorService;
	}

	@GetMapping
	public ResponseEntity<List<Autor>> listar(@RequestParam(required = false) String nome) {
		return ResponseEntity.ok(autorService.listar(nome));
	}

	@GetMapping("/{id}")
	public ResponseEntity<Autor> buscarPorId(@PathVariable Long id) {
		return ResponseEntity.ok(autorService.buscarPorId(id));
	}

	@PostMapping
	public ResponseEntity<Autor> criar(@Valid @RequestBody Autor autor) {
		return ResponseEntity.status(HttpStatus.CREATED).body(autorService.criar(autor));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Autor> atualizar(@PathVariable Long id, @Valid @RequestBody Autor autor) {
		return ResponseEntity.ok(autorService.atualizar(id, autor));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluir(@PathVariable Long id) {
		autorService.excluir(id);
		return ResponseEntity.noContent().build();
	}
}
