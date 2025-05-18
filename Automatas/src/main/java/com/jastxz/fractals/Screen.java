package com.jastxz.fractals;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Screen extends JPanel implements ActionListener {

    private final World localeWorld;

    public Screen(World world) {

        // Initializing locale world
        this.localeWorld = world;
        // Timer for refresh actions
        Timer timer = new Timer(GraphicUtilities.DELAY, this);

        // Basics of screen
        Dimension size = new Dimension(1400,700);
        setPreferredSize(size);
        setBackground(Color.DARK_GRAY);
        setLayout(new GridBagLayout());

        WelcomeScreen.createWelcome(this);

        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String command = e.getActionCommand();
        // Button actions for change screen
        if (GraphicUtilities.toOptionsCommand.equals(command)) {
            this.localeWorld.setScreen(GraphicUtilities.optionsScreen);
        } else if (GraphicUtilities.toPropertiesCommand.equals(command)) {
            this.localeWorld.setScreen(GraphicUtilities.propertiesScreen);
        } else if (GraphicUtilities.toAnimationCommand.equals(command)) {
            this.localeWorld.setScreen(GraphicUtilities.animationScreen);
        } else if (GraphicUtilities.pauseCommand.equals(command)) {
            this.localeWorld.setAnimation(false);
        } else if (GraphicUtilities.resumeCommand.equals(command)) {
            this.localeWorld.setAnimation(true);
        }

        // Change of options
        String[][] optionsLabels = OptionsScreen.getOptionsLabels();
        int radioRow = -1;
        for (int i = 0; i < optionsLabels.length - 1; i++) {
            for (String label : optionsLabels[i]) {
                if (label.equals(command)) {
                    radioRow = i;
                    break;
                }
            }
        }
        switch (radioRow) {
            case 1 -> {
                if (GraphicUtilities.randomCondition.equals(command)) {
                    this.localeWorld.setConditions(GraphicUtilities.randomCondition);
                } else if (GraphicUtilities.oneCondition.equals(command)) {
                    this.localeWorld.setConditions(GraphicUtilities.oneCondition);
                }
            }
            case 4 -> {
                for (String rule : optionsLabels[radioRow]) {
                    if (rule.equals(command)) {
                        this.localeWorld.setRule(Integer.parseInt(rule));
                    }
                }
            }
            case 7 -> {
                if (GraphicUtilities.smallBoard.equals(command)) {
                    this.localeWorld.setBoardTam(GraphicUtilities.smallBoard);
                } else if (GraphicUtilities.standardBoard.equals(command)) {
                    this.localeWorld.setBoardTam(GraphicUtilities.standardBoard);
                } else if (GraphicUtilities.bigBoard.equals(command)) {
                    this.localeWorld.setBoardTam(GraphicUtilities.bigBoard);
                } else if (GraphicUtilities.veryBigBoard.equals(command)) {
                    this.localeWorld.setBoardTam(GraphicUtilities.veryBigBoard);
                }
            }
            default -> {}
        }

        removeAll();
        updateScreen();
        revalidate();
        repaint();
    }

    private void updateScreen() {
        switch (this.localeWorld.getScreen()) {
            case GraphicUtilities.optionsScreen -> {
                this.localeWorld.setAutomata(Automata.voidedAutomata());
                this.localeWorld.setActualRow(1);
                this.localeWorld.setAnimation(false);
                OptionsScreen.createOptions(this);
            }
            case GraphicUtilities.propertiesScreen -> PropertiesScreen.createProperties(this,this.localeWorld);
            case GraphicUtilities.animationScreen -> {
                AnimationScreen.createAnimation(this);
                if (this.localeWorld.getAutomata().length <= 1) {
                    String conditions = this.localeWorld.getConditions();
                    String board = this.localeWorld.getBoardTam();
                    this.localeWorld.setAutomata(Automata.initializeAutomata(conditions,board));
                    this.localeWorld.setAnimation(true);
                } else if (this.localeWorld.getAnimation()
                        && this.localeWorld.getActualRow() < this.localeWorld.getAutomata().length){
                    Automata.updateAutomata(this.localeWorld);
                }
            }
            default -> WelcomeScreen.createWelcome(this);
        }
    }

    protected World getWorld() {return localeWorld;}

}
