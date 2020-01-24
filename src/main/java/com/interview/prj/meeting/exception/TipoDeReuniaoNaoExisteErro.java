package com.interview.prj.meeting.exception;

public class TipoDeReuniaoNaoExisteErro extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TipoDeReuniaoNaoExisteErro(String message){
		super(message);
	}
	
}
