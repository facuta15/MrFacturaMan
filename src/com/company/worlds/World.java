package com.company.worlds;

import com.company.Handler;
import com.company.Tiles.Tile;
import com.company.entitys.EntityManager;
import com.company.entitys.Items.ItemManager;
import com.company.entitys.Player;
import com.company.entitys.Seagull;
import com.company.entitys.statics.Bush;
import com.company.entitys.statics.Chest;
import com.company.entitys.statics.Tree;
import com.company.utils.Utils;

import java.awt.*;

public class World {
    private Handler handler;
    private int width,height;
    private int spawnX,spawnY;
    private int[][] tiles;
    //ENTITIES
    private EntityManager entityManager;
    //Item
    private ItemManager itemManager;


    public World(Handler handler, String path){
        this.handler = handler;
        entityManager = new EntityManager(handler,new Player(handler,300,300));
        itemManager = new ItemManager(handler);

        loadWorld(path);

        entityManager.getPlayer().setX(spawnX);
        entityManager.getPlayer().setY(spawnY);
        entityManager.addEntity(new Seagull(handler,750,70, entityManager.getPlayer(),(long)200));
        entityManager.addEntity(new Tree(handler,200,200));
        entityManager.addEntity(new Tree(handler,150,800));
        entityManager.addEntity(new Tree(handler,450,250));
        entityManager.addEntity(new Tree(handler,500,700));
        entityManager.addEntity(new Bush(handler,350,400));
        entityManager.addEntity(new Chest(handler,1100,200));
    }

    public void tick(){
        itemManager.tick();
        entityManager.tick();
    }

    public void render(Graphics g){
        int xStart = (int )Math.max(0, handler.getCamera().getxOffset()/Tile.TILE_WIDTH);
        int xEnd = (int) Math.min (width,(handler.getCamera().getxOffset() + handler.getWidth()) / Tile.TILE_WIDTH + 1);
        int yStart = (int )Math.max(0, handler.getCamera().getyOffset()/Tile.TILE_HEIGHT);
        int yEnd = (int) Math.min (height,(handler.getCamera().getyOffset() + handler.getHeigth()) / Tile.TILE_HEIGHT + 1);
        //lo que hace esto es que se renderize solo la porcion que esta visible, lo bueno de esto es que puedo hacer mapas grandes sin explotar la pc cargandolo
        //toodo de una
        for (int y = yStart;y<yEnd;y++){
            for (int x = xStart;x<xEnd;x++){
                getTile(x,y).render(g,(int)(x * Tile.TILE_WIDTH - handler.getCamera().getxOffset()),(int)(y * Tile.TILE_HEIGHT - handler.getCamera().getyOffset()));
                //lo que hace esto es renderizar en la ventana con la camara corrida
            }
        }
        //items
        itemManager.render(g);
        //entities
        entityManager.render(g);
    }
    private void loadWorld(String path){
        String file = Utils.loadFileAsString(path);
        String[] tokens = file.split("\\s+");
        System.out.println(tokens[0]);
        width = Utils.parseInt(tokens[0]);
        height = Utils.parseInt(tokens[1]);
        spawnX = Utils.parseInt(tokens[2]);
        spawnY = Utils.parseInt(tokens[3]);


        tiles = new int[width][height];
        for (int y = 0;y < height;y++){
            for (int x = 0; x < width ;x++){
              tiles[x][y] = Utils.parseInt(tokens[(x + y *width )+ 4]  );
            }
        }
    }
    public Tile getTile(int x, int y){

        if (x < 0 || y < 0 || x >= width || y >= height)
            return Tile.grassTile;//se tuvo que poner esta verificacion por si se glitchea y el player se sale del mapa
                                  // de esta forma el juego no se rompe

        Tile t = Tile.tiles[tiles[x][y]];
        if(t == null){//si el tile que se le pasa por parametro esta afuera de los que tengo, devuelve uno default de tierra
            return Tile.dirtTile;
        }
        return t;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public ItemManager getItemManager() {
        return itemManager;
    }

    public void setItemManager(ItemManager itemManager) {
        this.itemManager = itemManager;
    }
}
