package br.udesc.excecoes;

public class FilmeNaoEncontradoParaIniciarRestException extends RestException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FilmeNaoEncontradoParaIniciarRestException() {
		super(404, "Filme não encontrado");
	}
	
	 
}
