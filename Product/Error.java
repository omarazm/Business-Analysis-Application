/* This class creates the warning frame of the program in which it warns
and provides the user with an error message if the user inputs
something incorrect.*/

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

public class Error extends JFrame implements ActionListener
{
    //Sets Constants
    private final java.net.URL GIF_URL = getClass().getResource("errorGIF.gif");
    private final ImageIcon ERROR_GIF = new ImageIcon(new
        ImageIcon(GIF_URL).getImage().getScaledInstance(
        220,220,Image.SCALE_DEFAULT));

    //Declares label and button objects
    private JLabel errorTitle;
    private JButton tryAgainButton;
    private JLabel errorGIF;
    private JLabel errorMessage;
    private JPanel errorPanel;

    public Error(String message)
    {
        //Constructs frame
        super("ERROR");
        this.setBounds(100,100,450,450);
        this.getContentPane().setBackground(Color.red);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());

        //Constructs Label
        errorTitle = new JLabel("WARNING!", SwingConstants.CENTER);
        errorTitle.setFont(new Font("Arial", Font.BOLD, 30));
        errorMessage = new JLabel(message, SwingConstants.CENTER);
        errorMessage.setFont(new Font("Arial", Font.ITALIC, 20));

        //Constructs Button
        tryAgainButton = new JButton("Try Again");
        tryAgainButton.addActionListener(this);

        //Constructs Panel
        errorPanel = new JPanel(new FlowLayout());
        errorPanel.setBackground(Color.red);

        //Constructs image label
        errorGIF = new JLabel(ERROR_GIF);

        //Adds components to Panel
        errorPanel.add(errorMessage);
        errorPanel.add(errorGIF);

        //Adds components to the frame
        this.add(errorTitle, BorderLayout.NORTH);
        this.add(errorPanel, BorderLayout.CENTER);
        this.add(tryAgainButton, BorderLayout.SOUTH);

        //Makes the frame visible
        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        String command = e.getActionCommand();

        //Disposes this frame when the try again button is pushed
        if (command.equals("Try Again"))
        {
           //Disposes this frame
           this.dispose();
        }

    }

    //Creates main method for Warning class
    public static void main(String[] args)
    {
        Error errorObj = new Error("");
    }
}
