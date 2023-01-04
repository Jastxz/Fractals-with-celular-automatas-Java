import javax.swing.*;

public class MainInteraction {
    public static void main(String[] args){
        World world = GraphicUtilities.initialMenu();
        SwingUtilities.invokeLater(() -> mainInteraction(world));
    }

    protected static void mainInteraction(World world) {
        JFrame window = new JFrame("Automatas");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // create the jpanel to draw on.
        Screen screen = new Screen(world);
        window.add(screen);

        window.setResizable(false);
        // pack() should be called after setResizable() to avoid issues on some platforms
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
}
