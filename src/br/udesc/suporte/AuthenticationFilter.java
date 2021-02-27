package br.udesc.suporte;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import br.udesc.utils.AuthenticationUtils;

public class AuthenticationFilter implements ContainerRequestFilter {

	@Context
    private ResourceInfo resourceInfo;
	
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		
		MultivaluedMap<String, String> headers = requestContext.getHeaders();
		List<String> authorization = headers.get("Authorization");
		
		String authString = null;
		if (authorization != null && authorization.size() > 0) {
			authString = authorization.get(0);
		}
		
		AuthenticationUtils.setResourceInfo(resourceInfo);
		if (!AuthenticationUtils.isMetodoPermitido()) {
			if (!AuthenticationUtils.isUsuarioAutenticado(authString)) {
				requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
			}
		}
	}

}
