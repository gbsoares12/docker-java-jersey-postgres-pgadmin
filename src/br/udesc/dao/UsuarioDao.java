package br.udesc.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.udesc.modelo.IModelo;

public class UsuarioDao extends BaseDao {
	
	public UsuarioDao() {
		super("usuarios", new String[] {"cd_usuario"});
	}
	
	public IModelo findUser(String nome, String pass) {
		String query = "SELECT *"
				+     " FROM "  + this.table
				+     " WHERE ds_nome =? AND ds_senha =?";
		IModelo registro = this.newModeloInstance();
		try {
          PreparedStatement statement = connection.prepareStatement(query);
          statement.setString(1, nome);
          statement.setString(2, pass);
          ResultSet resultSet = statement.executeQuery();
          while (resultSet.next()) {
              this.loadModel(resultSet, registro);  
          }
        } catch (Exception e) {
           e.printStackTrace();
        }
        return registro;
	}
}
