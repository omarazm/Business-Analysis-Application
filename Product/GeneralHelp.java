/* This class creates the total calculation help frame
of the program and provides the user with a brief description on how
to use the program as a whole. */

//package businessinvestments;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author oabouelazm
 */
public class GeneralHelp extends JFrame implements ActionListener
{
   //Declares Constants
   private final Color FRAME_COLOR = new Color(249,215,87);
   private final java.net.URL IMAGE_URL = getClass().getResource("help.jpg");
   private final ImageIcon TITLE_IMAGE = new ImageIcon(new
        ImageIcon(IMAGE_URL).getImage().getScaledInstance(
        625,417,Image.SCALE_DEFAULT));

   //Declares label, panel, button, and text field objects
   private JLabel titleLabel;
   private JLabel introductionDescription;
   private JLabel menuDescription;
   private JLabel calculationDescription;
   private JLabel continuedDescription;

   private JLabel helpImageLabel;
   private JButton returnButton;
   private JPanel informationPanel;

   public GeneralHelp()
   {
       //Formatting Frame
       super("General Help");
       this.setBounds(200,100,1200,700);
       this.getContentPane().setBackground(FRAME_COLOR);
       this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
       this.setLayout(new BorderLayout());

       //Constructs the labels
       titleLabel = new JLabel("Help", SwingConstants.CENTER);
       titleLabel.setFont(new Font("Arial", Font.BOLD, 40));
       introductionDescription = new JLabel("For the general use of this program, you must "
               + "first decide what business you would like the program to evaluate for.");
       introductionDescription.setFont(new Font("Comic Sans", Font.ITALIC, 20));
       menuDescription = new JLabel("This is done through "
               + "the calculations menu under present value and hitting the register button.");
       menuDescription.setFont(new Font("Comic Sans", Font.ITALIC, 20));
       calculationDescription = new JLabel("After deciding upon what to calculate for, you enter the values ");
       calculationDescription.setFont(new Font("Comic Sans", Font.ITALIC, 20));
       continuedDescription = new JLabel("required, and press the calculate button and "
               + "next button to reveal the "
               + "result of the calculation.");
       continuedDescription.setFont(new Font("Comic Sans", Font.ITALIC, 20));

       //Constructs Button
       returnButton = new JButton("Return");
       returnButton.addActionListener(this);

       //Constructs image
       helpImageLabel = new JLabel(TITLE_IMAGE);

       //Constructing and formatting the labels of the panels
       informationPanel = new JPanel(new FlowLayout());
       informationPanel.setBackground(FRAME_COLOR);

       //Adding Components to the Panels
       informationPanel.add(introductionDescription);
       informationPanel.add(menuDescription);
       informationPanel.add(helpImageLabel);
       informationPanel.add(calculationDescription);
       informationPanel.add(continuedDescription);

       //Adding Components to the Frame
       this.add(informationPanel, BorderLayout.CENTER);
       this.add(titleLabel, BorderLayout.NORTH);
       this.add(returnButton, BorderLayout.SOUTH);

       //Makes the frame visible
       this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
         String command = e.getActionCommand();

         //Button disposes the help frame
         if (command.equals("Return"))
         {
             this.dispose();
         }
    }

    public static void main(String[] args)
    {
        GeneralHelp generalHelpObj = new GeneralHelp();
    }

}
