package com.company.entitys;

import com.company.Handler;

import java.awt.*;

public abstract class Entity {
    public static final int DEFAULT_HEALTH = 10;

    protected int health;
    protected float x,y;//son las coordenadas de donde esta ubicado
    protected int width,height;
    protected Handler handler;
    protected  Rectangle bounds;
    protected boolean active = true;



    public Entity(Handler handler, float x, float y, int width, int height) {

        this.handler = handler;
        this.x = x;
        this.y = y;
        this.height=height;
        this.width=width;
        this.health = DEFAULT_HEALTH;

        bounds = new Rectangle(0,0,width,height);
    }
    public boolean checkEntityColisions(float xOffset, float yOffset){
        for (Entity e:handler.getWorld().getEntityManager().getEntities()){
            if (e.equals(this)){
                continue;
            }
            if(e.getCollisionBounds(0f,0f).intersects(getCollisionBounds(xOffset,yOffset)))
                return true;
        }
        return false;
    }
    public Rectangle getCollisionBounds(float xOffset,float yOffset){
        return new Rectangle((int) (x + bounds.x + xOffset),(int) (y + bounds.y + yOffset),bounds.width,bounds.height);
        //devuelve el hitbox del entity
    }
    public abstract void die();

    public void hurt(int amt) {
        health -= amt;
        if(health <= 0 ){
            active = false;
            die();
        }
    }
    public abstract void tick();

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public abstract void render(Graphics g);
    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }


}
