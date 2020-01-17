package com.sf.Hierarchie.vue;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;


public class Shoot{

    private org.newdawn.slick.Image bullet;

    public void init(int player) throws SlickException {
        if (player == 1){
            this.bullet = new org.newdawn.slick.Image("sprites/bullet.jpg");
        }else{
            this.bullet = new org.newdawn.slick.Image("sprites/bullet.jpg");
        }
    }


    public void render(Graphics g, float x, float y) {
        g.resetTransform();
        g.drawImage(this.bullet,x,y);
    }
}
