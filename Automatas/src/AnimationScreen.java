import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class AnimationScreen {

    protected static void createAnimation(Screen screen, World world) {

        int ncols = world.getAutomata().length;
        if (ncols==0) {ncols=1;}

        // Dibujo del automata
        GridBagConstraints squareC = new GridBagConstraints();
        squareC.gridx = 1;squareC.gridy = 0;
        squareC.anchor = GridBagConstraints.CENTER;
        JPanel square = new JPanel(new GridLayout(0,ncols));
        Dimension size = new Dimension(screen.getHeight()*14/16,screen.getHeight()*14/16);
        square.setPreferredSize(size);
        square.setBackground(Color.DARK_GRAY);

        // Disposición de los botones
        GridBagConstraints optionsC = new GridBagConstraints();
        optionsC.gridx = 0;optionsC.gridy = 0;
        optionsC.ipadx = 10;optionsC.ipady = 5;
        optionsC.insets = new Insets(20,0,0,0);
        optionsC.anchor = GridBagConstraints.FIRST_LINE_START;

        GridBagConstraints pauseC = new GridBagConstraints();
        pauseC.gridx = 0;pauseC.gridy = 2;
        pauseC.ipadx = 10;pauseC.ipady = 5;
        pauseC.insets = new Insets(20,0,0,0);
        pauseC.anchor = GridBagConstraints.LAST_LINE_START;

        GridBagConstraints resumeC = new GridBagConstraints();
        resumeC.gridx = 2;resumeC.gridy = 2;
        resumeC.ipadx = 10;resumeC.ipady = 5;
        resumeC.insets = new Insets(20,0,0,0);
        resumeC.anchor = GridBagConstraints.LAST_LINE_END;

        // Elementos de la pantalla
        JButton toOptions = GraphicUtilities.button(GraphicUtilities.optionsButton);
        toOptions.setActionCommand(GraphicUtilities.toOptionsCommand);
        toOptions.addActionListener(screen);

        JButton pauseB = GraphicUtilities.button(GraphicUtilities.pauseButton);
        pauseB.setActionCommand(GraphicUtilities.pauseCommand);
        pauseB.addActionListener(screen);

        JButton resumeB = GraphicUtilities.button(GraphicUtilities.resumeButton);
        resumeB.setActionCommand(GraphicUtilities.resumeCommand);
        resumeB.addActionListener(screen);

        if (ncols>1) {
            int[][] matrix = world.getAutomata();
            Arrays.stream(matrix).forEach(r->printRow(square,r));
        }

        // Añadiendo los elementos en orden
        screen.add(square,squareC);
        screen.add(toOptions,optionsC);
        screen.add(pauseB,pauseC);
        screen.add(resumeB,resumeC);

    }

    private static void printRow(JPanel square, int[] row) {
        Arrays.stream(row).forEach(cell->printCell(square,cell));
    }

    private static void printCell(JPanel square, int cell) {
        if (cell == 0) {
            square.add(GraphicUtilities.deadCell());
        } else if (cell == 1) {
            square.add(GraphicUtilities.aliveCell());
        }
    }

}
