package com.sf.Hierarchie.vue;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;


public class Shoot {

    private Vector2f pos;
    private Vector2f vitesse;
    private int visible;
    private boolean active = true;
    private static int MAX_VISIBLE = 200;

    public Shoot(Vector2f pos, Vector2f vitesse){
        this.pos = pos;
        this.vitesse = vitesse;
    }

    public void update(int deplacement){
        if(active){
            Vector2f vitesseReal = vitesse.copy();
            vitesseReal.scale((deplacement/1000.0f));
            pos.add(vitesseReal);

            visible += 1;
            if(visible > MAX_VISIBLE){
                active = false;
            }
        }
    }

    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException{
        graphics.setColor(Color.white);
        graphics.fillOval(pos.getX()-10,pos.getY()-10,20,20);
    }

    public boolean isActive(){
        return active;
    }
}
