/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.udesc.modelo.Elenco;
import br.udesc.modelo.IModelo;
import br.udesc.modelo.Oscares;

/**
 *
 * @author Andrew, Viviane
 */
public class OscaresDao extends BaseDao {

	public OscaresDao() {
        super("oscares_melhor_filme", new String[] {"cd_filme"});
    }
	
	@Override
	public void addWhere(PreparedStatement stmt, IModelo entity) throws SQLException {
		int totalCampos = this.getCamposClasse().size();
		stmt.setInt(totalCampos + 1, ((Oscares) entity).getFilme().getId());
	}

}
