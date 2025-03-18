package com.wgsystems.sf_loteamentos.exceptions;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ClienteDuplicadoException.class)
	public ResponseEntity<Object> handleClienteDuplicado(ClienteDuplicadoException ex, WebRequest request) {
		return buildHandleException(ex, request, HttpStatus.CONFLICT);
	}

	@ExceptionHandler(ClienteNaoEncontrado.class)
	public ResponseEntity<Object> handleClienteNaoEncontrado(ClienteNaoEncontrado ex, WebRequest request) {
		return buildHandleException(ex, request, HttpStatus.NOT_FOUND);
	}

	private ResponseEntity<Object> buildHandleException(RuntimeException ex, WebRequest request, HttpStatus status) {
		ErrorMessage errorMessage = new ErrorMessage(new Date(), status.value(),
				status.getReasonPhrase(), ex.getMessage());

		return handleExceptionInternal(ex, errorMessage, new HttpHeaders(), status, request);
	}
	
	

}