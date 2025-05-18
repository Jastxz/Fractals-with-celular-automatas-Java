package com.jastxz.fractals;
import javax.swing.*;
import java.awt.*;

public class WelcomeScreen {

    // Constantes para los botones y mensajes
    private static final String w1 = "Welcome! With this application you will generate";
    private static final String w2 = " fractal patterns using cellular automata. ";
    private static final String w3 = "To get started, click on the button below.";
    private static final String b = "Start";

    protected static void createWelcome(Screen screen) {
        // Disposición de los elementos en la pantalla
        GridBagConstraints welcomeC = new GridBagConstraints();
        welcomeC.gridx = 1;welcomeC.gridy = 0;

        GridBagConstraints indicationC = new GridBagConstraints();
        indicationC.gridx = 1;indicationC.gridy = 1;

        GridBagConstraints buttonC = new GridBagConstraints();
        buttonC.gridx = 1;buttonC.gridy = 3;
        buttonC.ipadx = 10;buttonC.ipady = 5;
        buttonC.insets = new Insets(20,0,0,0);

        // Elementos de la pantalla
        JLabel welcome = GraphicUtilities.textLine(w1+w2);
        JLabel indication = GraphicUtilities.textLine(w3);
        JButton toOptions = GraphicUtilities.button(b);
        toOptions.setActionCommand(GraphicUtilities.toOptionsCommand);
        toOptions.addActionListener(screen);

        // Añadiendo los elementos en orden
        screen.add(welcome,welcomeC);
        screen.add(indication,indicationC);
        screen.add(toOptions,buttonC);
    }

}
