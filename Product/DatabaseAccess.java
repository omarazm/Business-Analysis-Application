/*
This class allows for other classes to access data from the database
 */
//package businessinvestments;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DatabaseAccess
{
    String dbName;
    Connection dbConn;
    ArrayList <ArrayList<String>> data;

    public DatabaseAccess()
    {
        dbName = "";
        dbConn = null;
        data = null;
    }
    public DatabaseAccess(String dbName)
    {
        setDbName(dbName);
        setDbConn();
        data = null;
    }

    //Sets database name
    public void setDbName(String dbName)
    {
        this.dbName = dbName;
    }

    /*
    Create connection URL
    Create Class.forName
    Connect to driver
    */

    //Sets database connection
    public void setDbConn()
    {
        String connectionURL = "jdbc:derby:" + this.dbName;
        this.dbConn = null;

        //find the driver and make connection
        try
        {
           Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
           this.dbConn = DriverManager.getConnection(connectionURL);
        }
        catch (ClassNotFoundException cnfe)
        {
           System.out.println("Class for name not found!");
           cnfe.printStackTrace(System.err);
        }
        catch (SQLException se)
        {
            System.out.println("SQL Connection Error!");
            se.printStackTrace(System.err);
        }
    }
    //Sets data
    public void setData(ArrayList<ArrayList<String>> data)
    {
        this.data = data;
    }
    //gets database name
    public String getDbName()
    {
        return this.dbName;
    }
    //gets database connection
    public Connection getDbConn()
    {
        return this.dbConn;
    }

    /*
    Obtain tableName
    Obtain tableHeaders

    Create query of SELECT

    try
        executingQuery

        while (there are rows of data)
            Obtain data from row
    catch
        Error

    return data
    */

    //gets data from database
    public ArrayList<ArrayList<String>> getData(String tableName, String[] tableHeaders)
    {
        int columnCount = tableHeaders.length;
        Statement s = null;
        ResultSet rs = null;
        String dbQuery = "SELECT * FROM " + tableName;
        this.data = new ArrayList<>();

        //Reads data
        try
        {
            //Send the query and recieve data
            s = this.dbConn.createStatement();
            rs = s.executeQuery(dbQuery);

            //Read the data using rs and store in ArrayList Data
            while (rs.next())
            {
                ArrayList<String> row = new ArrayList<>();
                for (int i=0; i<columnCount; i++)
                {
                    row.add(rs.getString(tableHeaders[i]));
                }
                this.data.add(row);
            }
        }
        catch (SQLException se)
        {
              System.out.println("SQL Error: Not able to get data");
              se.printStackTrace(System.err);
        }
        return this.data;
    }

    /*
    Obtain databaseName

    Set databaseName
    Create connection URL w createDatabase = trueß

    try
        Connecting to driver
    catch
        Error
    */

    //Creates database
    public void createDb(String newDbName)
    {
        setDbName(newDbName);
        String connectionURL = "jdbc:derby:" + this.dbName + ";create=true";
        this.dbConn = null;

        //find the driver and make connection
        try
        {
           Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
           this.dbConn = DriverManager.getConnection(connectionURL);
           System.out.println("New Database " + this.dbName + " created!");
        }
        catch (ClassNotFoundException cnfe)
        {
           System.out.println("Class for name not found!");
           cnfe.printStackTrace(System.err);
        }
        catch (SQLException se)
        {
            System.out.println("SQL Connection Error!");
            se.printStackTrace(System.err);
        }
    }
    @Override
    public String toString()
    {
        return "JavaDatabase{" + "dbName=" + dbName + ", dbConn=" +
                dbConn + ", data=" + data + '}';
    }

    /*
    Obtain tableName and databaseName

    Set databaseName
    Set databaseConnection

    try
        Create new Table
    catch
        Error
    */

    //Creates table in database
    public void createTable(String newTable, String dName)
    {
        System.out.println(newTable);
        setDbName(dbName);
        setDbConn();
        Statement s;

        try
        {
            s = this.dbConn.createStatement();
            s.execute(newTable);
            System.out.println("New Table Created!");
        }
        catch (SQLException se)
        {
            System.out.println("SQL Connection Error!");
            se.printStackTrace(System.err);
        }
    }
    //Closes database connection
    public void closeDbConn()
    {
        try
        {
            this.dbConn.close();
        }
        catch (Exception err)
        {
            System.out.println("DB closing error.");
            System.exit(0);
        }
    }

    /*
    Obtain data

    Obtain columnCount from data
    create dataList with dataSize and columnCount

    for(dataSize is less than maxDataSize)
        set row = data

        for(columnCount is less than maxColumnCount)
        set dataList = row

    return dataList
    */

    //Converts data to 2d array
    public Object[][] to2dArray(ArrayList<ArrayList<String>> data)
    {
        int columnCount = data.get(0).size();
        Object[][] dataList = new Object[data.size()][columnCount];

        for (int i=0; i<data.size(); i++)
        {
            ArrayList<String> row = data.get(i);

            for (int j=0; j<columnCount; j++)
            {
                dataList[i][j] = row.get(j);
            }
        }

        return dataList;
    }

    //Declares main method of database access
    public static void main(String[] args)
    {
        String dbName = "BusinessFinancials";

        //Creates object of database access
        String[] columnName = {"Email", "UserName", "Password"};
        DatabaseAccess objDb2 = new DatabaseAccess(dbName);
        Connection myDbConn = null;

        //Gets connection
        myDbConn = objDb2.getDbConn();

        String email = "abc@gmail.com";
        String username = "Abc";
        String password = "ABC";

//        String dbQuery1 = "INSERT INTO TimeFinancials VALUES (?,?,?)";
//
//        try
//        {
//            PreparedStatement ps = myDbConn.prepareStatement(dbQuery1);
//            ps.setString(1, email);
//            ps.setString(2, username);
//            ps.setString(3, password);
//            ps.executeUpdate();
//            System.out.println("Data inserted succesfully");
//        }
//        catch(SQLException se)
//        {
//            System.out.println("Error inserting data");
//            se.printStackTrace(System.err);
//        }
//
        //gets data
        ArrayList<ArrayList<String>> myData = objDb2.getData("ProfileInformation", columnName);

        //converts data to 2d array
        Object[][] testData = objDb2.to2dArray(myData);

        //displays data
        for (int i=0; i<testData.length; i++)
        {
            for (int j=0; j<testData[i].length; j++)
            {
                System.out.println(testData[i][j]);
            }
        }
//
    }
}
