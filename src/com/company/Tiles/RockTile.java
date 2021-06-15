package com.company.Tiles;

import com.company.gfx.Assets;

public class RockTile extends Tile {
    public RockTile( int id) {
        super(Assets.rock, id);
    }

    @Override
    public boolean isSolid() {
        return true;//como es solid no va a poder caminar sobre el, obvio porque es un arbol
    }
}
