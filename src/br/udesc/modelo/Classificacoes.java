package br.udesc.modelo;

import java.io.Serializable;
import java.sql.Timestamp;

public class Classificacoes implements IModelo, Serializable {
	
	private int cdFilme;
	private int cdUsuario;
	private boolean snGostou;
	private Timestamp dtMomento;
	
	@Override
	public int getId() {
		return 0;
	}
		
	public Classificacoes() {
		super();
	}
	
	public Classificacoes(int cdFilme, int cdUsuario, boolean snGostou, Timestamp dtMomento) {
		super();
		this.cdFilme = cdFilme;
		this.cdUsuario = cdUsuario;
		this.snGostou = snGostou;
		this.dtMomento = dtMomento;
	}

	
	public Timestamp getDtMomento() {
		return dtMomento;
	}

	public void setDtMomento(Timestamp dtMomento) {
		this.dtMomento = dtMomento;
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
