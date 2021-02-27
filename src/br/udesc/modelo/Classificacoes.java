package br.udesc.modelo;

import java.io.Serializable;

public class Classificacoes implements IModelo, Serializable {
	
	private int cdFilme;
	private int cdUsuario;
	private boolean snGostou;
	
	@Override
	public int getId() {
		return 0;
	}
		
	public Classificacoes(int cdFilme, int cdUsuario, boolean snGostou) {
		super();
		this.cdFilme = cdFilme;
		this.cdUsuario = cdUsuario;
		this.snGostou = snGostou;
	}

	public int getCdFilme() {
		return cdFilme;
	}

	public void setCdFilme(int cd_filme) {
		this.cdFilme = cd_filme;
	}

	public int getCdUsuario() {
		return cdUsuario;
	}

	public void setCdUsuario(int cd_usuario) {
		this.cdUsuario = cd_usuario;
	}

	public void setSnGostou(boolean gostou) {
		this.snGostou = gostou;
	}
	
	public boolean getSnGostou() {
		return this.snGostou;
	}
}
