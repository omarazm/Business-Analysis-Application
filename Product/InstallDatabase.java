/*
This class installs database
 */
//package businessinvestments;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InstallDatabase
{
    public static void main(String[] args)
    {
        String dbName = "BusinessFinancials";

        //Creates new database
        DatabaseAccess objDb = new DatabaseAccess();
        objDb.createDb(dbName);
        Connection myDbConn = null;

        objDb.setDbConn();
        myDbConn = objDb.getDbConn();

        //Creates new table
        String newTable = "CREATE TABLE ProfileInformation (Email varchar(100), Username varchar(100),"
                + " Password varchar(100), PRIMARY KEY (Email))";
        objDb.createTable(newTable, dbName);

        newTable = "CREATE TABLE SecurityQuestions (SecurityId int, SecurityQuestion varchar(100), "
                + "PRIMARY KEY (SecurityId))";
        objDb.createTable(newTable, dbName);

        String dbQuery = "INSERT INTO SecurityQuestions VALUES (?,?)";
        //Uses query to add data to database
        try
        {
            PreparedStatement ps = myDbConn.prepareStatement(dbQuery);
            ps.setInt(1, 1);
            ps.setString(2, "What is your favorite pet's name?");
            ps.executeUpdate();
        }
        catch(SQLException se)
        {
            System.out.println("Error inserting data");
            se.printStackTrace(System.err);
        }

        dbQuery = "INSERT INTO SecurityQuestions VALUES (?,?)";
        //Uses query to add data to database
        try
        {
            PreparedStatement ps = myDbConn.prepareStatement(dbQuery);
            ps.setInt(1, 2);
            ps.setString(2, "What city were you born in?");
            ps.executeUpdate();
        }
        catch(SQLException se)
        {
            System.out.println("Error inserting data");
            se.printStackTrace(System.err);
        }

        dbQuery = "INSERT INTO SecurityQuestions VALUES (?,?)";
        //Uses query to add data to database
        try
        {
            PreparedStatement ps = myDbConn.prepareStatement(dbQuery);
            ps.setInt(1, 3);
            ps.setString(2, "What is your mother's maiden name?");
            ps.executeUpdate();
        }
        catch(SQLException se)
        {
            System.out.println("Error inserting data");
            se.printStackTrace(System.err);
        }

        dbQuery = "INSERT INTO SecurityQuestions VALUES (?,?)";
        //Uses query to add data to database
        try
        {
            PreparedStatement ps = myDbConn.prepareStatement(dbQuery);
            ps.setInt(1, 4);
            ps.setString(2, "What was the name of the primary school that you attended?");
            ps.executeUpdate();
        }
        catch(SQLException se)
        {
            System.out.println("Error inserting data");
            se.printStackTrace(System.err);
        }

        dbQuery = "INSERT INTO SecurityQuestions VALUES (?,?)";
        //Uses query to add data to database
        try
        {
            PreparedStatement ps = myDbConn.prepareStatement(dbQuery);
            ps.setInt(1, 5);
            ps.setString(2, "What time of day were you born?");
            ps.executeUpdate();
        }
        catch(SQLException se)
        {
            System.out.println("Error inserting data");
            se.printStackTrace(System.err);
        }

        dbQuery = "INSERT INTO SecurityQuestions VALUES (?,?)";
        //Uses query to add data to database
        try
        {
            PreparedStatement ps = myDbConn.prepareStatement(dbQuery);
            ps.setInt(1, 6);
            ps.setString(2, "In what city did your parents meet?");
            ps.executeUpdate();
        }
        catch(SQLException se)
        {
            System.out.println("Error inserting data");
            se.printStackTrace(System.err);
        }

        newTable = "CREATE TABLE SecurityAnswers (Email varchar(100), SecurityId int, "
                + "SecurityAnswer varchar(100), PRIMARY KEY (Email, SecurityId))";
        objDb.createTable(newTable, dbName);

        newTable = "CREATE TABLE NonTimeFinancials (Email varchar(100), BusinessName varchar(100), "
                + "ActualEquity Double, ActualDebt Double, DebtCost Double, EmployeeAmount int, "
                + "BusinessYears int, CountriesAmount int, CompetitorsAmount int, "
                + "PRIMARY KEY (Email, BusinessName))";
        objDb.createTable(newTable, dbName);

        newTable = "CREATE TABLE RiskFactor (EmployeeAmount int, BusinessYears int, "
                + "CountriesAmount int, CompetitorsAmount int, RiskFactor Double, PRIMARY KEY (EmployeeAmount, "
                + "BusinessYears, CountriesAmount, CompetitorsAmount))";
        objDb.createTable(newTable, dbName);

        newTable = "CREATE TABLE TimeFinancials (Email varchar(100), BusinessName varchar(100), "
                + "Time int, CapitalExpenditure Double, Assets varchar(100), Liabilities varchar(100), "
                + "Depreciation Double, Revenue Double, Costs Double, PRIMARY KEY (Email, BusinessName, Time))";
        objDb.createTable(newTable, dbName);

        newTable = "CREATE TABLE Profits (Time int, "
                + "Revenue Double, Costs Double, Profits Double, PRIMARY KEY (Time, Revenue, Costs))";
        objDb.createTable(newTable, dbName);

        newTable = "CREATE TABLE WorkingCapital (Time int, Assets varchar(100), Liabilities varchar(100), "
                + "WorkingCapital Double, PRIMARY KEY (Time, Assets, Liabilities))";
        objDb.createTable(newTable, dbName);

        newTable = "CREATE TABLE FreeCashFlow (Time int, WorkingCapital Double, "
                + "Depreciation Double, Profits Double, CapitalExpenditure Double, "
                + "FreeCashFlow Double, PRIMARY KEY (Time, WorkingCapital, "
                + "Depreciation, Profits, CapitalExpenditure))";
        objDb.createTable(newTable, dbName);

        newTable = "CREATE TABLE WeightVariables (ActualDebt Double, "
                + "ActualEquity Double, DebtWeight Double, EquityWeight Double, "
                + "PRIMARY KEY (ActualDebt, ActualEquity))";
        objDb.createTable(newTable, dbName);

        newTable = "CREATE TABLE EquityCost (DebtWeight Double, RiskFactor Double, "
                + "EquityCost Double, PRIMARY KEY (DebtWeight, RiskFactor))";
        objDb.createTable(newTable, dbName);

        newTable = "CREATE TABLE Wacc (DebtCost Double, EquityCost Double, DebtWeight Double, "
                + "EquityWeight Double, Wacc Double, PRIMARY KEY (DebtCost, EquityCost, "
                + "DebtWeight, EquityWeight))";
        objDb.createTable(newTable, dbName);

        newTable = "CREATE TABLE NetPresentValue (Time int, FreeCashFlow Double, Wacc Double, "
                + "NetPresentValue Double, PRIMARY KEY (Time, FreeCashFlow, "
                + "Wacc))";
        objDb.createTable(newTable, dbName);

        newTable = "CREATE TABLE Assets (Email varchar(100), BusinessName varchar(100), Assets varchar(100), "
                + "AssetValue Double, PRIMARY KEY (Email, BusinessName, "
                + "Assets))";
        objDb.createTable(newTable, dbName);

        newTable = "CREATE TABLE Liabilities (Email varchar(100), BusinessName varchar(100), "
                + "Liabilities varchar(100), LiabilityValue Double, PRIMARY KEY (Email, BusinessName, "
                + "Liabilities))";
        objDb.createTable(newTable, dbName);
    }
}
