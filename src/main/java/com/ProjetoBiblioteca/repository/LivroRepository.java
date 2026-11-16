package com.ProjetoBiblioteca.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ProjetoBiblioteca.model.Livro;

public interface LivroRepository extends JpaRepository<Livro, Long> {
	List<Livro> findByTituloContainingIgnoreCaseOrderByTituloAsc(String titulo);
}
