package com.company.Ui;

import com.company.Handler;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class UiManager {

    private Handler handler;
    private ArrayList<UiObject> objects;

    public UiManager(Handler handler){
        this.handler = handler;
        objects = new ArrayList<UiObject>();
    }

    public void tick(){
        for (UiObject o : objects){
            o.tick();
        }
    }
    public void render(Graphics g){
        for (UiObject o : objects)
            o.render(g);
    }
    public void onMouseMove(MouseEvent e ){
        for (UiObject o: objects){
            o.onMouseMove(e);
        }
    }
    public void onMouseRelease(MouseEvent e){
        for (UiObject o: objects){
            o.onMouseRealesed(e);
        }
    }
    public void addObject(UiObject o){
        objects.add(o);
    }
    public void removeObject(UiObject o){
        objects.remove(o);
    }

    public Handler getHandler() {
        return handler;
    }

    public ArrayList<UiObject> getObjects() {
        return objects;
    }
}
