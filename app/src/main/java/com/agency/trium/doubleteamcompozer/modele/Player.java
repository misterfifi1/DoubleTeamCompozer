package com.agency.trium.doubleteamcompozer.modele;

import java.io.Serializable;

/**
 * Created by dev-w8-gfi on 06/08/2014.
 */
public class Player implements Serializable{

    private String pseudo;
    private int note;
    int equipe;


    public Player(){
        this.note = 1;
    }

    public Player(String pseudo){
        this.pseudo = pseudo;
        this.note = 1;
    }

    public Player(String pseudo,int note){
        this.pseudo = pseudo;
        this.note = note;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public int getEquipe() {
        return equipe;
    }

    public void setEquipe(int equipe) {
        this.equipe = equipe;
    }

    @Override
    public String toString() {
        return "Player{" +
                "pseudo='" + pseudo + '\'' +
                ", note=" + note +
                ", equipe=" + equipe +
                '}';
    }
}
