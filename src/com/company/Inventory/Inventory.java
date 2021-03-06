package com.company.Inventory;

import com.company.Handler;
import com.company.States.State;
import com.company.entitys.Items.Item;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Inventory {

    private Handler handler;
    private Boolean active = false;
    private ArrayList<Item> inventoryItems;
    private int points;

    public Inventory(Handler handler) {
        this.handler = handler;
        inventoryItems = new ArrayList<Item>();
        this.points = 0;
    }
    public void tick(){
        if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_E)) {
            active = !active;
        }
        if (!active)
            return;
    }
    public void render(Graphics g ){

    }
    public void addItem(Item item){
        for(Item i: inventoryItems ){
            if(i.getId() == item.getId()){
                i.setCount(i.getCount() + item.getCount());
            }
        }
        inventoryItems.add(item);
    }
    public void addPoints(){
        if (this.inventoryItems.contains(Item.mateItem)){
            this.points +=10;
        }
        if (this.inventoryItems.contains(Item.tucoItem)){
            State.setCurrentState(handler.getJuego().menuState);
        }
    }

    public int getPoints() {
        return points;
    }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }
}
