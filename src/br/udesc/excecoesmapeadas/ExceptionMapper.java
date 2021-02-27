package br.udesc.excecoesmapeadas;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import br.udesc.excecoes.RestException;

@Provider
public class ExceptionMapper implements javax.ws.rs.ext.ExceptionMapper<RestException> {

	@Override
	public Response toResponse(RestException ex) {
		return Response.status(ex.getCodigo()).entity(ex.getMensagem()).build();
	}

}
