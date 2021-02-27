package br.udesc.webservices;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import br.udesc.dao.ClassificacoesDao;
import br.udesc.excecoes.RestException;
import br.udesc.modelo.Classificacoes;

@Path("classificacao")
public class ClassificacoesWs extends WebService {

	@POST
	@Consumes("application/json")
	public Response insert(Classificacoes registro) {
		try {
			Classificacoes newClass = ((ClassificacoesDao) this.getDao()).insertClassificacao(registro);
			return Response.ok(newClass).build();
		} catch (Exception e) {
			throw new RestException(500, "erro na inserção");
		}

	}

	@PUT
	@Consumes("application/json")
	public Response update(Classificacoes registro) {
		try {
			Classificacoes newClass = ((ClassificacoesDao) this.getDao()).updateClassificacao(registro);
			return Response.ok(newClass).build();
		} catch (Exception e) {
			throw new RestException(500, "Erro na alteração");
		}
	}

	@DELETE
	@Path("filme/{idFilme}/cliente/{idUsuario}")
	@Produces("application/json")
	public Response delete(@PathParam("idFilme") int idFilme, @PathParam("idUsuario") int idUsuario) {
		if (!((ClassificacoesDao) this.getDao()).remove(idFilme, idUsuario)) {
			throw new RestException(404, "Classificação não encontrada");
		}
		return Response.ok("Registro removido com sucesso").build();
	}

	@Override
	public Response getById(int id) {
		throw new RestException(404, "Método não encontrado");
	}
	
	@Override
	public Response getAll() {
		throw new RestException(404, "Método não encontrado");
	}
	
	
}
