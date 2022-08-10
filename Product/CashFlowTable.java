/* This class creates the cash flow display frame of the program in which it displays
the cash flow table*/

//package businessinvestments;

import java.util.ArrayList;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

public class CashFlowTable extends JFrame implements ActionListener
{
    //Declares Constants
    private final java.net.URL IMAGE_URL = getClass().getResource("cashFlow.jpg");
    private final ImageIcon TABLE_IMAGE = new ImageIcon(new
        ImageIcon(IMAGE_URL).getImage().getScaledInstance(
        306,247,Image.SCALE_DEFAULT));
    private final String[] TABLE_HEADER = {"Time (Years)","WC", "Depreciation",
        "Profits", "CapEx", "FCF"};

    //Declares data array
    private Object[][] data;
    private Object[][] classData;

    //Declares Objects
    private JTable cashFlowTable;
    private JScrollPane cashFlowScroll;

    private JTableHeader header;
    private TableColumn column;

    private JLabel imageLabel;
    private JButton returnButton;

    public CashFlowTable(ArrayList<Double> cashFlowData)
    {
        //Formats the frame
        super("Cash Flow Table");
        this.setBounds(200,100,1000,700);
        this.getContentPane().setBackground(new Color(234,122,152));
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());

        //Creates object of database access
        String[] classColumn = {"Time", "WorkingCapital", "Depreciation", "Profits",
            "CapitalExpenditure", "FreeCashFlow"};
        DatabaseAccess dbObj = new DatabaseAccess("BusinessFinancials");

        /*Checks if there is data in the database and
        inserts blank data into table if there isn't*/
        try
        {
            classData = toCashArray(cashFlowData);
        }
        catch(IndexOutOfBoundsException ife)
        {
            classData = new Object[1][6];
            classData[0][0] = "";
            classData[0][1] = "";
            classData[0][2] = "";
            classData[0][3] = "";
            classData[0][4] = "";
            classData[0][5] = "";
        }

        //Closes database connection
        dbObj.closeDbConn();

        //Sets the data equal to the business financials array
        data = classData;

        //Constructs image
        imageLabel = new JLabel(TABLE_IMAGE);

        //Constructs the button
        returnButton = new JButton("Return");
        returnButton.addActionListener(this);

        //Constructs Table
        cashFlowTable = new JTable(data,TABLE_HEADER);

        //Formats Table
        cashFlowTable.setGridColor(new Color(230,52,39));
        cashFlowTable.setBackground(new Color(255,255,255));
        cashFlowTable.setFont(new Font("Arial", Font.ITALIC, 20));

        //Formats Header
        header = cashFlowTable.getTableHeader();
        header.setBackground(new Color(230,52,39));
        header.setForeground(new Color(255,255,255));
        header.setFont(new Font("Arial", Font.BOLD, 25));

        //Formats the row
        cashFlowTable.setRowHeight(25);

        //Formats Column
        column = cashFlowTable.getColumnModel().getColumn(0);
        column.setPreferredWidth(500);
        column = cashFlowTable.getColumnModel().getColumn(1);
        column.setPreferredWidth(500);
        column = cashFlowTable.getColumnModel().getColumn(2);
        column.setPreferredWidth(500);
        column = cashFlowTable.getColumnModel().getColumn(3);
        column.setPreferredWidth(500);
        column = cashFlowTable.getColumnModel().getColumn(4);
        column.setPreferredWidth(500);
        column = cashFlowTable.getColumnModel().getColumn(5);
        column.setPreferredWidth(500);

        //Constructs ScrollPane
        cashFlowScroll = new JScrollPane();
        cashFlowScroll.getViewport().add(cashFlowTable);
        cashFlowTable.setFillsViewportHeight(true);

        //Adds components to the frame
        this.add(imageLabel, BorderLayout.NORTH);
        this.add(cashFlowScroll, BorderLayout.CENTER);
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


    public static void main(String[] args)
    {
        ArrayList<Double> testArrayList = new ArrayList<>(30);

        testArrayList.add(1.0);
        testArrayList.add(2.0);
        testArrayList.add(3.0);
        testArrayList.add(4.0);
        testArrayList.add(5.0);
        testArrayList.add(6.0);

        //Opens the cash flow table frame
        CashFlowTable cashFlowObj = new CashFlowTable(testArrayList);
    }
//    public Object[][] toBalanceArray(ArrayList<Double> )
//    {
//
//    }
}
