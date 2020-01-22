package com.sf.Hierarchie.vue;

import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class MainScreenGameState extends BasicGameState {

    public static final int ID = 1;
    int idMenu = 2;
    private Image background;
    private StateBasedGame game;

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        this.game = game;
        this.background = new Image("sprites/forest.jpg");
    }

    /**
     * Contenons nous d'afficher l'image de fond.
     * Le text est placé approximativement au centre.
     */
    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        background.draw(0, 0, container.getWidth(), container.getHeight());
        g.drawString("BIENVENUE DANS LE DONJON", 380, 300);
    }

    /**
     * Passer à l’écran de jeu à l'appui de n'importe quel touche.
     */
    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {

    }

    @Override
    public void keyReleased(int key, char c) {

        switch (key) {
            case Input.KEY_UP: idMenu += 1;
                break;
            case Input.KEY_DOWN:  idMenu -= 1;
                break;
            case Input.KEY_ENTER: game.enterState(Game.ID);
                break;
        }


        System.out.println(idMenu);
    }

    /**
     * L'identifiant permet d'identifier les différentes boucles.
     * Pour passer de l'une à l'autre.
     */
    @Override
    public int getID() {
        return ID;
    }
}

