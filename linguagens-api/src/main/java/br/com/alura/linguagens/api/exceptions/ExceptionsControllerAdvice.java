package br.com.alura.linguagens.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionsControllerAdvice {

	@ExceptionHandler(ObjetoNaoEncontrado.class)
	public ResponseEntity<String> tratarObjetoNaoEncontrado(ObjetoNaoEncontrado ex) {

		return ResponseEntity.badRequest().body(ex.getMessage());

	}

	@ExceptionHandler(EntidadeNaoEncontrada.class)
	public ResponseEntity<String> tratarErroNotFound(EntidadeNaoEncontrada e) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());

	}

}
