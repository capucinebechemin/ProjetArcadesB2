package com.sf.Hierarchie.vue;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Hud {
    private Image playerbars;

    public void init(int player) throws SlickException {
        if (player == 1){
            this.playerbars = new Image("sprites/barre-de-vie.png");
        }else{
            this.playerbars = new Image("sprites/barre-de-vie-2.png");
        }
    }

    private static final int BAR_WIDTH = 200;
    private static final int BAR_HEIGHT = 40;

    private static final Color LIFE_GOOD = new Color(86, 235, 57);
    private static final Color LIFE_BECAREFUL = new Color(235, 232, 57);
    private static final Color LIFE_SOSO = new Color(235, 151, 57);
    private static final Color LIFE_BAD = new Color(229, 42, 13);


    public void render(Graphics g, int player, float vie) {
        g.resetTransform();

        if (player == 1){
            if (vie >= 0.8f){
                g.setColor(LIFE_GOOD);
            }else if(vie >= 0.6f){
                g.setColor(LIFE_BECAREFUL);
            }else if(vie >= 0.4f){
                g.setColor(LIFE_SOSO);
            }else{
                g.setColor(LIFE_BAD);
            }
            g.fillRect(125, 40, vie * BAR_WIDTH, BAR_HEIGHT);
            g.drawImage(this.playerbars, 115, 20);

        }else{
            if (vie >= 0.8){
                g.setColor(LIFE_GOOD);
            }else if(vie >= 0.6){
                g.setColor(LIFE_BECAREFUL);
            }else if(vie >= 0.4){
                g.setColor(LIFE_SOSO);
            }else{
                g.setColor(LIFE_BAD);
            }
            g.fillRect(625, 40, vie * BAR_WIDTH, BAR_HEIGHT);
            g.drawImage(this.playerbars, 615, 20);

        }

    }

}
