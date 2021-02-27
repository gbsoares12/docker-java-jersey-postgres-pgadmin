package br.udesc.suporte;

import org.glassfish.jersey.server.ResourceConfig;

public class ServerConfig extends ResourceConfig {

	public ServerConfig() {
        register(AuthenticationFilter.class);
    }
	
}
