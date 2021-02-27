package br.udesc.dao;

public class ServicoUsadoDao extends BaseDao {
	
	public ServicoUsadoDao() {
		super("servicos_usados", new String[] { "cd_cliente" });
	}
}
