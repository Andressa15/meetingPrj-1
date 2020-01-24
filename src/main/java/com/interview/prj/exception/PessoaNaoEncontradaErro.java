package com.interview.prj.exception;

public class PessoaNaoEncontradaErro extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public PessoaNaoEncontradaErro(String mensagem){
		super(mensagem);
	}

}
