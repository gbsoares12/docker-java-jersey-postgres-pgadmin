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
			this.classificacoes.add(new Classificacao(classi.getCdUsuario(), classi.getSnGostou()));
		}
	}
	
	public List<Classificacao> getClassificacoes() {
		return this.classificacoes;
	}
	
	public class Classificacao implements Serializable {
		private int cdUsuario;
		private boolean gostou;
		
		public Classificacao() {
			this(0, false);
		}
		
		public Classificacao(int cdUsuario, boolean gostou) {
			this.cdUsuario = cdUsuario;
			this.gostou = gostou;
		}
		
		
		public void setCdUsuario(int cdUsuario) {
			this.cdUsuario = cdUsuario;
		}
		
		public int getCdUsuario() {
			return this.cdUsuario;
		}
		
		public void setGostou(boolean gostou) {
			this.gostou = gostou;
		}
		
		public boolean getGostou() {
			return this.gostou;
		}
	}
}