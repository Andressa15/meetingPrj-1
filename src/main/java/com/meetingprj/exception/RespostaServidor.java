package com.meetingprj.exception;

import java.util.List;

public class RespostaServidor {

	private List<Erro> erros;
	private Object dados;

	public List<Erro> getErros() {
		return erros;
	}

	public void setErros(List<Erro> erros) {
		this.erros = erros;
	}

	public Object getDados() {
		return dados;
	}

	public void setDados(Object dados) {
		this.dados = dados;
	}
}
