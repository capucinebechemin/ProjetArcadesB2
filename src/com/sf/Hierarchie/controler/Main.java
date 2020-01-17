package com.sf.Hierarchie.controler;

import com.sf.Hierarchie.vue.FOptions;
import com.sf.Hierarchie.vue.Fenetre;
import com.sf.Hierarchie.vue.menu;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

public class Main {

    static private AppGameContainer app;
    private static FOptions f1;
    private static Fenetre f2;
    private static menu f3;

    public static void main(String[] args) throws SlickException {

        f3 = new menu();

        /*app = new AppGameContainer( new Game("map") );
        app.setDisplayMode( 960, 960, false );
        app.start();

        f1 = new FOptions();
        ArrayList<Personnage> liste = LienBDD.selectAll();
        System.out.println(liste);*/
    }
}
