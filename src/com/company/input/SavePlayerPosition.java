package com.company.input;

import com.company.Handler;
import com.company.dto.SaveFileDataToObject;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.awt.event.KeyEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;

public class SavePlayerPosition implements Serializable {
    private Handler handler;
    private boolean saveButton;
    private FileWriter fileWriter;

    public SavePlayerPosition(Handler handler) {
        this.handler = handler;
    }
    public void save(){
        if(saveButton == true){
            FileWriter fileWriter = null;
            try {
                fileWriter = new FileWriter("saveFile.json");
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                SaveFileDataToObject saveFileDto = new SaveFileDataToObject(handler.getWorld().getEntityManager().getPlayer().getX(),handler.getWorld().getEntityManager().getPlayer().getY());
                ObjectMapper objectMapper = new ObjectMapper();
                String json = objectMapper.writeValueAsString(saveFileDto);
                System.out.println(json);
                fileWriter.write(json);
                //guarda las coordenadas de donde esta el player al momento de guardar SOLO LA POSICION,NADA MAS
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    try {
                        fileWriter.flush();
                        fileWriter.close();
                        saveButton = false;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

    public void tick(){
        if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_F5)) {
            saveButton = true;
            save();
            }
        }
    }
