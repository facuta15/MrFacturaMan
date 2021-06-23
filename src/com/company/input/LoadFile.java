package com.company.input;

import com.company.Handler;
import com.company.dto.SaveFileDataToObject;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

public class LoadFile {
    private Handler handler;
    private boolean loadButton;
    //



    public LoadFile(Handler handler) {
        this.handler = handler;
    }
    public void load(){

        boolean f = true;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            File file = new File("saveFile.json");
            SaveFileDataToObject readSFD = objectMapper.readValue(file, SaveFileDataToObject.class);

            handler.getWorld().getEntityManager().getPlayer().setX(readSFD.getX());
            handler.getWorld().getEntityManager().getPlayer().setY(readSFD.getY());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void tick(){
        if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_F6)) {
            loadButton = true;
            load();
        }
    }
}
