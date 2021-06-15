package com.company;

import com.company.entitys.Player;
import com.company.gfx.GameCamera;
import com.company.input.KeyManager;
import com.company.input.MouseManager;
import com.company.worlds.World;

public class Handler {

    private Juego juego;
    private World world;
    private Player player;


    public Handler(Juego juego){
        this.juego = juego;
    }
    public int getWidth(){
        return juego.getWidth();
    }
    public int getHeigth(){
        return juego.getHeight();
    }
    public KeyManager getKeyManager(){
        return juego.getKeyManager();
    }
    public GameCamera getCamera(){
        return juego.getCamera();

    }

    public Juego getJuego() {
        return juego;
    }

    public void setJuego(Juego juego) {
        this.juego = juego;
    }
    public MouseManager getMouseManager(){
        return juego.getMouseManager();
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }
}
