package br.udesc.webservices;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import br.udesc.excecoes.RestException;
import br.udesc.modelo.Oscares;
import br.udesc.modelo.Usuario;

@Path("oscar")
public class OscaresWs extends WebService {

	@POST
	@Consumes("application/json")
	public Response insert(Oscares registro) {
		if(!this.getDao().add(registro)) {
			throw new RestException(500, "erro na inserção");
		}
		return Response.ok("Registro add com sucesso").build();
	}
	
	@PUT
	@Consumes("application/json")
	public Response update(Oscares registro) {
		if(!this.getDao().update(registro)) {
			throw new RestException(500, "erro na alteração");
		}
		return Response.ok("Registro update com sucesso").build();
	}
}
