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

import br.udesc.modelo.IModelo;

/**
 *
 * @author Andrew, Viviane
 */
public class AtorDao extends BaseDao {
	
	public AtorDao() {
		super("atores", new String[]{"cd_autor"});
	}
	
	@Override
	public void addWhere(PreparedStatement stmt, IModelo entity) throws SQLException {
		int totalCampos = this.getCamposClasse().size();
		stmt.setInt(totalCampos + 1, entity.getId());
	}

}
