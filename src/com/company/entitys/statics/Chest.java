package com.company.entitys.statics;

import com.company.Handler;
import com.company.Tiles.Tile;
import com.company.entitys.Items.Item;
import com.company.gfx.Assets;

import java.awt.*;

public class Chest extends StaticEntity{
    public Chest(Handler handler, float x, float y) {
        super(handler, x, y, Tile.TILE_WIDTH, Tile.TILE_HEIGHT);
    }

    @Override
    public void die() {
        handler.getWorld().getItemManager().addItem(Item.tucoItem.createNew((int)x,(int)y));
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.chest,(int) (x - handler.getCamera().getxOffset()),(int) (y - handler.getCamera().getyOffset()),width,height,null);
    }
}
