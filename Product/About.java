/* This class creates the about fframe of the program and
provides the user with a brief description of what the program can do,
and the overall purpose of the program*/

//package businessinvestments;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class About extends JFrame implements ActionListener
{
   //Declares Constants
   private final Color FRAME_COLOR = new Color(76,59,242);
   private final java.net.URL IMAGE_URL = getClass().getResource("investments.jpg");
   private final ImageIcon TITLE_IMAGE = new ImageIcon(new
        ImageIcon(IMAGE_URL).getImage().getScaledInstance(
        750,400,Image.SCALE_DEFAULT));

   //Declares label, panel, button, and text field objects
   private JLabel titleLabel;
   private JLabel descriptionLabel;
   private JLabel continuedDescription;
   private JLabel investmentImageLabel;
   private JButton returnButton;
   private JPanel buttonsPanel;
   private JPanel imagePanel;
   private JPanel mainPanel;
   private JPanel descriptionPanel;

   public About()
   {
       //Formatting Frame
       super("About");
       this.setBounds(200,100,1000,700);
       this.getContentPane().setBackground(FRAME_COLOR);
       this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
       this.setLayout(new BorderLayout());

       //Constructs the labels
       titleLabel = new JLabel("About", SwingConstants.CENTER);
       titleLabel.setFont(new Font("Arial", Font.BOLD, 40));
       descriptionLabel = new JLabel("This program calculates for different business reports so that ");
       descriptionLabel.setFont(new Font("Comic Sans", Font.ITALIC, 20));
       continuedDescription = new JLabel("investors can make an evaluation on multiple businesses");
       continuedDescription.setFont(new Font("Comic Sans", Font.ITALIC, 20));

       //Constructs Button
       returnButton = new JButton("Return");
       returnButton.addActionListener(this);

       //Constructs image
       investmentImageLabel = new JLabel(TITLE_IMAGE);

       //Constructing and formatting the labels of the panels
       mainPanel = new JPanel();
       mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
       mainPanel.setBackground(new Color(76,169,242));
       imagePanel = new JPanel(new FlowLayout());
       imagePanel.setBackground(new Color(76,169,242));
       descriptionPanel = new JPanel(new FlowLayout());
       descriptionPanel.setBackground(new Color(76,169,242));
       buttonsPanel = new JPanel(new FlowLayout());
       buttonsPanel.setBackground(FRAME_COLOR);

       descriptionPanel.add(descriptionLabel);
       descriptionPanel.add(continuedDescription);
       imagePanel.add(investmentImageLabel);

       //Adding Components to the Panels
       mainPanel.add(descriptionPanel);
       mainPanel.add(imagePanel);


       buttonsPanel.add(returnButton);

       //Adding Components to the Frame
       this.add(mainPanel, BorderLayout.CENTER);
       this.add(titleLabel, BorderLayout.NORTH);
       this.add(buttonsPanel, BorderLayout.SOUTH);

       //Makes the frame visible
       this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
         String command = e.getActionCommand();

         if (command.equals("Return"))
         {
             this.dispose();
         }
    }

    public static void main(String[] args)
    {
          //Opens the welcome frame
          About objAbout = new About();
    }
}
