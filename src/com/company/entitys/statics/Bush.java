package com.company.entitys.statics;

import com.company.Handler;
import com.company.Tiles.Tile;
import com.company.entitys.Items.Item;
import com.company.gfx.Assets;

import java.awt.*;

public class Bush extends StaticEntity{
    public Bush(Handler handler, float x, float y) {
        super(handler, x, y, Tile.TILE_WIDTH, Tile.TILE_HEIGHT);
        bounds.x = 20;
        bounds.y = 50;
        bounds.width= 20;
        bounds.height= 40;
        this.health=10;
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
        if(this.health>5)
            g.drawImage(Assets.bush[0],(int) (x - handler.getCamera().getxOffset()),(int) (y - handler.getCamera().getyOffset()),width,height,null);
        else
            g.drawImage(Assets.bush[1],(int) (x - handler.getCamera().getxOffset()),(int) (y - handler.getCamera().getyOffset()),width,height,null);

    }
}
