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
import java.util.List;
import br.udesc.modelo.Elenco;
import br.udesc.modelo.IModelo;

/**
 *
 * @author Andrew, Viviane
 */
public class ElencoDao extends BaseDao {

    public ElencoDao() {
    	super("elenco", new String[] {"cd_filme", "cd_autor"});
    }
    
    @Override
    public void addWhere(PreparedStatement stmt, IModelo entity) throws SQLException {
    	int totalCampos = this.getCamposClasse().size();
		stmt.setInt(totalCampos + 1, ((Elenco) entity).getFilme().getId());
		stmt.setInt(totalCampos + 2, ((Elenco) entity).getAtor().getId());
    }
    
    public List getElencoByFilme(int idFilme) {
    	String query = "select * from " + this.table + " where cd_filme =?"; 
    	List<IModelo> registros = new ArrayList<>();
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, idFilme);
			ResultSet rs = statement.executeQuery();

			List<Field> camposRegistro = this.getCamposClasse();
			while (rs.next()) {
				IModelo registro = this.newModeloInstance();
				this.loadModel(rs, registro);	
				registros.add(registro);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 

		return registros;
    }
}
