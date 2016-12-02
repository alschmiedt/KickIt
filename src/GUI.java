import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

public class GUI {

	/**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("KickIt");
        
        JPanel panel = new JPanel();
        panel.setBackground(Color.DARK_GRAY);
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        
        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        panel.add(tabbedPane);
        
        JComponent modifyPanel = makeTextPanel("Modify");
        tabbedPane.addTab("Modify", null, modifyPanel, null);
        
        
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    } 
    
    protected static JComponent makeTextPanel(String text) {
        JPanel panel = new JPanel(false);
        JLabel filler = new JLabel(text);
        filler.setHorizontalAlignment(JLabel.CENTER);
        panel.setLayout(new GridLayout(1, 1));
        panel.add(filler);
        return panel;
    }
}
