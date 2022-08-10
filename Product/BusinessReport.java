/* This class creates the business report display frame of the program in which it displays
the cash flow table, balance sheet, and other financial information*/

//package businessinvestments;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

public class BusinessReport extends JFrame implements ActionListener
{
    //Declares Constants
    private final java.net.URL IMAGE_URL = getClass().getResource("cashFlow.jpg");
    private final ImageIcon TABLE_IMAGE = new ImageIcon(new
        ImageIcon(IMAGE_URL).getImage().getScaledInstance(
        306,247,Image.SCALE_DEFAULT));
    private final String[] CASH_FLOW_HEADER = {"Time","WC", "Depreciation",
        "Profits", "CapEx", "FCF"};
    private final String[] BALANCE_SHEET_HEADER = {"Asset Name", "Asset Value",
        "Liability Name", "Liability Value"};
    private final String[] BUSINESS_HEADER = {"Business Name"};

    //Declares data array
    private Object[][] cashFlowData;

    //Declares Objects
    private JTable cashFlowTable;
    private JScrollPane cashFlowScroll;

    private JTableHeader cashFlowHeader;
    private TableColumn cashFlowColumn;

    //Declares data array
    private Object[][] balanceSheetData;

    //Declares data array
    private Object[][] businessData;

    private JTable businessTable;
    private JScrollPane businessScroll;

    private JTableHeader businessHeader;
    private TableColumn businessColumn;

    //Declares Objects
    private JTable balanceSheetTable;
    private JScrollPane balanceSheetScroll;

    private JTableHeader balanceSheetHeader;
    private TableColumn balanceSheetColumn;

    private JLabel imageLabel;
    private JButton returnButton;
    private JPanel tablePanel;
    private JPanel infoPanel;
    private JPanel imagePanel;

    private JLabel riskLabel;
    private JLabel presentValueLabel;

    private JLabel riskAnswerLabel;
    private JLabel presentValueAnswer;

    private JPanel riskPanel;
    private JPanel presentValuePanel;

    public BusinessReport(ArrayList<Double> cashFlowList,
            ArrayList<Object> balanceSheetList, String riskFactor,
            String netPresentValue)
    {
        //Formats the frame
        super("Cash Flow Table");
        this.setBounds(200,100,1200,700);
        this.getContentPane().setBackground(new Color(234,122,152));
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());

        /*Checks if there is data in the database and
        inserts blank data into table if there isn't*/
        try
        {
            cashFlowData = toCashArray(cashFlowList);
        }
        catch(IndexOutOfBoundsException ife)
        {
            cashFlowData = new Object[1][6];
            cashFlowData[0][0] = "";
            cashFlowData[0][1] = "";
            cashFlowData[0][2] = "";
            cashFlowData[0][3] = "";
            cashFlowData[0][4] = "";
            cashFlowData[0][5] = "";
        }

        /*Checks if there is data in the database and
        inserts blank data into table if there isn't*/
        try
        {
            balanceSheetData = toBalanceArray(balanceSheetList);
        }
        catch(IndexOutOfBoundsException ife)
        {
            balanceSheetData = new Object[1][6];
            balanceSheetData[0][0] = "";
            balanceSheetData[0][1] = "";
            balanceSheetData[0][2] = "";
            balanceSheetData[0][3] = "";
        }

        //Constructs Labels
        riskLabel = new JLabel("Risk: ");
        riskLabel.setFont(new Font("Arial", Font.BOLD, 18));
        riskLabel.setForeground(Color.white);
        presentValueLabel = new JLabel("Net Present Value: ");
        presentValueLabel.setFont(new Font("Arial", Font.BOLD, 18));
        presentValueLabel.setForeground(Color.white);
        riskAnswerLabel = new JLabel(riskFactor + "%");
        riskAnswerLabel.setFont(new Font("Arial", Font.BOLD, 18));
        riskAnswerLabel.setForeground(Color.white);
        presentValueAnswer = new JLabel("$" + netPresentValue);
        presentValueAnswer.setFont(new Font("Arial", Font.BOLD, 18));
        presentValueAnswer.setForeground(Color.white);

        //Constructs image
        imageLabel = new JLabel(TABLE_IMAGE);

