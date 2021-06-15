package com.company.States;

import com.company.Handler;

import java.awt.*;

public abstract class State {

    private static State currentState = null;


    public static void setCurrentState(State state){
        currentState = state;
    }

    public static State getCurrentState() {
        return currentState;
    }

    //CLASS

    protected Handler handler;

    public State(Handler handler){
        this.handler = handler;
    }
    public abstract void tick();
    public abstract void render(Graphics g);
}
