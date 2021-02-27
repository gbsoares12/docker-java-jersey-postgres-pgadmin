package br.udesc.modelo;

import java.io.Serializable;

public class Usuario implements IModelo, Serializable {
	
	private int cdUsuario;
	private String dsNome;
	private String dsSenha;
	
	public Usuario() {
		this("", "");
	}
	
	public Usuario(String nome, String senha) {
		this.dsNome = nome;
		this.dsSenha = senha;
	}
	
	@Override
	public int getId() {
		return this.cdUsuario;
	}
	
	public void setCdUsuario(int cdUsuario) {
		this.cdUsuario = cdUsuario;
	}
	
	public int getCdUsuario() {
		return this.cdUsuario;
	}
	
	public void setDsNome(String dsNome) {
		this.dsNome = dsNome;
	}
	
	public String getDsNome() {
		return this.dsNome;
	}
	
	public void setDsSenha(String dsSenha) {
		this.dsSenha = dsSenha;
	}
	
	public String getDsSenha() {
		return this.dsSenha;
	}

}
