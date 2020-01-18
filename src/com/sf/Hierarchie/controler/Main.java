package com.sf.Hierarchie.controler;
import com.sf.Hierarchie.vue.Game;
import com.sf.Hierarchie.vue.MainScreenGameState;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Main extends StateBasedGame {

    public Main() {
        super("Lesson 15 :: StateGame");
    }

    public static void main(String[] args) throws SlickException {
        new AppGameContainer(new Main(), 960, 960, false).start();
    }

    /**
     * Ici il suffit d'ajouter nos deux boucles de jeux.
     * La première ajoutèe sera celle qui sera utilisée au début
     */

    @Override
    public void initStatesList(GameContainer container) throws SlickException {
        addState(new MainScreenGameState());
        addState(new Game());
    }
}