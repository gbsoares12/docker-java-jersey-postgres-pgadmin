package br.udesc.modelo;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Cliente {
	
	private int id;
	private String nome;
	
	public Cliente() {}
	
	public Cliente(int id, String nome) {
		this.id   = id;
		this.nome = nome;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return this.id;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return this.nome;
	}

}
