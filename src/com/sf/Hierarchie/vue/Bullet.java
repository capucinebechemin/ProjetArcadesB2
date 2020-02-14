package com.sf.Hierarchie.vue;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.geom.Ellipse;
import org.newdawn.slick.geom.Shape;

import java.awt.image.BufferedImage;
import java.sql.SQLOutput;

public class Bullet {

    private Image missile;
    private float xPos;
    private float yPos;
    private Shape missileHB;
    private boolean isShot = false;

    public boolean isShot() {
        return isShot;
    }

    public void setShot(boolean shot) {
        isShot = shot;
    }

    //initialisation d'un missile
    Bullet() throws SlickException {
        missile = new Image("sprites/bullet.png");
        xPos = 0;
        yPos = 0;
        missileHB = new Ellipse((int)xPos, (int)yPos, 7, 15);
    }

    //copy du missile
    Bullet(Bullet copy) {
        xPos = copy.getX();
        yPos = copy.getY();
        missile = copy.getImage();
        missileHB = copy.getHB();
    }

    public Shape getHB() {
        return missileHB;
    }

    public void setHB(Shape hitbox) {
        missileHB = hitbox;
    }

    public float getX() {
        return xPos;
    }

    public float getY() {
        return yPos;
    }

    public void setX(float x) {
        this.xPos = x;
    }

    public void setY(float y) {
        this.yPos = y;
    }

    public Image getImage() {
        return missile;
    }

}