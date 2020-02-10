package com.sf.Hierarchie.vue;

import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class EndGame extends BasicGameState {
    public static final int ID = 3;
    private Image background;
    public StateBasedGame stateBasedGame;

    @Override
    public int getID() {
        return ID;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        this.stateBasedGame = stateBasedGame;
        this.background = new Image("sprites/background.jpg");
    }

    @Override
    public void render(GameContainer container, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        background.draw(0, 0, container.getWidth(), container.getHeight());
        if (Game.score1 < Game.score2) {
            graphics.setColor(Color.white);
            graphics.drawString("LE JOUEUR 2 GAGNE AVEC " + (int)((Game.score2 + 0.1) * 1000) + " POINTS", 380, 50);
            graphics.drawString("La partie a durée : " + Game.gameTime + "secondes", 380, 100);

        } else if (Game.score1 > Game.score2) {
            graphics.setColor(Color.white);
            graphics.drawString("LE JOUEUR 1 GAGNE AVEC " + (int)((Game.score1 + 0.1) * 1000) + " POINTS", 380, 50);
            graphics.drawString("La partie a durée : " + Game.gameTime + "secondes", 380, 100);
        }
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {

    }

    @Override
    public void keyReleased(int key, char c) {
        System.out.println(key);
        if (key == Input.KEY_ENTER){
            stateBasedGame.enterState(MainScreenGameState.ID);
            Game.score1 = 0.9;
            Game.score2 = 0.9;
            Game.x2 = 880;
            Game.y2 = 860;
            Game.direction2 = 2;
            Game.moving2 = false;
            Game.x = 80;
            Game.y = 220;
            Game.direction = 2;
            Game.moving = false;
        }
    }
}