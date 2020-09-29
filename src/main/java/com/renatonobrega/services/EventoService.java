package com.renatonobrega.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.renatonobrega.models.Evento;
import com.renatonobrega.repository.EventoRepository;

@Service
public class EventoService {

	@Autowired
	private EventoRepository eventoRepository;

	@Transactional
	public Evento insert(Evento evento) {
		evento.setId(null);
		evento = eventoRepository.save(evento);
		return evento;
	}
	
	public List<Evento> getEvento(){
		return eventoRepository.findAll();
	}
}
