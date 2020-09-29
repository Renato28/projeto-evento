package com.renatonobrega.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.renatonobrega.models.Evento;
import com.renatonobrega.models.Usuario;
import com.renatonobrega.repository.EventoRepository;
import com.renatonobrega.repository.UsuarioRepository;

@Service
public class DBService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private EventoRepository eventoRepository;
	
	public void instantiateTestDatabase() {
		
		Usuario usuario = new Usuario();
		usuario.setId(null);
		usuario.setNome("Renato Nóbrega da Silva");
		
		Evento evento = new Evento();
		evento.setId(null);
		evento.setNome("Expotec");
		evento.setVagas(100);
		evento.setHoraInicio(LocalDateTime.of(LocalDate.of(2020, 9, 29), LocalTime.of(9, 00)));
		evento.setHoraFim(LocalDateTime.of(LocalDate.of(2020, 9, 29), LocalTime.of(18, 00)));
		
		evento.getUsuarios().addAll(Arrays.asList(usuario));
		
		eventoRepository.saveAll(Arrays.asList(evento));
		usuarioRepository.saveAll(Arrays.asList(usuario));
		
		
		
		
	}
}
