package com.renatonobrega.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.renatonobrega.models.Usuario;
import com.renatonobrega.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Transactional
	public Usuario insert(Usuario usuario) {
		usuario.setId(null);
		usuario = usuarioRepository.save(usuario);
		return usuario;
	}
	
	public List<Usuario> getUsuarios(){
		return usuarioRepository.findAll();
	}
	
	public void delete(Usuario usuario) {
		usuarioRepository.delete(usuario);
	}
}
