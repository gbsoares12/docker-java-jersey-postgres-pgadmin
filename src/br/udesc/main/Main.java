package br.udesc.main;

import java.lang.reflect.Field;
import java.util.List;

import br.udesc.dao.AtorDao;
import br.udesc.dao.FilmeDao;
import br.udesc.dao.GeneroDao;
import br.udesc.modelo.Ator;
import br.udesc.modelo.Filme;
import br.udesc.modelo.Genero;

public class Main {
	public static void main(String[] args) {
		AtorDao d = new AtorDao();
		GeneroDao daoGenero = new GeneroDao();
		FilmeDao daoFilme = new FilmeDao();
		
		Genero g = new Genero();
		g.setCdGenero(1);
		g.setDsNome("terror");
		
//		
//		Filme f = new Filme();
//		f.setCdFilme(1);
//		f.setDsNome("Filme 1");
//		f.setGenero(g);
//		f.setNrDuracaoMin(10);
//		
//		System.out.println(daoFilme.getQueryInsert());
//		
//		daoFilme.add(f);
		
		Filme ss = (Filme) daoFilme.getById(1);
		
		System.out.println(ss.getGenero().getDsNome());
		
//		List<Filme> fd = daoFilme.getAll();
//		for(Filme m: fd) {
//			System.out.println(m.getDsNome());
//		}
////		
//		
//		
//		Ator as = (Ator) d.getById(1);
//		
//		System.out.println(as.getDsNome());
		
//		for(Field fs: f) {
//			System.out.println(fs.getType().getTypeName());
//		}
		
//		String s = "tudo";
//		String[] ss = s.split("(?=[A-Z])");
//		for(int i = 0; i < ss.length; i++) {
//			System.out.println(ss[i]);
//		}
		
//		String[] s = "int".split(";");
//		System.out.println("" + s[s.length - 1]);
	}
}
