package com.company.States;

import com.company.Handler;
import com.company.Ui.ClickListener;
import com.company.Ui.UIImageButton;
import com.company.Ui.UiManager;
import com.company.gfx.Assets;

import java.awt.*;

public class MenuState extends State{

    private UiManager uiManager;

    public MenuState(Handler handler) {
        super(handler);
        uiManager = new UiManager(handler);
       handler.getMouseManager().setUiManager(uiManager);

        uiManager.addObject(new UIImageButton(160, 200, 300, 160, Assets.playButton, new ClickListener() {
            @Override
            public void onClick() {
                State.setCurrentState(handler.getJuego().gameState);
            }
        }));
    }

    @Override
    public void tick() {
        uiManager.tick();
    }

    @Override
    public void render(Graphics g) {
        uiManager.render(g);
    }
}
