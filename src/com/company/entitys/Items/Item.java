package com.company.entitys.Items;

import com.company.Handler;
import com.company.gfx.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Item  {
    //HANDLER

    public static Item[] items = new Item[256];
    public static Item tucoItem= new Item(Assets.tuco,"tuco",0);
    public static Item mateItem= new Item(Assets.mate,"mate",1);


    //CLass
    public static final int ITEMWIDTH = 32,ITEMHEIGHT = 32;

    protected Handler handler;
    protected BufferedImage texture;
    protected String name;
    protected final int id;


    protected Rectangle bounds;
    protected boolean pickedUp = false;

    protected int x,y,count;

    public Item(BufferedImage texture,String name,int id){
        this.texture = texture;
        this.name = name;
        this.id = id;
        count = 1;
        bounds = new Rectangle(x,y,ITEMWIDTH,ITEMHEIGHT);
    }

    public void tick(){
        if(handler.getWorld().getEntityManager().getPlayer().getCollisionBounds(0f,0f).intersects(bounds)){
            pickedUp = true;
            handler.getWorld().getEntityManager().getPlayer().getInventory().addItem(this);
            handler.getWorld().getEntityManager().getPlayer().getInventory().addPoints();
        }
    }
    public void render(Graphics g,int x,int y ){
        if(handler == null)
            return;
        g.drawImage(texture,x,y,ITEMWIDTH,ITEMHEIGHT,null);
    }
    public void render(Graphics g){
        //para dibujar en el piso
        render(g,(int)(x - handler.getCamera().getxOffset()),(int)( y - handler.getCamera().getyOffset()));
    }
    public Item createNew(int x ,int y){
        //para dibujar en el inventorio
        Item i = new Item(texture,name,id);
        i.setPosition(x,y);
        return i;
    }

    public void setPosition(int x,int y){
        this.x = x;
        this.y = y;
        bounds.x = x;
        bounds.y = y;
    }
    //GETTES SETTERS

    public Handler getHandler() {
        return handler;
    }

    public boolean isPickedUp() {
        return pickedUp;
    }

    public void setPickedUp(boolean pickedUp) {
        this.pickedUp = pickedUp;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public BufferedImage getTexture() {
        return texture;
    }

    public void setTexture(BufferedImage texture) {
        this.texture = texture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
