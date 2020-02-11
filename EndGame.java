package com.sf.Hierarchie.vue;

import com.sf.Hierarchie.model.LienBDD;
import org.newdawn.slick.*;
import org.newdawn.slick.opengl.PNGImageData;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class EndGame extends BasicGameState {
    public static final int ID = 3;
    private Image background;
    public StateBasedGame stateBasedGame;
    DecimalFormat df = new DecimalFormat("0");
    private int ScoreBase1 = Integer.parseInt(df.format((float)LienBDD.selectLife(1)));
    private int ScoreBase2 = Integer.parseInt(df.format((float)LienBDD.selectLife(2)));

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
    }

    @Override
    public void render(GameContainer container, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        background.draw(0, 0, container.getWidth(), container.getHeight());
        graphics.setColor(Color.white);
        graphics.drawImage(gameover, 275, 15);
        graphics.drawString("Koopa", 100, 550);
        graphics.drawString("Point de vie : " + ScoreBase1, 100, 600);
        graphics.drawString("Vitesse joueur : " + MainScreenGameState.vitesseJ1 *10, 100, 650);
        graphics.drawString("Puissance tir : " + MainScreenGameState.puissanceJ1 *100, 100, 700);
        graphics.drawString("James", 500, 550);
        graphics.drawString("Point de vie : " + ScoreBase2, 500, 600);
        graphics.drawString("Vitesse joueur : " + MainScreenGameState.vitesseJ2*10, 500, 650);
        graphics.drawString("Puissance tir : " + MainScreenGameState.puissanceJ2*100, 500, 700);
        graphics.drawString("Temps de jeu : " + Game.gameTime + " secondes", 300, 750);

        if (MainScreenGameState.score1 < MainScreenGameState.score2) {
            graphics.drawString("JAMES GAGNE AVEC " + (int)(((MainScreenGameState.score2 + 0.1) * 1000 + (Game.gameTime * 10) + Math.abs(ScoreBase1 - ScoreBase2)) * 10) + " POINTS", 300, 800);
            score2 = (int) (((MainScreenGameState.score2 + 0.1) * 1000 + (Game.gameTime * 10) + Math.abs(ScoreBase1 - ScoreBase2)) * 10);
        } else if (MainScreenGameState.score1 > MainScreenGameState.score2) {
            score1 = (int)(((MainScreenGameState.score1 + 0.1) * 1000 + (Game.gameTime * 10) + Math.abs(ScoreBase1 - ScoreBase2)) * 10);
            graphics.drawString("KOOPA GAGNE AVEC " + (int)(((MainScreenGameState.score1 + 0.1) * 1000 + (Game.gameTime * 10) + Math.abs(ScoreBase1 - ScoreBase2)) * 10) + " POINTS", 300, 800);

        }
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {

    }

    @Override
    public void keyReleased(int key, char c) {
        System.out.println(key);
        if (key == Input.KEY_ENTER){
            if (MainScreenGameState.score1 < MainScreenGameState.score2) {

                LienBDD.insertScore("tes", score2);
                System.out.println(score2);
            }
            else {
                LienBDD.insertScore("tes", score1);
                System.out.println(score1);
            }
            stateBasedGame.enterState(MainScreenGameState.ID);
            MainScreenGameState.score1 = 0.9;
            MainScreenGameState.score2 = 0.9;
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