package com.proyectosPersonales.springboot.app.commons.exception;

import java.io.Serializable;

import lombok.Data;

@Data
public class ApiException extends RuntimeException implements Serializable {

	private static final long serialVersionUID = -3342414901572016437L;

	private final String code;

	public ApiException(String code, String message) {
		super(message);
        this.code = code;
    }
}
