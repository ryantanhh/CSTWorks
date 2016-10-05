package q4;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * <p>A class using a button to control the signal change of the 
 * traffic light.</p>
 *
 * @author Hai Hua, Tan
 * @version 1.0
 */
public class TrafficLight  extends JFrame {

    /**
     * A serial number to identify different version of the object.
     */
    private static final long serialVersionUID = 2054320679655514161L;
    /**
     * A button to control the traffic light.
     */
    private JButton button;
    /**
     * A panel to draw the lights.
     */
    private DrawingPanel lightPanel;
    /**
     * <p>The default constructor which sets the title of this app, sets the
     * default close operation to exit, creates a new content pane and finally
     * sets size and sets the visibility of this frame to true (show).</p>
     */
    public TrafficLight() {
        super("Hai Hua, Tan");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        button = new JButton("Change");
        button.addActionListener(new ButtonListener());
        JPanel panel = new JPanel();
        lightPanel = new DrawingPanel();
        panel.add(button);
        panel.add(lightPanel);
        final Color backgroundColor = new Color(236, 118, 0);
        panel.setBackground(backgroundColor);


        getContentPane().add(panel);
        final Dimension size = new Dimension(200, 420);

        setSize(size);
        setVisible(true);
    }
    
    
    /**
     * A class implements ActionListener to response button pushing.
     */
    private class ButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {
            // TODO Auto-generated method stub
            
            if (lightPanel.changeLightStatus()) {
                lightPanel.repaint();
            }
        }
        
    }
    

    /**
     * A panel for drawing.
     */
    class DrawingPanel extends JPanel {
        /**
         * A serial number to identify different version of the object.
         */
        private static final long serialVersionUID = 4967413106376101936L;
        
        /**
         * light signals have 3 situations, 0 stands for green light.
         */
        private final int greenLight = 0;
        /**
         * light signals have 3 situations, 1 stands for yellow light.
         */
        private final int yellowLight = 1;
        /**
         * light signals have 3 situations, 2 stands for red light.
         */
        private final int redLight = 2;
        /**
         * the current status of the lights.
         */
        private int currentLightState;

        /**
         * Constructor of DrawingPanel.
         */
        DrawingPanel() {
            final Dimension panelSize = new Dimension(150, 340);
            setPreferredSize(panelSize);
            final Color panelColor = new Color(193, 97, 0);
            setBackground(panelColor);
            currentLightState = 0;
            
        }

        /**
         * Override method to draw components in the panel.
         * @param g the Graphics object to draw
         */
        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            // replace this with your drawing code
            final int titlePositionX = 20;
            final int titlePositionY = 40;
            final int lightPositionX = 50;
            final int redPositionY = 60;
            final int yellowPositionY = 140;
            final int greenPositionY = 220;
            final Color redOff = new Color(0x660000);
            final Color yellowOff = new Color(0x999900);
            final Color greenOff = new Color(0x003300);
            g.drawString("Traffic light!", titlePositionX, titlePositionY);
            
            g.setColor(redOff);
            drawLight(lightPositionX, redPositionY, g);
            g.setColor(yellowOff);
            drawLight(lightPositionX, yellowPositionY, g); 
            g.setColor(greenOff);
            drawLight(lightPositionX, greenPositionY, g);
            
            if (currentLightState == 0) {
                g.setColor(Color.GREEN);
                drawLight(lightPositionX, greenPositionY, g);
            } else if (currentLightState == 1) {
                g.setColor(Color.YELLOW);
                drawLight(lightPositionX, yellowPositionY, g);
            } else {
                g.setColor(Color.RED);
                drawLight(lightPositionX, redPositionY, g);
            }
            
            
        }
        
        /**
         *Method to draw lights with given condition.
         *@param x coordinate x to draw the light
         *@param y coordinate y to draw the light 
         *@param g Graphics object obtained to draw the light
         */
        private void drawLight(int x, int y, Graphics g) {
            final int diameter = 60;
            g.fillOval(x, y, diameter, diameter);
        }
        
        /**
         * Method to change the traffic light status.
         * @return boolean value, return true if change the light.
         */
        public boolean changeLightStatus() {
            if (currentLightState == greenLight) {
                currentLightState = yellowLight;
                return true;
            }
            if (currentLightState == yellowLight) {
                currentLightState = redLight;
                return true;
            }
            if (currentLightState == redLight) {
                currentLightState = greenLight;
                return true;
            }
            return false;
        }
        
        
    }


    /**
     * <p>The main method.</p>
     * @param args command line parameters if input. unused in this program.
     */
    public static void main(String[] args) {
        new TrafficLight();
    }

};
