package com.company.gfx;

import java.awt.image.BufferedImage;

public class Animation {
    private int speed, index;
    private long lastTime,timer;
    private BufferedImage[] frames;

    public Animation (int speed, BufferedImage[] frames) {
        this.speed = speed;
        this.frames = frames;
        index = 0;
        timer = 0;
        lastTime = System.currentTimeMillis();
    }
    public void tick(){
        timer += System.currentTimeMillis() - lastTime;//esto es el tiempo que pasa entre c/vez que se llama este metodo
        lastTime = System.currentTimeMillis();

        if (timer > speed){//c/vez que se llama, cambia de frame
            index++;
            timer = 0;
            if (index >= frames.length ) //se asegura que no se vaya del array
                index = 0;
        }
    }

    public BufferedImage getCurrentFrame(){
        return frames[index];
    }

}



