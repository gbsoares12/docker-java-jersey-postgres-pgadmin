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
public class Oscares implements IModelo, Serializable{
    private Filme filme;
    private int nrAno;
    
    public Oscares() {}
    
    public int getId() {
    	return 0;
    }

    public Filme getFilme() {
        return filme;
    }

    public void setFilme(Filme filme) {
        this.filme = filme;
    }

    public int getNrAno() {
        return this.nrAno;
    }

    public void setNrAno(int ano) {
        this.nrAno = ano;
    }
}
