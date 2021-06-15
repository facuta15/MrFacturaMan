package com.company.entitys;

import com.company.Handler;
import com.company.gfx.Assets;

import java.awt.*;

public class Seagull extends Creature implements Imovement{

    private long speed,timer;
    private boolean direction;
    private Player player;

    public Seagull(Handler handler, float x, float y,Player player,long speed) {
        super(handler,x, y,Creature.DEFAULT_CREATURE_WIDTH,Creature.DEFAULT_CREATURE_HEIGTH);

        bounds.x = 30;
        bounds.y = 30;
        bounds.width= 30;
        bounds.height= 30;
        this.player = player;
        this.setHealth(10);
        this.speed = speed;
        this.timer = 0;
        this.direction = true;
    }
    public void checkAtack() {
        Rectangle cb = getCollisionBounds(0, 0);
        Rectangle ar = new Rectangle();

        ar.width = this.width;
        ar.height = this.height;

        for (Entity e : handler.getWorld().getEntityManager().getEntities()) {
            if (e.equals(this))
                continue;
            if (e.getCollisionBounds(0, 0).intersects(ar)) {
                e.hurt(5);
                return;
            }
        }
    }

    //hacerlo con un contador, cada tantos milisegundos cambia la direccion
    @Override
    public void tick() {
        upAndDownMovement(speed);
        checkAtack();
        //checkEntityColisions(x,y);
       /* x = xMove;
        y = yMove;

        if(handler.getWorld().getEntityManager().getPlayer().getX() > x )
            xMove += speedx;*/
    }
    @Override
    public void die(){

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.seagull,(int)(x - handler.getCamera().getxOffset()),(int) (y - handler.getCamera().getyOffset()),width,height,null);
    }

    @Override
    public void upAndDownMovement(long speed) {

        if (direction == true){
            if(timer < speed ){
                timer +=3;
                y+=3;
                if (timer >= speed ){
                    direction = false;
                }
            }
        }else {
            if(timer >0){
                timer-=3;
                y-=3;
            }else
                direction =true;
        }
    }

    @Override
    public void leftAndRightMovement(long speed) {

    }
}
