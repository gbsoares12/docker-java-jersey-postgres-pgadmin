/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.modelo;

import java.io.Serializable;

/**
 *
 * @author Andrew, Viviane
 */
public class Ator implements IModelo, Serializable{
    
    private int    cdAtor;
    private String dsNome;
    
    public Ator(){}
    
    public Ator(int cdAtor, String dsNome) {
        this.cdAtor = cdAtor;
        this.dsNome = dsNome;
    }
    
    public int getId() {
    	return cdAtor;
    }

    public int getCdAtor() {
        return cdAtor;
    }

    public void setCdAtor(int cdAtor) {
        this.cdAtor = cdAtor;
    }

    public String getDsNome() {
        return dsNome;
    }

    public void setDsNome(String dsNome) {
        this.dsNome = dsNome;
    }
    
}
