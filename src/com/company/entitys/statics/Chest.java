package com.company.entitys.statics;

import com.company.Handler;
import com.company.Tiles.Tile;

import java.awt.*;

public class Chest extends StaticEntity{
    public Chest(Handler handler, float x, float y) {
        super(handler, x, y, Tile.TILE_WIDTH, Tile.TILE_HEIGHT);
    }

    @Override
    public void die() {
        //State.setCurrentState();
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {

    }
}
