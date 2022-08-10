/* This class creates the total calculation help frame
of the program and provides the user with a brief description on how
to use the program to perform calculations. */

//package businessinvestments;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class CalculationHelp extends JFrame implements ActionListener
{
    //Declares Constants
   private final Color FRAME_COLOR = new Color(249,215,87);
   private final java.net.URL HELP_IMAGE_URL = getClass().getResource("help.jpg");
   private final ImageIcon TITLE_IMAGE = new ImageIcon(new
        ImageIcon(HELP_IMAGE_URL).getImage().getScaledInstance(
        635,334,Image.SCALE_DEFAULT));
   private final java.net.URL SECOND_IMAGE_URL = getClass().getResource("weCanHelp.jpg");
   private final ImageIcon SECOND_IMAGE = new ImageIcon(new
        ImageIcon(SECOND_IMAGE_URL).getImage().getScaledInstance(
        500,334,Image.SCALE_DEFAULT));

   //Declares label, panel, button, and text field objects
   private JLabel titleLabel;
   private JLabel introductionDescription;
   private JLabel addDescription;
   private JLabel displayDescription;
   private JLabel continuedDisplayDescription;

   private JLabel questionsTitle;
   private JLabel questionOne;
   private JLabel descriptionOne;
   private JLabel questionTwo;
   private JLabel descriptionTwo;

   private JLabel helpImageLabel;
   private JLabel secondImageLabel;
   private JButton returnButton;
   private JPanel informationPanel;
   private JPanel imagePanel;

   public CalculationHelp()
   {
       //Formatting Frame
       super("Calculation Help");
       this.setBounds(100,100,1300,625);
       this.getContentPane().setBackground(FRAME_COLOR);
       this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
       this.setLayout(new BorderLayout());

       //Constructs the labels
       titleLabel = new JLabel("HELP", SwingConstants.CENTER);
       titleLabel.setFont(new Font("Arial", Font.BOLD, 40));
       introductionDescription = new JLabel("For the net present valuecalculation of a business, "
               + "this program requires you to enter several fields.");
       introductionDescription.setFont(new Font("Comic Sans", Font.ITALIC, 20));
       addDescription = new JLabel("With each input, once you click the add and/or calulate button, this program adds, "
               + "updates, and stores the data");
       addDescription.setFont(new Font("Comic Sans", Font.ITALIC, 20));
       displayDescription = new JLabel("After storing and calculating all the data needed, "
               + "the program can display a business report filled with all the variables calculated for");
       displayDescription.setFont(new Font("Comic Sans", Font.ITALIC, 20));
       continuedDisplayDescription = new JLabel("as well as the cash flow table, balance sheet, and "
               + "net present value of the chosen business.");
       continuedDisplayDescription.setFont(new Font("Comic Sans", Font.ITALIC, 20));
       questionsTitle = new JLabel("", SwingConstants.LEFT);
       questionsTitle.setFont(new Font("Arial", Font.BOLD, 25));
       questionOne = new JLabel("",
               SwingConstants.CENTER);
       questionOne.setFont(new Font("Arial", Font.ITALIC, 20));
       descriptionOne = new JLabel("" ,
               SwingConstants.CENTER);
       descriptionOne.setFont(new Font("Arial", Font.PLAIN, 20));
       questionTwo = new JLabel("", SwingConstants.CENTER);
       questionTwo.setFont(new Font("Arial", Font.ITALIC, 20));
       descriptionTwo = new JLabel("", SwingConstants.CENTER);
       descriptionTwo.setFont(new Font("Arial", Font.PLAIN, 20));

       //Constructs Button
       returnButton = new JButton("Return");
       returnButton.addActionListener(this);

       //Constructs image
       helpImageLabel = new JLabel(TITLE_IMAGE);
       secondImageLabel = new JLabel(SECOND_IMAGE);

       //Constructing and formatting the labels of the panels
       informationPanel = new JPanel(new FlowLayout());
       informationPanel.setBackground(FRAME_COLOR);
       imagePanel = new JPanel(new FlowLayout());
       imagePanel.setBackground(FRAME_COLOR);

       //Adding Components to the Panels
       imagePanel.add(helpImageLabel);
       imagePanel.add(secondImageLabel);

       informationPanel.add(introductionDescription);
       informationPanel.add(addDescription);
       informationPanel.add(displayDescription);
       informationPanel.add(continuedDisplayDescription);
       informationPanel.add(imagePanel);
       informationPanel.add(questionsTitle);
       informationPanel.add(questionOne);
       informationPanel.add(descriptionOne);
       informationPanel.add(questionTwo);
       informationPanel.add(descriptionTwo);

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
        CalculationHelp calculationHelpObj = new CalculationHelp();
    }
}
