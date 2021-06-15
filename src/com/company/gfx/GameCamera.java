package com.company.gfx;


import com.company.Handler;
import com.company.Tiles.Tile;
import com.company.entitys.Entity;

//como el mundo se renderiza afuera de la ventana, hagouna camara que se mueva asi puedo tener un mapa mas grande que el tamano
// de la ventana
public class GameCamera {
    private Handler handler;
    private float xOffset,yOffset;// es para saber que tan lejos dibuja de la posicion original

    public GameCamera(Handler  handler, float xOffset, float yOffset) {
        this.handler = handler;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }
    public void move(float xAmt, float yAmt){
        xOffset += xAmt;
        yOffset += yAmt;
        checkBlankSpace();
    }

    public void checkBlankSpace(){
        if(xOffset < 0){
            xOffset = 0;
        }else if (xOffset > handler.getWorld().getWidth() * Tile.TILE_WIDTH - handler.getWidth()){
            xOffset = handler.getWorld().getWidth() * Tile.TILE_WIDTH - handler.getWidth();
        }
        if (yOffset < 0){
            yOffset = 0;
        }
        else if(yOffset > handler.getWorld().getHeight() * Tile.TILE_HEIGHT - handler.getHeigth()){
            yOffset= handler.getWorld().getHeight() * Tile.TILE_HEIGHT - handler.getHeigth();
        }
    }
    public void centerOnEntity(Entity e){//centra la camara en el player
        xOffset = e.getX() - handler.getWidth() /2 + e.getWidth() /2;
        yOffset = e.getY() - handler.getHeigth() /2 + e.getHeight() /2;
        checkBlankSpace();

    }

    public float getxOffset() {
        return xOffset;
    }

    public void setxOffset(float xOffset) {
        this.xOffset = xOffset;
    }

    public float getyOffset() {
        return yOffset;
    }

    public void setyOffset(float yOffset) {
        this.yOffset = yOffset;
    }
}
