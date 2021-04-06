/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import br.udesc.modelo.Filme;
import br.udesc.modelo.FilmeClassificacao;
import br.udesc.modelo.ServicoUsado;
import br.udesc.modelo.Usuario;

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

	public Filme iniciarFilme(int idCliente, int idFilme) throws Exception {
		Filme filme;
		filme = (Filme) this.getById(idFilme);
		Usuario usuario;

		UsuarioDao userDao = (UsuarioDao) this.newDaoInstance("UsuarioDao");

		usuario = (Usuario) userDao.getById(idCliente);
		
		if (usuario.getDsNome().isEmpty()) {
			throw new Exception("Usuário não encontrado");
		} else if (filme.getDsNome() != null) {

			ServicoUsadoDao bcDao = (ServicoUsadoDao) this.newDaoInstance("ServicoUsadoDao");

			ServicoUsado backlog = new ServicoUsado(idCliente, 4, new Timestamp(System.currentTimeMillis()));
			bcDao.add(backlog);

			return filme;
		} else {
			throw new Exception("Filme não encontrado");
		}

	}

}
