package com.meetingprj.exception;

import java.util.List;

import org.springframework.validation.FieldError;

public class ArgumentoNaoValidoErro extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<FieldError> errors;

	public ArgumentoNaoValidoErro(List<FieldError> errors) {
		this.errors = errors;
	}

	public List<FieldError> getErrors() {
		return errors;
	}

	public void setErrors(List<FieldError> errors) {
		this.errors = errors;
	}
}
