package br.udesc.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import br.udesc.modelo.IModelo;

public interface IDao<T extends IModelo> {
	public List<T> getAll();
	
	public T getById(int id);
	
	public boolean add(T entity);
	
	public boolean update(T entity);
	
	public boolean remove(T entuty);
	
	public boolean removeById(int id);

	public void addWhere(PreparedStatement stmt, IModelo entity) throws SQLException;
}
