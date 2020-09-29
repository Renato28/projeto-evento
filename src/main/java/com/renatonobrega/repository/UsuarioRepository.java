package com.renatonobrega.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.renatonobrega.models.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
	Optional<Usuario> findByNome(String nome);
}