        //Constructs Panel
        tablePanel = new JPanel();
        tablePanel.setLayout(new BoxLayout(tablePanel, BoxLayout.X_AXIS));
        tablePanel.setBackground(new Color(76, 169, 242));
        infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBackground(new Color(234,122,152));
        riskPanel = new JPanel();
        riskPanel.setLayout(new FlowLayout());
        riskPanel.setBackground(new Color(234,122,152));
        presentValuePanel = new JPanel();
        presentValuePanel.setLayout(new FlowLayout());
        presentValuePanel.setBackground(new Color(234,122,152));
        imagePanel = new JPanel();
        imagePanel.setLayout(new FlowLayout());
        imagePanel.setBackground(new Color(234,122,152));

        //Constructs the button
        returnButton = new JButton("Return");
        returnButton.addActionListener(this);

        //Constructs Tables
        cashFlowTable = new JTable(cashFlowData,CASH_FLOW_HEADER);
        balanceSheetTable = new JTable(balanceSheetData,BALANCE_SHEET_HEADER);

        //Formats Tables
        cashFlowTable.setGridColor(new Color(230,52,39));
        cashFlowTable.setBackground(new Color(255,255,255));
        cashFlowTable.setFont(new Font("Arial", Font.ITALIC, 18));

        balanceSheetTable.setGridColor(new Color(230,52,39));
        balanceSheetTable.setBackground(new Color(255,255,255));
        balanceSheetTable.setFont(new Font("Arial", Font.ITALIC, 18));

        //Formats Headers
        cashFlowHeader = cashFlowTable.getTableHeader();
        cashFlowHeader.setBackground(new Color(230,52,39));
        cashFlowHeader.setForeground(new Color(255,255,255));
        cashFlowHeader.setFont(new Font("Arial", Font.BOLD, 18));

        balanceSheetHeader = balanceSheetTable.getTableHeader();
        balanceSheetHeader.setBackground(new Color(230,52,39));
        balanceSheetHeader.setForeground(new Color(255,255,255));
        balanceSheetHeader.setFont(new Font("Arial", Font.BOLD, 18));

        //Formats the rows
        cashFlowTable.setRowHeight(25);
        balanceSheetTable.setRowHeight(25);

        //Formats Columns
        cashFlowColumn = cashFlowTable.getColumnModel().getColumn(0);
        cashFlowColumn.setPreferredWidth(500);
        cashFlowColumn = cashFlowTable.getColumnModel().getColumn(1);
        cashFlowColumn.setPreferredWidth(500);
        cashFlowColumn = cashFlowTable.getColumnModel().getColumn(2);
        cashFlowColumn.setPreferredWidth(700);
        cashFlowColumn = cashFlowTable.getColumnModel().getColumn(3);
        cashFlowColumn.setPreferredWidth(500);
        cashFlowColumn = cashFlowTable.getColumnModel().getColumn(4);
        cashFlowColumn.setPreferredWidth(500);
        cashFlowColumn = cashFlowTable.getColumnModel().getColumn(5);
        cashFlowColumn.setPreferredWidth(500);

        balanceSheetColumn = balanceSheetTable.getColumnModel().getColumn(0);
        balanceSheetColumn.setPreferredWidth(500);
        balanceSheetColumn = balanceSheetTable.getColumnModel().getColumn(1);
        balanceSheetColumn.setPreferredWidth(500);
        balanceSheetColumn = balanceSheetTable.getColumnModel().getColumn(2);
        balanceSheetColumn.setPreferredWidth(500);
        balanceSheetColumn = balanceSheetTable.getColumnModel().getColumn(3);
        balanceSheetColumn.setPreferredWidth(500);

        //Constructs ScrollPane
        cashFlowScroll = new JScrollPane();
        cashFlowScroll.getViewport().add(cashFlowTable);
        cashFlowTable.setFillsViewportHeight(true);

        balanceSheetScroll = new JScrollPane();
        balanceSheetScroll.getViewport().add(balanceSheetTable);
        balanceSheetTable.setFillsViewportHeight(true);

        //Adding components to panels
        imagePanel.add(imageLabel);

        riskPanel.add(riskLabel);
        riskPanel.add(riskAnswerLabel);

        presentValuePanel.add(presentValueLabel);
        presentValuePanel.add(presentValueAnswer);

        infoPanel.add(imagePanel);
        infoPanel.add(riskPanel);
        infoPanel.add(presentValuePanel);

        tablePanel.add(cashFlowScroll);
        tablePanel.add(balanceSheetScroll);

        //Adds components to the frame
        this.add(infoPanel, BorderLayout.NORTH);
        this.add(tablePanel, BorderLayout.CENTER);
        this.add(returnButton, BorderLayout.SOUTH);

