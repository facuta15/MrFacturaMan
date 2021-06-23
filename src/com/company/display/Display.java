package com.company.display;

import javax.swing.*;
import java.awt.*;

public class Display {
    private JFrame frame;//ventana
    private Canvas canvas;

    private String title;
    private int width,height;

    public Display(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;

        createDisplay();
    }

    public Canvas getCanvas() {
        return canvas;
    }

    private void createDisplay(){
        frame = new JFrame(title);
        frame.setSize(width,height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//es para que se cierre cuando apretas la x
        frame.setResizable(false);//asi se queda en las dimensiones que quiero
        frame.setLocationRelativeTo(null);//para que aparezca en el medio de la pantalla
        frame.setVisible(true);//es para que la ventana se vea


        canvas = new Canvas();//seria donde se van a poner los dibujitos
        canvas.setPreferredSize(new Dimension(width,height));
        canvas.setMaximumSize(new Dimension(width,height));
        canvas.setMinimumSize(new Dimension(width,height));//esto es para que se quede en las dimensiones que quiero
        canvas.setFocusable(false);

        frame.add(canvas);
        frame.pack();
    }
    public JFrame getFrame(){
        return frame;
    }
}
