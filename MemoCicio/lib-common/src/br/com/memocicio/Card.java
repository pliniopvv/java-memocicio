/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.memocicio;

import java.io.Serializable;

/**
 *
 * @author GanGss
 */
public class Card implements Serializable {

    public Card(String frente, String verso) {
        this.frente = frente;
        this.verso = verso;
    }

    private String frente;
    private String verso;

    public String getFrente() {
        return frente;
    }

    public void setFrente(String frente) {
        this.frente = frente;
    }

    public String getVerso() {
        return verso;
    }

    public void setVerso(String verso) {
        this.verso = verso;
    }

}
