package com.sf.Hierarchie.vue;

import com.sf.Hierarchie.model.LienBDD;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class MainScreenGameState extends BasicGameState {

    public static final int ID = 1;
    int idMenu = 2;
    private Image background;
    private StateBasedGame game;

    private FOptions fenetre;
    private Fenetre instruction;

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        this.game = game;
        this.background = new Image("sprites/background.png");
    }

    /**
     * Contenons nous d'afficher l'image de fond.
     * Le text est placé approximativement au centre.
     */
    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        background.draw(0, 0, container.getWidth(), container.getHeight());
        g.drawString("BIENVENUE DANS LE DONJON", 380, 100);
        g.drawString("APPUYER SUR LE BOUTON 1 POUR COMMENCER", 330, 200);
        g.drawString("APPUYER SUR LE BOUTON 2 POUR VOIR LES OPTIONS", 310, 300);
        g.drawString("APPUYER SUR LE BOUTON 3 POUR VOIR LES INSTRUCTIONS", 310, 400);


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
            case Input.KEY_ENTER: game.enterState(Game.ID);
                break;
            case Input.KEY_I: Fenetre instruction = new Fenetre();
                break;
            case Input.KEY_O : FOptions options = new FOptions();
                break;

        }


        System.out.println(idMenu);
    }

    @Override
    public void controllerButtonPressed(int controller, int button) {
        System.out.println(controller);
        System.out.println(button);
        switch (controller){
            case 1 : {
                switch (button) {
                    case 1: game.enterState(Game.ID);
                        break;
                    case 2: FOptions options = new FOptions();
                        break;
                    case 3: Fenetre instruction = new Fenetre();
                        break;
                    case 4: System.out.println("test bouton bleu");
                        break;
                }
                break;
            }
            case 2 :
                System.out.println("test bouton rouge");
                break;


        }
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

