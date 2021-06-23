package com.company.States;

import com.company.Handler;

import javax.swing.*;
import java.awt.*;

public class EndGameState extends State{
    public EndGameState(Handler handler) {
        super(handler);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        JOptionPane.showMessageDialog(null, "Your message goes here!","Message", JOptionPane.ERROR_MESSAGE);
    }
}
