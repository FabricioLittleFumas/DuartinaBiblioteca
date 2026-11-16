package com.ProjetoBiblioteca.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ProjetoBiblioteca.model.Autor;

public interface AutorRepository extends JpaRepository<Autor, Long> {
	List<Autor> findByNomeContainingIgnoreCaseOrderByNomeAsc(String nome);
}
