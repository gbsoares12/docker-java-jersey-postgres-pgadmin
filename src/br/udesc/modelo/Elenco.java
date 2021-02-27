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
public class Elenco implements IModelo, Serializable{
    private Ator ator;
    private Filme filme;
    private boolean snPrincipal;
    
    public Elenco() {}
    
    public Elenco(Ator ator, Filme filme) {
        this.ator  = ator;
        this.filme = filme;
    }
    
    public int getId() {
    	return 0;
    }

    public Ator getAtor() {
        return ator;
    }

    public void setAtor(Ator ator) {
        this.ator = ator;
    }

    public Filme getFilme() {
        return filme;
    }

    public void setFilme(Filme filme) {
        this.filme = filme;
    }

    public boolean getSnPrincipal() {
        return snPrincipal;
    }

    public void setSnPrincipal(boolean snPrincipal) {
        this.snPrincipal = snPrincipal;
    }

    
}
