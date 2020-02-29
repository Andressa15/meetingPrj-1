package com.meetingprj.meeting.exception;

public class TipoDeReuniaoNaoExisteErro extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TipoDeReuniaoNaoExisteErro(String message){
		super(message);
	}
	
}
