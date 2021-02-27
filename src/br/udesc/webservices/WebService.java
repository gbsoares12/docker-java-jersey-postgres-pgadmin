package br.udesc.webservices;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import br.udesc.dao.IDao;
import br.udesc.excecoes.RestException;
import br.udesc.modelo.Cliente;
import br.udesc.modelo.IModelo;

public abstract class WebService implements IWebService {
	protected Gson gson;
	protected IDao dao;
	
	@GET
	@Path("lista")
	@Produces("application/json")
	public Response getAll() {
		return Response.ok(this.getDao().getAll()).build();
	}	
	
	@GET
	@Path("{id}")//o nome "id" pode ser um nome qualquer, ele nao esta relacionado ao nome do parametro no metodo
	@Produces("application/json")
	public Response getById(@PathParam("id")int id) {
		return Response.ok(this.getDao().getById(id)).build();
	}
	
	@DELETE
	@Path("{id}")//o nome "id" pode ser um nome qualquer, ele nao esta relacionado ao nome do parametro no metodo
	@Produces("application/json")
	public Response delete(@PathParam("id")int id) {
		if(!this.getDao().removeById(id)) {
			throw new RestException(500, "erro na remoção");
		}
		return Response.ok("Registro removido com sucesso").build();
	}
	
	protected IDao getDao() {
		if(this.dao == null) {
			this.dao = this.newDaoInstance();
		}
		return this.dao;
	}
	
	protected Gson getGson() {
		if(this.gson == null) {
			this.gson = new Gson();
		}
		return this.gson;
	}
	
	
	protected IDao newDaoInstance() {
		String pacoteClass = "br.udesc.dao.";
		IDao dao = null;
		try {
			dao = (IDao) this.getClazz(pacoteClass + this.getClassName() + "Dao").getDeclaredConstructor().newInstance();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dao;
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
		return className[className.length - 1].replace("Ws", "");
	}
}
