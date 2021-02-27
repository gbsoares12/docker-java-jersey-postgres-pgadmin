package br.udesc.dao;

import java.util.ArrayList;
import java.util.List;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import br.udesc.modelo.*;

public abstract class BaseDao implements IDao {

	protected Connection connection;
	protected String table;
	protected String[] filter;

	public BaseDao() {
		this("", null);
	}

	public BaseDao(String table, String[] idFilter) {
		connection = Conexao.getConnection();
		this.table  = table;
		this.filter = idFilter;
	}

	@Override
	public List getAll() {
		List<IModelo> registros = new ArrayList<>();
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("select * from " + this.table);

			List<Field> camposRegistro = this.getCamposClasse();
			while (rs.next()) {
				IModelo registro = this.newModeloInstance();
				this.loadModel(rs, registro);	
				registros.add(registro);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return registros;
	}

	@Override
	public IModelo getById(int id) {
		String query = "SELECT *"
				+     " FROM "  + this.table
				+     " WHERE " + this.getFiltro();
		IModelo registro = this.newModeloInstance();
		try {
          PreparedStatement statement = connection.prepareStatement(query);
          statement.setInt(1, id);
          ResultSet resultSet = statement.executeQuery();
          while (resultSet.next()) {
              this.loadModel(resultSet, registro);  
          }
        } catch (Exception e) {
           e.printStackTrace();
        }
        return registro;
	}

	@Override
	public boolean add(IModelo entity) {
		boolean result = false;
		try {
			PreparedStatement stmt = connection.prepareStatement(this.getQueryInsert());
			this.bindValue(stmt, entity, false);
	        stmt.execute();
	        result = true;
	    } catch (SQLException e) {
	    	e.printStackTrace();
	    }
		return result;
	}

	@Override
	public boolean update(IModelo entity) {
		boolean result = false;
		try {
			PreparedStatement stmt = connection.prepareStatement(this.getQueryUpdate());
			this.bindValue(stmt, entity, true);
			stmt.execute();	    
			result = true;
	    } catch (SQLException e) {
	    	e.printStackTrace();
	    }
		return result;
	}

	@Override
	public boolean remove(IModelo entuty) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeById(int id) {
		boolean result = false;
		String query = "DELETE "
				+     " FROM "  + this.table
				+     " WHERE " + this.getFiltro();
		try {
	          PreparedStatement statement = connection.prepareStatement(query);
	          statement.setInt(1, id);
	          statement.execute();
	          result = true;
        } catch (Exception e) {
           e.printStackTrace();
        }
		return result;
	}
	
	protected void loadModel(ResultSet rs, IModelo registro) throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Class<?> clazz = this.getClazzReflection();
		List<Field> camposRegistro = this.getCamposClasse();
		for(Field campo: camposRegistro) {
			// pega o nome do atributo
			String nomeCampo = campo.getName();
			/*
			 * concatena o prefixo "set" com o nome do atributo com a primeira letra do
			 * atributo sendo convertida para maiuscula
			 */
			String sMetodo = "set" + this.capitalizeFirstLetter(nomeCampo);
			// pega o metodo a ser invocado da classe
			Method oMetodo = clazz.getMethod(sMetodo, campo.getType());
			
			if(campo.getType().getTypeName().startsWith("br.udesc.modelo")) {
				String[] aCampoNome = campo.getName().split("(?=[A-Z])");
				String className = this.capitalizeFirstLetter(aCampoNome[aCampoNome.length - 1]) + "Dao";
				
				IDao daoClassAninhada = this.newDaoInstance(className);
				IModelo registroAninhado = daoClassAninhada.getById((int)this.getField(rs, campo));
				
				oMetodo.invoke(registro, registroAninhado);
				continue;
			}
			
			oMetodo.invoke(registro, this.getField(rs, campo));
		}
	} 
	
