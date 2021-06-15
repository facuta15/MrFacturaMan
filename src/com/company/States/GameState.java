package com.company.States;

import com.company.Handler;
import com.company.worlds.World;

import java.awt.*;

public class GameState extends State{


    private World world;


    public GameState(Handler handler) {
        super(handler);
        world = new World(handler,"src/res/Worlds/World2.txt");
        handler.setWorld(world);
    }

    @Override
    public void tick() {
        world.tick();
    }

    @Override
    public void render(Graphics g) {
        world.render(g);
    }
}
