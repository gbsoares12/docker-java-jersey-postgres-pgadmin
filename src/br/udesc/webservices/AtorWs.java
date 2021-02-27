package br.udesc.webservices;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import br.udesc.dao.AtorDao;
import br.udesc.dao.IDao;
import br.udesc.excecoes.RestException;
import br.udesc.modelo.Ator;
import br.udesc.modelo.Genero;

@Path("ator")
public class AtorWs extends WebService {
	
	@POST
	@Consumes("application/json")
	public Response insert(Ator registro) {
		if(!this.getDao().add(registro)) {
			throw new RestException(500, "erro na inserção");
		}
		return Response.ok("Registro add com sucesso").build();
	}
	
	@PUT
	@Consumes("application/json")
	public Response update(Ator registro) {
		if(!this.getDao().update(registro)) {
			throw new RestException(500, "erro na alteração");
		}
		return Response.ok("Registro update com sucesso").build();
	}
	
}
