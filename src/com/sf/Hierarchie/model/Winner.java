package com.sf.Hierarchie.model;
//Elements liés à l'entrée du score du gagnant de la partie
public class Winner {
    private String pseudo;
    private int best_score;
    private int id;


    public Winner() { }

    public Winner(String pseudo, int best_score) {
        this.pseudo = pseudo;
        this.best_score = best_score;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public int getBest_score() {
        return best_score;
    }

    public void setBest_score(int best_score) {
        this.best_score = best_score;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
