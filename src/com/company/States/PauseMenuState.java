package com.company.States;

import com.company.Handler;
import com.company.Ui.ClickListener;
import com.company.Ui.UIImageButton;
import com.company.Ui.UiManager;
import com.company.gfx.Assets;
import org.json.JSONException;
import org.json.JSONObject;

import java.awt.*;
import java.awt.event.KeyEvent;

public class PauseMenuState extends State {

    private UiManager uiManager;
    private boolean active=true;

    public PauseMenuState(Handler handler) {
        super(handler);

        uiManager = new UiManager(handler);

        uiManager.addObject(new UIImageButton(160, 200, 300, 160, Assets.bush, new ClickListener() {
            @Override
            public void onClick() {

            }
        }));
    }
    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public void tick() {
        if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_SPACE)) {
            active = !active;
        }
        if (!active){
            State.setCurrentState(handler.getJuego().gameState);
            return;
        }
        uiManager.tick();
    }

    @Override
    public void render(Graphics g) {
        uiManager.render(g);
    }


}
