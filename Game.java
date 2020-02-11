package com.sf.Hierarchie.vue;

import com.sf.Hierarchie.model.LienBDD;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Ellipse;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Iterator;

public class Game extends BasicGameState {

    Image BulletImage = new Image("sprites/bullet.png");
    protected static ArrayList<Bullet> LesBulletsJ1;
    protected static ArrayList<Bullet> LesBulletsJ2;
    private Bullet bulletJ1 = new Bullet();
    private Bullet bulletJ2 = new Bullet();

    protected Shape shapeJ1;
    protected Shape shapeJ2;
    protected Shape shapeBulletJ1;
    protected Shape shapeBulletJ2;

    public static final int ID = 2;
    public static float x2 = 880, y2 = 860;
    public static int direction2 = 2;
    public static boolean moving2 = false;
    private Animation[] animations2 = new Animation[16];

    public static float x = 80, y = 220;
    public static int direction = 2;
    public static boolean moving = false;
    private Animation[] animations = new Animation[16];

    private TiledMap map;

//    public float vitesseJ1 = (float)LienBDD.selectVitesse(1) / 10;
//    public float vitesseJ2 = (float)LienBDD.selectVitesse(2) / 10;
//
//    public float puissanceJ1 = (float)LienBDD.selectPuissance(1) / 100;
//    public float puissanceJ2 = (float)LienBDD.selectPuissance(2) / 100;
//
//    public float vitesseTirJ1 = (float)LienBDD.selectVitesseTir(1);
//    public float vitesseTirJ2 = (float)LienBDD.selectVitesseTir(2);
//
//    public static double score1 = (float)LienBDD.selectLife(1) / 100 - 0.1;
//    public static double score2 = (float)LienBDD.selectLife(2) / 100 - 0.1;

    private Hud hud = new Hud();
    private Hud hud2 = new Hud();

    protected static float gameTime;

