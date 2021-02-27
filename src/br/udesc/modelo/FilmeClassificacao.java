package br.udesc.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FilmeClassificacao implements IModelo, Serializable {
	
	private Filme filme;
	private List<Classificacao> classificacoes;
	

	@Override
	public int getId() {
		return 0;
	}
	
	public void setFilme(Filme filme) {
		this.filme = filme;
	}
	
	public Filme getFilme() {
		return this.filme;
	}
	
	public void setClassificacoes(List<Classificacoes> list) {
		this.classificacoes = new ArrayList<>();
		for (Classificacoes classi : list) {
			this.classificacoes.add(new Classificacao(classi.getUsuario().getDsNome(), classi.getSnGostou()));
		}
	}
	
	public List<Classificacao> getClassificacoes() {
		return this.classificacoes;
	}
	
	public class Classificacao implements Serializable {
		private String userName;
		private boolean gostou;
		
		public Classificacao() {
			this("", false);
		}
		
		public Classificacao(String userName, boolean gostou) {
			this.userName = userName;
			this.gostou = gostou;
		}
		
		
		public void setUserName(String userName) {
			this.userName = userName;
		}
		
		public String getUserName() {
			return this.userName;
		}
		
		public void setGostou(boolean gostou) {
			this.gostou = gostou;
		}
		
		public boolean getGostou() {
			return this.gostou;
		}
	}
}