	protected String getFiltro() {
		String filtro = "";
		for(int i = 0; i < this.filter.length; i++) {
			filtro += this.filter[i] + " =? ";
			if(i < this.filter.length - 1) {
				filtro += "AND ";
			}
		}
		return filtro;
	}
	
	/**
	 * Retorna o nome do campo como no banco
	 * 
	 * @param campo - campo da entidade da qual pegar o nome
	 * @return
	 */
	protected String getFieldDbName(Field campo) {
		String[] aCampoNome = campo.getName().split("(?=[A-Z])");
		String campoNome = aCampoNome[aCampoNome.length - 1];
		if(aCampoNome.length > 1) {
			campoNome = aCampoNome[0] + "_";
			for(int i = 1; i < aCampoNome.length; i++) {
				campoNome += aCampoNome[i].toLowerCase();
			}
		}
		if(campo.getType().getTypeName().startsWith("br.udesc.modelo")) {
			campoNome = "cd_" + campoNome;
		}
		return campoNome;
	}
	
    /**
	* Retorna a string com a query de update
	* 
	* @return String 
	*/
	public String getQueryInsert() {
		String query     = "INSERT INTO " + this.table + " ";
		String atributos = "(";
		String params    = "(";
		List<Field> campos = this.getCamposClasse();
		int cont = 1;
		for(Field campo : campos) {
			String campoNome = this.getFieldDbName(campo);
			atributos += campoNome;
			params    += "?";
			if(cont < campos.size()) {
				atributos += ", ";
				params    += ", ";
			}
			cont++;
		}
		atributos += ")";
		params += ")";
		query += atributos + " VALUES " + params;
		return query;
	}
	
	/**
	 * Retorna a string com a query de update
	 * 
	 * @return String 
	 */
	protected String getQueryUpdate() {
		String query     = "UPDATE " + this.table + " SET ";
		String atributos = "";
		List<Field> campos = this.getCamposClasse();
		int cont = 1;
		for(Field campo : campos) {
			String campoNome = this.getFieldDbName(campo);
			atributos += campoNome + "=?";
			if(cont < campos.size()) {
				atributos += ", ";
			}
			cont++;
		}
		query += atributos + " WHERE " + this.getFiltro();
		return query;
	}
	
	protected String capitalizeFirstLetter(String str) {
		return str.substring(0, 1).toUpperCase() + str.substring(1);
	}
	
	protected void bindValue(PreparedStatement stmt, IModelo entity, boolean addWhere) {
		Class<?> clazz = this.getClazzReflection();
		List<Field> campos = this.getCamposClasse();
		int cont = 1;
        for(Field campo : campos) {
        	// pega o nome do atributo
			String nomeCampo = campo.getName();
			/*
			 * concatena o prefixo "get" com o nome do atributo com a primeira letra do
			 * atributo sendo convertida para maiscula
			 */
			String sMetodo = "get" + this.capitalizeFirstLetter(nomeCampo);
			// pega o metodo a ser invocado da classe	
			try {

				Method oMetodo = clazz.getMethod(sMetodo);
				
				if(campo.getType().getTypeName().startsWith("br.udesc.modelo")) {
//					String[] aCampoNome = campo.getName().split("(?=[A-Z])");
//					String className = this.capitalizeFirstLetter(aCampoNome[aCampoNome.length - 1]) + "Dao";
//					
//					IDao daoClassAninhada = this.newDaoInstance(className);
//					IModelo registroAninhado = daoClassAninhada.getById((int)this.getField(rs, campo));
					
					IModelo registroAninhado = (IModelo)oMetodo.invoke(entity);
					
					this.setField(stmt, campo, cont, registroAninhado.getId());
					cont++;
					continue;
				}
				
				if(addWhere) {
					this.addWhere(stmt, entity);
				}
				this.setField(stmt, campo, cont, oMetodo.invoke(entity));
			} catch (Exception e) {
				e.printStackTrace();
			}
			cont++;
        }
	}
	
