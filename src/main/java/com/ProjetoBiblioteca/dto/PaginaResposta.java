package com.ProjetoBiblioteca.dto;

import java.util.List;

public record PaginaResposta<T>(
		List<T> content,
		int page,
		int size,
		long totalElements,
		int totalPages,
		boolean first,
		boolean last) {
}
