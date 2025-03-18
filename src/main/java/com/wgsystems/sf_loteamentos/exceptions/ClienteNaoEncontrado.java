package com.wgsystems.sf_loteamentos.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ClienteNaoEncontrado extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ClienteNaoEncontrado(String msg) {
		super(msg);
	}

}