package br.udesc.webservices;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import br.udesc.dao.FilmeDao;
import br.udesc.dao.GeneroDao;
import br.udesc.dao.IDao;
import br.udesc.excecoes.FilmeNaoEncontradoParaIniciarRestException;
import br.udesc.excecoes.RestException;
import br.udesc.modelo.Ator;
import br.udesc.modelo.Filme;

@Path("filme")
public class FilmeWs extends WebService {

	@POST
	@Consumes("application/json")
	public Response insert(Filme registro) {
		if (!this.getDao().add(registro)) {
			throw new RestException(500, "erro na inserção");
		}
		return Response.ok("Registro add com sucesso").build();
	}

	@GET
	@Path("iniciarFilme/filme/{idFilme}/cliente/{idCliente}")
	@Consumes("application/json")
	public Response iniciarFilme(@PathParam("idFilme") int idFilme, @PathParam("idCliente") int idCliente) {
		try {
			Filme result = ((FilmeDao) this.getDao()).iniciarFilme(idFilme, idCliente);
			return Response.ok(result).build();
		} catch (Exception e) {
			throw new FilmeNaoEncontradoParaIniciarRestException();
		}		
	}

	@PUT
	@Consumes("application/json")
	public Response update(Filme registro) {
		if (!this.getDao().update(registro)) {
			throw new RestException(500, "erro na alteração");
		}
		return Response.ok("Registro update com sucesso").build();
	}

	/**
	 * retorna filme com a classificacao
	 */
	@GET
	@Path("listaClassificacao")
	@Produces("application/json")
	public Response getAllClassification() {
		return Response.ok(((FilmeDao) this.getDao()).getAllWithClassification()).build();
	}

}