	public void addWhere(PreparedStatement stmt, IModelo entity) throws SQLException{
		int totalCampos = this.getCamposClasse().size();
		stmt.setInt(totalCampos + 1, entity.getId());
	}
	
	/**
	 * Retorna a string contendo o tipo do campo
	 * 
	 * @param campo
	 * @return String
	 */
	protected String getFieldType(Field campo) {
		String[] aTipoCampo = campo.getType().getTypeName().replace(".", ";").split(";");
		String campoNome = aTipoCampo[aTipoCampo.length - 1];
		if(campo.getType().getTypeName().startsWith("br.udesc.modelo")) {
			campoNome = "int";
		}
		return campoNome;
	}
	
	protected void setField(PreparedStatement stm, Field campo, int position, Object result) {
		String tipoCampo = this.getFieldType(campo);
		
		try {
//			if(campo.getType().getTypeName().startsWith("br.udesc.modelo")) {
//				stm.setInt(position, (int) result);
//			}
			switch (tipoCampo) {
				case "String": {
					stm.setString(position, (String) result);
					break;
				}
				case "int": {
					stm.setInt(position, (int) result);
					break;
				}
				case "boolean": {
					stm.setBoolean(position, (boolean) result);
					break;
				}
				case "Timestamp": {
					stm.setTimestamp(position, (Timestamp) result);
					break;
				}
				default:
					throw new IllegalArgumentException("Tipo do campo inesperado: " + tipoCampo);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	protected Object getField(ResultSet rs, Field campo) {
		// pega o tipo do campo
		String tipoCampo = this.getFieldType(campo);
		// pega o nome do campo
		String campoNome = this.getFieldDbName(campo);
		try {
			switch (tipoCampo) {
			case "String": {
				return rs.getString(campoNome);
			}
			case "int": {
				return rs.getInt(campoNome);
			}
			case "boolean": {
				return rs.getBoolean(campoNome);
			}
			default:
				throw new IllegalArgumentException("Tipo do campo inesperado: " + tipoCampo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Field> getCamposClasse() {
		Class<?> clazz = this.getClazzReflection();
		List<Field> campos = new ArrayList();
		for (Field campo : clazz.getDeclaredFields()) {
			// se o atributo atual não for privado, significa que nao possui set e get, e
			// deve ser pulado
			if (campo.getModifiers() != MethodHandles.Lookup.PRIVATE
					&& campo.getModifiers() != MethodHandles.Lookup.PROTECTED) {
				continue;
			}
			campos.add(campo);
		}
		return campos;
	}
	
	protected IDao newDaoInstance(String className) {
		String pacoteClass = "br.udesc.dao.";
		IDao dao = null;
		try {
			dao = (IDao) this.getClazz(pacoteClass + className).getDeclaredConstructor().newInstance();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dao;
	} 
	
	protected IModelo newModeloInstance() {
		String pacoteClass = "br.udesc.modelo.";
		IModelo model = null;
		try {
			model = (IModelo) this.getClazz(pacoteClass + this.getClassName()).getDeclaredConstructor().newInstance();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return model;
	}
	
	protected Class<?> getClazzReflection() {
		return this.getClazz("br.udesc.modelo." + this.getClassName());
	}

	/**
	 * Obtem a classe de modelo para este objeto
	 * 
	 * @return Class<?>
	 */
	protected Class<?> getClazz(String className) {
		Class<?> clazz = null;
		try {
			clazz = Class.forName(className);
		} catch (ClassNotFoundException e) {
			System.out.println("erro no metodo getClass do base dao");
			e.printStackTrace();
		}
		return clazz;
	}
	
	/**
	 * Obtem o nome do objeto da class ex: Ator 
	 * 
	 * @return String
	 */
	protected String getClassName() {
		// quebra o nome da classe atual pelo ponto
		String[] className = this.getClass().getName().replace(".", ";").split(";");
		// concatena o nome da classe com o nome do pacote dando o caminho da classe
		return className[className.length - 1].replace("Dao", "");
	}

}
