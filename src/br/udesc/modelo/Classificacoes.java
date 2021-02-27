package br.udesc.modelo;

import java.io.Serializable;

public class Classificacoes implements IModelo, Serializable {
	
	private Filme filme;
	private Usuario usuario;
	private boolean snGostou;
	
	@Override
	public int getId() {
		return 0;
	}
	
	public void setUsuario(Usuario user) {
		this.usuario = user;
	}
	
	public Usuario getUsuario() {
		return this.usuario;
	}
	
	public void setFilme(Filme filme) {
		this.filme = filme;
	}
	
	public Filme getFilme() {
		return this.filme;
	}
	
	public void setSnGostou(boolean gostou) {
		this.snGostou = gostou;
	}
	
	public boolean getSnGostou() {
		return this.snGostou;
	}
}
