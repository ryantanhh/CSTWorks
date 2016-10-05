package q3;

import javax.swing.JFrame;

/**
 * <p>A driver class that display and runs the stop watch.</p>
 *
 * @author Hai Hua, Tan
 * @version 1.0
 */
public class StopWatch {
    /**
     * <p>This is the main method (entry point) that gets called by the JVM.</p>
     *
     * @param args command line arguments.
     */
    public static void main(String[] args) {
        // your code will go here!!!
        JFrame frame = new JFrame("Stop Watch");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.getContentPane().add(new StopWatchPanel());
        frame.pack();
        frame.setVisible(true);
        System.out.println("Question three was called and ran sucessfully.");
    }

};
