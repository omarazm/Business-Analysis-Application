/* This class creates the input frame of the program and
provides the user with a chance to input multiple fields,
in order to create a business report and overall evaluation. */

//package businessinvestments;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Input extends JFrame implements ActionListener
{

    //Declares Constants
    private final Color FRAME_COLOR = new Color(76, 59, 242);
    private final Color MAIN_PANEL_COLOR = new Color(76, 169, 242);
    private final java.net.URL IMAGE_URL = getClass().getResource("imageedit_1_9978380526.png");
    private final ImageIcon INVESTMENT_EXCHANGE_IMAGE = new ImageIcon(new ImageIcon(IMAGE_URL).getImage().getScaledInstance(
            300, 173, Image.SCALE_DEFAULT));
    private final java.net.URL VALUE_IMAGE_URL = getClass().getResource("dollarBulb.jpg");
    private final ImageIcon BUSINESS_VALUE_IMAGE = new ImageIcon(new ImageIcon(VALUE_IMAGE_URL).getImage().getScaledInstance(
            300, 300, Image.SCALE_DEFAULT));
    private final Font DEFAULT_LABEL_FONT = new Font("Comic Sans", Font.ITALIC, 20);

    private boolean savedInfo = false;

    private Error errorObj;
    private GeneralOutput generalOutputObj;
    private BusinessReport businessReportObj;

    private BusinessFinancials businessObj;
    private Risk riskObj;

    //Declares Array Lists of Data
    ArrayList<Double> cashFlowData = new ArrayList<>();
    ArrayList<Object> balanceSheetData = new ArrayList<>();
    ArrayList<Double> profitData = new ArrayList<>();
    ArrayList<Double> presentValueData = new ArrayList<>();
    ArrayList<String> bestBusinessNames = new ArrayList<>();
    ArrayList<String> orderedBusinessNames = new ArrayList<>();

    //Declares other data
    double maxTime = 5;

    String email;
    String businessName = "";
    int time = 1;
    double actualEquity = 0;
    double actualDebt = 0;
    double debtCost = 0;
    double growthRate = 5;

    //Declares label, panel, button, text field, and menu objects
    private JMenuBar mainBar;
    private JMenu calculationsMenu;
    private JMenuItem investmentComparisonItem;
    private JMenuItem businessReportItem;
    private JMenuItem presentValueItem;
    private JMenuItem removeBusinessItem;
    private JMenuItem displayBusinessesItem;

    private JMenu helpMenu;
    private JMenuItem generalHelpItem;
    private JMenuItem calculationHelpItem;

    private JButton aboutButton;
    private JButton logOutButton;

    private JLabel registerLabel;
    private JLabel revenueLabel;
    private JLabel costsLabel;
    private JLabel employeeLabel;
    private JLabel businessYearsLabel;
    private JLabel countriesAmountLabel;
    private JLabel competitorsAmountLabel;
    private JLabel actualEquityLabel;
    private JLabel actualDebtLabel;
    private JLabel costDebtLabel;
    private JLabel assetNameLabel;
    private JLabel assetValueLabel;
    private JLabel liabilityNameLabel;
    private JLabel liabilityValueLabel;
    private JLabel depreciationLabel;
    private JLabel capitalExpenditureLabel;
    //private JLabel timeLabel;
    private JLabel spaceLabel;

    private JLabel financialExchangeImage;
    private JLabel presentValueImage;

    private JTextField registerField;
    private JTextField revenueField;
    private JTextField costsField;
    private JTextField employeeField;
    private JTextField businessYearsField;
    private JTextField countriesAmountField;
    private JTextField competitorsAmountField;
    private JTextField actualEquityField;
    private JTextField actualDebtField;
    private JTextField costDebtField;
    private JTextField assetNameField;
    private JTextField assetValueField;
    private JTextField liabilityNameField;
    private JTextField liabilityValueField;
    private JTextField depreciationField;
    private JTextField capitalExpenditureField;
    //private JTextField timeField;

    private JButton registerButton;
    private JButton calculateButton;
    private JButton nextButton;
    private JButton backButton;
    private JButton addButton;
    private JButton displayButton;
    private JButton displayBusinessesButton;
    private JButton presentValueButton;
    private JButton removeButton;

    private JPanel imagePanel;
    private JPanel buttonsPanel;
    private JPanel inputPanel;

    private JPanel registerPanel;
    private JPanel revenuePanel;
    private JPanel costsPanel;
    private JPanel employeePanel;
    private JPanel businessYearsPanel;
    private JPanel countriesAmountPanel;
    private JPanel competitorsAmountPanel;
    private JPanel actualEquityPanel;
    private JPanel actualDebtPanel;
    private JPanel costDebtPanel;
    private JPanel assetNamePanel;
    private JPanel assetValuePanel;
    private JPanel liabilityNamePanel;
    private JPanel liabilityValuePanel;
    private JPanel depreciationPanel;
    private JPanel capitalExpenditurePanel;
    //private JPanel timePanel;

    public Input(String pEmail)
    {
        //Formatting Frame
        super("Input");
        this.setBounds(200, 100, 1000, 700);
        this.getContentPane().setBackground(FRAME_COLOR);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        email = pEmail;

        //Constructs the labels
        registerLabel = new JLabel("Business Name: ");
        registerLabel.setFont(DEFAULT_LABEL_FONT);
        revenueLabel = new JLabel("Annual Revenue: ");
        revenueLabel.setFont(DEFAULT_LABEL_FONT);
        costsLabel = new JLabel("Annual Costs: ");
        costsLabel.setFont(DEFAULT_LABEL_FONT);
        employeeLabel = new JLabel("Number of Employees: ");
        employeeLabel.setFont(DEFAULT_LABEL_FONT);
        businessYearsLabel = new JLabel("Years in Business: ");
        businessYearsLabel.setFont(DEFAULT_LABEL_FONT);
        countriesAmountLabel = new JLabel("Number of Countries: ");
        countriesAmountLabel.setFont(DEFAULT_LABEL_FONT);
        competitorsAmountLabel = new JLabel("Number of Competitors");
        competitorsAmountLabel.setFont(DEFAULT_LABEL_FONT);
        actualEquityLabel = new JLabel("Actual Equity: ");
        actualEquityLabel.setFont(DEFAULT_LABEL_FONT);
        actualDebtLabel = new JLabel("Actual Debt: ");
        actualDebtLabel.setFont(DEFAULT_LABEL_FONT);
        costDebtLabel = new JLabel("Cost of Debt: ");
        costDebtLabel.setFont(DEFAULT_LABEL_FONT);
        assetNameLabel = new JLabel("Current Asset Name: ");
        assetNameLabel.setFont(DEFAULT_LABEL_FONT);
        assetValueLabel = new JLabel("Current Asset Value: ");
        assetValueLabel.setFont(DEFAULT_LABEL_FONT);
        liabilityNameLabel = new JLabel("Current Liability Name: ");
        liabilityNameLabel.setFont(DEFAULT_LABEL_FONT);
        liabilityValueLabel = new JLabel("Current Liability Value: ");
        liabilityValueLabel.setFont(DEFAULT_LABEL_FONT);
        depreciationLabel = new JLabel("Depreciation: ");
        depreciationLabel.setFont(DEFAULT_LABEL_FONT);
        capitalExpenditureLabel = new JLabel("Capital Expenditure: ");
        capitalExpenditureLabel.setFont(DEFAULT_LABEL_FONT);
        //timeLabel = new JLabel("Time: ");
        //timeLabel.setFont(DEFAULT_LABEL_FONT);
        spaceLabel = new JLabel(" ");
        spaceLabel.setFont(DEFAULT_LABEL_FONT);

        //Constructs Buttons
        registerButton = new JButton("Register");
        registerButton.addActionListener(this);
        calculateButton = new JButton("Calculate/SaveInfo");
        calculateButton.addActionListener(this);
        nextButton = new JButton("Next");
        nextButton.addActionListener(this);
        backButton = new JButton("Back");
        backButton.addActionListener(this);
        addButton = new JButton("Add");
        addButton.addActionListener(this);
        displayButton = new JButton("Display Report");
        displayButton.addActionListener(this);
        presentValueButton = new JButton("Calculate Present Value");
        presentValueButton.addActionListener(this);
        removeButton = new JButton("Remove");
        removeButton.addActionListener(this);
        displayBusinessesButton = new JButton("Display Businesses");
        displayBusinessesButton.addActionListener(this);

        //Constructs image
        financialExchangeImage = new JLabel(INVESTMENT_EXCHANGE_IMAGE);
        presentValueImage = new JLabel(BUSINESS_VALUE_IMAGE);

        //Constructs textfields
        registerField = new JTextField(10);
        revenueField = new JTextField(10);
        costsField = new JTextField(10);
        employeeField = new JTextField(10);
        businessYearsField = new JTextField(10);
        countriesAmountField = new JTextField(10);
        competitorsAmountField = new JTextField(10);
        actualEquityField = new JTextField(10);
        actualDebtField = new JTextField(10);
        costDebtField = new JTextField(10);
        assetNameField = new JTextField(10);
        assetValueField = new JTextField(10);
        liabilityNameField = new JTextField(10);
        liabilityValueField = new JTextField(10);
        depreciationField = new JTextField(10);
        capitalExpenditureField = new JTextField(10);
        //timeField = new JTextField(10);

        //Constructs the Menu
        mainBar = new JMenuBar();

        calculationsMenu = new JMenu("Calculations");
        investmentComparisonItem = new JMenuItem("Value Comparison");
        investmentComparisonItem.addActionListener(this);
        businessReportItem = new JMenuItem("Business Report");
        businessReportItem.addActionListener(this);
        presentValueItem = new JMenuItem("Present Value Calculation");
        presentValueItem.addActionListener(this);
        removeBusinessItem = new JMenuItem("Remove A Business");
        removeBusinessItem.addActionListener(this);
        displayBusinessesItem = new JMenuItem("Display Registered Businesses");
        displayBusinessesItem.addActionListener(this);

        calculationsMenu.add(presentValueItem);
        calculationsMenu.add(investmentComparisonItem);
        calculationsMenu.add(businessReportItem);
        calculationsMenu.add(removeBusinessItem);
        calculationsMenu.add(displayBusinessesItem);

        helpMenu = new JMenu("Help");
        helpMenu.setFont(new Font("Arial", Font.PLAIN, 16));
        generalHelpItem = new JMenuItem("General Help");
        generalHelpItem.addActionListener(this);
        calculationHelpItem = new JMenuItem("Calculation Help");
        calculationHelpItem.addActionListener(this);

        helpMenu.add(generalHelpItem);
        helpMenu.add(calculationHelpItem);

        aboutButton = new JButton("About");
        aboutButton.addActionListener(this);
        aboutButton.setFont(new Font("Arial", Font.PLAIN, 16));
        logOutButton = new JButton("Log Out");
        logOutButton.addActionListener(this);
        logOutButton.setFont(new Font("Arial", Font.PLAIN, 16));

        logOutButton.setBorderPainted(false);
        aboutButton.setBorderPainted(false);

        //Adds components to the menu
        mainBar.add(calculationsMenu);
        mainBar.add(helpMenu);
        mainBar.add(Box.createHorizontalGlue());
        mainBar.add(aboutButton);
        mainBar.add(logOutButton);

        //Constructing and formatting the labels of the panels
        imagePanel = new JPanel(new FlowLayout());
        imagePanel.setBackground(MAIN_PANEL_COLOR);
        inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
        inputPanel.setBackground(MAIN_PANEL_COLOR);

        registerPanel = new JPanel(new FlowLayout());
        registerPanel.setBackground(MAIN_PANEL_COLOR);
        revenuePanel = new JPanel(new FlowLayout());
        revenuePanel.setBackground(MAIN_PANEL_COLOR);
        costsPanel = new JPanel(new FlowLayout());
        costsPanel.setBackground(MAIN_PANEL_COLOR);
        employeePanel = new JPanel(new FlowLayout());
        employeePanel.setBackground(MAIN_PANEL_COLOR);
        businessYearsPanel = new JPanel(new FlowLayout());
        businessYearsPanel.setBackground(MAIN_PANEL_COLOR);
        countriesAmountPanel = new JPanel(new FlowLayout());
        countriesAmountPanel.setBackground(MAIN_PANEL_COLOR);
        competitorsAmountPanel = new JPanel(new FlowLayout());
        competitorsAmountPanel.setBackground(MAIN_PANEL_COLOR);
        actualEquityPanel = new JPanel(new FlowLayout());
        actualEquityPanel.setBackground(MAIN_PANEL_COLOR);
        actualDebtPanel = new JPanel(new FlowLayout());
        actualDebtPanel.setBackground(MAIN_PANEL_COLOR);
        costDebtPanel = new JPanel(new FlowLayout());
        costDebtPanel.setBackground(MAIN_PANEL_COLOR);
        assetNamePanel = new JPanel(new FlowLayout());
        assetNamePanel.setBackground(MAIN_PANEL_COLOR);
        assetValuePanel = new JPanel(new FlowLayout());
        assetValuePanel.setBackground(MAIN_PANEL_COLOR);
        liabilityNamePanel = new JPanel(new FlowLayout());
        liabilityNamePanel.setBackground(MAIN_PANEL_COLOR);
        liabilityValuePanel = new JPanel(new FlowLayout());
        liabilityValuePanel.setBackground(MAIN_PANEL_COLOR);
        depreciationPanel = new JPanel(new FlowLayout());
        depreciationPanel.setBackground(MAIN_PANEL_COLOR);
        capitalExpenditurePanel = new JPanel(new FlowLayout());
        capitalExpenditurePanel.setBackground(MAIN_PANEL_COLOR);
        //timePanel = new JPanel(new FlowLayout());
        //timePanel.setBackground(MAIN_PANEL_COLOR);

        buttonsPanel = new JPanel(new FlowLayout());
        buttonsPanel.setBackground(FRAME_COLOR);

        registerPanel.add(registerLabel);
        registerPanel.add(registerField);
        revenuePanel.add(revenueLabel);
        revenuePanel.add(revenueField);
        costsPanel.add(costsLabel);
        costsPanel.add(costsField);
        employeePanel.add(employeeLabel);
        employeePanel.add(employeeField);
        businessYearsPanel.add(businessYearsLabel);
        businessYearsPanel.add(businessYearsField);
        countriesAmountPanel.add(countriesAmountLabel);
        countriesAmountPanel.add(countriesAmountField);
        competitorsAmountPanel.add(competitorsAmountLabel);
        competitorsAmountPanel.add(competitorsAmountField);
        actualEquityPanel.add(actualEquityLabel);
        actualEquityPanel.add(actualEquityField);
        actualDebtPanel.add(actualDebtLabel);
        actualDebtPanel.add(actualDebtField);
        costDebtPanel.add(costDebtLabel);
        costDebtPanel.add(costDebtField);
        assetNamePanel.add(assetNameLabel);
        assetNamePanel.add(assetNameField);
        assetValuePanel.add(assetValueLabel);
        assetValuePanel.add(assetValueField);
        liabilityNamePanel.add(liabilityNameLabel);
        liabilityNamePanel.add(liabilityNameField);
        liabilityValuePanel.add(liabilityValueLabel);
        liabilityValuePanel.add(liabilityValueField);
        depreciationPanel.add(depreciationLabel);
        depreciationPanel.add(depreciationField);
        capitalExpenditurePanel.add(capitalExpenditureLabel);
        capitalExpenditurePanel.add(capitalExpenditureField);
        //timePanel.add(timeLabel);
        //timePanel.add(timeField);

        //adds image to image panel
        imagePanel.add(financialExchangeImage);
        imagePanel.add(spaceLabel);

        //adds components to the main input panel
        inputPanel.add(spaceLabel);
        inputPanel.add(registerPanel);

        //adds components to button panel
        buttonsPanel.add(registerButton);
        buttonsPanel.add(nextButton);

        //Adds the menu to the frame
        this.setJMenuBar(mainBar);

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

        if (command.equals("Log Out"))
        {
            //Opens Log in frame
            LogIn logInObj = new LogIn();
            this.dispose();

            registerField.setText("");
            revenueField.setText("");
            costsField.setText("");
            employeeField.setText("");
            businessYearsField.setText("");
            countriesAmountField.setText("");
            competitorsAmountField.setText("");
            actualEquityField.setText("");
            actualDebtField.setText("");
            costDebtField.setText("");
            assetNameField.setText("");
            assetValueField.setText("");
            liabilityNameField.setText("");
            liabilityValueField.setText("");
            depreciationField.setText("");
            capitalExpenditureField.setText("");
        }
        else if (command.equals("About"))
        {
            //Opens About frame
            About aboutObj = new About();
        }
        else if (command.equals("General Help"))
        {
            //Opens General Help frame
            GeneralHelp generalHelpObj = new GeneralHelp();
        }
        else if (command.equals("Calculation Help"))
        {
            //Opens Calculation Help frame
            CalculationHelp calculationHelpObj = new CalculationHelp();
        }
        else if (command.equals("Remove A Business"))
        {
            inputPanel.removeAll();

            //adds components to the main input panel
            inputPanel.add(spaceLabel);
            inputPanel.add(registerPanel);

            buttonsPanel.removeAll();

            buttonsPanel.add(removeButton);

            cashFlowData.clear();
            balanceSheetData.clear();
            presentValueData.clear();
            profitData.clear();
            time = 1;

            registerField.setText("");
            revenueField.setText("");
            costsField.setText("");
            employeeField.setText("");
            businessYearsField.setText("");
            countriesAmountField.setText("");
            competitorsAmountField.setText("");
            actualEquityField.setText("");
            actualDebtField.setText("");
            costDebtField.setText("");
            assetNameField.setText("");
            assetValueField.setText("");
            liabilityNameField.setText("");
            liabilityValueField.setText("");
            depreciationField.setText("");
            capitalExpenditureField.setText("");
        }
        else if (command.equals("Display Registered Businesses"))
        {
            inputPanel.removeAll();

            //Adds components to the main input panel
            inputPanel.add(spaceLabel);

            buttonsPanel.removeAll();

            buttonsPanel.add(displayBusinessesButton);

            cashFlowData.clear();
            balanceSheetData.clear();
            presentValueData.clear();
            profitData.clear();
            time = 1;

            registerField.setText("");
            revenueField.setText("");
            costsField.setText("");
            employeeField.setText("");
            businessYearsField.setText("");
            countriesAmountField.setText("");
            competitorsAmountField.setText("");
            actualEquityField.setText("");
            actualDebtField.setText("");
            costDebtField.setText("");
            assetNameField.setText("");
            assetValueField.setText("");
            liabilityNameField.setText("");
            liabilityValueField.setText("");
            depreciationField.setText("");
            capitalExpenditureField.setText("");
        }
        else if (command.equals("Value Comparison"))
        {
            inputPanel.removeAll();

            //adds components to the main input panel
            inputPanel.add(spaceLabel);
            //inputPanel.add(timePanel);

            buttonsPanel.removeAll();

            buttonsPanel.add(calculateButton);

            cashFlowData.clear();
            balanceSheetData.clear();
            presentValueData.clear();
            profitData.clear();
            time = 1;

            registerField.setText("");
            revenueField.setText("");
            costsField.setText("");
            employeeField.setText("");
            businessYearsField.setText("");
            countriesAmountField.setText("");
            competitorsAmountField.setText("");
            actualEquityField.setText("");
            actualDebtField.setText("");
            costDebtField.setText("");
            assetNameField.setText("");
            assetValueField.setText("");
            liabilityNameField.setText("");
            liabilityValueField.setText("");
            depreciationField.setText("");
            capitalExpenditureField.setText("");
        }
        else if (command.equals("Business Report"))
        {
            inputPanel.removeAll();

            //adds components to the main input panel
            inputPanel.add(spaceLabel);
            inputPanel.add(registerPanel);

            buttonsPanel.removeAll();

            buttonsPanel.add(displayButton);

            cashFlowData.clear();
            balanceSheetData.clear();
            presentValueData.clear();
            profitData.clear();
            time = 1;

            registerField.setText("");
            revenueField.setText("");
            costsField.setText("");
            employeeField.setText("");
            businessYearsField.setText("");
            countriesAmountField.setText("");
            competitorsAmountField.setText("");
            actualEquityField.setText("");
            actualDebtField.setText("");
            costDebtField.setText("");
            assetNameField.setText("");
            assetValueField.setText("");
            liabilityNameField.setText("");
            liabilityValueField.setText("");
            depreciationField.setText("");
            capitalExpenditureField.setText("");
        }
        else if (command.equals("Present Value Calculation"))
        {
            inputPanel.removeAll();

            //adds components to the main input panel
            inputPanel.add(spaceLabel);
            inputPanel.add(registerPanel);

            buttonsPanel.removeAll();

            buttonsPanel.add(registerButton);
            buttonsPanel.add(nextButton);

            cashFlowData.clear();
            balanceSheetData.clear();
            presentValueData.clear();
            profitData.clear();
            time = 1;

            try
            {
                businessObj.setNetPresentValue(0);
            }
            catch (NullPointerException nfe)
            {

            }

            registerField.setText("");
            revenueField.setText("");
            costsField.setText("");
            employeeField.setText("");
            businessYearsField.setText("");
            countriesAmountField.setText("");
            competitorsAmountField.setText("");
            actualEquityField.setText("");
            actualDebtField.setText("");
            costDebtField.setText("");
            assetNameField.setText("");
            assetValueField.setText("");
            liabilityNameField.setText("");
            liabilityValueField.setText("");
            depreciationField.setText("");
            capitalExpenditureField.setText("");

        }
        else if (command.equals("Display Businesses"))
        {
            //Get business names in alphabetical order
            orderedBusinessNames = getBusinessNames();

            businessReportObj = new BusinessReport(orderedBusinessNames);
        }
        else if (command.equals("Register"))
        {
            //Checks if register field is empty
            if (registerField.getText().equals(""))
            {
                errorObj = new Error("Please Enter a Business Name");
            }
            else
            {
                businessName = registerField.getText();

                businessObj = new BusinessFinancials();
                riskObj = new Risk();

                //Displays answer through general output frame
                generalOutputObj = new GeneralOutput("The Business has been Registered");
            }
        }
        else if (command.equals("Remove"))
        {
            try
            {
                //removes data
                removeValueData(registerField.getText());
            }
            catch(IndexOutOfBoundsException ibe)
            {
                errorObj = new Error("The Business Does Not Exist");
            }

            //Displays answer through general output frame
            generalOutputObj = new GeneralOutput("The Business has been Removed");
        }
        else if (command.equals("Next"))
        {

            //Checks if this panel is displayed
            if (registerPanel.isDisplayable())
            {
                if (businessName.equals("") == false)
                {
                    inputPanel.removeAll();

                    inputPanel.add(spaceLabel);
                    inputPanel.add(revenuePanel);
                    inputPanel.add(costsPanel);

                    buttonsPanel.removeAll();

                    buttonsPanel.add(backButton);
                    buttonsPanel.add(calculateButton);
                    buttonsPanel.add(nextButton);
                } else
                {
                    errorObj = new Error("Please Register/Choose A Business");
                }
            }
            //Checks if these panels are displayed
            else if (revenuePanel.isDisplayable() && costsPanel.isDisplayable())
            {
                if (savedInfo == true && time > maxTime)
                {
                    inputPanel.removeAll();

                    inputPanel.add(spaceLabel);
                    inputPanel.add(employeePanel);
                    inputPanel.add(businessYearsPanel);
                    inputPanel.add(countriesAmountPanel);
                    inputPanel.add(competitorsAmountPanel);

                    time = 1;
                } else
                {
                    int timeLeft = 6 - time;
                    errorObj = new Error("Please Enter " + timeLeft + " More Years of Information");
                }
            }
            //Checks if these panels are displayed
            else if (employeePanel.isDisplayable() && businessYearsPanel.isDisplayable()
                    && countriesAmountPanel.isDisplayable() && competitorsAmountPanel.isDisplayable())
            {
                if (savedInfo == true)
                {
                    inputPanel.removeAll();

                    inputPanel.add(spaceLabel);
                    inputPanel.add(actualEquityPanel);
                    inputPanel.add(actualDebtPanel);
                    inputPanel.add(costDebtPanel);
                } else
                {
                    errorObj = new Error("Save Information to Continue");
                }
            }
            //Checks if these panels are displayed
            else if (actualEquityPanel.isDisplayable() && actualDebtPanel.isDisplayable()
                    && costDebtPanel.isDisplayable())
            {
                if (savedInfo == true)
                {
                    inputPanel.removeAll();

                    inputPanel.add(spaceLabel);
                    inputPanel.add(assetNamePanel);
                    inputPanel.add(assetValuePanel);
                    inputPanel.add(liabilityNamePanel);
                    inputPanel.add(liabilityValuePanel);

                    buttonsPanel.removeAll();

                    buttonsPanel.add(backButton);
                    buttonsPanel.add(addButton);
                    buttonsPanel.add(nextButton);
                } else
                {
                    errorObj = new Error("Save Information to Continue");
                }
            }
            //Checks if these panels are displayed
            else if (assetNamePanel.isDisplayable() && assetValuePanel.isDisplayable()
                    && liabilityNamePanel.isDisplayable() && liabilityValuePanel.isDisplayable())
            {
                if (savedInfo == true && time > maxTime)
                {
                    inputPanel.removeAll();

                    inputPanel.add(spaceLabel);
                    inputPanel.add(depreciationPanel);
                    inputPanel.add(capitalExpenditurePanel);
                    //inputPanel.add(timePanel);

                    buttonsPanel.removeAll();

                    buttonsPanel.add(backButton);
                    buttonsPanel.add(calculateButton);
                    buttonsPanel.add(nextButton);

                    time = 1;
                }
                else
                {
                    //Displays counter for how many more years of information the user has to enter
                    int timeLeft = 6 - time;
                    errorObj = new Error("Please Enter " + timeLeft + " More Years of Information");
                }
            }
            //Checks if these panels are displayed
            else if (depreciationPanel.isDisplayable() && capitalExpenditurePanel.isDisplayable() /*&& timePanel.isDisplayable()*/)
            {
                if (savedInfo == true && time > maxTime)
                {
                    inputPanel.removeAll();

                    presentValueImage.setAlignmentX(inputPanel.CENTER_ALIGNMENT);

                    inputPanel.add(spaceLabel);
                    inputPanel.add(presentValueImage);

                    buttonsPanel.removeAll();

                    buttonsPanel.add(backButton);
                    buttonsPanel.add(presentValueButton);
                    buttonsPanel.add(displayButton);
                }
                else
                {
                    //Displays counter for how many more years of information the user has to enter
                    int timeLeft = 6 - time;
                    errorObj = new Error("Please Enter " + timeLeft + " More Years of Information");
                }
            }

            registerField.setText("");
            revenueField.setText("");
            costsField.setText("");
            employeeField.setText("");
            businessYearsField.setText("");
            countriesAmountField.setText("");
            competitorsAmountField.setText("");
            actualEquityField.setText("");
            actualDebtField.setText("");
            costDebtField.setText("");
            assetNameField.setText("");
            assetValueField.setText("");
            liabilityNameField.setText("");
            liabilityValueField.setText("");
            depreciationField.setText("");
            capitalExpenditureField.setText("");

            savedInfo = false;
        }
        else if (command.equals("Back"))
        {
            //Checks if these panels are displayed
            if (revenuePanel.isDisplayable() && costsPanel.isDisplayable())
            {
                inputPanel.removeAll();

                //adds components to the main input panel
                inputPanel.add(spaceLabel);
                inputPanel.add(registerPanel);

                buttonsPanel.removeAll();

                buttonsPanel.add(registerButton);
                buttonsPanel.add(nextButton);

                profitData.clear();
                time = 1;
            }
            //Checks if these panels are displayed
            else if (employeePanel.isDisplayable() && businessYearsPanel.isDisplayable()
                    && countriesAmountPanel.isDisplayable() && competitorsAmountPanel.isDisplayable())
            {
                inputPanel.removeAll();

                inputPanel.add(spaceLabel);
                inputPanel.add(revenuePanel);
                inputPanel.add(costsPanel);

                buttonsPanel.removeAll();

                buttonsPanel.add(backButton);
                buttonsPanel.add(calculateButton);
                buttonsPanel.add(nextButton);

                profitData.clear();
                time = 1;
            } //Checks if these panels are displayed
            else if (actualEquityPanel.isDisplayable() && actualDebtPanel.isDisplayable()
                    && costDebtPanel.isDisplayable())
            {
                inputPanel.removeAll();

                inputPanel.add(spaceLabel);
                inputPanel.add(employeePanel);
                inputPanel.add(businessYearsPanel);
                inputPanel.add(countriesAmountPanel);
                inputPanel.add(competitorsAmountPanel);
            } //Checks if these panels are displayed
            else if (assetNamePanel.isDisplayable() && assetValuePanel.isDisplayable()
                    && liabilityNamePanel.isDisplayable() && liabilityValuePanel.isDisplayable())
            {
                inputPanel.removeAll();

                inputPanel.add(spaceLabel);
                inputPanel.add(actualEquityPanel);
                inputPanel.add(actualDebtPanel);
                inputPanel.add(costDebtPanel);

                buttonsPanel.removeAll();

                buttonsPanel.add(backButton);
                buttonsPanel.add(calculateButton);
                buttonsPanel.add(nextButton);

                balanceSheetData.clear();
                time = 1;
            } //Checks if these panels are displayed
            else if (depreciationPanel.isDisplayable() && capitalExpenditurePanel.isDisplayable() /*&& timePanel.isDisplayable()*/)
            {
                inputPanel.removeAll();

                inputPanel.add(spaceLabel);
                inputPanel.add(assetNamePanel);
                inputPanel.add(assetValuePanel);
                inputPanel.add(liabilityNamePanel);
                inputPanel.add(liabilityValuePanel);

                buttonsPanel.removeAll();

                buttonsPanel.add(backButton);
                buttonsPanel.add(addButton);
                buttonsPanel.add(nextButton);

                balanceSheetData.clear();
                time = 1;
                cashFlowData.clear();
                time = 1;
            }
            else
            {
                inputPanel.removeAll();

                inputPanel.add(spaceLabel);
                inputPanel.add(depreciationPanel);
                inputPanel.add(capitalExpenditurePanel);
                //inputPanel.add(timePanel);

                buttonsPanel.removeAll();

                buttonsPanel.add(backButton);
                buttonsPanel.add(calculateButton);
                buttonsPanel.add(nextButton);

                cashFlowData.clear();
                time = 1;
                presentValueData.clear();
            }

            registerField.setText("");
            revenueField.setText("");
            costsField.setText("");
            employeeField.setText("");
            businessYearsField.setText("");
            countriesAmountField.setText("");
            competitorsAmountField.setText("");
            actualEquityField.setText("");
            actualDebtField.setText("");
            costDebtField.setText("");
            assetNameField.setText("");
            assetValueField.setText("");
            liabilityNameField.setText("");
            liabilityValueField.setText("");
            depreciationField.setText("");
            capitalExpenditureField.setText("");

            savedInfo = false;
        }
        else if (command.equals("Calculate/SaveInfo"))
        {
            //Checks if these panels are displayed
            if (revenuePanel.isDisplayable() && costsPanel.isDisplayable())
            {
                try
                {
                    if (time <= maxTime)
                    {
                        //Caclulates profits for the business
                        businessObj.profitCalculation(Double.parseDouble(revenueField.getText()),
                                Double.parseDouble(costsField.getText()));
                        generalOutputObj = new GeneralOutput("Profit", businessObj.getProfit());

                        profitData.add(Double.parseDouble(revenueField.getText()));
                        profitData.add(Double.parseDouble(costsField.getText()));
                        profitData.add(businessObj.getProfit());

                        savedInfo = true;

                        time++;
                    }
                    else
                    {
                        errorObj = new Error("Error: You can't add any more");
                    }
                }
                catch (NumberFormatException nfe)
                {
                    errorObj = new Error("Please Enter Correct Values");
                }
                catch (NullPointerException npe)
                {
                }
            }
            //Checks if these panels are displayed
            else if (employeePanel.isDisplayable() && businessYearsPanel.isDisplayable()
                    && countriesAmountPanel.isDisplayable() && competitorsAmountPanel.isDisplayable())
            {
                try
                {
                    riskObj.calculateRisk(Integer.parseInt(employeeField.getText()),
                            Integer.parseInt(businessYearsField.getText()),
                            Integer.parseInt(countriesAmountField.getText()),
                            Integer.parseInt(competitorsAmountField.getText()));
                    generalOutputObj = new GeneralOutput("Risk", (double) riskObj.getRiskPercentage());

                    savedInfo = true;
                } catch (NumberFormatException nfe)
                {
                    errorObj = new Error("Please Enter Correct Values");
                }
            }
            //Checks if these panels are displayed
            else if (actualEquityPanel.isDisplayable() && actualDebtPanel.isDisplayable()
                    && costDebtPanel.isDisplayable())
            {
                try
                {
                    actualEquity = Double.parseDouble(actualEquityField.getText());
                    actualDebt = Double.parseDouble(actualDebtField.getText());
                    debtCost = Double.parseDouble(costDebtField.getText());

                    businessObj.weightedDebtCalculation(actualEquity, actualDebt);
                    businessObj.weightedEquityCalculation(actualEquity, actualDebt);
                    businessObj.equityCostCalculation(riskObj.getRiskPercentage(), debtCost);
                    businessObj.waccCalculation(businessObj.getWeightedEquity(), businessObj.getWeightedDebt(),
                            businessObj.getEquityCost(), debtCost);

                    generalOutputObj = new GeneralOutput("Weighted Average Cost of Capital", businessObj.getWacc());

                    savedInfo = true;
                }
                catch (NumberFormatException nfe)
                {
                    errorObj = new Error("Please Enter Correct Values");
                }
            }
            //Checks if these panels are displayed
            else if (depreciationPanel.isDisplayable() && capitalExpenditurePanel.isDisplayable() /*&& timePanel.isDisplayable()*/)
            {
                try
                {
                    if (time <= maxTime)
                    {
                        //time = Integer.parseInt(timeField.getText());
                        double previousWorkingCapital = 0;

                        try
                        {
                            previousWorkingCapital = cashFlowData.get((time-1)*6-5);
                            System.out.println("I_HATE_THIS");
                        }
                        catch(IndexOutOfBoundsException abe)
                        {

                        }

                        cashFlowData.add((double) time);
                        cashFlowData.add(businessObj.calculateWorkingCapital(
                                Double.parseDouble(balanceSheetData.get(4 * (time - 1) + 1).toString()),
                                Double.parseDouble(balanceSheetData.get(4 * (time - 1) + 3).toString())));

                        cashFlowData.add(Double.parseDouble(depreciationField.getText()));
                        cashFlowData.add(profitData.get(2 + 3 * (time - 1)));
                        cashFlowData.add(Double.parseDouble(capitalExpenditureField.getText()));
                        cashFlowData.add(businessObj.cashFlowCalculation(profitData.get(2 + 3 * (time - 1)),
                                businessObj.getWorkingCapital(), previousWorkingCapital,
                                Double.parseDouble(capitalExpenditureField.getText()),
                                Double.parseDouble(depreciationField.getText())));

                        //Displays cash flow table
                        CashFlowTable cashFlowObj = new CashFlowTable(cashFlowData);

                        time++;

                        savedInfo = true;

                    }
                    else
                    {
                        errorObj = new Error("Error: You can't add any more");
                    }
                }
                catch (NumberFormatException nfe)
                {
                    errorObj = new Error("Please Enter Valid Inputs");
                }
            }
            //Checks if this panel is displayed
            else //if (timePanel.isDisplayable())
            {
                bestBusinessNames = compareValues();

                if (bestBusinessNames.size() == 0)
                {
                    errorObj = new Error("Sorry, You Have Not Registered Any Businesses");
                }
                else
                {
                    //Obtains all the businesses with the highest present value and displays them
                    String presentValueMessage = "The Businesses with the Best Net "
                            + "Present Value of $" +
                            Math.round(Double.parseDouble(bestBusinessNames.get(0))*100)/100
                            + ": ";

                    for (int i=0; i<bestBusinessNames.size(); i+=2)
                    {
                        if (i+2 >= bestBusinessNames.size())
                        {
                            presentValueMessage = presentValueMessage + bestBusinessNames.get(i+1);
                        }
                        else
                        {
                            presentValueMessage = presentValueMessage + bestBusinessNames.get(i+1) + ", ";
                        }
                    }

                    GeneralOutput generalOutputObj = new GeneralOutput(presentValueMessage);
                }
            }
        }
        else if (command.equals("Add"))
        {

            //Checks if the balance data was not duplicated
            if (checkBalanceData(assetNameField.getText(), liabilityNameField.getText()) == true)
            {
                if (assetNameField.getText().equals("") || liabilityNameField.getText().equals("")
                        || assetValueField.getText().equals("") || liabilityValueField.getText().equals(""))
                {
                    errorObj = new Error("Please Enter Correct Values");
                }
                else
                {
                    //Adds balance data for a certain amount of years
                    if (time <= maxTime)
                    {
                        try
                        {
                            balanceSheetData.add(assetNameField.getText());
                            balanceSheetData.add(assetValueField.getText());
                            balanceSheetData.add(liabilityNameField.getText());
                            balanceSheetData.add(liabilityValueField.getText());

                            //Opens General Output
                            generalOutputObj = new GeneralOutput("The Data has Been Stored");

                            time++;

                            savedInfo = true;
                        }
                        catch (NumberFormatException nfe)
                        {
                            errorObj = new Error("Error: Invalid Inputs");
                        }
                    }
                    else
                    {
                        errorObj = new Error("Error: You can't add any more");
                    }
                }
            }
            else
            {
                errorObj = new Error("Error: Duplicate Asset or Liability Name");
            }
        }
        else if (command.equals("Calculate Present Value"))
        {
            try
            {
                businessObj.setNetPresentValue(0);
            }
            catch (NullPointerException npe)
            {

            }
            presentValueData.clear();

            time = 1;

            //Adds and calculates for net present values
            presentValueData.add(businessObj.presentValueCalculation(time, cashFlowData.get(5), businessObj.getWacc()));
            presentValueData.add(businessObj.presentValueCalculation(time + 1, cashFlowData.get(11), businessObj.getWacc()));
            presentValueData.add(businessObj.presentValueCalculation(time + 2, cashFlowData.get(17), businessObj.getWacc()));
            presentValueData.add(businessObj.presentValueCalculation(time + 3, cashFlowData.get(23), businessObj.getWacc()));
            presentValueData.add(businessObj.presentValueCalculation(time + 4, cashFlowData.get(29), businessObj.getWacc()));

            generalOutputObj = new GeneralOutput("Net Present Value", businessObj.getNetPresentValue());
        }
        else if (command.equals("Display Report"))
        {
            if (registerPanel.isDisplayable())
            {
                businessName = registerField.getText();

                boolean businessExists;

                //Checks if business is registered/exists
                try
                {
                    businessExists = checkBusinessName();
                }
                catch (IndexOutOfBoundsException ibe)
                {
                    businessExists = false;
                }

                if (businessExists == true)
                {
                    cashFlowData.clear();
                    balanceSheetData.clear();
                    time = 1;

                    //Obtain balance and cash flow data
                    balanceSheetData = getBalanceData();
                    cashFlowData = getFlowData();

                    //Displays business report
                    businessReportObj = new BusinessReport(cashFlowData, balanceSheetData,
                            Integer.toString(getRisk(businessName)),
                            Double.toString(getPresentValue(businessName)));
                }
                else
                {
                    errorObj = new Error("Sorry, that business name does not exist");
                }

            }
            else
            {
                boolean businessExists;

                //Checks if business registered/exists
                try
                {
                    businessExists = checkBusinessName();
                }
                catch (IndexOutOfBoundsException ibe)
                {
                    businessExists = false;
                }

                if (businessExists == true)
                {
                    //Removes all the data associated with the existing business
                    removeValueData(businessName);

                    //Inserts all the balance data
                    for (int i=0; i<maxTime; i++)
                    {
                        insertBalanceData(balanceSheetData.get(i*4).toString(),
                                Double.parseDouble(balanceSheetData.get(i*4+1).toString()),
                                balanceSheetData.get(i*4+2).toString(),
                                Double.parseDouble(balanceSheetData.get(i*4+3).toString()));
                    }
                    insertCashFlow();
                    insertValueData();
                }
                else
                {
                    //Inserts all the balance data
                    for (int i=0; i<maxTime; i++)
                    {
                        insertBalanceData(balanceSheetData.get(i*4).toString(),
                                Double.parseDouble(balanceSheetData.get(i*4+1).toString()),
                                balanceSheetData.get(i*4+2).toString(),
                                Double.parseDouble(balanceSheetData.get(i*4+3).toString()));
                    }
                    //Inserts cash flow data
                    insertCashFlow();

                    insertValueData();
                }

                //Displays business report
                businessReportObj = new BusinessReport(cashFlowData, balanceSheetData,
                            Integer.toString(getRisk(businessName)),
                            Double.toString(getPresentValue(businessName)));
            }
        }
        /*Revalidates and repaints the frame in order to allow
        the panel to show up on the frame*/
        this.validate();
        this.repaint();
    }

    //Inserts all the data of a business except for cash flow and balance sheet
    private void insertValueData()
    {
        Connection myDbConn = null;

        //Creates object of database and connects to database
        DatabaseAccess objDb = new DatabaseAccess();
        objDb.setDbName("BusinessFinancials");
        objDb.setDbConn();
        myDbConn = objDb.getDbConn();

        Object[][] data;

        String nonTimeQuery = "INSERT INTO NonTimeFinancials VALUES (?,?,?,?,?,?,?,?,?)";

        //Uses query to add data to database
        try
        {
            PreparedStatement ps = myDbConn.prepareStatement(nonTimeQuery);
            ps.setString(1, email);
            ps.setString(2, businessName);
            ps.setDouble(3, actualEquity);
            ps.setDouble(4, actualDebt);
            ps.setDouble(5, debtCost);
            ps.setInt(6, riskObj.getEmployeeAmount());
            ps.setInt(7, riskObj.getBusinessYears());
            ps.setInt(8, riskObj.getCountriesAmount());
            ps.setInt(9, riskObj.getCompetitorsAmount());
            ps.executeUpdate();
        }
        catch (SQLException se)
        {
            se.printStackTrace(System.err);
        }

        //Inserts data associated with time
        for (int i = 0; i < maxTime; i++)
        {
            String timeFinancialsQuery = "INSERT INTO TimeFinancials VALUES (?,?,?,?,?,?,?,?,?)";

            try
            {
                PreparedStatement ps = myDbConn.prepareStatement(timeFinancialsQuery);
                ps.setString(1, email);
                ps.setString(2, businessName);
                ps.setInt(3, i+1);
                ps.setDouble(4, cashFlowData.get(i * 6 + 4));
                ps.setString(5, balanceSheetData.get(i * 4).toString());
                ps.setString(6, balanceSheetData.get(i * 4 + 2).toString());
                ps.setDouble(7, cashFlowData.get(i * 6 + 2));
                ps.setDouble(8, profitData.get(i * 3));
                ps.setDouble(9, profitData.get(i * 3 + 1));
                ps.executeUpdate();
//                System.out.println("Data inserted succesfully");
            }
            catch (SQLException se)
            {
//                System.out.println("Error inserting data");
                se.printStackTrace(System.err);
            }

            String profitsQuery = "INSERT INTO Profits VALUES (?,?,?,?)";

            try
            {
                PreparedStatement ps = myDbConn.prepareStatement(profitsQuery);
                ps.setInt(1, i+1);
                ps.setDouble(2, profitData.get(i * 3));
                ps.setDouble(3, profitData.get(i * 3 + 1));
                ps.setDouble(4, profitData.get(i * 3 + 2));

                ps.executeUpdate();
//                System.out.println("Data inserted succesfully");
            }
            catch (SQLException se)
            {
//                System.out.println("Error inserting data");
                //se.printStackTrace(System.err);
            }

            String workingCapitalQuery = "INSERT INTO WorkingCapital VALUES (?,?,?,?)";

            try
            {
                PreparedStatement ps = myDbConn.prepareStatement(workingCapitalQuery);
                ps.setInt(1, i+1);
                ps.setString(2, balanceSheetData.get(i * 4).toString());
                ps.setString(3, balanceSheetData.get(i * 4 + 2).toString());
                ps.setDouble(4, businessObj.getWorkingCapital());

                ps.executeUpdate();
//                System.out.println("Data inserted succesfully");
            } catch (SQLException se)
            {
                //               System.out.println("Error inserting data");
                //se.printStackTrace(System.err);
            }

            String presentValueQuery = "INSERT INTO NetPresentValue VALUES (?,?,?,?)";

            try
            {
                PreparedStatement ps = myDbConn.prepareStatement(presentValueQuery);
                ps.setInt(1, i+1);
                ps.setDouble(2, cashFlowData.get(i * 6 + 5));
                ps.setDouble(3, businessObj.getWacc());
                ps.setDouble(4, presentValueData.get(i));

                ps.executeUpdate();
//                System.out.println("Data inserted succesfully");
            }
            catch (SQLException se)
            {
                //             System.out.println("Error inserting data");
                //se.printStackTrace(System.err);
            }
        }

        String riskQuery = "INSERT INTO RiskFactor VALUES (?,?,?,?,?)";

        //Uses query to add data to database
        try
        {
            PreparedStatement ps = myDbConn.prepareStatement(riskQuery);
            ps.setInt(1, riskObj.getEmployeeAmount());
            ps.setInt(2, riskObj.getBusinessYears());
            ps.setInt(3, riskObj.getCountriesAmount());
            ps.setInt(4, riskObj.getCompetitorsAmount());
            ps.setInt(5, riskObj.getRiskPercentage());
            ps.executeUpdate();
//            System.out.println("Data inserted succesfully");
        } catch (SQLException se)
        {
            //         System.out.println("Error inserting data");
            //se.printStackTrace(System.err);
        }

        String weightVariablesQuery = "INSERT INTO WeightVariables VALUES (?,?,?,?)";

        //Uses query to add data to database
        try
        {
            PreparedStatement ps = myDbConn.prepareStatement(weightVariablesQuery);
            ps.setDouble(1, actualDebt);
            ps.setDouble(2, actualEquity);
            ps.setDouble(3, businessObj.getWeightedDebt());
            ps.setDouble(4, businessObj.getWeightedEquity());
            ps.executeUpdate();
//            System.out.println("Data inserted succesfully");
        }
        catch (SQLException se)
        {
            //           System.out.println("Error inserting data");
            //se.printStackTrace(System.err);
        }

        String equityCostQuery = "INSERT INTO EquityCost VALUES (?,?,?)";

        //Uses query to add data to database
        try
        {
            PreparedStatement ps = myDbConn.prepareStatement(equityCostQuery);
            ps.setDouble(1, businessObj.getWeightedDebt());
            ps.setInt(2, riskObj.getRiskPercentage());
            ps.setDouble(3, businessObj.getEquityCost());
            ps.executeUpdate();
//            System.out.println("Data inserted succesfully");
        } catch (SQLException se)
        {
            //       System.out.println("Error inserting data");
            //se.printStackTrace(System.err);
        }

        String waccQuery = "INSERT INTO Wacc VALUES (?,?,?,?,?)";

        //Uses query to add data to database
        try
        {
            PreparedStatement ps = myDbConn.prepareStatement(waccQuery);
            ps.setDouble(1, debtCost);
            ps.setDouble(2, businessObj.getEquityCost());
            ps.setDouble(3, businessObj.getWeightedDebt());
            ps.setDouble(4, businessObj.getWeightedEquity());
            ps.setDouble(5, businessObj.getWacc());
            ps.executeUpdate();
//            System.out.println("Data inserted succesfully");
        } catch (SQLException se)
        {
            //        System.out.println("Error inserting data");
            //se.printStackTrace(System.err);
        }
    }

    //Inserts cash flow data
    private void insertCashFlow()
    {
        Connection myDbConn = null;

        //Creates object of database and connects to database
        DatabaseAccess objDb = new DatabaseAccess();
        objDb.setDbName("BusinessFinancials");
        objDb.setDbConn();
        myDbConn = objDb.getDbConn();
        Object[][] data;

        //Inserts all the cashflow data based on a certain amount of time
        for (int i = 0; i < cashFlowData.size(); i = i + 6)
        {
            String cashFlowQuery = "INSERT INTO FreeCashFlow VALUES (?,?,?,?,?,?)";

            //Uses query to add data to database
            try
            {
                PreparedStatement ps = myDbConn.prepareStatement(cashFlowQuery);
                ps.setInt(1, (int) Double.parseDouble(cashFlowData.get(i).toString()));
                ps.setDouble(2, cashFlowData.get(i + 1));
                ps.setDouble(3, cashFlowData.get(i + 2));
                ps.setDouble(4, cashFlowData.get(i + 3));
                ps.setDouble(5, cashFlowData.get(i + 4));
                ps.setDouble(6, cashFlowData.get(i + 5));
                ps.executeUpdate();
            }
            catch (SQLException se)
            {
//                System.out.println("Error inserting data");
//                se.printStackTrace(System.err);
            }
            catch (ArrayIndexOutOfBoundsException abe)
            {
//                System.out.println("There is no cash flow data");
            }
        }
    }

    //Removes all the data associated with a business
    private void removeValueData(String businessName)
    {
        Connection myDbConn = null;

        int employeeAmount = 0;
        int businessYears = 0;
        int countriesAmount = 0;
        int competitorsAmount = 0;

        double actualDebt = 0;
        double actualEquity = 0;

        double equityWeight = 0;
        double debtWeight = 0;

        double equityCost = 0;
        double debtCost = 0;

        int riskFactor = 0;

        double depreciation = 0;
        double capitalExpenditure = 0;
        double revenue = 0;
        double costs = 0;
        double profits = 0;
        double workingCapital = 0;
        double freeCashFlow = 0;

        String assetName = "";
        String liabilityName = "";
//        ArrayList<String> assetNames = new ArrayList<>();
//        ArrayList<String> liabilityNames = new ArrayList<>();

        double wacc = 0;


        int otherEmployeeAmount = 0;
        int otherBusinessYears = 0;
        int otherCountriesAmount = 0;
        int otherCompetitorsAmount = 0;

        double otherActualDebt = 0;
        double otherActualEquity = 0;

        double otherEquityWeight = 0;
        double otherDebtWeight = 0;

        double otherEquityCost = 0;
        double otherDebtCost = 0;

        int otherRiskFactor = 0;

        double otherDepreciation = 0;
        double otherCapitalExpenditure = 0;
        double otherRevenue = 0;
        double otherCosts = 0;
        double otherProfits = 0;
        double otherWorkingCapital = 0;
        double otherCashFlow = 0;

        String otherAssetName = "";
        String otherLiabilityName = "";
//        ArrayList<String> assetNames = new ArrayList<>();
//        ArrayList<String> liabilityNames = new ArrayList<>();

        double otherWacc = 0;

        boolean duplicateRiskData = false;
        ArrayList<Boolean> duplicateProfitsData = new ArrayList<>();
        ArrayList<Boolean> duplicateWorkingCapital = new ArrayList<>();
        ArrayList<Boolean> duplicateCashFlow = new ArrayList<>();
        boolean duplicateWeightVariables = false;
        boolean duplicateEquityCost = false;
        boolean duplicateWaccData = false;
        ArrayList<Boolean> duplicatePresentValue = new ArrayList<>();

        ArrayList<String> registeredBusinesses = new ArrayList<>();
        String currentBusinessName;

        //Creates object of database and connects to database
        DatabaseAccess objDb = new DatabaseAccess();
        objDb.setDbName("BusinessFinancials");
        objDb.setDbConn();
        myDbConn = objDb.getDbConn();

        String[] nonTimeHeader =
        {
            "Email", "BusinessName",
            "ActualEquity", "ActualDebt", "DebtCost", "EmployeeAmount",
            "BusinessYears", "CountriesAmount", "CompetitorsAmount"
        };

        Object[][] nonTimeData;

        nonTimeData = objDb.to2dArray(objDb.getData("NonTimeFinancials", nonTimeHeader));

        //Goes through non time data of the business
        //Goes through specific data and checks if data matches
        for (int i = 0; i < nonTimeData.length; i++)
        {
            //Checks if the email and businessName match to the data
            if (email.equalsIgnoreCase(nonTimeData[i][0].toString())
                    && businessName.equalsIgnoreCase(nonTimeData[i][1].toString()))
            {
                actualEquity = Double.parseDouble(nonTimeData[i][2].toString());
                actualDebt = Double.parseDouble(nonTimeData[i][3].toString());
                debtCost = Double.parseDouble(nonTimeData[i][4].toString());

                employeeAmount = Integer.parseInt(nonTimeData[i][5].toString());
                businessYears = Integer.parseInt(nonTimeData[i][6].toString());
                countriesAmount = Integer.parseInt(nonTimeData[i][7].toString());
                competitorsAmount = Integer.parseInt(nonTimeData[i][8].toString());
            }
        }

        String[] riskHeader =
        {
            "EmployeeAmount", "BusinessYears",
            "CountriesAmount", "CompetitorsAmount", "RiskFactor"
        };
        Object[][] riskData;

        riskData = objDb.to2dArray(objDb.getData("RiskFactor", riskHeader));

        //Goes through specific data and checks if data matches
        for (int i = 0; i < riskData.length; i++)
        {
            if (employeeAmount == Integer.parseInt(riskData[i][0].toString())
                    && businessYears == Integer.parseInt(riskData[i][1].toString())
                    && countriesAmount == Integer.parseInt(riskData[i][2].toString())
                    && competitorsAmount == Integer.parseInt(riskData[i][3].toString()))
            {
                riskFactor = (int) Double.parseDouble(riskData[i][4].toString());
            }
        }

        String[] weightVariablesHeader =
        {
            "ActualDebt", "ActualEquity",
            "DebtWeight", "EquityWeight"
        };
        Object[][] weightVariablesData;

        weightVariablesData = objDb.to2dArray(objDb.getData("WeightVariables", weightVariablesHeader));

        //Goes through specific data and checks if data matches
        for (int i = 0; i < weightVariablesData.length; i++)
        {
            if (actualDebt == Double.parseDouble(weightVariablesData[i][0].toString())
                    && actualEquity == Double.parseDouble(weightVariablesData[i][1].toString()))
            {
                equityWeight = Double.parseDouble(weightVariablesData[i][3].toString());
                debtWeight = Double.parseDouble(weightVariablesData[i][2].toString());
            }
        }

        String[] equityCostHeader =
        {
            "DebtWeight", "RiskFactor", "EquityCost"
        };
        Object[][] equityCostData;

        equityCostData = objDb.to2dArray(objDb.getData("EquityCost", equityCostHeader));

        //Goes through specific data and checks if data matches
        for (int i = 0; i < equityCostData.length; i++)
        {
            if (debtWeight == Double.parseDouble(equityCostData[i][0].toString())
                    && riskFactor == Double.parseDouble(equityCostData[i][1].toString()))
            {
                equityCost = Double.parseDouble(equityCostData[i][2].toString());
            }
        }

        String[] waccHeader =
        {
            "DebtCost", "EquityCost", "DebtWeight", "EquityWeight", "Wacc"
        };
        Object[][] waccData;

        waccData = objDb.to2dArray(objDb.getData("Wacc", waccHeader));

        //Goes through specific data and checks if data matches
        for (int i = 0; i < waccData.length; i++)
        {
            if (debtCost == Double.parseDouble(waccData[i][0].toString())
                    && equityCost == Double.parseDouble(waccData[i][1].toString())
                    && debtWeight == Double.parseDouble(waccData[i][2].toString())
                    && equityWeight == Double.parseDouble(waccData[i][3].toString()))
            {
                wacc = Double.parseDouble(waccData[i][4].toString());
            }
        }


        //Obtains all the other businesses that were registered
        try
        {
            registeredBusinesses = getBusinessNames();
        }
        catch(IndexOutOfBoundsException ibe)
        {

        }

        //Removes this existing business from the list
        registeredBusinesses.remove(businessName);

        /*This goes through each businesses info and makes sure
        that if there is info that the removed business has in common*/
        for (int i=0; i<registeredBusinesses.size(); i++)
        {
            currentBusinessName = registeredBusinesses.get(i);

            myDbConn = objDb.getDbConn();

            nonTimeData = objDb.to2dArray(objDb.getData("NonTimeFinancials", nonTimeHeader));

            //Goes through specific data and checks if data matches
            for (int j = 0; j < nonTimeData.length; j++)
            {
                if (email.equalsIgnoreCase(nonTimeData[j][0].toString()) &&
                        currentBusinessName.equalsIgnoreCase(nonTimeData[j][1].toString()))
                {
                    otherActualEquity = Double.parseDouble(nonTimeData[j][2].toString());
                    otherActualDebt = Double.parseDouble(nonTimeData[j][3].toString());
                    otherDebtCost = Double.parseDouble(nonTimeData[j][4].toString());

                    otherEmployeeAmount = Integer.parseInt(nonTimeData[j][5].toString());
                    otherBusinessYears = Integer.parseInt(nonTimeData[j][6].toString());
                    otherCountriesAmount = Integer.parseInt(nonTimeData[j][7].toString());
                    otherCompetitorsAmount = Integer.parseInt(nonTimeData[j][8].toString());
                }
            }

            riskData = objDb.to2dArray(objDb.getData("RiskFactor", riskHeader));

            //Goes through specific data and checks if data matches
            for (int j = 0; j < riskData.length; j++)
            {
                if (otherEmployeeAmount == Integer.parseInt(riskData[j][0].toString())
                        && otherBusinessYears == Integer.parseInt(riskData[j][1].toString())
                        && otherCountriesAmount == Integer.parseInt(riskData[j][2].toString())
                        && otherCompetitorsAmount == Integer.parseInt(riskData[j][3].toString()))
                {
                    otherRiskFactor = (int) Double.parseDouble(riskData[j][4].toString());
                }
            }

            weightVariablesData = objDb.to2dArray(objDb.getData("WeightVariables", weightVariablesHeader));

            //Goes through specific data and checks if data matches
            for (int j = 0; j < weightVariablesData.length; j++)
            {
                if (otherActualDebt == Double.parseDouble(weightVariablesData[j][0].toString())
                        && otherActualEquity == Double.parseDouble(weightVariablesData[j][1].toString()))
                {
                    otherEquityWeight = Double.parseDouble(weightVariablesData[j][3].toString());
                    otherDebtWeight = Double.parseDouble(weightVariablesData[j][2].toString());
                }
            }

            equityCostData = objDb.to2dArray(objDb.getData("EquityCost", equityCostHeader));

            //Goes through specific data and checks if data matches
            for (int j = 0; j < equityCostData.length; j++)
            {
                if (otherDebtWeight == Double.parseDouble(equityCostData[j][0].toString())
                        && otherRiskFactor == Double.parseDouble(equityCostData[j][1].toString()))
                {
                    otherEquityCost = Double.parseDouble(equityCostData[j][2].toString());
                }
            }

            waccData = objDb.to2dArray(objDb.getData("Wacc", waccHeader));

            //Goes through specific data and checks if data matches
            for (int j = 0; j < waccData.length; j++)
            {
                if (otherDebtCost == Double.parseDouble(waccData[j][0].toString())
                        && otherEquityCost == Double.parseDouble(waccData[j][1].toString())
                        && otherDebtWeight == Double.parseDouble(waccData[j][2].toString())
                        && otherEquityWeight == Double.parseDouble(waccData[j][3].toString()))
                {
                    otherWacc = Double.parseDouble(waccData[j][4].toString());
                }
            }

            String[] timeHeader =
            {
                "Email", "BusinessName", "Time", "CapitalExpenditure",
                "Assets", "Liabilities", "Depreciation", "Revenue", "Costs"
            };
            Object[][] timeData;

            timeData = objDb.to2dArray(objDb.getData("TimeFinancials", timeHeader));

            String[] profitHeader =
            {
                "Time", "Revenue", "Costs", "Profits"
            };
            Object[][] profitArrayData;

            profitArrayData = objDb.to2dArray(objDb.getData("Profits", profitHeader));

            String[] cashFlowHeader =
            {
                "Time", "WorkingCapital", "Depreciation",
                "Profits", "CapitalExpenditure", "FreeCashFlow"
            };
            Object[][] cashFlowData;

            cashFlowData = objDb.to2dArray(objDb.getData("FreeCashFlow", cashFlowHeader));

            String[] workingCapitalHeader =
            {
                "Time", "Assets", "Liabilities", "WorkingCapital"
            };
            Object[][] workingCapitalData;

            workingCapitalData = objDb.to2dArray(objDb.getData("WorkingCapital", workingCapitalHeader));

            String[] presentValueHeader =
            {
                "Time", "FreeCashFlow", "Wacc", "NetPresentValue"
            };
            Object[][] presentValueData;

            presentValueData = objDb.to2dArray(objDb.getData("NetPresentValue", presentValueHeader));

            /*Goes through time specific data and checks if data matches
            of other businesses than the one being removed*/
            for (int j = 1; j <= maxTime; j++)
            {
                /*Goes through specific data and checks if data matches
                of other businesses than the one being removed*/
                for (int k = 0; k < timeData.length; k++)
                {
                    if (email.equalsIgnoreCase(timeData[k][0].toString())
                            && currentBusinessName.equalsIgnoreCase(timeData[k][1].toString())
                            && j == Integer.parseInt(timeData[k][2].toString()))
                    {
                        otherCapitalExpenditure = Double.parseDouble(timeData[k][3].toString());
                        otherAssetName = timeData[k][4].toString();
                        otherLiabilityName = timeData[k][5].toString();
                        otherDepreciation = Double.parseDouble(timeData[k][6].toString());
                        otherRevenue = Double.parseDouble(timeData[k][7].toString());
                        otherCosts = Double.parseDouble(timeData[k][8].toString());
                    }
                }

                /*Goes through specific data and checks if data matches
                of other businesses than the one being removed*/
                for (int k = 0; k < profitArrayData.length; k++)
                {
                    if (j == Integer.parseInt(profitArrayData[k][0].toString())
                            && otherRevenue == Double.parseDouble(profitArrayData[k][1].toString())
                            && otherCosts == Double.parseDouble(profitArrayData[k][2].toString()))
                    {
                        otherProfits = Double.parseDouble(profitArrayData[k][3].toString());
                    }
                }

                /*Goes through specific data and checks if data matches
                of other businesses than the one being removed*/
                for (int k = 0; k < workingCapitalData.length; k++)
                {
                    if (j == Integer.parseInt(workingCapitalData[k][0].toString())
                            && otherAssetName.equalsIgnoreCase(workingCapitalData[k][1].toString())
                            && otherLiabilityName.equalsIgnoreCase(workingCapitalData[k][2].toString()))
                    {
                        otherWorkingCapital = Double.parseDouble(workingCapitalData[k][3].toString());
                    }
                }

                /*Goes through specific data and checks if data matches
                of other businesses than the one being removed*/
                for (int k = 0; k < cashFlowData.length; k++)
                {
                    if (j == Integer.parseInt(cashFlowData[k][0].toString())
                            && otherWorkingCapital == Double.parseDouble(cashFlowData[k][1].toString())
                            && otherDepreciation == Double.parseDouble(cashFlowData[k][2].toString())
                            && otherProfits == Double.parseDouble(cashFlowData[k][3].toString())
                            && otherCapitalExpenditure == Double.parseDouble(cashFlowData[k][4].toString()))
                    {
                        otherCashFlow = Double.parseDouble(cashFlowData[k][5].toString());
                    }
                }

                timeData = objDb.to2dArray(objDb.getData("TimeFinancials", timeHeader));

                profitArrayData = objDb.to2dArray(objDb.getData("Profits", profitHeader));

                cashFlowData = objDb.to2dArray(objDb.getData("FreeCashFlow", cashFlowHeader));

                workingCapitalData = objDb.to2dArray(objDb.getData("WorkingCapital", workingCapitalHeader));

                /*Goes through specific data and checks if data matches
                of business being removed*/
                for (int k = 0; k < timeData.length; k++)
                {
                    if (email.equalsIgnoreCase(timeData[k][0].toString())
                            && businessName.equalsIgnoreCase(timeData[k][1].toString())
                            && j == Integer.parseInt(timeData[k][2].toString()))
                    {
                        capitalExpenditure = Double.parseDouble(timeData[k][3].toString());
                        assetName = timeData[k][4].toString();
                        liabilityName = timeData[k][5].toString();
                        depreciation = Double.parseDouble(timeData[k][6].toString());
                        revenue = Double.parseDouble(timeData[k][7].toString());
                        costs = Double.parseDouble(timeData[k][8].toString());
                    }
                }

                /*Goes through specific data and checks if data matches
                of business being removed*/
                for (int k = 0; k < profitArrayData.length; k++)
                {
                    if (j == Integer.parseInt(profitArrayData[k][0].toString())
                            && revenue == Double.parseDouble(profitArrayData[k][1].toString())
                            && costs == Double.parseDouble(profitArrayData[k][2].toString()))
                    {
                        profits = Double.parseDouble(profitArrayData[k][3].toString());
                    }
                }

                /*Goes through specific data and checks if data matches
                of business being removed*/
                for (int k = 0; k < workingCapitalData.length; k++)
                {
                    if (j == Integer.parseInt(workingCapitalData[k][0].toString())
                            && assetName.equalsIgnoreCase(workingCapitalData[k][1].toString())
                            && liabilityName.equalsIgnoreCase(workingCapitalData[k][2].toString()))
                    {
                        workingCapital = Double.parseDouble(workingCapitalData[k][3].toString());
                    }
                }

                /*Goes through specific data and checks if data matches
                of business being removed*/
                for (int k = 0; k < cashFlowData.length; k++)
                {
                    if (j == Integer.parseInt(cashFlowData[k][0].toString())
                            && workingCapital == Double.parseDouble(cashFlowData[k][1].toString())
                            && depreciation == Double.parseDouble(cashFlowData[k][2].toString())
                            && profits == Double.parseDouble(cashFlowData[k][3].toString())
                            && capitalExpenditure == Double.parseDouble(cashFlowData[k][4].toString()))
                    {
                        freeCashFlow = Double.parseDouble(cashFlowData[k][5].toString());
                    }
                }

                /*Checks if time specific data of the business
                being removed matches with the data of the businesses
                not being removed*/
                if (revenue == otherRevenue && costs == otherCosts)
                {
                    duplicateProfitsData.add(true);
                }
                else
                {
                    duplicateProfitsData.add(false);
                }

                /*Checks if time specific data of the business
                being removed matches with the data of the businesses
                not being removed*/
                if (assetName.equalsIgnoreCase(otherAssetName) &&
                        liabilityName.equalsIgnoreCase(otherLiabilityName))
                {
                    duplicateWorkingCapital.add(true);
                }
                else
                {
                    duplicateWorkingCapital.add(false);
                }

                /*Checks if time specific data of the business
                being removed matches with the data of the businesses
                not being removed*/
                if (workingCapital == otherWorkingCapital &&
                        depreciation == otherDepreciation &&
                        profits == otherProfits &&
                        capitalExpenditure == otherCapitalExpenditure)
                {
                    duplicateCashFlow.add(true);
                }
                else
                {
                    duplicateCashFlow.add(false);
                }

                /*Checks if time specific data of the business
                being removed matches with the data of the businesses
                not being removed*/
                if (freeCashFlow == otherCashFlow &&
                        wacc == otherWacc)
                {
                    duplicatePresentValue.add(true);
                }
                else
                {
                    duplicatePresentValue.add(false);
                }
            }

            /*Checks if non time specific data of the business
            being removed matches with the data of the businesses
            not being removed*/
            if (employeeAmount == otherEmployeeAmount &&
                    businessYears == otherBusinessYears &&
                    countriesAmount == otherCountriesAmount &&
                    competitorsAmount == otherCompetitorsAmount)
            {
                duplicateRiskData = true;
            }
            /*Checks if time specific data of the business
            being removed matches with the data of the businesses
            not being removed*/
            if (actualDebt == otherActualDebt &&
                    actualEquity == otherActualEquity &&
                    debtWeight == otherDebtWeight &&
                    equityWeight == otherEquityWeight)
            {
                duplicateWeightVariables = true;
            }
            /*Checks if time specific data of the business
            being removed matches with the data of the businesses
            not being removed*/
            if (debtWeight == otherDebtWeight &&
                    riskFactor == otherRiskFactor)
            {
                duplicateEquityCost = true;
            }
            /*Checks if time specific data of the business
            being removed matches with the data of the businesses
            not being removed*/
            if (debtCost == otherDebtCost &&
                    equityCost == otherEquityCost &&
                    debtWeight == otherDebtWeight &&
                    equityWeight == otherEquityWeight)
            {
                duplicateWaccData = true;
            }
        }


        String nonTimeQuery = "DELETE FROM NonTimeFinancials WHERE Email = ? AND BusinessName = ?";

        //Uses query to add data to database
        try
        {
            PreparedStatement ps = myDbConn.prepareStatement(nonTimeQuery);
            ps.setString(1, email);
            ps.setString(2, businessName);
            ps.executeUpdate();
        }
        catch (SQLException se)
        {
//            System.out.println("Error inserting data");
            se.printStackTrace(System.err);
        }

        /*If the data is the same for the business being removed compared to
        the ones not being removed, it does not delete the data*/
        if (duplicateRiskData == false)
        {
            String riskQuery = "DELETE FROM RiskFactor WHERE EmployeeAmount = ? AND "
                    + "BusinessYears = ? AND CountriesAmount = ? AND CompetitorsAmount = ?";

            //Uses query to add data to database
            try
            {
                PreparedStatement ps = myDbConn.prepareStatement(riskQuery);
                ps.setInt(1, employeeAmount);
                ps.setInt(2, businessYears);
                ps.setInt(3, countriesAmount);
                ps.setInt(4, competitorsAmount);
                ps.executeUpdate();
            }
            catch (SQLException se)
            {
//                System.out.println("Error inserting data");
                se.printStackTrace(System.err);
            }
        }

        /*If the data is the same for the business being removed compared to
        the ones not being removed, it does not delete the data*/
        if (duplicateWeightVariables == false)
        {
            String weightVariablesQuery = "DELETE FROM WeightVariables "
                    + "WHERE ActualEquity = ? AND ActualDebt = ?";

            //Uses query to add data to database
            try
            {
                PreparedStatement ps = myDbConn.prepareStatement(weightVariablesQuery);
                ps.setDouble(1, actualEquity);
                ps.setDouble(2, actualDebt);
                ps.executeUpdate();
            } catch (SQLException se)
            {
//                System.out.println("Error inserting data");
                se.printStackTrace(System.err);
            }
        }

        /*If the data is the same for the business being removed compared to
        the ones not being removed, it does not delete the data*/
        if (duplicateEquityCost == false)
        {
            String equityCostQuery = "DELETE FROM EquityCost WHERE DebtWeight = ? "
                    + "AND RiskFactor = ?";

            //Uses query to add data to database
            try
            {
                PreparedStatement ps = myDbConn.prepareStatement(equityCostQuery);
                ps.setDouble(1, debtWeight);
                ps.setInt(2, riskFactor);
                ps.executeUpdate();
            } catch (SQLException se)
            {
//                System.out.println("Error inserting data");
                se.printStackTrace(System.err);
            }
        }

        /*If the data is the same for the business being removed compared to
        the ones not being removed, it does not delete the data*/
        if (duplicateWaccData == false)
        {
            String waccQuery = "DELETE FROM Wacc WHERE DebtCost = ? AND EquityCost = ? AND DebtWeight = ? "
                    + "AND EquityWeight = ?";

            //Uses query to add data to database
            try
            {
                PreparedStatement ps = myDbConn.prepareStatement(waccQuery);
                ps.setDouble(1, debtCost);
                ps.setDouble(2, equityCost);
                ps.setDouble(3, debtWeight);
                ps.setDouble(4, equityWeight);
                ps.executeUpdate();
            } catch (SQLException se)
            {
//                System.out.println("Error inserting data");
                se.printStackTrace(System.err);
            }
        }

        String[] timeHeader =
        {
            "Email", "BusinessName", "Time", "CapitalExpenditure",
            "Assets", "Liabilities", "Depreciation", "Revenue", "Costs"
        };
        Object[][] timeData;

        timeData = objDb.to2dArray(objDb.getData("TimeFinancials", timeHeader));

        String[] profitHeader =
        {
            "Time", "Revenue", "Costs", "Profits"
        };
        Object[][] profitArrayData;

        profitArrayData = objDb.to2dArray(objDb.getData("Profits", profitHeader));

        String[] cashFlowHeader =
        {
            "Time", "WorkingCapital", "Depreciation",
            "Profits", "CapitalExpenditure", "FreeCashFlow"
        };
        Object[][] cashFlowData;

        cashFlowData = objDb.to2dArray(objDb.getData("FreeCashFlow", cashFlowHeader));

        String[] workingCapitalHeader =
        {
            "Time", "Assets", "Liabilities", "WorkingCapital"
        };
        Object[][] workingCapitalData;

        workingCapitalData = objDb.to2dArray(objDb.getData("WorkingCapital", workingCapitalHeader));

        //Goes through time data
        for (int i = 1; i <= maxTime; i++)
        {
            for (int j = 0; j < timeData.length; j++)
            {
                if (email.equalsIgnoreCase(timeData[j][0].toString())
                        && businessName.equalsIgnoreCase(timeData[j][1].toString())
                        && i == Integer.parseInt(timeData[j][2].toString()))
                {
                    capitalExpenditure = Double.parseDouble(timeData[j][3].toString());
                    assetName = timeData[j][4].toString();
                    liabilityName = timeData[j][5].toString();
                    depreciation = Double.parseDouble(timeData[j][6].toString());
                    revenue = Double.parseDouble(timeData[j][7].toString());
                    costs = Double.parseDouble(timeData[j][8].toString());
                }
            }

            for (int j = 0; j < profitArrayData.length; j++)
            {
                if (i == Integer.parseInt(profitArrayData[j][0].toString())
                        && revenue == Double.parseDouble(profitArrayData[j][1].toString())
                        && costs == Double.parseDouble(profitArrayData[j][2].toString()))
                {
                    profits = Double.parseDouble(profitArrayData[j][3].toString());
                }
            }

            for (int j = 0; j < workingCapitalData.length; j++)
            {
                if (i == Integer.parseInt(workingCapitalData[j][0].toString())
                        && assetName.equalsIgnoreCase(workingCapitalData[j][1].toString())
                        && liabilityName.equalsIgnoreCase(workingCapitalData[j][2].toString()))
                {
                    workingCapital = Double.parseDouble(workingCapitalData[j][3].toString());
                }
            }

            for (int j = 0; j < cashFlowData.length; j++)
            {
                if (i == Integer.parseInt(cashFlowData[j][0].toString())
                        && workingCapital == Double.parseDouble(cashFlowData[j][1].toString())
                        && depreciation == Double.parseDouble(cashFlowData[j][2].toString())
                        && profits == Double.parseDouble(cashFlowData[j][3].toString())
                        && capitalExpenditure == Double.parseDouble(cashFlowData[j][4].toString()))
                {
                    freeCashFlow = Double.parseDouble(cashFlowData[j][5].toString());
                }
            }

            String timeFinancialsQuery = "DELETE FROM TimeFinancials "
                    + "WHERE Email = ? AND BusinessName = ? AND Time = ?";

            //Uses query to add data to database
            try
            {
                PreparedStatement ps = myDbConn.prepareStatement(timeFinancialsQuery);
                ps.setString(1, email);
                ps.setString(2, businessName);
                ps.setInt(3, i);
                ps.executeUpdate();
            }
            catch (SQLException se)
            {
//                System.out.println("Error inserting data");
                se.printStackTrace(System.err);
            }

            /*If the data is the same for the business being removed compared to
            the ones not being removed, it does not delete the data*/
            if (duplicateProfitsData.get(i-1) == false)
            {
                String profitsQuery = "DELETE FROM Profits "
                        + "WHERE Time = ? AND Revenue = ? AND Costs = ?";

                //Uses query to add data to database
                try
                {
                    PreparedStatement ps = myDbConn.prepareStatement(profitsQuery);
                    ps.setInt(1, i);
                    ps.setDouble(2, revenue);
                    ps.setDouble(3, costs);
                    ps.executeUpdate();
                }
                catch (SQLException se)
                {
//                    System.out.println("Error inserting data");
                    se.printStackTrace(System.err);
                }
            }

            /*If the data is the same for the business being removed compared to
            the ones not being removed, it does not delete the data*/
            if (duplicateCashFlow.get(i-1) == false)
            {
                String cashFlowQuery = "DELETE FROM FreeCashFlow "
                        + "WHERE Time = ? AND WorkingCapital = ? AND "
                        + "Depreciation = ? AND Profits = ? AND "
                        + "CapitalExpenditure = ?";

                //Uses query to add data to database
                try
                {
                    PreparedStatement ps = myDbConn.prepareStatement(cashFlowQuery);
                    ps.setInt(1, i);
                    ps.setDouble(2, workingCapital);
                    ps.setDouble(3, depreciation);
                    ps.setDouble(4, profits);
                    ps.setDouble(5, capitalExpenditure);
                    ps.executeUpdate();
                }
                catch (SQLException se)
                {
//                    System.out.println("Error inserting data");
                    se.printStackTrace(System.err);
                }
            }

            /*If the data is the same for the business being removed compared to
            the ones not being removed, it does not delete the data*/
            if (duplicateWorkingCapital.get(i-1) == false)
            {
                String workingCapitalQuery = "DELETE FROM WorkingCapital "
                        + "WHERE Time = ? AND Assets = ? AND "
                        + "Liabilities = ?";

                //Uses query to add data to database
                try
                {
                    PreparedStatement ps = myDbConn.prepareStatement(workingCapitalQuery);
                    ps.setInt(1, i);
                    ps.setString(2, assetName);
                    ps.setString(3, liabilityName);
                    ps.executeUpdate();
                }
                catch (SQLException se)
                {
//                    System.out.println("Error inserting data");
                    se.printStackTrace(System.err);
                }
            }

            /*If the data is the same for the business being removed compared to
            the ones not being removed, it does not delete the data*/
            if (duplicatePresentValue.get(i-1) == false)
            {
                String presentValueQuery = "DELETE FROM NetPresentValue "
                        + "WHERE Time = ? AND FreeCashFlow = ? AND "
                        + "Wacc = ?";

                //Uses query to add data to database
                try
                {
                    PreparedStatement ps = myDbConn.prepareStatement(presentValueQuery);
                    ps.setInt(1, i);
                    ps.setDouble(2, freeCashFlow);
                    ps.setDouble(3, wacc);
                    ps.executeUpdate();
                }
                catch (SQLException se)
                {
//                    System.out.println("Error inserting data");
                    se.printStackTrace(System.err);
                }
            }

            String assetQuery = "DELETE FROM Assets WHERE Email = ? AND BusinessName = ?";

            //Uses query to add data to database
            try
            {
                PreparedStatement ps = myDbConn.prepareStatement(assetQuery);
                ps.setString(1, email);
                ps.setString(2, businessName);
                ps.executeUpdate();
            } catch (SQLException se)
            {
//                System.out.println("Error inserting data");
                se.printStackTrace(System.err);
            }

            String liabilityQuery = "DELETE FROM Liabilities WHERE Email = ? AND BusinessName = ?";

            //Uses query to add data to database
            try
            {
                PreparedStatement ps = myDbConn.prepareStatement(liabilityQuery);
                ps.setString(1, email);
                ps.setString(2, businessName);
                ps.executeUpdate();
            } catch (SQLException se)
            {
//                System.out.println("Error inserting data");
                se.printStackTrace(System.err);
            }
        }

    }

    /*Checks if the business name the user
    entered already exists in their account*/
    private boolean checkBusinessName()
    {
        Connection myDbConn = null;

        //Creates object of database and connects to database
        DatabaseAccess objDb = new DatabaseAccess();
        objDb.setDbName("BusinessFinancials");
        objDb.setDbConn();
        myDbConn = objDb.getDbConn();
        String[] nonTimeHeader =
        {
            "Email", "BusinessName",
            "ActualEquity", "ActualDebt", "DebtCost", "EmployeeAmount",
            "BusinessYears", "CountriesAmount", "CompetitorsAmount"
        };
        Object[][] data;

        data = objDb.to2dArray(objDb.getData("NonTimeFinancials", nonTimeHeader));

        //Closes connection
        objDb.closeDbConn();

        //Checks if the business the user inputed in is valid
        for (int i = 0; i < data.length; i++)
        {
            if (email.equalsIgnoreCase(data[i][0].toString()))
            {
                if (businessName.equalsIgnoreCase(data[i][1].toString()))
                {
                    return true;
                }
            }
        }
        return false;
    }

    //Obtains cash flow data
    private ArrayList<Double> getFlowData()
    {
        int time;
        double workingCapital;
        double depreciation;
        double profits;
        double capitalExpenditure;
        int balanceSheetCounter = 0;

        BusinessFinancials businessObj = new BusinessFinancials();

        //Creates object of database and connects to database
        DatabaseAccess objDb = new DatabaseAccess();
        objDb.setDbName("BusinessFinancials");
        objDb.setDbConn();

        ArrayList<ArrayList<String>> data;
        Object[][] timeArrayData;

        String tableName = "TimeFinancials";
        String[] timeFinancialsHeader =
        {
            "Email", "BusinessName",
            "Time", "CapitalExpenditure", "Assets", "Liabilities",
            "Depreciation", "Revenue", "Costs"
        };

        data = objDb.getData(tableName, timeFinancialsHeader);

        timeArrayData = objDb.to2dArray(data);

        tableName = "FreeCashFlow";
        String[] tableHeaders =
        {
            "Time", "WorkingCapital", "Depreciation",
            "Profits", "CapitalExpenditure", "FreeCashFlow"
        };

        ArrayList<Double> convertedData = new ArrayList<>(12);

        data = objDb.getData(tableName, tableHeaders);

        int columnCount = data.get(0).size();

        //Goes through the data
        for (int i = 0; i < timeArrayData.length; i++)
        {
            //Checks for the data that matches with specified email and business name
            if (email.equalsIgnoreCase(timeArrayData[i][0].toString()))
            {
                if (businessName.equalsIgnoreCase(timeArrayData[i][1].toString()))
                {
                    time = Integer.parseInt(timeArrayData[i][2].toString());
                    workingCapital = businessObj.calculateWorkingCapital(
                            Double.parseDouble(balanceSheetData.get(balanceSheetCounter * 4 + 1).toString()),
                            Double.parseDouble(balanceSheetData.get(balanceSheetCounter * 4 + 3).toString()));
                    depreciation = Double.parseDouble(timeArrayData[i][6].toString());
                    profits = businessObj.profitCalculation(
                            Double.parseDouble(timeArrayData[i][7].toString()),
                            Double.parseDouble(timeArrayData[i][8].toString()));
                    capitalExpenditure = Double.parseDouble(timeArrayData[i][3].toString());

                    //Obtains cash flow data
                    for (int j = 0; j < data.size(); j++)
                    {
                        ArrayList<String> row = data.get(j);

                        for (int k = 0; k < columnCount; k = k + 6)
                        {
                            //Converts the data into an ArrayList<Double>
                            if (time == Integer.valueOf(row.get(k))
                                    && workingCapital == Double.parseDouble(row.get(k + 1))
                                    && depreciation == Double.parseDouble(row.get(k + 2))
                                    && profits == Double.parseDouble(row.get(k + 3))
                                    && capitalExpenditure == Double.parseDouble(row.get(k + 4)))
                            {
                                convertedData.add(Double.parseDouble(row.get(k)));
                                convertedData.add(Double.parseDouble(row.get(k + 1)));
                                convertedData.add(Double.parseDouble(row.get(k + 2)));
                                convertedData.add(Double.parseDouble(row.get(k + 3)));
                                convertedData.add(Double.parseDouble(row.get(k + 4)));
                                convertedData.add(Double.parseDouble(row.get(k + 5)));
                            }

                        }
                    }

                    balanceSheetCounter++;
                }
            }
        }

        return convertedData;
    }

    //Checks for duplicate balance sheet data
    private boolean checkBalanceData(String assetName,
            String liabilityName)
    {
        //Checks if the asset name or the liability name the user inputed is repeated
        for (int i = 0; i < balanceSheetData.size(); i+=4)
        {
            if (assetName.equalsIgnoreCase(balanceSheetData.get(i).toString()) ||
                    liabilityName.equalsIgnoreCase(balanceSheetData.get(i+2).toString()))
            {
                return false;
            }
        }

        return true;
    }

    //inserts balance sheet data
    private void insertBalanceData(String assetName, double assetValue,
            String liabilityName, double liabilityValue)
    {
        Connection myDbConn = null;

        //Creates object of database and connects to database
        DatabaseAccess objDb = new DatabaseAccess();
        objDb.setDbName("BusinessFinancials");
        objDb.setDbConn();
        myDbConn = objDb.getDbConn();

        Object[][] data;

        String assetsQuery = "INSERT INTO Assets VALUES (?,?,?,?)";

        //Uses query to add data to database
        try
        {
            PreparedStatement ps = myDbConn.prepareStatement(assetsQuery);
            ps.setString(1, email);
            ps.setString(2, businessName);
            ps.setString(3, assetName);
            ps.setDouble(4, assetValue);
            ps.executeUpdate();
        }
        catch (SQLException se)
        {
//            System.out.println("Error inserting data");
            se.printStackTrace(System.err);
        }

        String liabilitiesQuery = "INSERT INTO Liabilities VALUES (?,?,?,?)";

        //Uses query to add data to database
        try
        {
            PreparedStatement ps = myDbConn.prepareStatement(liabilitiesQuery);
            ps.setString(1, email);
            ps.setString(2, businessName);
            ps.setString(3, liabilityName);
            ps.setDouble(4, liabilityValue);
            ps.executeUpdate();
        }
        catch (SQLException se)
        {
//            System.out.println("Error inserting data");
            se.printStackTrace(System.err);
        }

    }

    //Obtains the risk data
    private int getRisk(String businessName)
    {
        Connection myDbConn = null;

        int employeeAmount = 0;
        int businessYears = 0;
        int countriesAmount = 0;
        int competitorsAmount = 0;

        double actualDebt = 0;
        double actualEquity = 0;

        double debtCost = 0;

        int riskFactor = 0;

        //Creates object of database and connects to database
        DatabaseAccess objDb = new DatabaseAccess();
        objDb.setDbName("BusinessFinancials");
        objDb.setDbConn();

        myDbConn = objDb.getDbConn();
        String[] nonTimeHeader =
        {
            "Email", "BusinessName",
            "ActualEquity", "ActualDebt", "DebtCost", "EmployeeAmount",
            "BusinessYears", "CountriesAmount", "CompetitorsAmount"
        };
        Object[][] nonTimeData;

        nonTimeData = objDb.to2dArray(objDb.getData("NonTimeFinancials", nonTimeHeader));

        /*Goes through specific data and checks if data matches
        of business*/
        for (int j = 0; j < nonTimeData.length; j++)
        {
            if (email.equalsIgnoreCase(nonTimeData[j][0].toString()) &&
                    businessName.equalsIgnoreCase(nonTimeData[j][1].toString()))
            {
                actualEquity = Double.parseDouble(nonTimeData[j][2].toString());
                actualDebt = Double.parseDouble(nonTimeData[j][3].toString());
                debtCost = Double.parseDouble(nonTimeData[j][4].toString());

                employeeAmount = Integer.parseInt(nonTimeData[j][5].toString());
                businessYears = Integer.parseInt(nonTimeData[j][6].toString());
                countriesAmount = Integer.parseInt(nonTimeData[j][7].toString());
                competitorsAmount = Integer.parseInt(nonTimeData[j][8].toString());
            }
        }

        String[] riskHeader =
        {
            "EmployeeAmount", "BusinessYears",
            "CountriesAmount", "CompetitorsAmount", "RiskFactor"
        };
        Object[][] riskData;

        riskData = objDb.to2dArray(objDb.getData("RiskFactor", riskHeader));

        /*Goes through specific data and checks if data matches
        of business*/
        for (int j = 0; j < riskData.length; j++)
        {
            if (employeeAmount == Integer.parseInt(riskData[j][0].toString())
                    && businessYears == Integer.parseInt(riskData[j][1].toString())
                    && countriesAmount == Integer.parseInt(riskData[j][2].toString())
                    && competitorsAmount == Integer.parseInt(riskData[j][3].toString()))
            {
                riskFactor = (int) Double.parseDouble(riskData[j][4].toString());
            }
        }

        return riskFactor;
    }
    private double getPresentValue(String businessName)
    {
        Connection myDbConn = null;

        int employeeAmount = 0;
        int businessYears = 0;
        int countriesAmount = 0;
        int competitorsAmount = 0;

        double actualDebt = 0;
        double actualEquity = 0;

        double equityWeight = 0;
        double debtWeight = 0;

        double equityCost = 0;
        double debtCost = 0;

        int riskFactor = 0;

        double depreciation = 0;
        double capitalExpenditure = 0;
        double revenue = 0;
        double costs = 0;
        double profits = 0;
        double workingCapital = 0;
        double freeCashFlow = 0;

        String assetName = "";
        String liabilityName = "";

        double wacc = 0;

        double finalPresentValue = 0;

        //Creates object of database and connects to database
        DatabaseAccess objDb = new DatabaseAccess();
        objDb.setDbName("BusinessFinancials");
        objDb.setDbConn();

        myDbConn = objDb.getDbConn();
        String[] nonTimeHeader =
        {
            "Email", "BusinessName",
            "ActualEquity", "ActualDebt", "DebtCost", "EmployeeAmount",
            "BusinessYears", "CountriesAmount", "CompetitorsAmount"
        };
        Object[][] nonTimeData;

        nonTimeData = objDb.to2dArray(objDb.getData("NonTimeFinancials", nonTimeHeader));

        /*Goes through specific data and checks if data matches
        of business*/
        for (int j = 0; j < nonTimeData.length; j++)
        {
            if (email.equalsIgnoreCase(nonTimeData[j][0].toString()) &&
                    businessName.equalsIgnoreCase(nonTimeData[j][1].toString()))
            {
                actualEquity = Double.parseDouble(nonTimeData[j][2].toString());
                actualDebt = Double.parseDouble(nonTimeData[j][3].toString());
                debtCost = Double.parseDouble(nonTimeData[j][4].toString());

                employeeAmount = Integer.parseInt(nonTimeData[j][5].toString());
                businessYears = Integer.parseInt(nonTimeData[j][6].toString());
                countriesAmount = Integer.parseInt(nonTimeData[j][7].toString());
                competitorsAmount = Integer.parseInt(nonTimeData[j][8].toString());
            }
        }

        String[] riskHeader =
        {
            "EmployeeAmount", "BusinessYears",
            "CountriesAmount", "CompetitorsAmount", "RiskFactor"
        };
        Object[][] riskData;

        riskData = objDb.to2dArray(objDb.getData("RiskFactor", riskHeader));

        /*Goes through specific data and checks if data matches
        of business*/
        for (int j = 0; j < riskData.length; j++)
        {
            if (employeeAmount == Integer.parseInt(riskData[j][0].toString())
                    && businessYears == Integer.parseInt(riskData[j][1].toString())
                    && countriesAmount == Integer.parseInt(riskData[j][2].toString())
                    && competitorsAmount == Integer.parseInt(riskData[j][3].toString()))
            {
                riskFactor = (int) Double.parseDouble(riskData[j][4].toString());
            }
        }

        String[] weightVariablesHeader =
        {
            "ActualDebt", "ActualEquity",
            "DebtWeight", "EquityWeight"
        };
        Object[][] weightVariablesData;

        weightVariablesData = objDb.to2dArray(objDb.getData("WeightVariables", weightVariablesHeader));

        /*Goes through specific data and checks if data matches
        of business*/
        for (int j = 0; j < weightVariablesData.length; j++)
        {
            if (actualDebt == Double.parseDouble(weightVariablesData[j][0].toString())
                    && actualEquity == Double.parseDouble(weightVariablesData[j][1].toString()))
            {
                equityWeight = Double.parseDouble(weightVariablesData[j][3].toString());
                debtWeight = Double.parseDouble(weightVariablesData[j][2].toString());
            }
        }

        String[] equityCostHeader =
        {
            "DebtWeight", "RiskFactor", "EquityCost"
        };
        Object[][] equityCostData;

        equityCostData = objDb.to2dArray(objDb.getData("EquityCost", equityCostHeader));

        /*Goes through specific data and checks if data matches
        of business*/
        for (int j = 0; j < equityCostData.length; j++)
        {
            if (debtWeight == Double.parseDouble(equityCostData[j][0].toString())
                    && riskFactor == Double.parseDouble(equityCostData[j][1].toString()))
            {
                equityCost = Double.parseDouble(equityCostData[j][2].toString());
            }
        }

        String[] waccHeader =
        {
            "DebtCost", "EquityCost", "DebtWeight", "EquityWeight", "Wacc"
        };
        Object[][] waccData;

        waccData = objDb.to2dArray(objDb.getData("Wacc", waccHeader));

        /*Goes through specific data and checks if data matches
        of business*/
        for (int j = 0; j < waccData.length; j++)
        {
            if (debtCost == Double.parseDouble(waccData[j][0].toString())
                    && equityCost == Double.parseDouble(waccData[j][1].toString())
                    && debtWeight == Double.parseDouble(waccData[j][2].toString())
                    && equityWeight == Double.parseDouble(waccData[j][3].toString()))
            {
                wacc = Double.parseDouble(waccData[j][4].toString());
            }
        }

        String[] timeHeader =
        {
            "Email", "BusinessName", "Time", "CapitalExpenditure",
            "Assets", "Liabilities", "Depreciation", "Revenue", "Costs"
        };
        Object[][] timeData;

        timeData = objDb.to2dArray(objDb.getData("TimeFinancials", timeHeader));

        String[] profitHeader =
        {
            "Time", "Revenue", "Costs", "Profits"
        };

        Object[][] profitArrayData;

        profitArrayData = objDb.to2dArray(objDb.getData("Profits", profitHeader));

        String[] cashFlowHeader =
        {
            "Time", "WorkingCapital", "Depreciation",
            "Profits", "CapitalExpenditure", "FreeCashFlow"
        };
        Object[][] cashFlowData;

        cashFlowData = objDb.to2dArray(objDb.getData("FreeCashFlow", cashFlowHeader));

        String[] workingCapitalHeader =
        {
            "Time", "Assets", "Liabilities", "WorkingCapital"
        };
        Object[][] workingCapitalData;

        workingCapitalData = objDb.to2dArray(objDb.getData("WorkingCapital", workingCapitalHeader));

        String[] presentValueHeader =
        {
            "Time", "FreeCashFlow", "Wacc", "NetPresentValue"
        };
        Object[][] presentValueData;

        presentValueData = objDb.to2dArray(objDb.getData("NetPresentValue", presentValueHeader));

        /*Goes through time specific data*/
        for (int j = 1; j <= maxTime; j++)
        {
            /*Goes through specific data and checks if data matches
                of business*/
            for (int k = 0; k < timeData.length; k++)
            {
                if (email.equalsIgnoreCase(timeData[k][0].toString())
                        && businessName.equalsIgnoreCase(timeData[k][1].toString())
                        && j == Integer.parseInt(timeData[k][2].toString()))
                {
                    capitalExpenditure = Double.parseDouble(timeData[k][3].toString());
                    assetName = timeData[k][4].toString();
                    liabilityName = timeData[k][5].toString();
                    depreciation = Double.parseDouble(timeData[k][6].toString());
                    revenue = Double.parseDouble(timeData[k][7].toString());
                    costs = Double.parseDouble(timeData[k][8].toString());
                }
            }

            /*Goes through specific data and checks if data matches
                of business*/
            for (int k = 0; k < profitArrayData.length; k++)
            {
                if (j == Integer.parseInt(profitArrayData[k][0].toString())
                        && revenue == Double.parseDouble(profitArrayData[k][1].toString())
                        && costs == Double.parseDouble(profitArrayData[k][2].toString()))
                {
                    profits = Double.parseDouble(profitArrayData[k][3].toString());
                }
            }

            /*Goes through specific data and checks if data matches
                of business*/
            for (int k = 0; k < workingCapitalData.length; k++)
            {
                if (j == Integer.parseInt(workingCapitalData[k][0].toString())
                        && assetName.equalsIgnoreCase(workingCapitalData[k][1].toString())
                        && liabilityName.equalsIgnoreCase(workingCapitalData[k][2].toString()))
                {
                    workingCapital = Double.parseDouble(workingCapitalData[k][3].toString());
                }
            }

            /*Goes through specific data and checks if data matches
                of business*/
            for (int k = 0; k < cashFlowData.length; k++)
            {
                if (j == Integer.parseInt(cashFlowData[k][0].toString())
                        && workingCapital == Double.parseDouble(cashFlowData[k][1].toString())
                        && depreciation == Double.parseDouble(cashFlowData[k][2].toString())
                        && profits == Double.parseDouble(cashFlowData[k][3].toString())
                        && capitalExpenditure == Double.parseDouble(cashFlowData[k][4].toString()))
                {
                    freeCashFlow = Double.parseDouble(cashFlowData[k][5].toString());
                }
            }

            /*Goes through specific data and checks if data matches
                of business*/
            for (int k = 0; k < presentValueData.length; k++)
            {
                if (j == Integer.parseInt(presentValueData[k][0].toString())
                        && freeCashFlow == Double.parseDouble(presentValueData[k][1].toString())
                        && wacc == Double.parseDouble(presentValueData[k][2].toString()))
                {
                    finalPresentValue = Double.parseDouble(presentValueData[k][3].toString());
                }
            }
        }

        return finalPresentValue;
    }

    //Compares net present values of all the businesses
    private ArrayList<String> compareValues()
    {
        int employeeAmount = 0;
        int businessYears = 0;
        int countriesAmount = 0;
        int competitorsAmount = 0;

        double actualDebt = 0;
        double actualEquity = 0;

        double equityWeight = 0;
        double debtWeight = 0;

        double equityCost = 0;
        double debtCost = 0;

        int riskFactor = 0;

        double depreciation = 0;
        double capitalExpenditure = 0;
        double revenue = 0;
        double costs = 0;
        double profits = 0;
        double workingCapital = 0;
        double freeCashFlow = 0;

        String assetName = "";
        String liabilityName = "";

        double wacc = 0;

        double bestPresentValue = Double.MIN_VALUE;

        ArrayList<String> presentValueBusinesses = new ArrayList<>();
        ArrayList<String> registeredBusinesses = new ArrayList<>();
        ArrayList<String> bestBusinessNames = new ArrayList<>();

        String currentBusinessName = "";

        try
        {
            registeredBusinesses = getBusinessNames();
        }
        catch(IndexOutOfBoundsException ibe)
        {
            return bestBusinessNames;
        }

        //Creates object of database and connects to database
        DatabaseAccess objDb = new DatabaseAccess();
        objDb.setDbName("BusinessFinancials");
        objDb.setDbConn();

        //Goes through every businesses information
        for (int i=0; i<registeredBusinesses.size(); i++)
        {
            currentBusinessName = registeredBusinesses.get(i);

            String[] nonTimeHeader =
            {
                "Email", "BusinessName",
                "ActualEquity", "ActualDebt", "DebtCost", "EmployeeAmount",
                "BusinessYears", "CountriesAmount", "CompetitorsAmount"
            };
            Object[][] nonTimeData;

            nonTimeData = objDb.to2dArray(objDb.getData("NonTimeFinancials", nonTimeHeader));

            /*Goes through specific data and checks if data matches
                of business*/
            for (int j = 0; j < nonTimeData.length; j++)
            {
                if (email.equalsIgnoreCase(nonTimeData[j][0].toString()) &&
                        currentBusinessName.equalsIgnoreCase(nonTimeData[j][1].toString()))
                {
                    actualEquity = Double.parseDouble(nonTimeData[j][2].toString());
                    actualDebt = Double.parseDouble(nonTimeData[j][3].toString());
                    debtCost = Double.parseDouble(nonTimeData[j][4].toString());

                    employeeAmount = Integer.parseInt(nonTimeData[j][5].toString());
                    businessYears = Integer.parseInt(nonTimeData[j][6].toString());
                    countriesAmount = Integer.parseInt(nonTimeData[j][7].toString());
                    competitorsAmount = Integer.parseInt(nonTimeData[j][8].toString());
                }
            }

            String[] riskHeader =
            {
                "EmployeeAmount", "BusinessYears",
                "CountriesAmount", "CompetitorsAmount", "RiskFactor"
            };
            Object[][] riskData;

            riskData = objDb.to2dArray(objDb.getData("RiskFactor", riskHeader));

            /*Goes through specific data and checks if data matches
                of business*/
            for (int j = 0; j < riskData.length; j++)
            {
                if (employeeAmount == Integer.parseInt(riskData[j][0].toString())
                        && businessYears == Integer.parseInt(riskData[j][1].toString())
                        && countriesAmount == Integer.parseInt(riskData[j][2].toString())
                        && competitorsAmount == Integer.parseInt(riskData[j][3].toString()))
                {
                    riskFactor = (int) Double.parseDouble(riskData[j][4].toString());
                }
            }

            String[] weightVariablesHeader =
            {
                "ActualDebt", "ActualEquity",
                "DebtWeight", "EquityWeight"
            };
            Object[][] weightVariablesData;

            weightVariablesData = objDb.to2dArray(objDb.getData("WeightVariables", weightVariablesHeader));

            /*Goes through specific data and checks if data matches
                of business*/
            for (int j = 0; j < weightVariablesData.length; j++)
            {
                if (actualDebt == Double.parseDouble(weightVariablesData[j][0].toString())
                        && actualEquity == Double.parseDouble(weightVariablesData[j][1].toString()))
                {
                    equityWeight = Double.parseDouble(weightVariablesData[j][3].toString());
                    debtWeight = Double.parseDouble(weightVariablesData[j][2].toString());
                }
            }

            String[] equityCostHeader =
            {
                "DebtWeight", "RiskFactor", "EquityCost"
            };
            Object[][] equityCostData;

            equityCostData = objDb.to2dArray(objDb.getData("EquityCost", equityCostHeader));

            /*Goes through specific data and checks if data matches
                of business*/
            for (int j = 0; j < equityCostData.length; j++)
            {
                if (debtWeight == Double.parseDouble(equityCostData[j][0].toString())
                        && riskFactor == Double.parseDouble(equityCostData[j][1].toString()))
                {
                    equityCost = Double.parseDouble(equityCostData[j][2].toString());
                }
            }

            String[] waccHeader =
            {
                "DebtCost", "EquityCost", "DebtWeight", "EquityWeight", "Wacc"
            };
            Object[][] waccData;

            waccData = objDb.to2dArray(objDb.getData("Wacc", waccHeader));

            /*Goes through specific data and checks if data matches
                of business*/
            for (int j = 0; j < waccData.length; j++)
            {
                if (debtCost == Double.parseDouble(waccData[j][0].toString())
                        && equityCost == Double.parseDouble(waccData[j][1].toString())
                        && debtWeight == Double.parseDouble(waccData[j][2].toString())
                        && equityWeight == Double.parseDouble(waccData[j][3].toString()))
                {
                    wacc = Double.parseDouble(waccData[j][4].toString());
                }
            }

            String[] timeHeader =
            {
                "Email", "BusinessName", "Time", "CapitalExpenditure",
                "Assets", "Liabilities", "Depreciation", "Revenue", "Costs"
            };
            Object[][] timeData;

            timeData = objDb.to2dArray(objDb.getData("TimeFinancials", timeHeader));

            String[] profitHeader =
            {
                "Time", "Revenue", "Costs", "Profits"
            };
            Object[][] profitArrayData;

            profitArrayData = objDb.to2dArray(objDb.getData("Profits", profitHeader));

            String[] cashFlowHeader =
            {
                "Time", "WorkingCapital", "Depreciation",
                "Profits", "CapitalExpenditure", "FreeCashFlow"
            };
            Object[][] cashFlowData;

            cashFlowData = objDb.to2dArray(objDb.getData("FreeCashFlow", cashFlowHeader));

            String[] workingCapitalHeader =
            {
                "Time", "Assets", "Liabilities", "WorkingCapital"
            };
            Object[][] workingCapitalData;

            workingCapitalData = objDb.to2dArray(objDb.getData("WorkingCapital", workingCapitalHeader));

            String[] presentValueHeader =
            {
                "Time", "FreeCashFlow", "Wacc", "NetPresentValue"
            };
            Object[][] presentValueData;

            presentValueData = objDb.to2dArray(objDb.getData("NetPresentValue", presentValueHeader));

            /*Goes through time specific data*/
            for (int j = 1; j <= maxTime; j++)
            {
                /*Goes through specific data and checks if data matches
                of business*/
                for (int k = 0; k < timeData.length; k++)
                {
                    if (email.equalsIgnoreCase(timeData[k][0].toString())
                            && currentBusinessName.equalsIgnoreCase(timeData[k][1].toString())
                            && j == Integer.parseInt(timeData[k][2].toString()))
                    {
                        capitalExpenditure = Double.parseDouble(timeData[k][3].toString());
                        assetName = timeData[k][4].toString();
                        liabilityName = timeData[k][5].toString();
                        depreciation = Double.parseDouble(timeData[k][6].toString());
                        revenue = Double.parseDouble(timeData[k][7].toString());
                        costs = Double.parseDouble(timeData[k][8].toString());
                    }
                }

                /*Goes through specific data and checks if data matches
                of business*/
                for (int k = 0; k < profitArrayData.length; k++)
                {
                    if (j == Integer.parseInt(profitArrayData[k][0].toString())
                            && revenue == Double.parseDouble(profitArrayData[k][1].toString())
                            && costs == Double.parseDouble(profitArrayData[k][2].toString()))
                    {
                        profits = Double.parseDouble(profitArrayData[k][3].toString());
                    }
                }

                /*Goes through specific data and checks if data matches
                of business*/
                for (int k = 0; k < workingCapitalData.length; k++)
                {
                    if (j == Integer.parseInt(workingCapitalData[k][0].toString())
                            && assetName.equalsIgnoreCase(workingCapitalData[k][1].toString())
                            && liabilityName.equalsIgnoreCase(workingCapitalData[k][2].toString()))
                    {
                        workingCapital = Double.parseDouble(workingCapitalData[k][3].toString());
                    }
                }

                /*Goes through specific data and checks if data matches
                of business*/
                for (int k = 0; k < cashFlowData.length; k++)
                {
                    if (j == Integer.parseInt(cashFlowData[k][0].toString())
                            && workingCapital == Double.parseDouble(cashFlowData[k][1].toString())
                            && depreciation == Double.parseDouble(cashFlowData[k][2].toString())
                            && profits == Double.parseDouble(cashFlowData[k][3].toString())
                            && capitalExpenditure == Double.parseDouble(cashFlowData[k][4].toString()))
                    {
                        freeCashFlow = Double.parseDouble(cashFlowData[k][5].toString());
                    }
                }

                /*Goes through specific data and checks if data matches
                of business*/
                for (int k = 0; k < presentValueData.length; k++)
                {
                    if (j == Integer.parseInt(presentValueData[k][0].toString())
                            && freeCashFlow == Double.parseDouble(presentValueData[k][1].toString())
                            && wacc == Double.parseDouble(presentValueData[k][2].toString()))
                    {
                        presentValueBusinesses.add(presentValueData[k][3].toString());
                        presentValueBusinesses.add(currentBusinessName);
                    }
                }
            }
        }

        /*Goes through present value data and obtains the greatest net present value*/
        for (int i = 0; i < presentValueBusinesses.size(); i+=2)
        {
            if (Double.parseDouble(presentValueBusinesses.get(i)) > bestPresentValue)
            {
                bestPresentValue = Double.parseDouble(presentValueBusinesses.get(i));
            }
        }

        /*Goes through present value data and obtains the
        businesses with the best present value*/
        for (int i = 0; i < presentValueBusinesses.size(); i+=2)
        {
            if (Double.parseDouble(presentValueBusinesses.get(i)) == bestPresentValue)
            {
                bestBusinessNames.add(presentValueBusinesses.get(i));
                bestBusinessNames.add(presentValueBusinesses.get(i+1));
            }

        }

        return bestBusinessNames;
    }
    //Obtain balance sheet data
    private ArrayList<Object> getBalanceData()
    {
        Connection myDbConn = null;

        //Creates object of database and connects to database
        DatabaseAccess objDb = new DatabaseAccess();
        objDb.setDbName("BusinessFinancials");
        objDb.setDbConn();
        myDbConn = objDb.getDbConn();
        String tableName = "Assets";
        String[] tableHeaders =
        {
            "Email", "BusinessName", "Assets", "AssetValue"
        };

        ArrayList<ArrayList<String>> data;

        data = objDb.getData(tableName, tableHeaders);

        ArrayList<Object> assetData = new ArrayList<>(12);

        int columnCount = data.get(0).size();

        //Obtains asset data
        for (int i = 0; i < data.size(); i++)
        {
            ArrayList<String> row = data.get(i);

            for (int j = 0; j < columnCount; j = j + 4)
            {
                if (email.equalsIgnoreCase(row.get(j)) &&
                        businessName.equalsIgnoreCase(row.get(j + 1)))
                {
                    assetData.add(row.get(j + 2));
                    assetData.add(row.get(j + 3));
                }
            }
        }

        tableName = "Liabilities";
        String[] assetHeaders =
        {
            "Email", "BusinessName", "Liabilities", "LiabilityValue"
        };

        data = objDb.getData(tableName, assetHeaders);

        ArrayList<Object> liabilityData = new ArrayList<>(12);

        columnCount = data.get(0).size();

        //Obtains liability data
        for (int i = 0; i < data.size(); i++)
        {
            ArrayList<String> row = data.get(i);

            for (int j = 0; j < columnCount; j = j + 4)
            {
                if (email.equalsIgnoreCase(row.get(j)) && businessName.equalsIgnoreCase(row.get(j + 1)))
                {
                    liabilityData.add(row.get(j + 2));
                    liabilityData.add(row.get(j + 3));
                }
            }
        }

        //Combines asset and liability data into one array list of data
        ArrayList<Object> convertedData = new ArrayList<>(12);

        for (int i = 0; i < assetData.size(); i = i + 2)
        {
            convertedData.add(assetData.get(i));
            convertedData.add(assetData.get(i + 1));
            convertedData.add(liabilityData.get(i));
            convertedData.add(liabilityData.get(i + 1));
        }

        return convertedData;
    }
    /*Obtain all the business names in alphabetical order*/
    private ArrayList<String> getBusinessNames()
    {
        ArrayList<String> registeredBusinesses = new ArrayList<>();

        Connection myDbConn = null;

        //Creates object of database and connects to database
        DatabaseAccess objDb = new DatabaseAccess();
        objDb.setDbName("BusinessFinancials");
        objDb.setDbConn();
        myDbConn = objDb.getDbConn();

        String[] nonTimeHeader =
        {
            "Email", "BusinessName",
            "ActualEquity", "ActualDebt", "DebtCost", "EmployeeAmount",
            "BusinessYears", "CountriesAmount", "CompetitorsAmount"
        };
        Object[][] nonTimeData;

        nonTimeData = objDb.to2dArray(objDb.getData("NonTimeFinancials", nonTimeHeader));

        //Obtains business names
        for (int i = 0; i < nonTimeData.length; i++)
        {
            if (email.equalsIgnoreCase(nonTimeData[i][0].toString()))
            {
                registeredBusinesses.add(nonTimeData[i][1].toString());
            }
        }

        //Sorts the business names in alphabetical order
        for (int i = 0; i < registeredBusinesses.size()-1; i++)
        {
            int best = i;

            for (int j = i+1; j < registeredBusinesses.size(); j++)
            {
                if (registeredBusinesses.get(best).compareToIgnoreCase(registeredBusinesses.get(j)) > 0)
                {
                    best = j;
                }
            }

            String temp = registeredBusinesses.get(best);
            registeredBusinesses.set(best, registeredBusinesses.get(i));
            registeredBusinesses.set(i, temp);
        }

        return registeredBusinesses;
    }
    public static void main(String[] args)
    {
        //Opens the log in frame
        Input inputObj = new Input("BRRRRRRRRRRR");
    }
}
