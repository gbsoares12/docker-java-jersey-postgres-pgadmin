/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.dao;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import br.udesc.modelo.Classificacoes;
import br.udesc.modelo.Elenco;
import br.udesc.modelo.Filme;
import br.udesc.modelo.FilmeClassificacao;
import br.udesc.modelo.IModelo;


/**
 *
 * @author Andrew, Viviane
 */
public class FilmeDao extends BaseDao {

    public FilmeDao() {
        super("filmes", new String[] {"cd_filme"});
    }
    
    public List<FilmeClassificacao> getAllWithClassification() {
    	List<Filme> registroFilmes = this.getAll();
    	List<FilmeClassificacao> registros = new ArrayList<>();
    	
    	ClassificacoesDao claDao = (ClassificacoesDao) this.newDaoInstance("ClassificacoesDao");
    	for (Filme filme: registroFilmes) {
    		FilmeClassificacao filClz = new FilmeClassificacao();
    		filClz.setFilme(filme);
    		filClz.setClassificacoes(claDao.getAllByFilmes(filme.getId()));
    		registros.add(filClz);
		}

		return registros;
    }
    
}
