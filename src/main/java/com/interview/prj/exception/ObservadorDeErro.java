package com.interview.prj.exception;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.interview.prj.meeting.exception.TipoDeReuniaoNaoExisteErro;

@ControllerAdvice
public class ObservadorDeErro {

	@ExceptionHandler({ PessoaNaoEncontradaErro.class, TipoDeReuniaoNaoExisteErro.class, SalaExisteErro.class,
			DataInvalida.class, InvalidMeetingSubject.class })
	public ResponseEntity<RespostaServidor> resolver(RuntimeException ex) {
		Erro erro = new Erro(ex.getMessage());
		RespostaServidor respostaServidor = new RespostaServidor();
		respostaServidor.setErros(Arrays.asList(erro));
		return ResponseEntity.badRequest().body(respostaServidor);
	}

	@ExceptionHandler(ArgumentoNaoValidoErro.class)
	public ResponseEntity<RespostaServidor> resolverArgumentoNaoValidoErro(ArgumentoNaoValidoErro ex) {		
		List<Erro> erros = ex.getErrors().stream().map(e -> new Erro(ex.getMessage())).collect(Collectors.toList());
		RespostaServidor respostaServidor = new RespostaServidor();
		respostaServidor.setErros(erros);
		return ResponseEntity.badRequest().body(respostaServidor);
	}
}
