package com.interview.prj.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.interview.prj.meeting.exception.TipoDeReuniaoNaoExisteErro;

@ControllerAdvice
public class ObservadorDeErro {

	@ExceptionHandler({ PessoaNaoEncontradaErro.class, TipoDeReuniaoNaoExisteErro.class, SalaExisteErro.class,
			DataInvalida.class , InvalidMeetingSubject.class})
	public ResponseEntity<String> resolver(RuntimeException ex) {
		return ResponseEntity.badRequest().body(ex.getMessage());
	}
}
