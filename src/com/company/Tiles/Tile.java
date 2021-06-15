package com.company.Tiles;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Tile {
    //Statics
    public static final int TILE_WIDTH=64,TILE_HEIGHT = 64;
    public static Tile[] tiles= new Tile[256];
    public static Tile grassTile = new GrassTile(0);
    public static Tile dirtTile = new DirtTile(1);
    public static Tile waterTile = new WaterTile(3);
    public static Tile rockTile = new RockTile(4);

    //CLASS
    protected BufferedImage texture;
    protected final int id;//es final porque el id del tile tiene que ser unico, y este nunca tendria que cambiar

    public Tile(BufferedImage texture, int id){
        this.texture = texture;
        this.id = id;
        tiles[id] = this;
    }

    public int getId() {
        return id;
    }

    public void tick(){

    }
    public void render(Graphics g,int x,int y ){
        g.drawImage(texture,x,y,TILE_WIDTH,TILE_HEIGHT,null);
    }
    public boolean isSolid(){//si el tile sobre el que se encuentra es solid no va a poder caminar sobre ese
        return false;
    }
}
