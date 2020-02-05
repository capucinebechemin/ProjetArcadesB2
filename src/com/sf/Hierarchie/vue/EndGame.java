package com.sf.Hierarchie.vue;

import com.sf.Hierarchie.controler.Main;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class EndGame extends BasicGameState {
    public static final int ID = 3;
    private Image background;


    @Override
    public int getID() {
        return ID;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        this.background = new Image("sprites/forest.jpg");
    }

    @Override
    public void render(GameContainer container, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        background.draw(0, 0, container.getWidth(), container.getHeight());
        if(Game.score1 < Game.score2){
            graphics.setColor(Color.white);
            graphics.drawString("LE JOUEUR 2 GAGNE AVEC " + (Game.score2 + 0.1) * 1000 + " POINTS", 380, 50);
        }else if(Game.score1 > Game.score2){
            graphics.drawString("LE JOUEUR 1 GAGNE AVEC " + (Game.score1 + 0.1) * 1000 + " POINTS", 380, 50);

        }
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {

        //stateBasedGame.enterState(MainScreenGameState.ID);
    }
}
