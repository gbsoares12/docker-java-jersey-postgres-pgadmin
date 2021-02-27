package br.udesc.dao;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.udesc.modelo.Classificacoes;
import br.udesc.modelo.IModelo;

public class ClassificacoesDao extends BaseDao {
	
	public ClassificacoesDao() {
		super("classificacoes", new String[] {"cd_filme", "cd_usuario"});
	}
	
	@Override
	public void addWhere(PreparedStatement stmt, IModelo entity) throws SQLException {
		int qtdCampos = this.getCamposClasse().size();
		stmt.setInt(qtdCampos + 1, ((Classificacoes)entity).getFilme().getId());
		stmt.setInt(qtdCampos + 2, ((Classificacoes)entity).getUsuario().getId());
	}
	
//	@Override
//	public boolean removeById(int id) {
//		boolean result = false;
//		String query = "DELETE "
//				+     " FROM "  + this.table
//				+     " WHERE " + this.getFiltro();
//		try {
//	          PreparedStatement statement = connection.prepareStatement(query);
//	          statement.setInt(1, id);
//	          statement.execute();
//	          result = true;
//        } catch (Exception e) {
//           e.printStackTrace();
//        }
//		return result;
//	}
	
	@Override
	public boolean remove(IModelo entuty) {
		boolean result = false;
		String query = "DELETE "
				+     " FROM "  + this.table
				+     " WHERE " + this.getFiltro();
		try {
	          PreparedStatement statement = connection.prepareStatement(query);
	          statement.setInt(1, ((Classificacoes)entuty).getFilme().getId());
	          statement.setInt(2, ((Classificacoes)entuty).getUsuario().getId());
	          statement.execute();
	          result = true;
        } catch (Exception e) {
           e.printStackTrace();
        }
		return result;
	}
	
	public List<Classificacoes> getAllByFilmes(int filmeId) {
		List<Classificacoes> registros = new ArrayList<>();
		try {
			PreparedStatement statement = connection.prepareStatement("select * from " + this.table + " where cd_filme =?");
			statement.setInt(1, filmeId);
			ResultSet rs = statement.executeQuery();

			List<Field> camposRegistro = this.getCamposClasse();
			while (rs.next()) {
				Classificacoes registro = new Classificacoes();
				this.loadModel(rs, registro);	
				registros.add(registro);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return registros;
	}
}
