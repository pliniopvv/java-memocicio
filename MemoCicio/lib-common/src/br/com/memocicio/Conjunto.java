/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.memocicio;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author GanGss
 */
public class Conjunto implements Serializable {

    private String nome;
    private ArrayList<Card> lista = new ArrayList<>();

    public Conjunto(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ArrayList<Card> getLista() {
        return lista;
    }

    public void setLista(ArrayList<Card> lista) {
        this.lista = lista;
    }

}
