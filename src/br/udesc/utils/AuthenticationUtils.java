package br.udesc.utils;

import java.lang.reflect.Method;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.container.ResourceInfo;

import br.udesc.modelo.Usuario;

public class AuthenticationUtils {

	private static Usuario userLogged;
	
	private static ResourceInfo _resourceInfo;
	
	
	private static Map< String, Usuario> usuarios = new HashMap<>();
	
	static {
		Usuario a = new Usuario("a", "a");
		a.setCdUsuario(1);
		usuarios.put("a", a); // YTph
		usuarios.put("b", new Usuario("b", "b")); // Yjpi
		Usuario u = new Usuario("c", "c");
		usuarios.put("c", u); // Yzpj
		
	}
	
	public static Usuario getUserLogado() {
		return userLogged;
	}
	
	public static boolean isUsuarioAutenticado(String authString){
        
		if (authString == null)
			return false;
		
        try {
            String decodedAuth = "";
            // Header is in the format "Basic 5tyc0uiDat4"
            // We need to extract data before decoding it back to original string
            String[] authParts = authString.split("\\s+");
            String authInfo = authParts[1];
            // Decode the data back to original string
            byte[] bytes = null;
            bytes = Base64.getDecoder().decode(authInfo);
            decodedAuth = new String(bytes);
            
	        StringTokenizer tokenizer = new StringTokenizer(decodedAuth, ":");
	        String username = tokenizer.nextToken();
	        String password = tokenizer.nextToken();
        
	        String[] roles;
	        if (_resourceInfo != null) {
	        	
	        	Method method = _resourceInfo.getResourceMethod();
	        	RolesAllowed[] anotacoes = method.getAnnotationsByType(RolesAllowed.class);
	        	if (anotacoes.length == 1) {
	        		roles = anotacoes[0].value();
	        	} else {
	        		roles = new String[] {};
	        	}
	        	
	        } else {
	        	roles = new String[] {};
	        }
	        
	        /**
	         * aqui voce vai incluir a sua logica para validar o usuario e a senha.
	         * Por exemplo, puxar do BD. Eu estou usando Map soh para exemplificar.
	         */
	        
	        Usuario usuario = usuarios.get(username);
	        userLogged = usuario;
	        
	        if ( usuario != null && usuario.getDsSenha().equals(password)) {
	        
	        	if (roles.length > 0) {
		        	
//		        	for (String role:roles) {
//		        		if (usuario.hasRole(role)) 
//		        			return true;
//		        	}
		        	
		        	return false;
	        	}
	        	
	            return true;
	        } else {
	        	return false;
	        }
        } catch (Exception e) {
        	return false;
        }
         
    }

	public static boolean isMetodoPermitido() {
		
		Method method = _resourceInfo.getResourceMethod();
    	RolesAllowed[] anotacoes = method.getAnnotationsByType(RolesAllowed.class);
    	
		return anotacoes.length == 0;
	}
	
	public static void setResourceInfo(ResourceInfo resourceInfo) {
		
		_resourceInfo = resourceInfo;
		
	}

}
