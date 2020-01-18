package com.sf.Hierarchie.vue;

import com.sf.Hierarchie.vue.Hud;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;
import javax.swing.*;


public class Game extends BasicGameState {

    private Shoot shoot;
    public static final int ID = 2;
    private float x2 = 880, y2 = 860;
    private int direction2 = 2;
    private boolean moving2 = false;
    private Animation[] animations2 = new Animation[16];

    private float x = 80, y = 220;
    private int direction = 2;
    private boolean moving = false;
    private Animation[] animations = new Animation[16];

    private TiledMap map;

    private Hud hud = new Hud();
    private Hud hud2 = new Hud();


    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException  {

        shoot.update(delta);

        if (this.moving) {
            float futurX = getFuturX(delta);
            float futurY = getFuturY(delta);
            boolean collision = isCollision(futurX, futurY);
            if (collision) {
                this.moving = false;
            } else {
                this.x = futurX;
                this.y = futurY;
            }
        }

        if (this.moving2) {
            float futurX2 = getFuturX2(delta);
            float futurY2 = getFuturY2(delta);
            boolean collision = isCollision(futurX2, futurY2);
            if (collision) {
                this.moving2 = false;
            } else {
                this.x2 = futurX2;
                this.y2 = futurY2;
            }
        }
    }

    private boolean isCollision(float x, float y) {
        int tileW = this.map.getTileWidth();
        int tileH = this.map.getTileHeight();
        int logicLayer = this.map.getLayerIndex("logic");

        Image tile = this.map.getTileImage((int) x / tileW, (int) y / tileH, logicLayer);

        boolean collision = (tile != null);
        if (collision) {
            Color color = tile.getColor((int) x % tileW, (int) y % tileH);
            collision = color.getAlpha() > 0;
        }
        return collision;
    }

    private float getFuturX(int delta) {
        float futurX = this.x;
        switch (this.direction) {
            case 1: futurX = this.x - .2f * delta;
                break;
            case 3: futurX = this.x + .2f * delta;
                break;
        }
        return futurX;
    }

    private float getFuturY(int delta) {
        float futurY = this.y;
        switch (this.direction) {
            case 0: futurY = this.y - .2f * delta;
                break;
            case 2: futurY = this.y + .2f * delta;
                break;
        }
        return futurY;
    }

    private float getFuturX2(int delta) {
        float futurX2 = this.x2;
        switch (this.direction2) {
            case 1: futurX2 = this.x2 - .2f * delta;
                break;
            case 3: futurX2 = this.x2 + .2f * delta;
                break;
        }
        return futurX2;
    }

    private float getFuturY2(int delta) {
        float futurY2 = this.y2;
        switch (this.direction2) {
            case 0: futurY2 = this.y2 - .2f * delta;
                break;
            case 2: futurY2 = this.y2 + .2f * delta;
                break;
        }
        return futurY2;
    }

    public void Shooting(float x, float y, Vector2f vitesse){
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

            this.hud.render(graphics,1,0.4f);
            this.hud2.render(graphics,2, 0.7f);

            shoot.render(container, graphics);

    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        map = new TiledMap("map/map.xml");
        shoot = new Shoot( new Vector2f(x,y), new Vector2f(500,100));


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
                case Input.KEY_UP:    this.direction = 0; this.moving = true;
                    break;
                case Input.KEY_LEFT:  this.direction = 1; this.moving = true;
                    break;
                case Input.KEY_DOWN:  this.direction = 2; this.moving = true;
                    break;
                case Input.KEY_RIGHT: this.direction = 3; this.moving = true;
                    break;
                case Input.KEY_Z:    this.direction2 = 0; this.moving2 = true;
                    break;
                case Input.KEY_Q:  this.direction2 = 1; this.moving2 = true;
                    break;
                case Input.KEY_S:  this.direction2 = 2; this.moving2 = true;
                    break;
                case Input.KEY_D: this.direction2 = 3; this.moving2 = true;
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