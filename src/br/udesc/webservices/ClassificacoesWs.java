package br.udesc.webservices;

import java.net.http.HttpHeaders;
import java.util.Base64;
import java.util.List;
import java.util.StringTokenizer;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import br.udesc.dao.ClassificacoesDao;
import br.udesc.dao.UsuarioDao;
import br.udesc.excecoes.RestException;
import br.udesc.modelo.Classificacoes;
import br.udesc.modelo.Filme;
import br.udesc.modelo.Usuario;
import br.udesc.utils.AuthenticationUtils;

@Path("classificacao")
public class ClassificacoesWs extends WebService {
	
	@POST
	@Consumes("application/json")
	public Response insert(Classificacoes registro, @Context HttpHeaders headers) {
		if(!this.getDao().add(registro)) {
			throw new RestException(500, "erro na inserção");
		}
		return Response.ok("Registro add com sucesso").build();
	}
	
	@PUT
	@Consumes("application/json")
	public Response update(Classificacoes registro) {
		if(!this.getDao().update(registro)) {
			throw new RestException(500, "erro na alteração");
		}
		return Response.ok("Registro update com sucesso").build();
	}
	
	@DELETE
	@Produces("application/json")
	public Response delete(Classificacoes registro) {
		if(!((ClassificacoesDao)this.getDao()).remove(registro)) {
			throw new RestException(500, "erro na remoção");
		}
		return Response.ok("Registro removido com sucesso").build();
	}
	
	@Override
	public Response getById(int id) {
		return Response.ok("metodo insdisponivel").build();
	}
	
}
