package com.company.entitys;

import com.company.Handler;
import com.company.entitys.Items.Item;
import com.company.gfx.Animation;
import com.company.gfx.Assets;

import java.awt.*;

public class Fisura extends Creature implements Imovement{
    private long speed,timer;
    private boolean direction;
    //Anims
    private Animation goingLeft;
    private Animation goingRight;

    public Fisura(Handler handler, float x, float y,long speed) {
        super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGTH);

        bounds.x = 30;
        bounds.y = 30;
        bounds.width= 30;
        bounds.height= 30;
        this.setHealth(10);
        this.speed = speed;
        this.timer = 0;
        this.direction = true;
        //Animations
        goingLeft = new Animation(300, Assets.fisuraLeft);
        goingRight =new Animation(300,Assets.fisuraRight);

    }

    @Override
    public void die() {
        handler.getWorld().getItemManager().addItem(Item.tucoItem.createNew((int)x -3,(int) y - 3));
    }

    @Override
    public void tick() {
        goingRight.tick();
        goingLeft.tick();
        leftAndRightMovement(speed);

    }

    @Override
    public void render(Graphics g) {

    }

    @Override
    public void upAndDownMovement(long speed) {

    }

    @Override
    public void leftAndRightMovement(long speed) {
        if (direction == true){
            if(timer < speed ){
                timer +=5;
                x+=5;
                if (timer >= speed ){
                    direction = false;
                }
            }
        }else {
            if(timer >0){
                timer-=5;
                x-=5;
            }else
                direction =true;
        }
    }
    }

