package com.renatonobrega.resources;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.renatonobrega.exceptions.NotCancelSubscribeUserException;
import com.renatonobrega.exceptions.NotEnterUserEventException;
import com.renatonobrega.exceptions.NotSubscribeUserException;
import com.renatonobrega.models.Evento;
import com.renatonobrega.models.Usuario;
import com.renatonobrega.services.EventoService;
import com.renatonobrega.services.UsuarioService;

@RestController
@RequestMapping(name = "/evento")
public class EventoResource {

	@Autowired
	private EventoService Eventoservice;

	@Autowired
	private UsuarioService UsuarioService;

	// Realiza a criação de um evento
	@RequestMapping(method = RequestMethod.POST)
	public Evento criarEvento(@RequestBody @Valid Evento evento) {
		return Eventoservice.insert(evento);
	}

	// Realiza a criação de um usuário
	@RequestMapping(method = RequestMethod.POST)
	public Usuario criarUsuario(@RequestBody @Valid Usuario usuario) {
		return UsuarioService.insert(usuario);
	}

	// Lista os inscritos de um evento
	@RequestMapping(method = RequestMethod.GET)
	public List<Usuario> getUsuarios() {
		return UsuarioService.getUsuarios();
	}

	// Lista as incrições de um evento
	@RequestMapping(method = RequestMethod.GET)
	public List<Evento> getEvento() {
		return Eventoservice.getEvento();
	}

	// Realiza a operação de inscrição de um usuário em um evento
	@RequestMapping(method = RequestMethod.POST)
	public void inscreverUsuario(@RequestBody @Valid Usuario usuario) throws NotSubscribeUserException {
		if (usuario.getEvento().getVagas() <= 100
				&& !usuario.getEvento().getHoraInicio().isAfter(LocalDateTime.now())) {
			Usuario user = new Usuario();
			user.setId(usuario.getId());
			user.setNome(user.getNome());
			user.setEvento(usuario.getEvento());
			UsuarioService.insert(user);
		} else {
			new NotSubscribeUserException(
					"Não é possivel realizar a inscrição do usuário pois o limite de vagas foi atingido!");
		}
	}

	// Realiza o cancelamento de uma inscrição de um usuário em um evento;
	@RequestMapping(method = RequestMethod.DELETE)
	public void cancelarInscricaoUsuario(@RequestBody @Valid Usuario usuario) {
		if (usuario.getEvento().getHoraInicio().isAfter(LocalDateTime.now())) {
			new NotCancelSubscribeUserException("Não é possivel realizar o cancelamento da inscrição do usuário!");
		} else {
			UsuarioService.delete(usuario);
		}

	}

	// Valida entrada do usuário no evento.
	@RequestMapping(method = RequestMethod.POST)
	public void validarUsuario(@RequestBody @Valid Usuario usuario) {
		if (usuario.getNome() != null && usuario.getEvento().getHoraInicio().isBefore(LocalDateTime.now().minusHours(1)) && usuario.getEvento().getHoraFim().isBefore(LocalDateTime.now())) {
			UsuarioService.insert(usuario);
		}else {
			new NotEnterUserEventException("Não é possivel a entrada do usuário no evento! ");
		}
	}
}
