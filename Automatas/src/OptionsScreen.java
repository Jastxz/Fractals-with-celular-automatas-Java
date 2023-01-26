import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class OptionsScreen {

    // Constantes de los títulos de cada opción
    private static final String conditionsTitle = "Initial conditions";
    private static final String rulesTitle = "Choose rule";
    private static final String boardTitle = "Choose number of cells";

    // Parabra para referir otros strings
    private static final String radio = "radio";

    protected static void createOptions(Screen screen) {
        // Elementos de la pantalla
        JPanel rectangle = new JPanel(new GridLayout(0,10));
        Dimension size = new Dimension(screen.getWidth()*3/4,screen.getHeight()*3/4);
        rectangle.setPreferredSize(size);
        rectangle.setBackground(Color.DARK_GRAY);
        rectangle.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));

        String[][] optionsLabels = getOptionsLabels();

        // Añadiendo los elementos en orden
        addRadioButtons(optionsLabels, rectangle, screen);
        for (String button : optionsLabels[optionsLabels.length-1]) {
            switch (button) {
                case GraphicUtilities.propertiesButton -> {
                    JButton toProps = GraphicUtilities.buttonWrapped(button);
                    toProps.setActionCommand(GraphicUtilities.toPropertiesCommand);
                    toProps.addActionListener(screen);
                    rectangle.add(toProps);
                }
                case GraphicUtilities.watchButton -> {
                    JButton toAnim = GraphicUtilities.button(button);
                    toAnim.setActionCommand(GraphicUtilities.toAnimationCommand);
                    toAnim.addActionListener(screen);
                    rectangle.add(toAnim);
                }
                default -> rectangle.add(GraphicUtilities.textLine(button));
            }
        }
        screen.add(rectangle);
    }

    protected static String[][] getOptionsLabels() {

        // Inicializamos las variables como podamos
        String[] firstLine = {conditionsTitle};
        String[] secondLine = {GraphicUtilities.randomCondition,GraphicUtilities.oneCondition};
        String[] thirdLine = enumCheckboxes(secondLine);
        String[] fourthLine = {rulesTitle};
        String[] fifthLine = Arrays.stream(GraphicUtilities.rules).
                mapToObj(Integer::toString).toArray(String[]::new);
        String[] sixthLine = enumCheckboxes(fifthLine);
        String[] seventhLine = {boardTitle};
        String[] eighthLine = {GraphicUtilities.smallBoard,GraphicUtilities.standardBoard,
                GraphicUtilities.bigBoard,GraphicUtilities.veryBigBoard};
        String[] nineLine = enumCheckboxes(eighthLine);
        String[] tenthLine = {GraphicUtilities.propertiesButton,"","","","",
                "","","","",GraphicUtilities.watchButton};

        // Tratamos las variables para que estén listas para su uso
        firstLine = fillUntilTen(firstLine);
        secondLine = fillUntilTen(secondLine);
        fourthLine = fillUntilTen(fourthLine);
        fifthLine = fillUntilTen(fifthLine);
        seventhLine = fillUntilTen(seventhLine);
        eighthLine = fillUntilTen(eighthLine);

        // Devolvemos lo que hay que devolver

        return new String[][]{firstLine,secondLine,thirdLine,fourthLine,fifthLine,
                sixthLine,seventhLine,eighthLine,nineLine,tenthLine};
    }

    private static String[] enumCheckboxes(String[] options) {

        options = Arrays.stream(options).map(o-> radio).toArray(String[]::new);

        return fillUntilTen(options);
    }

    private static String[] fillUntilTen(String[] toFill) {

        String[] res = new String[10];
        if (toFill.length == 10) {
            res = toFill;
        } else {
            int i = 0;
            while (i < 10) {
                if (i < toFill.length) {
                    res[i] = toFill[i];
                } else {
                    res[i] = "";
                }
                i++;
            }
        }

        return res;
    }

    private static void addRadioButtons(String[][] optionsLabels, JPanel rectangle, Screen screen) {

        ButtonGroup conditionsG = new ButtonGroup();
        ButtonGroup rulesG = new ButtonGroup();
        ButtonGroup boardG = new ButtonGroup();

        World world = screen.getWorld();

        // Labels
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < optionsLabels[i].length; j++) {
                String option = optionsLabels[i][j];
                if (!option.equals(radio)) {
                    rectangle.add(GraphicUtilities.textLine(option));
                }
            }
        }

        // Conditions radio buttons
        for (int i = 0; i < optionsLabels[2].length; i++) {
            String option = optionsLabels[2][i];
            String command = optionsLabels[1][i];
            if (option.equals(radio)) {
                JRadioButton radio = GraphicUtilities.radioButton();
                radio.setActionCommand(command);
                if (world.getConditions().equals(command)) radio.setSelected(true);
                conditionsG.add(radio);
                rectangle.add(radio);
                radio.addActionListener(screen);
            } else {
                rectangle.add(GraphicUtilities.textLine(option));
            }
        }

        // Labels
        for (int i = 3; i < 5; i++) {
            for (int j = 0; j < optionsLabels[i].length; j++) {
                String option = optionsLabels[i][j];
                if (!option.equals(radio)) {
                    rectangle.add(GraphicUtilities.textLine(option));
                }
            }
        }

        // Rules radio buttons
        for (int i = 0; i < optionsLabels[5].length; i++) {
            String option = optionsLabels[5][i];
            String command = optionsLabels[4][i];
            if (option.equals(radio)) {
                JRadioButton radio = GraphicUtilities.radioButton();
                radio.setActionCommand(command);
                if (world.getRule().toString().equals(command)) radio.setSelected(true);
                rulesG.add(radio);
                rectangle.add(radio);
                radio.addActionListener(screen);
            } else {
                rectangle.add(GraphicUtilities.textLine(option));
            }
        }

        // Labels
        for (int i = 6; i < 8; i++) {
            for (int j = 0; j < optionsLabels[i].length; j++) {
                String option = optionsLabels[i][j];
                if (!option.equals(radio)) {
                    rectangle.add(GraphicUtilities.textLine(option));
                }
            }
        }

        // Board radio buttons
        for (int i = 0; i < optionsLabels[8].length; i++) {
            String option = optionsLabels[8][i];
            String command = optionsLabels[7][i];
            if (option.equals(radio)) {
                JRadioButton radio = GraphicUtilities.radioButton();
                radio.setActionCommand(command);
                if (world.getBoardTam().equals(command)) radio.setSelected(true);
                boardG.add(radio);
                rectangle.add(radio);
                radio.addActionListener(screen);
            } else {
                rectangle.add(GraphicUtilities.textLine(option));
            }
        }
    }
}
