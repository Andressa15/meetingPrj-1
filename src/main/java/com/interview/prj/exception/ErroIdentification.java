package com.interview.prj.exception;

public class ErroIdentification extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ErroIdentification (String message) {
		super(message);
	}	
}
