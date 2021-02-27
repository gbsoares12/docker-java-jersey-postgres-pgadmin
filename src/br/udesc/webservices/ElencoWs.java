package br.udesc.webservices;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import br.udesc.dao.ElencoDao;
import br.udesc.excecoes.RestException;
import br.udesc.modelo.Elenco;

@Path("elenco")
public class ElencoWs extends WebService {
	
	@POST
	@Consumes("application/json")
	public Response insert(Elenco registro) {
		if(!this.getDao().add(registro)) {
			throw new RestException(500, "erro na inserção");
		}
		return Response.ok("Registro add com sucesso").build();
	}
	
	@PUT
	@Consumes("application/json")
	public Response update(Elenco registro) {
		if(!this.getDao().update(registro)) {
			throw new RestException(500, "erro na alteração");
		}
		return Response.ok("Registro update com sucesso").build();
	}
	
	@Override
	public Response getById(int id) {
		return Response.ok("metodo insdisponivel").build();
	}
	
	@Override
	public Response delete(int id) {
		return Response.ok("recurso nao disponivel").build();
	}
	
	@GET
	@Path("filme/{id}")//o nome "id" pode ser um nome qualquer, ele nao esta relacionado ao nome do parametro no metodo
	@Produces("application/json")
	public Response getElencoByFilme(@PathParam("id")int id) {
		return Response.ok(((ElencoDao)this.getDao()).getElencoByFilme(id)).build();
	}
	
}
