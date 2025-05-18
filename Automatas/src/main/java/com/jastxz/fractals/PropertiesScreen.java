package com.jastxz.fractals;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PropertiesScreen {

    // Título
    private static final String title = "Properties of rule ";

    protected static void createProperties(Screen screen, World world) {

        Integer rule = world.getRule();
        ArrayList<String> messages = ruleMessages(rule);
        int column = 1;
        String ruleTitle = title + rule;

        // Título para la pantalla
        GridBagConstraints titleC = new GridBagConstraints();
        titleC.gridx = 1;titleC.gridy = 0;
        JLabel titleL = GraphicUtilities.textLine(ruleTitle);
        screen.add(titleL,titleC);


        // Propiedades de la regla en cuestión
        for (String message : messages) {
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 1;gbc.gridy = column;
            JLabel label = GraphicUtilities.textLine(message);
            screen.add(label,gbc);
            column++;
        }

        // Disposición de los botones
        GridBagConstraints optionsC = new GridBagConstraints();
        optionsC.gridx = 0;optionsC.gridy = column;
        optionsC.ipadx = 10;optionsC.ipady = 5;
        optionsC.insets = new Insets(20,0,0,0);

        GridBagConstraints watchC = new GridBagConstraints();
        watchC.gridx = 2;watchC.gridy = column;
        watchC.ipadx = 10;watchC.ipady = 5;
        watchC.insets = new Insets(20,0,0,0);

        // Elementos de la pantalla
        JButton toOptions = GraphicUtilities.button(GraphicUtilities.optionsButton);
        toOptions.setActionCommand(GraphicUtilities.toOptionsCommand);
        toOptions.addActionListener(screen);

        JButton toAnim = GraphicUtilities.button(GraphicUtilities.watchButton);
        toAnim.setActionCommand(GraphicUtilities.toAnimationCommand);
        toAnim.addActionListener(screen);

        // Añadiendo los elementos en orden
        screen.add(toOptions,optionsC);
        screen.add(toAnim,watchC);

    }

    private static ArrayList<String> ruleMessages(Integer rule) {

        ArrayList<String> res = new ArrayList<>();
        String dimension = "";

        switch (rule) {
            case 73 -> dimension = "~1.7771";
            case 90 -> dimension = "~1.5401";
            case 105 -> dimension = "~1.7751";
            case 124 -> dimension = "~1.778";
            case 126 -> dimension = "~1.6657";
            case 150 -> dimension = "~1.6407";
            case 193 -> dimension = "~1.7598";
            case 195 -> dimension = "~1.5708";
        }

        String senIni = "- Sensitive to initial conditions";
        String topTrans = "- Topologically transitive";
        String densePoints = "- Periodic points are dense";
        String fractalDim = "- Fractal dimension: " + dimension;
        String variation = "- Sierpinski triangle variation";
        String effect = "Simply is an optic effect";
        String boxCounting = "Note: Calculated with box counting method and an error of ~|0.05|";

        switch (rule) {
            case 30 -> res.addAll(List.of(senIni,topTrans,densePoints));
            case 122 -> res.add(effect);
            case 195 -> res.addAll(List.of(variation,fractalDim,boxCounting));
            default -> res.addAll(List.of(fractalDim,boxCounting));
        }

        return res;
    }

}
