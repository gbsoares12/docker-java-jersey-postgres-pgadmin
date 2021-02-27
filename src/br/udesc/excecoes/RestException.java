package br.udesc.excecoes;

public class RestException extends RuntimeException {
	
	private String mensagem;
	private int codigo;
	
	public RestException(int codigo, String mensagem) {
		super();
		this.codigo   = codigo;
		this.mensagem = mensagem;
	}
	
	public int getCodigo() {
		return this.codigo;
	}
	
	public String getMensagem() {
		return this.mensagem;
	}

}
