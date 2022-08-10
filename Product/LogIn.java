/* This class creates the log in frame of the program and
provides the user with an ability to create an account, log in with that account,
and reset their password if needed*/

//package businessinvestments;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LogIn extends JFrame implements ActionListener
{
    //Declares Constants
    private final Color FRAME_COLOR = new Color(76, 59, 242);
    private final Color MAIN_PANEL_COLOR = new Color(76, 169, 242);
    private final Font DEFAULT_LABEL_FONT = new Font("Comic Sans", Font.ITALIC, 18);
    private final java.net.URL IMAGE_URL = getClass().getResource("profile.png");
    private final ImageIcon TITLE_IMAGE = new ImageIcon(new ImageIcon(IMAGE_URL).getImage().getScaledInstance(
            128, 128, Image.SCALE_DEFAULT));

    private String userName;
    private String password;
    private String email;

    private LogInManager logInObj = new LogInManager();

    //Declares label, panel, button, and text field objects
    private JLabel userNameLabel;
    private JLabel passwordLabel;
    private JLabel emailLabel;
    private JLabel profileImageLabel;
    private JLabel securityQuestion1;
    private JLabel securityQuestion2;
    private JLabel securityQuestion3;
    private JLabel spaceLabel;
    private JLabel newPasswordLabel;

    private JComboBox<String> questionChoice1;
    private JComboBox<String> questionChoice2;
    private JComboBox<String> questionChoice3;

    private String[] securityQuestions =
    {
        "What is your favorite pet's name?",
        "What city were you born in?",
        "What is your mother's maiden name?",
        "What was the name of the primary school that you attended?",
        "What time of day were you born?",
        "In what city did your parents meet?"
    };

    private JTextField userNameField;
    private JPasswordField passwordField;
    private JTextField emailField;
    private JTextField securityField1;
    private JTextField securityField2;
    private JTextField securityField3;
    private JTextField answerChoice1;
    private JTextField answerChoice2;
    private JTextField answerChoice3;
    private JPasswordField newPasswordField;

    private JButton logInButton;
    private JButton signUpButton;
    private JButton forgotPasswordButton;
    private JButton nextButton;
    private JButton backButton;
    private JButton resetPasswordButton;

    private JPanel imagePanel;
    private JPanel buttonsPanel;
    private JPanel inputPanel;
    private JPanel userNamePanel;
    private JPanel passwordPanel;
    private JPanel emailPanel;
    private JPanel newPasswordPanel;

    private JPanel security1Panel;
    private JPanel security2Panel;
    private JPanel security3Panel;

    private JPanel choice1Panel;
    private JPanel choice2Panel;
    private JPanel choice3Panel;

    public LogIn()
    {
        //Formatting Frame
        super("LogIn");
        this.setBounds(300, 200, 700, 415);
        this.getContentPane().setBackground(FRAME_COLOR);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        //Constructs the labels
        userNameLabel = new JLabel("Username: ");
        userNameLabel.setFont(DEFAULT_LABEL_FONT);
        passwordLabel = new JLabel("Password: ");
        passwordLabel.setFont(DEFAULT_LABEL_FONT);
        emailLabel = new JLabel("Email: ");
        emailLabel.setFont(DEFAULT_LABEL_FONT);
        securityQuestion1 = new JLabel("Security Question 1: ");
        securityQuestion1.setFont(DEFAULT_LABEL_FONT);
        securityQuestion2 = new JLabel("Secuirty Question 2: ");
        securityQuestion2.setFont(DEFAULT_LABEL_FONT);
        securityQuestion3 = new JLabel("Security Question 3: ");
        securityQuestion3.setFont(DEFAULT_LABEL_FONT);
        spaceLabel = new JLabel(" ");
        spaceLabel.setFont(DEFAULT_LABEL_FONT);
        newPasswordLabel = new JLabel("New Password: ");
        newPasswordLabel.setFont(DEFAULT_LABEL_FONT);

        //Constructs Buttons
        logInButton = new JButton("Log In");
        logInButton.addActionListener(this);
        signUpButton = new JButton("Sign Up");
        signUpButton.addActionListener(this);
        forgotPasswordButton = new JButton("Forgot Password?");
        forgotPasswordButton.addActionListener(this);
        nextButton = new JButton("Next");
        nextButton.addActionListener(this);
        backButton = new JButton("Back");
        backButton.addActionListener(this);
        resetPasswordButton = new JButton("Reset Password");
        resetPasswordButton.addActionListener(this);

        //Declares combo boxes
        questionChoice1 = new JComboBox<>(securityQuestions);
        questionChoice2 = new JComboBox<>(securityQuestions);
        questionChoice3 = new JComboBox<>(securityQuestions);

        //Constructs image
        profileImageLabel = new JLabel(TITLE_IMAGE);

        //Constructs textfields
        userNameField = new JTextField(10);
        passwordField = new JPasswordField(10);
        emailField = new JTextField(10);
        securityField1 = new JTextField(10);
        securityField2 = new JTextField(10);
        securityField3 = new JTextField(10);
        answerChoice1 = new JTextField(10);
        answerChoice2 = new JTextField(10);
        answerChoice3 = new JTextField(10);
        newPasswordField = new JPasswordField(10);

        //Constructing and formatting the labels of the panels
        imagePanel = new JPanel(new FlowLayout());
        imagePanel.setBackground(MAIN_PANEL_COLOR);
        inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
        inputPanel.setBackground(MAIN_PANEL_COLOR);
        userNamePanel = new JPanel(new FlowLayout());
        userNamePanel.setBackground(MAIN_PANEL_COLOR);
        passwordPanel = new JPanel(new FlowLayout());
        passwordPanel.setBackground(MAIN_PANEL_COLOR);
        emailPanel = new JPanel(new FlowLayout());
        emailPanel.setBackground(MAIN_PANEL_COLOR);
        buttonsPanel = new JPanel(new FlowLayout());
        buttonsPanel.setBackground(FRAME_COLOR);

        security1Panel = new JPanel(new FlowLayout());
        security1Panel.setBackground(MAIN_PANEL_COLOR);
        security2Panel = new JPanel(new FlowLayout());
        security2Panel.setBackground(MAIN_PANEL_COLOR);
        security3Panel = new JPanel(new FlowLayout());
        security3Panel.setBackground(MAIN_PANEL_COLOR);

        choice1Panel = new JPanel(new FlowLayout());
        choice1Panel.setBackground(MAIN_PANEL_COLOR);
        choice2Panel = new JPanel(new FlowLayout());
        choice2Panel.setBackground(MAIN_PANEL_COLOR);
        choice3Panel = new JPanel(new FlowLayout());
        choice3Panel.setBackground(MAIN_PANEL_COLOR);

        newPasswordPanel = new JPanel(new FlowLayout());
        newPasswordPanel.setBackground(MAIN_PANEL_COLOR);

        //adds components to all the input panels
        security1Panel.add(securityQuestion1);
        security1Panel.add(securityField1);
        security2Panel.add(securityQuestion2);
        security2Panel.add(securityField2);
        security3Panel.add(securityQuestion3);
        security3Panel.add(securityField3);

        choice1Panel.add(questionChoice1);
        choice1Panel.add(answerChoice1);
        choice2Panel.add(questionChoice2);
        choice2Panel.add(answerChoice2);
        choice3Panel.add(questionChoice3);
        choice3Panel.add(answerChoice3);

        newPasswordPanel.add(newPasswordLabel);
        newPasswordPanel.add(newPasswordField);

        userNamePanel.add(userNameLabel);
        userNamePanel.add(userNameField);
        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordField);
        emailPanel.add(emailLabel);
        emailPanel.add(emailField);

        //adds image to image panel
        imagePanel.add(profileImageLabel);
        imagePanel.add(spaceLabel);

        //adds components to the main input panel
        inputPanel.add(spaceLabel);
        inputPanel.add(userNamePanel);
        inputPanel.add(passwordPanel);

        //adds components to button panel
        buttonsPanel.add(logInButton);
        buttonsPanel.add(signUpButton);
        buttonsPanel.add(forgotPasswordButton);

        //Adding Components to the Frame
        this.add(inputPanel, BorderLayout.CENTER);
        this.add(imagePanel, BorderLayout.NORTH);
        this.add(buttonsPanel, BorderLayout.SOUTH);

        //Makes the frame visible
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        String command = e.getActionCommand();

        if (command.equals("Sign Up"))
        {
            //Checks if Log In panel is selected
            if (userNamePanel.isDisplayable() && passwordPanel.isDisplayable())
            {
                inputPanel.removeAll();

                inputPanel.add(userNamePanel);
                inputPanel.add(passwordPanel);
                inputPanel.add(emailPanel);

                buttonsPanel.removeAll();

                buttonsPanel.add(backButton);
                buttonsPanel.add(nextButton);
                buttonsPanel.add(forgotPasswordButton);
            }

            //Checks if Sign Up Panel is selected
            else if (choice1Panel.isDisplayable() && choice2Panel.isDisplayable()
                    && choice3Panel.isDisplayable())
            {

                //Checks if the security questions selected are equal to one another
                if (questionChoice1.getSelectedItem() == questionChoice2.getSelectedItem()
                        || questionChoice2.getSelectedItem() == questionChoice3.getSelectedItem()
                        || questionChoice1.getSelectedItem() == questionChoice3.getSelectedItem())
                {
                    Error errorObj = new Error("ERROR: Please Select Different Security Questions");
                }

                //Checks if the user inputed nothing for the answer to the security questions
                else if ((answerChoice1.getText()).equals("") || (answerChoice2.getText()).equals("")
                        || (answerChoice3.getText()).equals(""))
                {
                    Error errorObj = new Error("ERROR: Please Enter Answers to the Security Questions");
                }

                else
                {
                    //Inserts data into database
                    String question1 = questionChoice1.getSelectedItem().toString();
                    String question2 = questionChoice2.getSelectedItem().toString();
                    String question3 = questionChoice3.getSelectedItem().toString();

                    logInObj.insertProfileInformation(userName, password, email, question1,
                            question2, question3, answerChoice1.getText(), answerChoice2.getText(),
                            answerChoice3.getText());

                    //Resets values of security questions and answers
                    questionChoice1.setSelectedIndex(0);
                    questionChoice2.setSelectedIndex(0);
                    questionChoice3.setSelectedIndex(0);
                    answerChoice1.setText("");
                    answerChoice2.setText("");
                    answerChoice3.setText("");

                    inputPanel.removeAll();

                    inputPanel.add(spaceLabel);
                    inputPanel.add(userNamePanel);
                    inputPanel.add(passwordPanel);

                    buttonsPanel.removeAll();

                    buttonsPanel.add(logInButton);
                    buttonsPanel.add(signUpButton);
                    buttonsPanel.add(forgotPasswordButton);
                }

            }


            userNameField.setText("");
            passwordField.setText("");
            emailField.setText("");
            securityField1.setText("");
            securityField2.setText("");
            securityField3.setText("");
            answerChoice1.setText("");
            answerChoice2.setText("");
            answerChoice3.setText("");
            newPasswordField.setText("");

        }

        else if (command.equals("Log In"))
        {
            //Checks if the log in information is valid
            if (logInObj.checkLogIn(userNameField.getText(),
                    convertPassword(passwordField.getPassword())))
            {
                email = logInObj.findEmail(userNameField.getText(),
                        convertPassword(passwordField.getPassword()));

                Input inputObj = new Input(email);
                this.dispose();
            }
            else
            {
                Error errorObj = new Error("Error: Invalid Username Or Password");
            }
        }

        else if (command.equals("Forgot Password?"))
        {
             //replaces panel components
             inputPanel.removeAll();

             inputPanel.add(spaceLabel);
             inputPanel.add(emailPanel);

             buttonsPanel.removeAll();

             buttonsPanel.add(backButton);
             buttonsPanel.add(nextButton);

             userNameField.setText("");
             passwordField.setText("");
             emailField.setText("");
             securityField1.setText("");
             securityField2.setText("");
             securityField3.setText("");
             answerChoice1.setText("");
             answerChoice2.setText("");
             answerChoice3.setText("");
             newPasswordField.setText("");
        }

        else if (command.equals("Next"))
        {
            //Checks if the sign up panel is selected
            if (userNamePanel.isDisplayable() && passwordPanel.isDisplayable()
                    && emailPanel.isDisplayable())
            {
                //Checks if the email entered is a duplicate email
                if (logInObj.checkDuplicateEmail(emailField.getText()) == false &&
                        logInObj.checkDuplicateUserName(userNameField.getText()) == false &&
                        userNameField.getText().equals("") == false &&
                        emailField.getText().equals("") == false &&
                        convertPassword(passwordField.getPassword()).equals("") == false)
                {
                    userName = userNameField.getText();
                    password = convertPassword(passwordField.getPassword());
                    email = emailField.getText();

                    inputPanel.removeAll();

                    inputPanel.add(choice1Panel);
                    inputPanel.add(choice2Panel);
                    inputPanel.add(choice3Panel);

                    buttonsPanel.removeAll();

                    buttonsPanel.add(backButton);
                    buttonsPanel.add(signUpButton);
                    buttonsPanel.add(forgotPasswordButton);
                }
                else if (logInObj.checkDuplicateEmail(emailField.getText()) == true)
                {
                    Error errorObj= new Error("Error: That Email has already been taken");
                }
                else if (logInObj.checkDuplicateUserName(userNameField.getText()) == true)
                {
                    Error errorObj= new Error("Error: That UserName has already been taken");
                }
                else
                {
                    Error errorObj= new Error("Error: Please Enter Information");
                }
            }

            //checks if the forgot password panel is selected
            else if (emailPanel.isDisplayable())
            {
                if (logInObj.checkValidEmail(emailField.getText()) == true)
                {
                    email = emailField.getText();

                    findSecurityQuestions(email);

                    inputPanel.removeAll();

                    inputPanel.add(newPasswordPanel);
                    inputPanel.add(security1Panel);
                    inputPanel.add(security2Panel);
                    inputPanel.add(security3Panel);

                    buttonsPanel.removeAll();

                    buttonsPanel.add(backButton);
                    buttonsPanel.add(resetPasswordButton);
                }
                else
                {
                    Error errorObj = new Error("Error: Email is not Valid");
                }
            }


            userNameField.setText("");
            passwordField.setText("");
            emailField.setText("");
            answerChoice1.setText("");
            answerChoice2.setText("");
            answerChoice3.setText("");
            newPasswordField.setText("");
        }

        else if (command.equals("Back"))
        {
            //checks if the sign up panel is selected
            if (userNamePanel.isDisplayable() && passwordPanel.isDisplayable()
                    && emailPanel.isDisplayable())
            {
                userNameField.setText("");
                passwordField.setText("");
                emailField.setText("");

                inputPanel.removeAll();

                inputPanel.add(spaceLabel);
                inputPanel.add(userNamePanel);
                inputPanel.add(passwordPanel);

                buttonsPanel.removeAll();

                buttonsPanel.add(logInButton);
                buttonsPanel.add(signUpButton);
                buttonsPanel.add(forgotPasswordButton);
            }

            //checks if the continued sign up panel is selected
            else if (choice1Panel.isDisplayable() && choice2Panel.isDisplayable()
                    && choice3Panel.isDisplayable())
            {
                questionChoice1.setSelectedIndex(0);
                questionChoice2.setSelectedIndex(0);
                questionChoice3.setSelectedIndex(0);
                answerChoice1.setText("");
                answerChoice2.setText("");
                answerChoice3.setText("");

                inputPanel.removeAll();

                inputPanel.add(userNamePanel);
                inputPanel.add(passwordPanel);
                inputPanel.add(emailPanel);

                buttonsPanel.removeAll();

                buttonsPanel.add(backButton);
                buttonsPanel.add(nextButton);
                buttonsPanel.add(forgotPasswordButton);
            }

            //checks if the forgot password panel is selected
            else if (emailPanel.isDisplayable())
            {
                emailField.setText("");

                inputPanel.removeAll();

                inputPanel.add(spaceLabel);
                inputPanel.add(userNamePanel);
                inputPanel.add(passwordPanel);

                buttonsPanel.removeAll();

                buttonsPanel.add(logInButton);
                buttonsPanel.add(signUpButton);
                buttonsPanel.add(forgotPasswordButton);
            }

            //checks if the continued forgot password panel is selected
            else if (newPasswordPanel.isDisplayable() &&
                security1Panel.isDisplayable() &&
                security2Panel.isDisplayable() &&
                security3Panel.isDisplayable())
            {
                newPasswordField.setText("");
                securityField1.setText("");
                securityField2.setText("");
                securityField3.setText("");

                inputPanel.removeAll();

                inputPanel.add(spaceLabel);
                inputPanel.add(emailPanel);

                buttonsPanel.removeAll();

                buttonsPanel.add(backButton);
                buttonsPanel.add(nextButton);
            }
        }

        else if (command.equals("Reset Password"))
        {
            if (convertPassword(newPasswordField.getPassword()).equals("") == false)
            {
                if (logInObj.setNewPassword(email, securityQuestion1.getText(),
                    securityQuestion2.getText(), securityQuestion3.getText(), securityField1.getText(),
                    securityField2.getText(), securityField3.getText(),
                    convertPassword(newPasswordField.getPassword())))
                {
                    inputPanel.removeAll();

                    inputPanel.add(spaceLabel);
                    inputPanel.add(userNamePanel);
                    inputPanel.add(passwordPanel);

                    buttonsPanel.removeAll();

                    buttonsPanel.add(logInButton);
                    buttonsPanel.add(signUpButton);
                    buttonsPanel.add(forgotPasswordButton);
                }
                else
                {
                    Error errorObj = new Error("Your Answers Were Incorrect");
                }
            }
            else
            {
                Error errorObj = new Error("Please Enter A Valid Password");
            }
        }

        /*Revalidates and repaints the frame in order to allow
        the panel to show up on the frame*/
        this.validate();
        this.repaint();
    }

    //Finds security questions of specified account
    private void findSecurityQuestions(String email)
    {
        Connection myDbConn = null;

        int counter = 0;

        int questionId1 = 0;
        int questionId2 = 0;
        int questionId3 = 0;

        //Creates object of database and connects to database
        DatabaseAccess objDb = new DatabaseAccess();
        objDb.setDbName("BusinessFinancials");
        objDb.setDbConn();
        myDbConn = objDb.getDbConn();
        String[] securityColumnName = {"Email", "SecurityId", "SecurityAnswer"};
        Object[][] data;

        data = objDb.to2dArray(objDb.getData("SecurityAnswers", securityColumnName));

        //Finds the question ids for the email inputed
        for (int i=0; i<data.length; i++)
        {
            if (email.equalsIgnoreCase(data[i][0].toString()))
            {
                if (counter == 0)
                {
                    questionId1 = Integer.parseInt(data[i][1].toString());
                }
                else if (counter == 1)
                {
                    questionId2 = Integer.parseInt(data[i][1].toString());
                }
                else if (counter == 2)
                {
                    questionId3 = Integer.parseInt(data[i][1].toString());
                }

                counter++;
            }
        }

        String[] questionColumnName = {"SecurityId", "SecurityQuestion"};

        data = objDb.to2dArray(objDb.getData("SecurityQuestions", questionColumnName));

        //Checks if the email the user inputed in is valid
        for (int i=0; i<data.length; i++)
        {
            if (questionId1 == Integer.parseInt(data[i][0].toString()))
            {
                securityQuestion1.setText(data[i][1].toString() + " ");
            }
            if (questionId2 == Integer.parseInt(data[i][0].toString()))
            {
                securityQuestion2.setText(data[i][1].toString() + " ");
            }
            if (questionId3 == Integer.parseInt(data[i][0].toString()))
            {
                securityQuestion3.setText(data[i][1].toString() + " ");
            }
        }

        //Closes connection
        objDb.closeDbConn();
    }

    //Converts password from char[] to string
    private String convertPassword(char[] password)
    {
        String convertedPassword = "";

        for (int i = 0; i<password.length; i++)
        {
            convertedPassword = convertedPassword + password[i];
        }

        return convertedPassword;
    }

    public static void main(String[] args)
    {
        //Opens the log in frame
        LogIn logInObj = new LogIn();
    }
}
