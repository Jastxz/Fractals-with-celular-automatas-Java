import javax.swing.*;
import java.awt.*;
import java.util.StringTokenizer;

public class GraphicUtilities {

    // Constantes del sistema
    protected static final int DELAY = 100;

    // Constantes para la información de los mundos
    protected static final String menuScreen = "menu";
    protected static final String optionsScreen = "options";
    protected static final String propertiesScreen = "properties";
    protected static final String animationScreen = "animation";
    protected static final String randomCondition = "Random";
    protected static final String oneCondition = "One cell activated";
    protected static final int[] rules = {30,73,90,105,122,124,126,150,193,195};
    protected static final String smallBoard = "Small";
    protected static final String standardBoard = "Standard";
    protected static final String bigBoard = "Big";
    protected static final String veryBigBoard = "Very big";

    // Constantes para los comandos de los botones
    protected static final String toOptionsCommand = "go to options screen";
    protected static final String toPropertiesCommand = "go to properties screen";
    protected static final String toAnimationCommand = "go to animation screen";
    protected static final String pauseCommand = "pause animation";
    protected static final String resumeCommand = "resume animation";

    // Constantes para los botones
    protected static final String propertiesButton = "Properties of selected rule";
    protected static final String optionsButton = "Back to options";
    protected static final String watchButton = "Watch animation";
    protected static final String pauseButton = "Pause";
    protected static final String resumeButton = "Resume";

    protected static World initialMenu() {
        return new World(menuScreen,30,randomCondition,
                Automata.voidedAutomata(),1,standardBoard,false);
    }

    protected static JLabel textLine(String text) {
        JLabel line = new JLabel(text);
        line.setForeground(Color.WHITE);
        line.setBorder(BorderFactory.createEmptyBorder());
        return line;
    }

    protected static JLabel deadCell() {
        JLabel cell = new JLabel("");
        cell.setBackground(Color.BLACK);
        cell.setOpaque(true);
        return cell;
    }

    protected static JLabel aliveCell() {
        JLabel cell = new JLabel("");
        cell.setBackground(Color.WHITE);
        cell.setOpaque(true);
        return cell;
    }

    protected static JButton button(String label) {
        JButton b = new JButton(label);
        b.setBackground(Color.WHITE);
        b.setForeground(Color.BLACK);
        b.setBorder(BorderFactory.createEmptyBorder());
        return b;
    }

    protected static JButton buttonWrapped(String label) {
        JButton b = new JButton();
        label = wrapText(label,b.getWidth());
        b.setText(label);
        b.setBackground(Color.WHITE);
        b.setForeground(Color.BLACK);
        b.setBorder(BorderFactory.createEmptyBorder());
        return b;
    }

    protected static JRadioButton radioButton() {
        JRadioButton radio = new JRadioButton();
        radio.setBackground(Color.DARK_GRAY);
        return radio;
    }

    private static String wrapText(String text, int maxLineWidth) {
        //Return string initialized with opening html tag
        String returnString;

        //Current line width
        int lineWidth=0;

        //Iterate over string
        StringTokenizer tokenizer = new StringTokenizer(text," ");
        StringBuilder returnStringBuilder = new StringBuilder("<html>");
        while (tokenizer.hasMoreElements()) {
            String word = (String) tokenizer.nextElement();
            int stringWidth = word.length();

            //If word will cause a spill-over max line width
            if (stringWidth+lineWidth>=maxLineWidth) {

                //Add a new line, add a break tag and add the new word
                returnStringBuilder.append("<br>").append(word);

                //Reset line width
                lineWidth=0;
            }else{

                //No spill, so just add to current string
                returnStringBuilder.append(" ").append(word);
            }
            //Increase the width of the line
            lineWidth+=stringWidth;
        }
        returnString = returnStringBuilder.toString();

        //Close html tag
        returnString=(returnString+"<html>");

        //Return the string
        return returnString;
    }
}
