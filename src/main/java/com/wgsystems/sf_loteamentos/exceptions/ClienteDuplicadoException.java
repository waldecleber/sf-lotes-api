package com.wgsystems.sf_loteamentos.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ClienteDuplicadoException extends RuntimeException {

    public ClienteDuplicadoException(String msg) {
        super(msg);
    }
}
