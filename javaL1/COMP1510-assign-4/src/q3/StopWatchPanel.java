package q3;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
/**
 * A class that draw a stop watch.
 * @author Hai Hua, Tan
 * @version 1.0
 */
public class StopWatchPanel extends JPanel {
    /** Unique version of this class.*/
    private static final long serialVersionUID = 534772827577889326L;
    /**The hours in the watch.*/
    private int hours;
    /**The minutes in the watch.*/
    private int minutes;
    /**The seconds in the watch.*/
    private int seconds;
    /**The milliseconds in the watch.*/
    private int milliSeconds;
    /**Time interval for time to update the stop watch.*/
    private final int interval = 100;
    
    /**A label displaying the time elapse in the stop watch.*/
    private JLabel display;
    /**A button to start, pause or resume the stop watch.*/
    private JButton startButton;
    /**A button to stop the watch.*/
    private JButton stopButton;
    /**Constructor to create a stop watch.*/
    StopWatchPanel() {
        hours = 0;
        minutes = 0;
        seconds = 0;
        milliSeconds = 0;
        
        //Dimension of the window
        final int width = 400;
        final int height = 150;
        
        //Gap of GridLayout
        final int gap = 30;
        
        //Color for the background color of the buttons
        final int lightGreen = 0xccff99;
        final int lightRed = 0xff9999;
        
        //Set the dimension of the watch
        setPreferredSize(new Dimension(width, height));
        
        //Set background color
        setBackground(Color.BLACK);
        
        //Set the main panel to BorderLayout
        BorderLayout mainLayout = new BorderLayout();
        setLayout(mainLayout);
        
        //Create and add a label to display time
        display = new JLabel("00:00:00.0");
        add(BorderLayout.NORTH, display);
        
        //A control pane that buttons are put into
        JPanel controlPane = new JPanel();
        
        //Use grid layout to arrange the buttons
        GridLayout controlLayout = new GridLayout(0, 2, gap, gap);
        controlPane.setLayout(controlLayout);
        
        //Create  and add buttons to the control pane.
        startButton = new JButton("Start");
        StopWatchListener listener = new StopWatchListener();
        startButton.addActionListener(listener);
        startButton.setBackground(new Color(lightGreen));
        stopButton = new JButton("Stop");
        stopButton.addActionListener(listener);
        stopButton.setBackground(new Color(lightRed));
        controlPane.add(startButton);
        controlPane.add(stopButton);
        add(BorderLayout.CENTER, controlPane);
        
        //Display initial time
        displayTime();
    }
    
    /**
     * A method to reset the time of the stop watch to zero.
     */
    public void resetTime() {
        hours = 0;
        minutes = 0;
        seconds = 0;
        milliSeconds = 0;
    }
    
    /**
     * A method displaying time using HTML format.
     */
    public void displayTime() {
        DecimalFormat fmt = new DecimalFormat("00");
        String result = "<html><body style=\"color: orange; "
                + "font-size:60px; text-align:center\">" 
                + fmt.format(hours) 
                + ":" 
                + fmt.format(minutes)
                + ":"
                + fmt.format(seconds)
                + "." 
                + milliSeconds
                + "</body></html>";
        display.setText(result);
    }
    
    /**
     * A class extends TimeTask that creates a task which will be called by the
     * Timer's schedule() method.
     */
    private class CountTime extends TimerTask {

        @Override
        public void run() {
            // TODO Auto-generated method stub
            final int msCarryIn = 10;
            final int secondsCarryIn = 60;
            final int minutesCarryIn = 60;
            
            //Increase milliSeconds every 100 ms
            milliSeconds++;
            
            //Carry in to seconds if needed
            if (milliSeconds == msCarryIn) {
                seconds++;
                milliSeconds = 0;
            }
            
            //Carry in to minutes if needed
            if (seconds == secondsCarryIn) {
                minutes++;
                seconds = 0;
            }
            
            //Carry in to hours if needed
            if (minutes == minutesCarryIn) {
                hours++;
                minutes = 0;
            }
            
            //Display time
            displayTime();
        }
    }
    
    /**A listener that handle button push events.*/
    private class StopWatchListener implements ActionListener {
        /**A timer to update the watch repetitively.*/
        private Timer timer;
        @Override
        public void actionPerformed(ActionEvent event) {
            // TODO Auto-generated method stub
            final int lightGreen = 0xccff99;
            final int lightYellow = 0xffff99;
            
            //Get the text from the button as command.
            String command = ((JButton) event.getSource()).getText();
            
            //Handle different command
            switch (command) {
            case "Start":
            case "Resume":
                timer = new Timer();
                timer.schedule(new CountTime(), 0, interval);
                startButton.setText("Pause");
                startButton.setBackground(new Color(lightYellow));
                break;
                
            case "Pause":
                if (timer != null) {
                timer.cancel();
                timer = null;                   
                }
                startButton.setText("Resume");
                startButton.setBackground(new Color(lightGreen));
                break;
                
            case "Stop":
                if (timer != null) {
                timer.cancel();
                timer = null;                    
                }
                resetTime();
                startButton.setText("Start");
                break;
                
            default:
                break;
            }
            
            displayTime();
        }       
    }
}
