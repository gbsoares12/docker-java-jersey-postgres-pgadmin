/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.modelo;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Andrew, Viviane
 */
public class Filme implements IModelo, Serializable {
    private int cdFilme;
    private String dsNome;
    private int nrDuracaoMin;
    private Genero genero;
    
    public Filme() {}
    
    public Filme(int cdFilme, String dsNome, int nrDuracaoMin, Genero genero) {
        this.cdFilme = cdFilme;
        this.dsNome = dsNome;
        this.nrDuracaoMin = nrDuracaoMin;
        this.genero  = genero;
    }
    
    public int getId() {
    	return this.cdFilme;
    }
    
    public int getCdFilme() {
        return cdFilme;
    }

    public void setCdFilme(int cdFilme) {
        this.cdFilme = cdFilme;
    }

    public String getDsNome() {
        return dsNome;
    }

    public void setDsNome(String dsNome) {
        this.dsNome = dsNome;
    }

    public int getNrDuracaoMin() {
        return nrDuracaoMin;
    }

    public void setNrDuracaoMin(int nrDuracaoMin) {
        this.nrDuracaoMin = nrDuracaoMin;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }
    
    
}
