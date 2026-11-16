package com.ProjetoBiblioteca.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.ProjetoBiblioteca.model.RefreshToken;
import com.ProjetoBiblioteca.model.User;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

	Optional<RefreshToken> findByToken(String token);

	@Modifying
	@Transactional
	@Query("DELETE FROM RefreshToken r WHERE r.token = :token")
	void deleteByToken(@Param("token") String token);

	@Modifying
	@Transactional
	void deleteByUser(User user);
}