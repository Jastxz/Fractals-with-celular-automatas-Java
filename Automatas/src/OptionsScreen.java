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

        // Conditions radio buttons
        JRadioButton randomRB = GraphicUtilities.radioButton();
        randomRB.setActionCommand(optionsLabels[1][0]);
        JRadioButton oneRB = GraphicUtilities.radioButton();
        oneRB.setActionCommand(optionsLabels[1][1]);

        conditionsG.add(randomRB);conditionsG.add(oneRB);

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < optionsLabels[i].length; j++) {
                String option = optionsLabels[i][j];
                if (!option.equals(radio)) {
                    rectangle.add(GraphicUtilities.textLine(option));
                }
            }
        }
        rectangle.add(randomRB);rectangle.add(oneRB);
        // Vacios
        rectangle.add(GraphicUtilities.textLine(""));rectangle.add(GraphicUtilities.textLine(""));
        rectangle.add(GraphicUtilities.textLine(""));rectangle.add(GraphicUtilities.textLine(""));
        rectangle.add(GraphicUtilities.textLine(""));rectangle.add(GraphicUtilities.textLine(""));
        rectangle.add(GraphicUtilities.textLine(""));rectangle.add(GraphicUtilities.textLine(""));

        // Rules radio buttons
        JRadioButton r1RB = GraphicUtilities.radioButton();
        r1RB.setActionCommand(optionsLabels[4][0]);
        JRadioButton r2RB = GraphicUtilities.radioButton();
        r2RB.setActionCommand(optionsLabels[4][1]);
        JRadioButton r3RB = GraphicUtilities.radioButton();
        r3RB.setActionCommand(optionsLabels[4][2]);
        JRadioButton r4RB = GraphicUtilities.radioButton();
        r4RB.setActionCommand(optionsLabels[4][3]);
        JRadioButton r5RB = GraphicUtilities.radioButton();
        r5RB.setActionCommand(optionsLabels[4][4]);
        JRadioButton r6RB = GraphicUtilities.radioButton();
        r6RB.setActionCommand(optionsLabels[4][5]);
        JRadioButton r7RB = GraphicUtilities.radioButton();
        r7RB.setActionCommand(optionsLabels[4][6]);
        JRadioButton r8RB = GraphicUtilities.radioButton();
        r8RB.setActionCommand(optionsLabels[4][7]);
        JRadioButton r9RB = GraphicUtilities.radioButton();
        r9RB.setActionCommand(optionsLabels[4][8]);
        JRadioButton r10RB = GraphicUtilities.radioButton();
        r10RB.setActionCommand(optionsLabels[4][9]);

        rulesG.add(r1RB);rulesG.add(r2RB);rulesG.add(r3RB);rulesG.add(r4RB);rulesG.add(r5RB);
        rulesG.add(r6RB);rulesG.add(r7RB);rulesG.add(r8RB);rulesG.add(r9RB);rulesG.add(r10RB);

        for (int i = 3; i < 5; i++) {
            for (int j = 0; j < optionsLabels[i].length; j++) {
                String option = optionsLabels[i][j];
                if (!option.equals(radio)) {
                    rectangle.add(GraphicUtilities.textLine(option));
                }
            }
        }
        rectangle.add(r1RB);rectangle.add(r2RB);rectangle.add(r3RB);rectangle.add(r4RB);rectangle.add(r5RB);
        rectangle.add(r6RB);rectangle.add(r7RB);rectangle.add(r8RB);rectangle.add(r9RB);rectangle.add(r10RB);

        // Board radio buttons
        JRadioButton smallRB = GraphicUtilities.radioButton();
        smallRB.setActionCommand(optionsLabels[7][0]);
        JRadioButton standardRB = GraphicUtilities.radioButton();
        standardRB.setActionCommand(optionsLabels[7][1]);
        JRadioButton bigRB = GraphicUtilities.radioButton();
        bigRB.setActionCommand(optionsLabels[7][2]);
        JRadioButton veryBigRB = GraphicUtilities.radioButton();
        veryBigRB.setActionCommand(optionsLabels[7][3]);

        boardG.add(smallRB);boardG.add(standardRB);
        boardG.add(bigRB);boardG.add(veryBigRB);

        for (int i = 6; i < 8; i++) {
            for (int j = 0; j < optionsLabels[i].length; j++) {
                String option = optionsLabels[i][j];
                if (!option.equals(radio)) {
                    rectangle.add(GraphicUtilities.textLine(option));
                }
            }
        }
        rectangle.add(smallRB);rectangle.add(standardRB);
        rectangle.add(bigRB);rectangle.add(veryBigRB);
        // Vacios
        rectangle.add(GraphicUtilities.textLine(""));rectangle.add(GraphicUtilities.textLine(""));
        rectangle.add(GraphicUtilities.textLine(""));rectangle.add(GraphicUtilities.textLine(""));
        rectangle.add(GraphicUtilities.textLine(""));rectangle.add(GraphicUtilities.textLine(""));

        // Añadimos los radio buttons a los eventos
        randomRB.addActionListener(e -> {
            conditionsG.clearSelection();
            conditionsG.setSelected(randomRB.getModel(), true);
            randomRB.validate();
            screen.actionPerformed(e);
        });
        oneRB.addActionListener(e -> {
            conditionsG.clearSelection();
            oneRB.setSelected(true);
            screen.actionPerformed(e);
        });
        for (JRadioButton jRadioButton : Arrays.asList(r1RB, r2RB, r3RB, r4RB, r5RB, r6RB,
                r7RB, r8RB, r9RB, r10RB, smallRB, standardRB, bigRB, veryBigRB)) {
            jRadioButton.addActionListener(screen);
        }

    }
}