    public Game() throws SlickException {
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        map = new TiledMap("map/map.xml");

        SpriteSheet spriteSheet = new SpriteSheet("sprites/fbi.png", 64, 64);

        Animation animation = new Animation();

        this.animations[0] = loadAnimation(spriteSheet, 0, 1, 0);
        this.animations[1] = loadAnimation(spriteSheet, 0, 1, 1);
        this.animations[2] = loadAnimation(spriteSheet, 0, 1, 2);
        this.animations[3] = loadAnimation(spriteSheet, 0, 1, 3);
        this.animations[4] = loadAnimation(spriteSheet, 1, 9, 0);
        this.animations[5] = loadAnimation(spriteSheet, 1, 9, 1);
        this.animations[6] = loadAnimation(spriteSheet, 1, 9, 2);
        this.animations[7] = loadAnimation(spriteSheet, 1, 9, 3);

        SpriteSheet spriteSheet2 = new SpriteSheet("sprites/skeleton.png", 64, 64);

        Animation animation2 = new Animation();

        this.animations2[0] = loadAnimation(spriteSheet2, 0, 1, 0);
        this.animations2[1] = loadAnimation(spriteSheet2, 0, 1, 1);
        this.animations2[2] = loadAnimation(spriteSheet2, 0, 1, 2);
        this.animations2[3] = loadAnimation(spriteSheet2, 0, 1, 3);
        this.animations2[4] = loadAnimation(spriteSheet2, 1, 9, 0);
        this.animations2[5] = loadAnimation(spriteSheet2, 1, 9, 1);
        this.animations2[6] = loadAnimation(spriteSheet2, 1, 9, 2);
        this.animations2[7] = loadAnimation(spriteSheet2, 1, 9, 3);

        this.hud.init(1);
        this.hud2.init(2);

        LesBulletsJ1 = new  ArrayList<Bullet>();
        LesBulletsJ2 = new  ArrayList<Bullet>();

        shapeJ1 = new Ellipse((int)x, (int)y, 16, 16);
        shapeJ2 = new Ellipse((int)x2, (int)y2, 16, 16);

    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics graphics) throws SlickException {
        this.map.render(0, 0, 0);
        this.map.render(0, 0, 1);

        graphics.setColor(new Color(0, 0, 0, .5f));
        graphics.fillOval(x - 16, y - 8, 32, 16);
        graphics.fillOval(x2 - 16, y2 - 8, 32, 16);
        graphics.drawAnimation(animations[direction + (moving ? 4 : 0)], x-32, y-60);
        graphics.drawAnimation(animations2[direction2 + (moving2 ? 4 : 0)], x2-32, y2-60);

        this.hud.render(graphics,1,(float) MainScreenGameState.score1);
        this.hud2.render(graphics,2, (float) MainScreenGameState.score2);

        for (Bullet bulletJ1 : LesBulletsJ1) {
            graphics.drawImage(this.bulletJ1.getImage(),this.bulletJ1.getX(), this.bulletJ1.getY());
        }
        for (Bullet bulletJ2 : LesBulletsJ2) {
            graphics.drawImage(this.bulletJ2.getImage(), this.bulletJ2.getX(), this.bulletJ2.getY());
        }
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException  {

        if (this.moving) {
            float futurX = getFuturX(delta);
            float futurY = getFuturY(delta);
            boolean collision = isCollision(futurX, futurY,"logic");
            if (collision) {
                this.moving = false;
            } else {
                this.x = futurX;
                this.bulletJ1.setX(futurX);
                this.y = futurY;
                this.bulletJ1.setY(futurY);
            }
        }

        if (this.moving2) {
            float futurX2 = getFuturX2(delta);
            float futurY2 = getFuturY2(delta);
            boolean collision = isCollision(futurX2, futurY2,"logic");
            if (collision) {
                this.moving2 = false;
            } else {
                this.x2 = futurX2;
                this.bulletJ2.setX(futurX2);
                this.y2 = futurY2;
                this.bulletJ2.setY(futurY2);
            }
        }

        shapeJ1.setLocation(x, y);
        shapeJ2.setLocation(x2, y2);

        for (Bullet bulletJ1 : LesBulletsJ1){
            this.bulletJ1.setY(this.bulletJ1.getY() + MainScreenGameState.vitesseTirJ1);
        }

        for (Iterator<Bullet> bulletJ1Iterator = LesBulletsJ1.iterator(); bulletJ1Iterator.hasNext();) {

            Bullet curBulletJ1 = bulletJ1Iterator.next();

            shapeBulletJ1.setLocation(curBulletJ1.getX(), curBulletJ1.getY());

            if (isCollision(bulletJ1.getX(), bulletJ1.getY(), "noLineBetween") || moving) {
                bulletJ1Iterator.remove();
            }

            if (shapeBulletJ1.intersects(shapeJ2)) {
                bulletJ1Iterator.remove();
                MainScreenGameState.score1 -= MainScreenGameState.puissanceJ2;
            }
        }

        for (Bullet bulletJ2 : LesBulletsJ2){
            this.bulletJ2.setY(this.bulletJ2.getY() - MainScreenGameState.vitesseTirJ2);
        }

        for (Iterator<Bullet> bulletJ2Iterator = LesBulletsJ2.iterator(); bulletJ2Iterator.hasNext();) {

            Bullet curBulletJ1 = bulletJ2Iterator.next();

            shapeBulletJ2.setLocation(curBulletJ1.getX(), curBulletJ1.getY());

            if(isCollision(bulletJ2.getX(), bulletJ2.getY(), "noLineBetween") || moving2) {
                bulletJ2Iterator.remove();
            }

            if (shapeBulletJ2.intersects(shapeJ1)) {
                bulletJ2Iterator.remove();
                MainScreenGameState.score2 -= MainScreenGameState.puissanceJ1;
            }
        }

        if (MainScreenGameState.score1 <= 0.1 || MainScreenGameState.score2 <= 0.1){
            game.enterState(EndGame.ID);
            MainScreenGameState.chrono.stop();
            gameTime = MainScreenGameState.chrono.getDureeSec();
        }

    }

    private boolean isCollision(float x, float y, String layer) {
        int tileW = this.map.getTileWidth();
        int tileH = this.map.getTileHeight();
        int logicLayer = this.map.getLayerIndex(layer);

        Image tile = this.map.getTileImage((int) x / tileW, (int) y / tileH, logicLayer);

        boolean collision = (tile != null);
        if (collision) {
            Color color = tile.getColor((int) this.x % tileW, (int) this.y % tileH);
            collision = color.getAlpha() > 0;
        }
        return collision;
    }

    private float getFuturX(int delta) {
        float futurX = this.x;
        switch (this.direction) {
            case 1:
                futurX = this.x - MainScreenGameState.vitesseJ1 * delta;
                break;
            case 3: futurX = this.x + MainScreenGameState.vitesseJ1 * delta;
                break;
        }
        return futurX;
    }

    private float getFuturY(int delta) {
        float futurY = this.y;
        switch (this.direction) {
            case 0: futurY = this.y - MainScreenGameState.vitesseJ1 * delta;
                break;
            case 2: futurY = this.y + MainScreenGameState.vitesseJ1 * delta;
                break;
        }
        return futurY;
    }

    private float getFuturX2(int delta) {
        float futurX2 = this.x2;
        switch (this.direction2) {
            case 1: futurX2 = this.x2 - MainScreenGameState.vitesseJ2 * delta;
                break;
            case 3: futurX2 = this.x2 + MainScreenGameState.vitesseJ2 * delta;
                break;
        }
        return futurX2;
    }

    private float getFuturY2(int delta) {
        float futurY2 = this.y2;
        switch (this.direction2) {
            case 0: futurY2 = this.y2 - MainScreenGameState.vitesseJ2 * delta;
                break;
            case 2: futurY2 = this.y2 + MainScreenGameState.vitesseJ2 * delta;
                break;
        }
        return futurY2;
    }

    public void ShootEnemy1() throws SlickException{
        LesBulletsJ2.add(bulletJ2);
        shapeBulletJ2 = new Ellipse((int)bulletJ2.getX(), (int)bulletJ2.getY(), 5, 5);
        this.bulletJ2.setShot(true);
    }

    public void ShootEnemy2() throws SlickException{
        LesBulletsJ1.add(bulletJ1);
        shapeBulletJ1 = new Ellipse((int)bulletJ1.getX(), (int)bulletJ1.getY(), 5, 5);
        this.bulletJ1.setShot(true);
    }

    private Animation loadAnimation(SpriteSheet spriteSheet, int startX, int endX, int y) {
        Animation animation = new Animation();
        for (int x = startX; x < endX; x++) {
            animation.addFrame(spriteSheet.getSprite(x, y), 100);
        }
        return animation;
    }

    @Override
    public void keyPressed(int key, char c) {
        System.out.println(key);
        switch (key) {
            case Input.KEY_UP:
                this.direction = 0;
                this.moving = true;
                break;
            case Input.KEY_LEFT:
                this.direction = 1;
                this.moving = true;
                break;
            case Input.KEY_DOWN:
                this.direction = 2;
                this.moving = true;
                break;
            case Input.KEY_RIGHT:
                this.direction = 3;
                this.moving = true;
                break;
            case Input.KEY_Z:
                this.direction2 = 0;
                this.moving2 = true;
                break;
            case Input.KEY_Q:
                this.direction2 = 1;
                this.moving2 = true;
                break;
            case Input.KEY_S:
                this.direction2 = 2;
                this.moving2 = true;
                break;
            case Input.KEY_D:
                this.direction2 = 3;
                this.moving2 = true;
                break;

            case Input.KEY_R:
                try {
                    this.ShootEnemy1();
                } catch (SlickException e) {
                    e.printStackTrace();
                }
                System.out.println("R enfoncé");
                break;

            case Input.KEY_M:
                try {
                    this.ShootEnemy2();
                } catch (SlickException e) {
                    e.printStackTrace();
                }
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
                    case 4: try {
                        this.ShootEnemy1();
                    } catch (SlickException e) {
                        e.printStackTrace();
                    }
                        break;


                }
                break;
            }
            case 1 :
                switch (button) {
                    case 1: try {
                        this.ShootEnemy2();
                    } catch (SlickException e) {
                        e.printStackTrace();
                    }
                        break;
                }

                break;


        }
    }

    @Override
    public void controllerLeftPressed(int controller) {
        switch (controller) {
            case 1:
                this.direction = 1;
                this.moving = true;
                break;

            case 0:
                this.direction2 = 1;
                this.moving2 = true;
                break;
        }

    }

    @Override
    public void controllerLeftReleased(int controller) {
        switch (controller) {
            case 1:
                this.moving = false;
                break;

            case 0:
                this.moving2 = false;
                break;
        }
    }

    @Override
    public void controllerRightPressed(int controller) {
        switch (controller) {
            case 1:
                this.direction = 3;
                this.moving = true;
                break;

            case 0:
                this.direction2 = 3;
                this.moving2 = true;
                break;
        }

    }

    @Override
    public void controllerRightReleased(int controller) {
        switch (controller) {
            case 1:
                this.moving = false;
                break;

            case 0:
                this.moving2 = false;
                break;
        }
    }

    @Override
    public void controllerUpPressed(int controller) {
        switch (controller) {
            case 1:
                this.direction = 0;
                this.moving = true;
                break;

            case 0:
                this.direction2 = 0;
                this.moving2 = true;
                break;
        }

    }

    @Override
    public void controllerUpReleased(int controller) {
        switch (controller) {
            case 1:
                this.moving = false;
                break;

            case 0:
                this.moving2 = false;
                break;
        }
    }

    @Override
    public void controllerDownPressed(int controller) {
        switch (controller) {
            case 1:
                this.direction = 2;
                this.moving = true;
                break;

            case 0:
                this.direction2 = 2;
                this.moving2 = true;
                break;
        }

    }

    @Override
    public void controllerDownReleased(int controller) {
        switch (controller) {
            case 1:
                this.moving = false;
                break;

            case 0:
                this.moving2 = false;
                break;
        }
    }

    @Override
    public void keyReleased(int key, char c) {
        switch (key) {
            case Input.KEY_UP:    this.direction = 0; this.moving = false;
                break;
            case Input.KEY_LEFT:  this.direction = 1; this.moving = false;
                break;
            case Input.KEY_DOWN:  this.direction = 2; this.moving = false;
                break;
            case Input.KEY_RIGHT: this.direction = 3; this.moving = false;
                break;
            case Input.KEY_Z:    this.direction2 = 0; this.moving2 = false;
                break;
            case Input.KEY_Q:  this.direction2 = 1; this.moving2 = false;
                break;
            case Input.KEY_S:  this.direction2 = 2; this.moving2 = false;
                break;
            case Input.KEY_D: this.direction2 = 3; this.moving2 = false;
                break;
        }
    }

    @Override
    public int getID() {
        return ID;
    }
}