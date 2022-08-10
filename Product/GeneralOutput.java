/* This class creates the general output of the program and
provides the user with their answer depending on what the
program is calculating for. */

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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class GeneralOutput extends JFrame implements ActionListener
{
    //Sets Constants
    private final Font LABEL_FONT = new Font("Arial", Font.PLAIN ,20);
    private final java.net.URL IMAGE_URL = getClass().getResource("congratulations.jpg");
    private final ImageIcon ANSWER_IMAGE = new ImageIcon(new
        ImageIcon(IMAGE_URL).getImage().getScaledInstance(
        483,167,Image.SCALE_DEFAULT));

    //Declares label and button objects
    private JLabel messageLabel;
    private JLabel answerLabel;
    private JButton returnButton;
    private JLabel imageLabel;

    private JPanel buttonsPanel;
    private JPanel imagePanel;
    private JPanel mainPanel;
    private JPanel descriptionPanel;

    public GeneralOutput(String message)
    {
        //Constructs frame
        super("Calculation Result");
        this.setBounds(200, 100, 1000, 700);
        this.getContentPane().setBackground(new Color(240,201,211));
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());

        //Constructs the labels
        messageLabel = new JLabel(message , SwingConstants.CENTER);
        messageLabel.setFont(LABEL_FONT);

        //Constructs the image
        imageLabel = new JLabel(ANSWER_IMAGE);

        //Constructs the button
        returnButton = new JButton("Return");
        returnButton.addActionListener(this);

        //Constructing and formatting the labels of the panels
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(new Color(76,169,242));
        imagePanel = new JPanel(new FlowLayout());
        imagePanel.setBackground(new Color(76,169,242));
        descriptionPanel = new JPanel(new FlowLayout());
        descriptionPanel.setBackground(new Color(76,169,242));
        buttonsPanel = new JPanel(new FlowLayout());
        buttonsPanel.setBackground(new Color(76,169,242));

        //Adds components to the panels of the frame
        imagePanel.add(imageLabel);

        buttonsPanel.add(returnButton);

        descriptionPanel.add(messageLabel);

        mainPanel.add(imagePanel);
        mainPanel.add(descriptionPanel);

        //Adds Components to the Frame
        this.add(mainPanel, BorderLayout.CENTER);
        this.add(buttonsPanel, BorderLayout.SOUTH);

        //Makes the frame visible
        this.setVisible(true);

    }
    public GeneralOutput(String calculationVariable, Double result)
    {
        //Constructs frame
        super("Calculation Result");
        this.setBounds(200, 100, 1000, 700);
        this.getContentPane().setBackground(new Color(240,201,211));
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());

        //Constructs the labels
        messageLabel = new JLabel("The " + calculationVariable + " is: ", SwingConstants.CENTER);
        messageLabel.setFont(LABEL_FONT);
        answerLabel = new JLabel("", SwingConstants.CENTER);
        answerLabel.setFont(LABEL_FONT);

        //Constructs the image
        imageLabel = new JLabel(ANSWER_IMAGE);

        //Constructs the button
        returnButton = new JButton("Return");
        returnButton.addActionListener(this);

        //Constructing and formatting the labels of the panels
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(new Color(76,169,242));
        imagePanel = new JPanel(new FlowLayout());
        imagePanel.setBackground(new Color(76,169,242));
        descriptionPanel = new JPanel(new FlowLayout());
        descriptionPanel.setBackground(new Color(76,169,242));
        buttonsPanel = new JPanel(new FlowLayout());
        buttonsPanel.setBackground(new Color(76,169,242));

        //Adds components to the panels of the frame
        imagePanel.add(imageLabel);

        buttonsPanel.add(returnButton);

        descriptionPanel.add(messageLabel);
        descriptionPanel.add(answerLabel);

        mainPanel.add(imagePanel);
        mainPanel.add(descriptionPanel);

        //Adds Components to the Frame
        this.add(mainPanel, BorderLayout.CENTER);
        this.add(buttonsPanel, BorderLayout.SOUTH);

        //Displays the answer in the given label
        answerLabel.setText(Double.toString(result));

        //Makes the frame visible
        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        String command = e.getActionCommand();

        //Disposes this frame when return button is pushed
        if (command.equals("Return"))
        {
           //Disposes this frame
           this.dispose();
        }

    }

    //Creates main method for GeneralOutput class
    public static void main(String[] args)
    {
        GeneralOutput outputObj = new GeneralOutput("answer", 0.0);
    }
}
