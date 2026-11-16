package com.ProjetoBiblioteca.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "livros")
public class Livro {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "O título do livro é obrigatório")
	@Size(max = 200, message = "O título deve ter no máximo 200 caracteres")
	@Column(nullable = false, length = 200)
	private String titulo;

	@Size(max = 20, message = "O ISBN deve ter no máximo 20 caracteres")
	@Column(length = 20, unique = true)
	private String isbn;

	private Integer anoPublicacao;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "livros_autores",
		joinColumns = @JoinColumn(name = "livro_id"),
		inverseJoinColumns = @JoinColumn(name = "autor_id"))
	@JsonIgnoreProperties("livros")
	private List<Autor> autores = new ArrayList<>();

	public Long getId() { return id; }
	public void setId(Long id) { this.id = id; }
	public String getTitulo() { return titulo; }
	public void setTitulo(String titulo) { this.titulo = titulo; }
	public String getIsbn() { return isbn; }
	public void setIsbn(String isbn) { this.isbn = isbn; }
	public Integer getAnoPublicacao() { return anoPublicacao; }
	public void setAnoPublicacao(Integer anoPublicacao) { this.anoPublicacao = anoPublicacao; }
	public List<Autor> getAutores() { return autores; }
	public void setAutores(List<Autor> autores) { this.autores = autores; }
}
