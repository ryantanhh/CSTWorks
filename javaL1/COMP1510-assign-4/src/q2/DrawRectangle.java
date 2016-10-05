package q2;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * A class extends JFrame, running as the main window of the application.
 * @author Hai Hua, Tan
 * @version 1.0
 */
public class DrawRectangle extends JFrame {

    /**
     * Unique version of this class.
     */
    private static final long serialVersionUID = -2227817674878876948L;

    /**
     * A method sets up the main window and add the drawing panel into 
     * the main window.
     */
    public DrawRectangle() {
        super("Hai Hua, Tan");
        final int width = 400;
        final int height = 400;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(new DrawRectanglePanel());
        setSize(width, height); 
        setVisible(true);
    }

    /**
     * A class that draws a rectangle using the rubber banding technique.
     */
    private class DrawRectanglePanel extends JPanel implements MouseListener,
            MouseMotionListener {
        /**
         * Unique version of this panel. 
         */
        private static final long serialVersionUID = -6463836043227685274L;
        /**A Point where the mouse is pressed.*/
        private Point point1;
        /**A Point where the mouse stops at when dragging.*/
        private Point point2;
        /**
         * Constructor.
         */
        DrawRectanglePanel() {
            addMouseListener(this);
            addMouseMotionListener(this);
        }

        /**
         * Draw image in the current panel.
         * @param g Graphics component to draw on
         */
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            // DRAWING CODE HERE
            
            if (point1 != null && point2 != null) {
                int rectWidth = Math.abs((point1.x - point2.x));
                int rectHeight = Math.abs((point1.y - point2.y));
                int leftTopX = (point1.x + point2.x) / 2 - rectWidth / 2;
                int leftTopY = (point1.y + point2.y) / 2 - rectHeight / 2;
                
                g.setColor(Color.YELLOW);
                g.drawRect(leftTopX, leftTopY, rectWidth, rectHeight);
            }
            System.out.println("Paint called");
        }
        /**
        * Gets the current position of the mouse as it is pressed.
        * @param e Contains position where mouse is released
         */
        public void mousePressed(MouseEvent e) {
            point1 = e.getPoint();
            System.out.println("Mouse pressed called");
        }
        /** {@inheritDoc} */
        public void mouseReleased(MouseEvent e) {
            System.out.println("Mouse released called");
        }
        /**
         * Gets the current position of the mouse as it is dragged.
         * @param e Contains position where mouse is released
         */
        public void mouseDragged(MouseEvent e) {
            point2 = e.getPoint();
            repaint();
            System.out.println("Mouse dragged called");
        }
        /** {@inheritDoc} */
        public void mouseEntered(MouseEvent e) {
        }
        /** {@inheritDoc} */
        public void mouseExited(MouseEvent e) {
        }
        /** {@inheritDoc} */
        public void mouseClicked(MouseEvent e) {
        }
        /** {@inheritDoc} */
        public void mouseMoved(MouseEvent e) {
        }

    }

    /**
     * Main entry of the program, called by JVM.
     * @param args Array of Command line arguments, unused.
     */
    public static void main(String[] args) {
        new DrawRectangle();
    }

};