        //Makes Frame Visible
        this.setVisible(true);
    }
    public BusinessReport(ArrayList<String> businessList)
    {
        //Formats the frame
        super("Cash Flow Table");
        this.setBounds(200,100,1000,700);
        this.getContentPane().setBackground(new Color(234,122,152));
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());

        /*Checks if there is data in the database and
        inserts blank data into table if there isn't*/
        try
        {
            businessData = toBusinessArray(businessList);
        }
        catch(IndexOutOfBoundsException ife)
        {
            businessData = new Object[1][1];
            businessData[0][0] = "";
        }

        //Constructs image
        imageLabel = new JLabel(TABLE_IMAGE);

        //Constructs Panel
        tablePanel = new JPanel();
        tablePanel.setLayout(new BoxLayout(tablePanel, BoxLayout.X_AXIS));
        tablePanel.setBackground(new Color(76, 169, 242));

        //Constructs the button
        returnButton = new JButton("Return");
        returnButton.addActionListener(this);

        //Constructs Tables
        businessTable = new JTable(businessData, BUSINESS_HEADER);

        //Formats Tables
        businessTable.setGridColor(new Color(230,52,39));
        businessTable.setBackground(new Color(255,255,255));
        businessTable.setFont(new Font("Arial", Font.ITALIC, 18));

        //Formats Headers
        businessHeader = businessTable.getTableHeader();
        businessHeader.setBackground(new Color(230,52,39));
        businessHeader.setForeground(new Color(255,255,255));
        businessHeader.setFont(new Font("Arial", Font.BOLD, 18));

        //Formats the rows
        businessTable.setRowHeight(25);

        //Formats Columns
        businessColumn = businessTable.getColumnModel().getColumn(0);
        businessColumn.setPreferredWidth(500);

        //Constructs ScrollPane
        businessScroll = new JScrollPane();
        businessScroll.getViewport().add(businessTable);
        businessTable.setFillsViewportHeight(true);

        tablePanel.add(businessScroll);

        //Adds components to the frame
        this.add(imageLabel, BorderLayout.NORTH);
        this.add(tablePanel, BorderLayout.CENTER);
        this.add(returnButton, BorderLayout.SOUTH);

        //Makes Frame Visible
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        String command = e.getActionCommand();

         //Returns to registration frame
         if (command.equals("Return"))
         {
             //Disposes this frame
             this.dispose();
         }
    }
    public Object[][] toCashArray(ArrayList<Double> arrayList)
    {
        int cashColumnCount = 6;
        int counter = 0;
        Object[][] array = new Object[arrayList.size()/cashColumnCount][cashColumnCount];

        //Converts arraylist to array
        for (int i=0; i<arrayList.size()/cashColumnCount; i++)
        {
            for (int j=0; j<cashColumnCount; j++)
            {
                array[i][j] = arrayList.get(counter);

                counter++;
            }
        }

        return array;
    }
    public Object[][] toBalanceArray(ArrayList<Object> arrayList)
    {
        int balanceColumnCount = 4;
        int counter = 0;
        Object[][] array = new Object[arrayList.size()/balanceColumnCount][balanceColumnCount];

        //Converts arraylist to array
        for (int i=0; i<arrayList.size()/balanceColumnCount; i++)
        {
            for (int j=0; j<balanceColumnCount; j++)
            {
                array[i][j] = arrayList.get(counter);

                counter++;
            }
        }

        return array;
    }
    public Object[][] toBusinessArray(ArrayList<String> arrayList)
    {
        int businessColumnCount = 1;
        int counter = 0;
        Object[][] array = new Object[arrayList.size()/businessColumnCount][businessColumnCount];

        //Converts arraylist to array
        for (int i=0; i<arrayList.size()/businessColumnCount; i++)
        {
            for (int j=0; j<businessColumnCount; j++)
            {
                array[i][j] = arrayList.get(counter);

                counter++;
            }
        }

        return array;
    }
    public static void main(String[] args)
    {
        ArrayList<Double> testArrayList = new ArrayList<>(30);

        testArrayList.add(1.0);

        ArrayList<Object> testArrayList2 = new ArrayList<>(30);

        testArrayList2.add("OK");

        //Opens the cash flow table frame
        BusinessReport businessReportObj = new BusinessReport(testArrayList, testArrayList2, "", "");
    }

}
