package com.company.entitys;

import com.company.Handler;
import com.company.Tiles.Tile;

public abstract class Creature extends Entity{


    public static final float DEFAULT_SPEED = 3.0f;
    public static final int DEFAULT_CREATURE_WIDTH = 64;
    public static final int DEFAULT_CREATURE_HEIGTH = 64;



    protected float speed;
    protected float xMove;
    protected float yMove;


    public Creature(Handler handler, float x, float y, int width, int height) {
        super(handler,x, y,width,height);

        this.speed = DEFAULT_SPEED;

    }
    public void move(){
        if(!checkEntityColisions(xMove,0f))
            moveX();
        if(!checkEntityColisions(0f,yMove))
            moveY();

    }
    public void moveX(){
        if(xMove > 0){//se mueve a la derecha
            int tx = (int)( x + xMove + bounds.x + bounds.width) / Tile.TILE_WIDTH;
            if(!collisionWithTile(tx,(int)(y + bounds.y) / Tile.TILE_HEIGHT) &&
                    !collisionWithTile(tx,(int)(y + bounds.y + bounds.height) /Tile.TILE_HEIGHT)){// si no es solid, se puede mover!
                x += xMove;
            }else{
                x = tx * Tile.TILE_WIDTH - bounds.x - bounds.width - 1;//tx = temporal x, esto es para que no queden espacios entre el player y
                                                                        // el objeto que se choca
            }
        }else if (xMove < 0 ){//se mueve a la izquierda
            int tx = (int)( x + xMove + bounds.x ) / Tile.TILE_WIDTH;
            if(!collisionWithTile(tx,(int)(y + bounds.y) / Tile.TILE_HEIGHT) &&
                    !collisionWithTile(tx,(int)(y + bounds.y + bounds.height) /Tile.TILE_HEIGHT)){// si no es solid, se puede mover!
                x += xMove;
            }
            else{
                x = tx * Tile.TILE_WIDTH + Tile.TILE_WIDTH - bounds.x;
            }
        }
    }
    public void moveY(){
        if (yMove < 0){//se mueve hacia arriba
            int ty = (int)(y+ yMove + bounds.y)/ Tile.TILE_HEIGHT;
            if(!collisionWithTile((int)(x+ bounds.x)/Tile.TILE_WIDTH, ty) &&
                    (!collisionWithTile((int)(x+ bounds.x + bounds.width)/Tile.TILE_WIDTH, ty))){
                y+=yMove;
            }
        }
        else if(yMove > 0 ){// se mueve hacia abajo
            int ty = (int)(y+ yMove + bounds.y + bounds.height)/ Tile.TILE_HEIGHT;
            if(!collisionWithTile((int)(x+ bounds.x)/Tile.TILE_WIDTH, ty) &&
                    (!collisionWithTile((int)(x+ bounds.x + bounds.width)/Tile.TILE_WIDTH, ty))){
                y+=yMove;
            }
        }
    }

    protected boolean collisionWithTile(int x, int y ){
        return handler.getWorld().getTile(x,y).isSolid();
    }

    //GETERS SETTERS
    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getxMove() {
        return xMove;
    }

    public void setxMove(float xMove) {
        this.xMove = xMove;
    }

    public float getyMove() {
        return yMove;
    }

    public void setyMove(float yMove) {
        this.yMove = yMove;
    }
}
