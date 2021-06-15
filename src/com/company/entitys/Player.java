package com.company.entitys;

import com.company.Handler;
import com.company.Inventory.Inventory;
import com.company.gfx.Animation;
import com.company.gfx.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Creature{

    //Animations

    private Animation animRLD;
    private Animation animRLU;
    private Animation animUH;
    private Animation animDH;
    private Animation animRH;
    private Animation animLH;

    //AttackTimer
    private long lastAttackTimer,attackCooldown = 300, attackTimer = attackCooldown;

    //Inventory
    private Inventory inventory;


    public Player(Handler handler, float x, float y) {
        super(handler,x, y,Creature.DEFAULT_CREATURE_WIDTH,Creature.DEFAULT_CREATURE_HEIGTH);
        //los bounds definen el hitbox
        bounds.x = 15;
        bounds.y = 15;
        bounds.width= 30;
        bounds.height= 48;
        //ANIMATIONS
        animRLD = new Animation(300,Assets.player_LRD);
        animRLU = new Animation(300,Assets.player_LRU);
        animUH = new Animation(300,Assets.player_UH);
        animDH = new Animation(300,Assets.player_DH);
        animRH = new Animation(300,Assets.player_RH);
        animLH = new Animation(300,Assets.player_LH);

        inventory = new Inventory(handler);
    }

    @Override
    public void tick() {
        //si no se hace tick de la anim no se mueve lindo
        animRLD.tick();
        animRLU.tick();
        animLH.tick();
        animRH.tick();
        animDH.tick();
        animUH.tick();
        getInput();
        move();
        handler.getCamera().centerOnEntity(this);
        //Attack
        checkAttacks();
        //inventory
        inventory.tick();
    }
    public void checkAttacks() {

        //para que no deletee cosas de un hit, va a tener cooldown de atack
        attackTimer += System.currentTimeMillis() - lastAttackTimer;
        lastAttackTimer = System.currentTimeMillis();
        if(attackTimer < attackCooldown)
            return;

        Rectangle cb = getCollisionBounds(0, 0);
        Rectangle ar = new Rectangle();
        int arSize = 20;
        ar.width = arSize;
        ar.height = arSize;


        if (handler.getKeyManager().arrowUp) {
            ar.x = cb.x + cb.width / 2 - arSize / 2;
            ar.y = cb.y - arSize;
        }
        else if (handler.getKeyManager().arrowDown) {
            ar.x = cb.x + cb.width / 2 - arSize / 2;
            ar.y = cb.y + cb.height;
        }
        else if (handler.getKeyManager().arrowRight) {
            ar.x = cb.x + cb.width;
            ar.y = cb.y +cb.height / 2 - arSize /2 ;
        }
        else if (handler.getKeyManager().arrowLeft) {
            ar.x = cb.x -arSize;
            ar.y = cb.y +cb.height / 2 - arSize /2 ;
        }
        else {
            return;
        }
        attackTimer = 0;
        for (Entity e: handler.getWorld().getEntityManager().getEntities()){
            if(e.equals(this))
                continue;
            if(e.getCollisionBounds(0,0).intersects(ar)){
                e.hurt(1);
                return;
            }
        }
    }
    @Override
    public void die(){

    }
    private void getInput(){
        xMove = 0;
        yMove = 0;

        if(handler.getKeyManager().up)
            yMove = -speed;
        if(handler.getKeyManager().down)
            yMove = +speed;
        if(handler.getKeyManager().right)
            xMove = speed;
        if(handler.getKeyManager().left)
            xMove = -speed;
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(getCurrentAnimFrame(),(int)(x - handler.getCamera().getxOffset()),(int)(y - handler.getCamera().getyOffset()),width,height,null);
        inventory.render(g);
        //dibuja el player con la camara centrada en el

        //g.setColor(Color.red);
        //g.fillRect((int)(x + bounds.x-handler.getCamera().getxOffset()),
        //        (int)(y + bounds.x-handler.getCamera().getyOffset()), bounds.width, bounds.height);
        //para que aparezca el hitbox
    }
    private BufferedImage getCurrentAnimFrame(){
        if(handler.getKeyManager().arrowRight){
            return animRH.getCurrentFrame();
        }
        else if(handler.getKeyManager().arrowLeft){
            return animLH.getCurrentFrame();
        }
        else if(handler.getKeyManager().arrowUp){
            return animUH.getCurrentFrame();
        }
        else if(handler.getKeyManager().arrowDown) {
            return animDH.getCurrentFrame();
        }
        else
            return animRLD.getCurrentFrame();
        }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }
}
