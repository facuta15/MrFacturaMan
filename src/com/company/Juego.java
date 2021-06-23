package com.company;

import com.company.States.*;
import com.company.display.Display;
import com.company.gfx.Assets;
import com.company.gfx.GameCamera;
import com.company.input.KeyManager;
import com.company.input.LoadFile;
import com.company.input.MouseManager;
import com.company.input.SavePlayerPosition;
import com.sun.tools.javac.Main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.io.Serializable;

//

public class Juego implements Runnable, Serializable {

    private Display display;
    private int width,height;
    public String title;


    private boolean running = false;//es para sabeer si esta corriendo o no
    private Thread thread,sonido;//creo un hilo para que el programa corra en un hilo separado
    private BufferStrategy bs;//es una manera de decirle al pc como dibujar cosas en la pantalla
    private Graphics g;

    //States

    public State gameState;
    public State menuState;
    public State pauseState;
    public State endGameState;

    //INPUT

    private KeyManager keyManager;
    private MouseManager mouseManager;

    //HANDLER
    private Handler handler;

    //CAMERA
    private GameCamera camera;
    //Save
    private SavePlayerPosition savePlayerPosition;
    private LoadFile loadFile;



    public Juego(String title,int width,int height){
        this.title= title;
        this.width=width;
        this.height=height;
        keyManager = new KeyManager();
        mouseManager = new MouseManager();
    }


    private void init(){


        display = new Display(title,width,height);
        display.getFrame().addKeyListener(keyManager);
        display.getFrame().addMouseListener(mouseManager);
        display.getFrame().addMouseMotionListener(mouseManager);
        display.getCanvas().addMouseListener(mouseManager);
        display.getCanvas().addMouseMotionListener(mouseManager);

        Assets.init();

        handler = new Handler(this);
        camera = new GameCamera(handler,0, 0 );
        savePlayerPosition = new SavePlayerPosition(handler);
        loadFile = new LoadFile(handler);

        gameState = new GameState(handler);
        menuState = new MenuState(handler );
        pauseState= new PauseMenuState(handler);
        endGameState= new EndGameState(handler);


        State.setCurrentState(menuState);


    }

    private void tick(){//es el tickrate o update rate, cada cuanto refreshea
        keyManager.tick();
        if(State.getCurrentState() !=null){
            if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_ESCAPE)){
                State.setCurrentState(pauseState);
            }
            State.getCurrentState().tick();
        }
        savePlayerPosition.tick();
        loadFile.tick();
    }
    private void render(){
        bs = display.getCanvas().getBufferStrategy();
        if(bs == null)//si es la primera vez que corre el programa
        {
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();//es un pincel para dibujar
        g.clearRect(0,0,width,height);//refreshea la pantalla

        //Dibujar aca
        if(State.getCurrentState() !=null){
            State.getCurrentState().render(g);
        }



        //termina aca

        bs.show();//hace que se muestre
        g.dispose();//se termina el objeto graphics
    }
    public void run(){
        init();

        int fps = 60;
        double timePerTick= 1000000000 / fps; // maxima cantidad de tiempo que espera antes de ejecutar tick y render para tener 60fps
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        long timer = 0;
        int ticks = 0;

        while (running){
            now = System.nanoTime();
            delta += (now-lastTime) / timePerTick;//cuanto tiempo espera antes de llamar a los otros metodos
            timer += now-lastTime;
            lastTime=now;
            if(delta >= 1)
            {
                tick();
                render();
                ticks++;
                delta--;
            }
            if(timer >= 1000000000){
                //System.out.println("Ticks and fps:"+ticks);//contador de fps en la consola
                ticks=0;
                timer=0;
            }
        }
        stop();//por las dudas que no se frene, es como un paracaidas
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Handler getHandler() {
        return handler;
    }
    public KeyManager getKeyManager(){
        return keyManager;
    }
    public GameCamera getCamera(){
        return camera;
    }
    public MouseManager getMouseManager() {
        return mouseManager;
    }

    public synchronized void start(){
        if(running)
            return;//si esta andando no hace el resto
        running = true;//esto cambia el estado y dice que esta corriendo
        thread = new Thread(this);//inicializo el thread y le paso this porque quiero que corra ESTA clase en el hilo
        thread.start();// arranca el hilo
        //playSound("/res/sounds/Littleroot Town - Pokemon RubyEmeraldSapphire (but it's created in Lo-Fi version).wav");
        //System.out.println("playing");
    }
    public synchronized void stop(){
        if(!running)//si no esta andando no hace el resto
            return;
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static synchronized void playSound(final String url) {
        new Thread(new Runnable() {
            // The wrapper thread is unnecessary, unless it blocks on the
            // Clip finishing; see comments.
            public void run() {
                try {
                    Clip clip = AudioSystem.getClip();
                    AudioInputStream inputStream = AudioSystem.getAudioInputStream(
                            Main.class.getResourceAsStream( "res/sounds/Littleroot Town - Pokemon RubyEmeraldSapphire (but it's created in Lo-Fi version).wav"+ url));
                    clip.open(inputStream);
                    clip.start();
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            }
        }).start();
    }
}
