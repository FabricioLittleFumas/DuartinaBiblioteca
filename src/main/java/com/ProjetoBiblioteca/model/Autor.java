package com.ProjetoBiblioteca.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "autores")
public class Autor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "O nome do autor é obrigatório")
	@Size(max = 150, message = "O nome do autor deve ter no máximo 150 caracteres")
	@Column(nullable = false, length = 150)
	private String nome;

	@Size(max = 100, message = "A nacionalidade deve ter no máximo 100 caracteres")
	@Column(length = 100)
	private String nacionalidade;

	@Size(max = 4000, message = "A biografia deve ter no máximo 4.000 caracteres")
	@Column(length = 4000)
	private String biografia;

	@JsonIgnore
	@ManyToMany(mappedBy = "autores")
	private List<Livro> livros = new ArrayList<>();

	public Long getId() { return id; }
	public void setId(Long id) { this.id = id; }
	public String getNome() { return nome; }
	public void setNome(String nome) { this.nome = nome; }
	public String getNacionalidade() { return nacionalidade; }
	public void setNacionalidade(String nacionalidade) { this.nacionalidade = nacionalidade; }
	public String getBiografia() { return biografia; }
	public void setBiografia(String biografia) { this.biografia = biografia; }
	public List<Livro> getLivros() { return livros; }
	public void setLivros(List<Livro> livros) { this.livros = livros; }
}
