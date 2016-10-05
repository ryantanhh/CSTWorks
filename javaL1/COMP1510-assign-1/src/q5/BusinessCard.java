package q5;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * <p>A class drawing a simple business card.</p>
 *
 * @author Hai Hua, Tan
 * @version 1.0
 */
public class BusinessCard extends JFrame {
    /**
     * serialVersionUID to keep class compliant with the previous version.
     */
    private static final long serialVersionUID = 7062469334560391173L;

    /**
     * <p>The default constructor which sets the title of this app, sets the
     * default close operation to exit, creates a new content pane and finally
     * sets size and sets the visibility of this frame to true (show).</p>
     */
    public BusinessCard() {
        super("INSERT YOUR NAME HERE");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(new BusinessCardPanel());
        
        final int cardWidth = 360;
        final int cardHeight = 240;
        setSize(cardWidth, cardHeight); // the size can be changed, 
                                        // but don't make it HUGE!
        
        setVisible(true);
    }

    /**
     * <p>A panel that acts as the Frame's content pane.</p>
     */
    class BusinessCardPanel extends JPanel {

        /**
         * serialVersionUID to keep class compliant with the previous version.
         */
        private static final long serialVersionUID = 7707485562200870103L;
        
        /**
         * <p>An image object that is drawn as background.</p>
         */
        private Image imgBackground;
        
        /**
         * <p>An image object that is used as phone icon.</p>
         */
        private Image imgPhoneIcon;
        
               
        /**
         * <p>An image object that is used as location icon.</p>
         */
        private Image imgAddressIcon;
        
        /**
         * <p>An image object that is used as home page icon.</p>
         */
        private Image imgHomepageIcon;
        
        /**
         * <p>An image object that is used as contact icon.</p>
         */
        private Image imgEmailIcon;
        /**
         * <p>The default constructor that retrieves an image to draw.</p>
         */
        BusinessCardPanel() {
            imgBackground = new ImageIcon(BusinessCardPanel.class.getResource(
                "/q5/background-blue.jpg")).getImage();
            
            imgEmailIcon = new ImageIcon(BusinessCardPanel.class.getResource(
                    "/q5/icon-email.jpg")).getImage();
            
            imgHomepageIcon = new ImageIcon(BusinessCardPanel.class.getResource(
                    "/q5/icon-homepage.jpg")).getImage();
            
            imgAddressIcon = new ImageIcon(BusinessCardPanel.class.getResource(
                    "/q5/icon-address.jpg")).getImage();
            
            imgPhoneIcon = new ImageIcon(BusinessCardPanel.class.getResource(
                    "/q5/icon-phone.jpg")).getImage();
        }

        /**
         * <p>Called by the environment when the frame needs to be updated.</p>
         *
         * @param g the graphics context with which we paint into.
         */
        public void paintComponent(Graphics g) {
            //font and size for drawing string
            final Font font = new Font("Helvetica", Font.BOLD, 40);
            final float nameFontSize = 18;
            final float titleFontSize = 12;
            final float contactFontSize = 12;
            
            //position of all the elements in the business card
            final int logoStringPositionX = 13;
            final int logoStringPositionY = 65;
            
            final int phoneIconPositionX = 10;
            final int phoneIconPositionY = 150;
            
            final int emailIconPositionX = 10;
            final int emailIconPositionY = 175;

            final int homepageIconPositionX = 160;
            final int homepageIconPositionY = 150;

            final int addressIconPositionX = 160;
            final int addressIconPositionY = 175;
            
            final int namePositionX = 60;
            final int namePositionY = 130;
            
            final int titlePositionX = 180;
            final int titlePositionY = namePositionY;
            
            final int companyNamePositionX = 180;
            final int companyNamePositionY = 20;
            final int companyNameLineWidth = 5;
            
            final int sloganPositionX = companyNamePositionX;
            final int sloganPositionY = 40;
            
            final int logoOuterCirclePositionX = 10;
            final int logoOuterCirclePositionY = 10;
            final int logoOuterCircleDiameter = 80;
            
            final int logoInnerCirclePositionX = 15;
            final int logoInnerCirclePositionY = 15;
            final int logoInnerCircleDiameter = 70;
                     
            final Color logoColor = new Color(0x7A1920);
            final Color companyNameColor = new Color(0xEF6F18);
                        
            super.paintComponent(g);

            //Draw the background picture
            g.drawImage(imgBackground, 0, 0, this);
            
            //Draw the company name and slogan
            g.setFont(font.deriveFont(nameFontSize));
            g.setColor(companyNameColor);
            g.drawString("XYZ Software", 
                    companyNamePositionX, 
                    companyNamePositionY);
            //Draw a line as separator

            g.drawLine(companyNamePositionX,
                    companyNamePositionY + companyNameLineWidth,
                    getWidth(),
                    companyNamePositionY + companyNameLineWidth);
            
            //Draw a slogan 
            g.setFont(font.deriveFont(Font.ITALIC, titleFontSize));
            g.drawString("Code Creats Future", 
                          sloganPositionX, 
                          sloganPositionY);

            
            //Draw the name and title
            g.setFont(font.deriveFont(nameFontSize));
            g.setColor(Color.BLACK);
            g.drawString("Hai Hua, Tan", namePositionX, namePositionY);
            g.setColor(Color.GRAY);
            g.setFont(font.deriveFont(titleFontSize));
            g.drawString("Software Engineer", titlePositionX, titlePositionY);
            
            //Draw the logo
            g.setColor(Color.WHITE);
                      
            g.fillOval(logoOuterCirclePositionX,
                        logoOuterCirclePositionY,
                        logoOuterCircleDiameter,
                        logoOuterCircleDiameter
                    );
                        
            g.setColor(logoColor);
            g.fillOval(logoInnerCirclePositionX,
                        logoInnerCirclePositionY,
                        logoInnerCircleDiameter,
                        logoInnerCircleDiameter
                    );
                       
            g.setColor(Color.white);
            g.setFont(font);
            g.drawString("XYZ", logoStringPositionX, logoStringPositionY);
            
            /*Draw all the contact information*/
            g.setFont(font.deriveFont(Font.PLAIN, contactFontSize));
            //Draw the phone info
            drawContact(g, imgPhoneIcon, 
                    phoneIconPositionX, 
                    phoneIconPositionY, 
                    "604-123-4567");
            
            //Draw the email info
            drawContact(g, imgEmailIcon, 
                    emailIconPositionX, 
                    emailIconPositionY, 
                    "tanhh@xyz.com");
            
            //Draw the home page info
            drawContact(g, imgHomepageIcon, 
                    homepageIconPositionX, 
                    homepageIconPositionY, 
                    "http://www.xyz.com");
            
            //Draw the address info
            drawContact(g, imgAddressIcon, 
                    addressIconPositionX, 
                    addressIconPositionY, 
                    "1234 main street, Vancouver");
        }
        
        /**
         * Function to draw contact info with an icon.
         * @param g the graphics context with which we paint into.
         * @param img the icon to draw.
         * @param x the x position to draw.
         * @param y the y position to draw.
         * @param content the info to draw.
         */
        private void drawContact(Graphics g, 
                                    Image img, 
                                    int x, 
                                    int y, 
                                    String content) {
            final int iconTextOffsetX = 25;
            final int iconTextOffsetY = 15;
            g.drawImage(img, x, y, this);
            g.setColor(Color.BLACK);
            g.drawString(content, x + iconTextOffsetX, y + iconTextOffsetY);
        }
    }

    /**
     * <p>The main method.</p>
     * @param args is unused
     */
    public static void main(String[] args) {
        new BusinessCard();
    }

};
