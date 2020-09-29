package com.renatonobrega.services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
		
		Usuario usuario = new Usuario(null, "Renato Nobrega da Silva");
		
		LocalDateTime dataHoraInicio = LocalDateTime.of(2020, 9, 29, 9, 00);
		dataHoraInicio.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
		
		LocalDateTime dataHoraFim = LocalDateTime.of(2020, 9, 29, 18, 00);
		dataHoraFim.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
		
		Evento evento = new Evento(null, "Expotec", 100, dataHoraInicio, dataHoraFim);
		
		eventoRepository.saveAll(Arrays.asList(evento));
		usuarioRepository.saveAll(Arrays.asList(usuario));
		
		
		
		
	}
}
