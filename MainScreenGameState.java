package com.sf.Hierarchie.vue;

import com.sf.Hierarchie.model.LienBDD;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;

public class MainScreenGameState extends BasicGameState {

    public static final int ID = 1;
    private Image background;
    private StateBasedGame game;
    private FOptions fenetre;
    private Fenetre instruction;
    ArrayList listescore = LienBDD.selectScore();
    private String first = "";

    public static float vitesseJ1 = 0;
    public static float vitesseJ2 = 0;

    public static float puissanceJ1 = 0;
    public static float puissanceJ2 = 0;

    public static float vitesseTirJ1 = 0;
    public static float vitesseTirJ2 = 0;

    public static double score1 = 0;
    public static double score2 = 0;

    public static Chrono chrono = new Chrono();


    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        this.game = game;
        this.background = new Image("sprites/background.jpg");
    }

    /**
     * Contenons nous d'afficher l'image de fond.
     * Le text est placé approximativement au centre.
     */

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        background.draw(0, 0, container.getWidth(), container.getHeight());
        g.drawString("BIENVENUE DANS LE DONJON", 380, 100);
        g.drawString("APPUYER SUR ENTRER POUR COMMENCER", 330, 200);
        g.drawString("APPUYER SUR o POUR VOIR LES OPTIONS", 310, 300);
        g.drawString("APPUYER SUR i POUR VOIR LES INSTRUCTIONS", 310, 400);

        g.drawString("TABLEAU DES MEILLEURS SCORES", 380, 500);

        int y = 600;
        for(int i = 0; i < 5; i++) {
            try {
                first = (String) listescore.get(i);
            }catch (Exception e){
                first = "--------------";
            }
            g.drawString(i+1 + " " + first, 200, y);
            y = y + 50;
        }

        int y2 = 600;
        for(int i = 5; i < 10; i++) {
            try {
                first = (String) listescore.get(i);
            }catch (Exception e){
                first = "--------------";
            }
            g.drawString(i+1 + " " + first, 550, y2);
            y2 = y2 + 50;
        }
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
                chrono.start();
                vitesseJ1 = (float)LienBDD.selectVitesse(1) / 10;
                vitesseJ2 = (float)LienBDD.selectVitesse(2) / 10;

                puissanceJ1 = (float)LienBDD.selectPuissance(1) / 100;
                puissanceJ2 = (float)LienBDD.selectPuissance(2) / 100;

                vitesseTirJ1 = (float)LienBDD.selectVitesseTir(1);
                vitesseTirJ2 = (float)LienBDD.selectVitesseTir(2);

                score1 = (float)LienBDD.selectLife(1) / 100 - 0.1;
                score2 = (float)LienBDD.selectLife(2) / 100 - 0.1;
                break;
            case Input.KEY_I: Fenetre instruction = new Fenetre();
                break;
            case Input.KEY_O: FOptions options = new FOptions();
                break;

        }
    }

    @Override
    public void controllerButtonPressed(int controller, int button) {
        System.out.println(controller);
        System.out.println(button);
        switch (controller){
            case 0 : {
                switch (button) {
                    case 2: game.enterState(Game.ID);
                        chrono.start();
                        vitesseJ1 = (float)LienBDD.selectVitesse(1) / 10;
                        vitesseJ2 = (float)LienBDD.selectVitesse(2) / 10;

                        puissanceJ1 = (float)LienBDD.selectPuissance(1) / 100;
                        puissanceJ2 = (float)LienBDD.selectPuissance(2) / 100;

                        vitesseTirJ1 = (float)LienBDD.selectVitesseTir(1);
                        vitesseTirJ2 = (float)LienBDD.selectVitesseTir(2);

                        score1 = (float)LienBDD.selectLife(1) / 100 - 0.1;
                        score2 = (float)LienBDD.selectLife(2) / 100 - 0.1;
                        break;
                    case 1: FOptions options = new FOptions();
                        break;
                    case 3: Fenetre instruction = new Fenetre();
                        break;
                    case 4: System.out.println("test bouton bleu");
                        break;
                }
                break;
            }
            case 1 :
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

