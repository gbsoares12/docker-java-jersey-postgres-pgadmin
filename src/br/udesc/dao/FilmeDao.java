/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.dao;

import java.util.ArrayList;
import java.util.List;

import br.udesc.modelo.Filme;
import br.udesc.modelo.FilmeClassificacao;

/**
 *
 * @author Andrew, Viviane, Gabriel
 */
public class FilmeDao extends BaseDao {

	public FilmeDao() {
		super("filmes", new String[] { "cd_filme" });
	}

	public List<FilmeClassificacao> getAllWithClassification() {
		List<Filme> registroFilmes = this.getAll();
		List<FilmeClassificacao> registros = new ArrayList<>();

		ClassificacoesDao claDao = (ClassificacoesDao) this.newDaoInstance("ClassificacoesDao");
		for (Filme filme : registroFilmes) {
			FilmeClassificacao filClz = new FilmeClassificacao();
			filClz.setFilme(filme);
			filClz.setClassificacoes(claDao.getAllByFilmes(filme.getId()));
			registros.add(filClz);
		}

		return registros;
	}

	public Filme iniciarFilme(int id) throws Exception {
		Filme filme;

		filme = (Filme) this.getById(id);
		if (!filme.getDsNome().isEmpty()) {

			return filme;
		} else {
			
			throw new Exception("Filme não encontrado");
		}

	}

}
