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
public class Genero implements IModelo, Serializable{
    
    private int    cdGenero;
    private String dsNome;
    
    public Genero() {}
    
    public Genero(int cdGenero, String dsNome) {
        this.cdGenero = cdGenero;
        this.dsNome = dsNome;
    }
    
    public int getId() {
    	return this.cdGenero;
    }

    public int getCdGenero() {
        return cdGenero;
    }

    public void setCdGenero(int cdGenero) {
        this.cdGenero = cdGenero;
    }

    public String getDsNome() {
        return dsNome;
    }

    public void setDsNome(String dsNome) {
        this.dsNome = dsNome;
    }
}