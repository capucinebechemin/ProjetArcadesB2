package com.sf.Hierarchie.model;

public class Personnage {
    private String nom;
    private int vitesse_personnage;
    private int points_vie;
    private int delai_tir;
    private int puissance_tir;
    private int vitesse_rotaion;
    private int vitesse_projectil;
    private int id;

    public Personnage() {
    }

    public Personnage(String nom, int points_vie, int puissance_tir, int id) {
        this.nom = nom;
        this.vitesse_personnage = vitesse_personnage;
        this.points_vie = points_vie;
        this.delai_tir = delai_tir;
        this.puissance_tir = puissance_tir;
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getVitesse_personnage() {
        return vitesse_personnage;
    }

    public void setVitesse_personnage(int vitesse_personnage) {
        this.vitesse_personnage = vitesse_personnage;
    }

    public int getPoints_vie() {
        return points_vie;
    }

    public void setPoints_vie(int points_vie) {
        this.points_vie = points_vie;
    }

    public int getDelai_tir() {
        return delai_tir;
    }

    public void setDelai_tir(int delai_tir) {
        this.delai_tir = delai_tir;
    }

    public int getPuissance_tir() {
        return puissance_tir;
    }

    public void setPuissance_tir(int puissance_tir) {
        this.puissance_tir = puissance_tir;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVitesse_rotaion() {
        return vitesse_rotaion;
    }

    public void setVitesse_rotaion(int vitesse_rotaion) {
        this.vitesse_rotaion = vitesse_rotaion;
    }

    public int getVitesse_projectil() {
        return vitesse_projectil;
    }

    public void setVitesse_projectil(int vitesse_projectil) {
        this.vitesse_projectil = vitesse_projectil;
    }

    @Override
    public String toString() {
        return nom.toUpperCase();
    }

}