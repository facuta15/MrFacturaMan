package com.company.gfx;

import java.awt.image.BufferedImage;

public class Assets {
    //esta clase es para cualquier imagen o sonido que haya

    public static BufferedImage grass,dirt,medialuna, water,tuco,seagull,rock,mate,chest;
    public static BufferedImage[] player_LRD;//left right down
    public static BufferedImage[] player_LRU;//left right up
    public static BufferedImage[] playButton;
    public static BufferedImage[] player_UH;
    public static BufferedImage[] player_DH;
    public static BufferedImage[] player_LH;
    public static BufferedImage[] player_RH;
    public static BufferedImage[] tree;
    public static BufferedImage[] fisuraLeft;
    public static BufferedImage[] fisuraRight;
    public static BufferedImage[] bush;



    private static final int width = 100,height = 100;

    public static void init(){
        SpriteSheet sheet = new SpriteSheet(ImageLoad.loadImage("/res/textures/Sprite Sheet posta.png"));

        player_LRD = new BufferedImage[2];
        player_LRU = new BufferedImage[2];
        playButton = new BufferedImage[2];
        player_UH  = new BufferedImage[2];
        player_DH  = new BufferedImage[2];
        player_LH  = new BufferedImage[2];
        player_RH  = new BufferedImage[2];
        tree  = new BufferedImage[2];
        fisuraLeft = new BufferedImage[2];
        fisuraRight = new BufferedImage[2];
        bush = new BufferedImage[2];

        player_LRU[0] = sheet.crop(width  * 2 ,height * 2,width,height);
        player_LRU[1] = sheet.crop(width * 3,height * 2,width,height);
        player_LRD[0] = sheet.crop(0,height * 2,width,height);
        player_LRD[1] = sheet.crop(width,height * 2,width,height);
        playButton[0] = sheet.crop(width * 2, height * 3,width *2,height);
        playButton[1] = sheet.crop(width * 4, 0,width *2,height);
        //Hit Animations
        player_UH[0] = sheet.crop(0   ,height * 4,width,height);
        player_UH[1] = sheet.crop(0   ,0,width,height);
        player_DH[0] = sheet.crop(width,height * 4, width,height);
        player_DH[1] = sheet.crop(0   ,0,width,height);
        player_LH[0] = sheet.crop(width * 4,height * 3 ,width,height);
        player_LH[1] = sheet.crop(width ,height * 2 ,width,height);
        player_RH[0] = sheet.crop(width * 5,height * 4, width,height);
        player_RH[1] = sheet.crop(0,0, width,height);
        //Objects Animations
        tree[0] = sheet.crop(width * 4,height * 2,width,height );
        tree[1] = sheet.crop(width* 6, 0,width,height);
        fisuraLeft[0] = sheet.crop(0,height*5,width,height);
        fisuraRight[0] = sheet.crop(width,height*5,width,height);
        fisuraLeft[1] = sheet.crop(width *2,height*5,width,height);
        fisuraRight[1] = sheet.crop(width * 3,height*5,width,height);
        bush[0] = sheet.crop(width * 2,height * 4,width,height);
        bush[1] = sheet.crop(width * 3,height * 4,width,height);

        //Still objects
        chest = sheet.crop(width * 4,height * 5,width,height);
        grass = sheet.crop(width,0,width ,height - 2  );
        dirt = sheet.crop(width * 2 ,0, width - 1,height - 1 );
        medialuna = sheet.crop(width* 3,0,width,height);
        water = sheet.crop(0 ,height,width,height - 2 );
        rock = sheet.crop(width,height * 3,width,height);
        tuco = sheet.crop(width * 2,height ,width ,height  );
        seagull = sheet.crop(width * 3,height ,width ,height  );
        mate = sheet.crop(width * 4,height,width,height);
    }
}
