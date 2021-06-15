package com.company.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener {

    private boolean[] keys,justPressed,cantPress;
    public boolean up,down,right,left,arrowUp,arrowDown,arrowLeft,arrowRight;

    public KeyManager()  {
        keys = new boolean[256];//cada tecla tiene un id entonces ese id se almacena ahi
        justPressed = new boolean[keys.length];
        cantPress = new boolean[keys.length];

    }
    public void tick(){

        for (int i = 0 ; i < keys.length ; i++ ){
            if (cantPress[i] && !keys[i]){
                cantPress[i] = false;
            }else if (justPressed[i]){
                cantPress[i]=true;
                justPressed[i]=false;
            }
            if (!cantPress[i] && keys[i]) {
                justPressed[i] = true;
            }
        }
        up = keys[KeyEvent.VK_W];
        down = keys[KeyEvent.VK_S];
        left = keys[KeyEvent.VK_A];
        right = keys[KeyEvent.VK_D];
        arrowUp = keys[KeyEvent.VK_UP];
        arrowDown = keys[KeyEvent.VK_DOWN];
        arrowLeft = keys[KeyEvent.VK_LEFT];
        arrowRight = keys[KeyEvent.VK_RIGHT];
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;//cuando una tecla se presiona cambia su estado y lo mismo cuando se deja de presionar
        System.out.println("pressed");
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() < 0 || e.getKeyCode() >= keys.length) {
            return;
        }
        keys[e.getKeyCode()] = false;
    }
    public boolean keyJustPressed(int keyCode){
        if(keyCode < 0 || keyCode >= keys.length)
            return false;
        return justPressed[keyCode];
    }

}
