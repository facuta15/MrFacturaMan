package com.company.input;

import com.company.Handler;
import org.json.JSONException;
import org.json.JSONObject;

import java.awt.event.KeyEvent;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class SavePlayerPosition {
    private Handler handler;
    private boolean saveButton;
    private DataOutputStream savefile = null;

    public SavePlayerPosition(Handler handler) {
        this.handler = handler;
    }
    public void save(){
        if(saveButton == true){
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("xPos",handler.getWorld().getEntityManager().getPlayer().getX());
                jsonObject.put("yPos",handler.getWorld().getEntityManager().getPlayer().getY());
                savefile = new DataOutputStream(new FileOutputStream("saveFile.dat"));
                try {
                    savefile.writeUTF(jsonObject.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    try {
                        savefile.close();
                        System.out.println("save succsesfull");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                saveButton = false;
            } catch (JSONException | FileNotFoundException e) {
                e.printStackTrace();
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
