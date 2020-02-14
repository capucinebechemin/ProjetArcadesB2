package com.sf.Hierarchie.vue;

import com.sf.Hierarchie.controler.Main;
import com.sf.Hierarchie.model.LienBDD;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.text.DecimalFormat;

public class EndGame extends BasicGameState {
    public static final int ID = 3;
    private Image background;
    public StateBasedGame stateBasedGame;
    DecimalFormat df = new DecimalFormat("0");
    /**
     *Récupération des caractéristiques des joueurs en début de partie
     */

    private int ScoreBase1 = Integer.parseInt(df.format((float)LienBDD.selectLife(1)));
    private int ScoreBase2 = Integer.parseInt(df.format((float)LienBDD.selectLife(2)));
    int DifScore1 = 0;
    int DifScore2 = 0;

    private int VitesseBase1 = Integer.parseInt(df.format((float)LienBDD.selectVitesse(1)));
    private int VitesseBase2 = Integer.parseInt(df.format((float)LienBDD.selectVitesse(2)));
    int DifVitesse1 = 0;
    int DifVitesse2 = 0;

    private int VitesseTirBase1 = Integer.parseInt(df.format((float)LienBDD.selectVitesseTir(1)));
    private int VitesseTirBase2 = Integer.parseInt(df.format((float)LienBDD.selectVitesseTir(2)));
    int DifVitesseTir1 = 0;
    int DifVitesseTir2 = 0;

    private int score2;
    private int score1;
    private Image gameover;


    @Override
    public int getID() {
        return ID;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        this.stateBasedGame = stateBasedGame;
        this.background = new Image("sprites/background.jpg");
        this.gameover = new Image("sprites/game_over.png");

        if (MainScreen.score1 > MainScreen.score2){
            MainScreen.score2 = 0;
        }else if (MainScreen.score1 < MainScreen.score2){
            MainScreen.score1 = 0;
        }
        /**
         *Calcul des points bonus en fonction des difference de caractéristiques
         */
        if (ScoreBase2 > ScoreBase1){
            DifScore1 = Math.abs(ScoreBase1 - ScoreBase2);
            System.out.println("Le score base 2 est plus grand");
        }else if (ScoreBase2 < ScoreBase1){
            DifScore2 = Math.abs(ScoreBase1 - ScoreBase2);
            System.out.println("Le score base 2 est plus petit");
        }

        if (VitesseBase2 > VitesseBase1){
            DifVitesse1 = Math.abs(VitesseBase2 - VitesseBase1);
        }else if (VitesseBase2 < VitesseBase1){
            DifVitesse2 = Math.abs(VitesseBase2 - VitesseBase1);
        }

        if (VitesseTirBase2 > VitesseTirBase1){
            DifVitesseTir1 = Math.abs(VitesseTirBase2 - VitesseTirBase1);
        }else if (VitesseTirBase2 < VitesseTirBase1){
            DifVitesseTir2 = Math.abs(VitesseTirBase2 - VitesseTirBase1);
        }

    }
    /**
     * Affichage du tableau de fin de partie
     */

    @Override
    public void render(GameContainer container, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        background.draw(0, 0, container.getWidth(), container.getHeight());
        graphics.setColor(Color.white);
        graphics.drawImage(gameover, 275, 15);
        graphics.drawString("Koopa", 100, 550);
        graphics.drawString("Point de vie : " + ScoreBase1, 100, 600);
        graphics.drawString("Vitesse joueur : " + MainScreen.vitesseJ1 *10, 100, 650);
        graphics.drawString("Puissance tir : " + MainScreen.puissanceJ1 *100, 100, 700);
        graphics.drawString("Vitesse tir : " + MainScreen.vitesseTirJ1 *100, 100, 750);
        graphics.drawString("James", 500, 550);
        graphics.drawString("Point de vie : " + ScoreBase2, 500, 600);
        graphics.drawString("Vitesse joueur : " + MainScreen.vitesseJ2*10, 500, 650);
        graphics.drawString("Puissance tir : " + MainScreen.puissanceJ2*100, 500, 700);
        graphics.drawString("Vitesse tir : " + MainScreen.vitesseTirJ2*100, 500, 750);

        graphics.drawString("Temps de jeu : " + Game.gameTime + " secondes", 300, 800);

        if (MainScreen.score1 < MainScreen.score2) {
            //Calcul du score final du vainqueur
            score2 = (int)(((int)(MainScreen.score2 + 0.1)) * 1000 + (Game.gameTime * 10) + (DifScore2 * 10)
                    + (DifVitesse2 * 10) + (DifVitesseTir2 * 10));
            graphics.drawString("JAMES GAGNE AVEC " + score2 + " POINTS", 300, 850);

        } else if (MainScreen.score1 > MainScreen.score2) {
            //vie de fin de partie + temps de jeu + difference des points de vie en debut de partie si plus petits points de vie + points bonus si puissance de tir inferieure + points bonus si vitesse de tir inferieure
            score1 = (int)(((int)(MainScreen.score1 + 0.1)) * 1000 + (Game.gameTime * 10) + (DifScore1 * 10) + (DifVitesse1 * 10) + (DifVitesseTir1 * 10));
            graphics.drawString("KOOPA GAGNE AVEC " + score1 + " POINTS", 300, 850);
        }
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {

    }

    /**
     * Fonction de fin de partie
     */

    @Override
    public void controllerButtonPressed(int controller, int button) {
        System.out.println(controller);
        System.out.println(button);
        switch (controller){
            case 3 : {
                switch (button) {
                    case 2:
                        //AJout dans la base de données du score du vainqueur
                        if (MainScreen.score1 < MainScreen.score2) {

                            LienBDD.insertScore("James", score2);
                            System.out.println(score2);
                        }
                        else {
                            LienBDD.insertScore("Koopa", score1);
                            System.out.println(score1);
                        }
                        //Remise à zéro des données du jeu
                        Main.listescore = LienBDD.selectScore();
                        stateBasedGame.enterState(MainScreen.ID);
                        MainScreen.score1 = 0.9;
                        MainScreen.score2 = 0.9;
                        Game.x2 = 880;
                        Game.y2 = 860;
                        Game.direction2 = 2;
                        Game.moving2 = false;
                        Game.x = 80;
                        Game.y = 220;
                        Game.direction = 2;
                        Game.moving = false;
                        break;
                }
                break;
            }
        }
    }

    @Override
    public void keyReleased(int key, char c) {
        System.out.println(key);
        if (key == Input.KEY_ENTER){
            if (MainScreen.score1 < MainScreen.score2) {
                LienBDD.insertScore("James", score2);
            }
            else {
                LienBDD.insertScore("Koopa", score1);
            }
            Main.listescore = LienBDD.selectScore();
            stateBasedGame.enterState(MainScreen.ID);
            MainScreen.score1 = 0.9;
            MainScreen.score2 = 0.9;
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