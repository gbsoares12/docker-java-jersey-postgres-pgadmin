package br.udesc.modelo;

import java.io.Serializable;
import java.sql.Timestamp;

public class ServicoUsado implements IModelo, Serializable{

	private int cd_usuario;
	private int cd_servico;
	/*
	 * cd_servico 1 = incluir classificacao
	 * cd_servico 2 = alterar classificacao
	 * cd_servico 3 = excluir classificacao
	 * cd_servico 4 = iniciar filme
	 * */
	private Timestamp dt_momento;

	public ServicoUsado(int cd_usuario, int cd_servico, Timestamp dt_momento) {
		super();
		this.cd_usuario = cd_usuario;
		this.cd_servico = cd_servico;
		this.dt_momento = dt_momento;
	}

	public int getCd_usuario() {
		return cd_usuario;
	}

	public void setCd_usuario(int cd_usuario) {
		this.cd_usuario = cd_usuario;
	}

	public int getCd_servico() {
		return cd_servico;
	}

	public void setCd_servico(int cd_servico) {
		this.cd_servico = cd_servico;
	}

	public Timestamp getDt_momento() {
		return dt_momento;
	}

	public void setDt_momento(Timestamp dt_momento) {
		this.dt_momento = dt_momento;
	}

	@Override
	public int getId() {
		return 0;
	}

}
