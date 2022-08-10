/*
This manages log in
information for the log in class
 */
//package businessinvestments;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LogInManager
{
    private String userName;
    private String email;
    private String password;
    private String securityQuestion;
    private int securityQuestionId;
    private String securityAnswer;

    public LogInManager()
    {
        userName = "N/a";
        email = "N/a";
        password = "N/a";
        securityQuestion = "N/a";
        securityQuestionId = 0;
        securityAnswer = "N/a";
    }
    public LogInManager(String pUserName, String pEmail, String pPassword, String pSecurityQuestion,
             int pSecurityQuestionId, String pSecurityAnswer)
     {
        userName = pUserName;
        email = pEmail;
        password = pPassword;
        securityQuestion = pSecurityQuestion;
        securityQuestionId = pSecurityQuestionId;
        securityAnswer = pSecurityAnswer;
     }
     //sets info
     public void setUserName(String pUserName)
     {
         this.userName = pUserName;
     }
     //sets info
     public void setEmail(String pEmail)
     {
         this.email = pEmail;
     }
     //sets info
     public void setPassword(String pPassword)
     {
         this.password = pPassword;
     }
     //sets info
     public void setSecurityQuestion(String pSecurityQuestion)
     {
         this.securityQuestion = pSecurityQuestion;
     }
     //sets info
     public void setSecurityQuestionId(int pSecurityQuestionId)
     {
         this.securityQuestionId = pSecurityQuestionId;
     }
     //sets info
     public void setSecurityAnswer(String pSecurityAnswer)
     {
         this.securityAnswer = pSecurityAnswer;
     }
     //gets info
     public String getUserName()
     {
         return this.userName;
     }
     //gets info
     public String getEmail()
     {
         return this.email;
     }
     //gets info
     public String getPassword()
     {
         return this.password;
     }
     //gets info
     public String getSecurityQuestion()
     {
         return this.securityQuestion;
     }
     //gets info
     public int getSecurityQuestionId()
     {
         return this.securityQuestionId;
     }
     //gets info
     public String getSecurityAnswer()
     {
         return this.securityAnswer;
     }
     //gets finds email with specified userName and password
     public String findEmail(String userName, String password)
     {
         Connection myDbConn = null;

        //Creates object of database and connects to database
        DatabaseAccess objDb = new DatabaseAccess();
        objDb.setDbName("BusinessFinancials");
        objDb.setDbConn();
        myDbConn = objDb.getDbConn();
        String[] profileColumnName = {"Email", "Username", "Password"};
        Object[][] data;

        try
        {
            data = objDb.to2dArray(objDb.getData("ProfileInformation", profileColumnName));

            //Closes connection
            objDb.closeDbConn();

            //Finds email corresponding to given userName
            for (int i=0; i<data.length; i++)
            {
                for (int j=1; j<data[i].length; j+=3)
                {
                    if (userName.equalsIgnoreCase(data[i][j].toString()))
                    {
                         return data[i][j-1].toString();
                    }
                }
            }

            return "N/a";
        }
        catch(IndexOutOfBoundsException ibe)
        {
            return "N/a";
        }

     }
     //Checkes if the email already exists
     public boolean checkDuplicateEmail(String givenEmail)
     {
        Connection myDbConn = null;

        //Creates object of database and connects to database
        DatabaseAccess objDb = new DatabaseAccess();
        objDb.setDbName("BusinessFinancials");
        objDb.setDbConn();
        myDbConn = objDb.getDbConn();
        String[] profileColumnName = {"Email", "Username", "Password"};
        Object[][] data;
        try
        {
            data = objDb.to2dArray(objDb.getData("ProfileInformation", profileColumnName));

            //Closes connection
            objDb.closeDbConn();

            //Checks for a duplicate email
            for (int i=0; i<data.length; i++)
            {
                for (int j=0; j<data[i].length; j+=3)
                {
                    if (givenEmail.equalsIgnoreCase(data[i][j].toString()))
                    {
                         return true;
                    }
                }
            }
            return false;
        }
        catch(IndexOutOfBoundsException iobe)
        {
            return false;
        }
        catch(NullPointerException npe)
        {
            return false;
        }
    }
     //Checks if the userName already exists
    public boolean checkDuplicateUserName(String givenUserName)
    {
        Connection myDbConn = null;

        //Creates object of database and connects to database
        DatabaseAccess objDb = new DatabaseAccess();
        objDb.setDbName("BusinessFinancials");
        objDb.setDbConn();
        myDbConn = objDb.getDbConn();
        String[] profileColumnName = {"Email", "Username", "Password"};
        Object[][] data;
        try
        {
            data = objDb.to2dArray(objDb.getData("ProfileInformation", profileColumnName));

            //Closes connection
            objDb.closeDbConn();

            //Checks for a duplicate email
            for (int i=0; i<data.length; i++)
            {
                for (int j=1; j<data[i].length; j+=3)
                {
                    if (givenUserName.equalsIgnoreCase(data[i][j].toString()))
                    {
                         return true;
                    }
                }
            }
            return false;
        }
        catch(IndexOutOfBoundsException iobe)
        {
            return false;
        }
        catch(NullPointerException npe)
        {
            return false;
        }
    }
    //Inserts profile/account information
    public void insertProfileInformation(String username, String password, String email,
            String questionChoice1, String questionChoice2, String questionChoice3,
            String answerChoice1, String answerChoice2, String answerChoice3)
    {
        Connection myDbConn = null;

        //Creates object of database and connects to database
        DatabaseAccess objDb = new DatabaseAccess();
        objDb.setDbName("BusinessFinancials");
        objDb.setDbConn();
        myDbConn = objDb.getDbConn();
        String[] questionsColumnName = {"SecurityId", "SecurityQuestion"};
        Object[][] data;

        int questionId1 = 0;
        int questionId2 = 0;
        int questionId3 = 0;


        String dbQueryProfile = "INSERT INTO ProfileInformation VALUES (?,?,?)";

        //Uses query to add data to database
        try
        {
            PreparedStatement ps = myDbConn.prepareStatement(dbQueryProfile);
            ps.setString(1, email);
            ps.setString(2, username);
            ps.setString(3, password);
            ps.executeUpdate();
        }
        catch(SQLException se)
        {
//            System.out.println("Error inserting data");
            se.printStackTrace(System.err);
        }


        data = objDb.to2dArray(objDb.getData("SecurityQuestions", questionsColumnName));

        //Finds question Id's
        for (int i=0; i<data.length; i++)
        {
            for (int j=0; j<data[i].length; j++)
            {
                if (questionChoice1.equals(data[i][j].toString()))
                {
                    questionId1 = Integer.parseInt(data[i][j-1].toString());
                }
                else if (questionChoice2.equals(data[i][j].toString()))
                {
                    questionId2 = Integer.parseInt(data[i][j-1].toString());
                }
                else if (questionChoice3.equals(data[i][j].toString()))
                {
                    questionId3 = Integer.parseInt(data[i][j-1].toString());
                }
            }
        }

        String dbQueryAnswers = "INSERT INTO SecurityAnswers VALUES (?,?,?)";

        //Uses query to add data to database
        try
        {
            PreparedStatement ps = myDbConn.prepareStatement(dbQueryAnswers);
            ps.setString(1, email);
            ps.setInt(2, questionId1);
            ps.setString(3, answerChoice1);
            ps.executeUpdate();

            ps = myDbConn.prepareStatement(dbQueryAnswers);
            ps.setString(1, email);
            ps.setInt(2, questionId2);
            ps.setString(3, answerChoice2);
            ps.executeUpdate();

            ps = myDbConn.prepareStatement(dbQueryAnswers);
            ps.setString(1, email);
            ps.setInt(2, questionId3);
            ps.setString(3, answerChoice3);
            ps.executeUpdate();
        }
        catch(SQLException se)
        {
//            System.out.println("Error inserting data");
            se.printStackTrace(System.err);
        }

        //Closes connection
        objDb.closeDbConn();
    }
    //Checks if the email inputed is valid for resetting password
    public boolean checkValidEmail(String email)
    {
        Connection myDbConn = null;

        //Creates object of database and connects to database
        DatabaseAccess objDb = new DatabaseAccess();
        objDb.setDbName("BusinessFinancials");
        objDb.setDbConn();
        myDbConn = objDb.getDbConn();
        String[] profileColumnName = {"Email", "Username", "Password"};
        Object[][] data;

        try
        {
            data = objDb.to2dArray(objDb.getData("ProfileInformation", profileColumnName));
        }
        catch (IndexOutOfBoundsException ibe)
        {
            return false;
        }

        //Closes connection
        objDb.closeDbConn();

        //Checks if the email the user inputed in is valid
        for (int i=0; i<data.length; i++)
        {
            if (email.equalsIgnoreCase(data[i][0].toString()))
            {
                 return true;
            }
        }
        return false;
    }
    //Checks if the log in information is correct
    public boolean checkLogIn(String userName, String password)
    {
        Connection myDbConn = null;

        //Creates object of database and connects to database
        DatabaseAccess objDb = new DatabaseAccess();
        objDb.setDbName("BusinessFinancials");
        objDb.setDbConn();
        myDbConn = objDb.getDbConn();
        String[] profileColumnName = {"Email", "Username", "Password"};
        Object[][] data;

        try
        {
            data = objDb.to2dArray(objDb.getData("ProfileInformation", profileColumnName));
        }
        catch(IndexOutOfBoundsException ibe)
        {
            return false;
        }

        //Closes connection
        objDb.closeDbConn();

        //Checks if user log in is valid
        for (int i=0; i<data.length; i++)
        {
            if (userName.equalsIgnoreCase(data[i][1].toString()) &&
                    password.equals(data[i][2].toString()))
            {
                 return true;
            }
        }
        return false;
    }
    //Sets new password
    public boolean setNewPassword(String email, String securityQuestion1,
            String securityQuestion2, String securityQuestion3, String answer1,
            String answer2, String answer3, String newPassword)
    {
        Connection myDbConn = null;

        int counter = 0;

        String question1 = securityQuestion1.trim();
        String question2 = securityQuestion2.trim();
        String question3 = securityQuestion3.trim();

        int questionId1 = 0;
        int questionId2 = 0;
        int questionId3 = 0;

        //Creates object of database and connects to database
        DatabaseAccess objDb = new DatabaseAccess();
        objDb.setDbName("BusinessFinancials");
        objDb.setDbConn();
        myDbConn = objDb.getDbConn();
        String[] questionColumnName = {"SecurityId", "SecurityQuestion"};
        Object[][] data;

        data = objDb.to2dArray(objDb.getData("SecurityQuestions", questionColumnName));

        //Finds the question ids for the email inputed
        for (int i=0; i<data.length; i++)
        {
            if (question1.equals(data[i][1].toString()))
            {
                questionId1 = Integer.parseInt(data[i][0].toString());
            }
            else if (question2.equals(data[i][1].toString()))
            {
                questionId2 = Integer.parseInt(data[i][0].toString());
            }
            else if (question3.equals(data[i][1].toString()))
            {
                questionId3 = Integer.parseInt(data[i][0].toString());
            }
        }

        String[] securityColumnName = {"Email", "SecurityId", "SecurityAnswer"};

        data = objDb.to2dArray(objDb.getData("SecurityAnswers", securityColumnName));

        //Finds the question ids for the email inputed
        for (int i=0; i<data.length; i++)
        {
            if (email.equalsIgnoreCase(data[i][0].toString()))
            {
                if (questionId1 == Integer.parseInt(data[i][1].toString()))
                {
                    //Checks if user's answers match the answers on the account
                    if (answer1.equalsIgnoreCase(data[i][2].toString()))
                    {
                        counter++;
                    }
                }
                else if (questionId2 == Integer.parseInt(data[i][1].toString()))
                {
                    //Checks if user's answers match the answers on the account
                    if (answer2.equalsIgnoreCase(data[i][2].toString()))
                    {
                        counter++;
                    }
                }
                else if (questionId3 == Integer.parseInt(data[i][1].toString()))
                {
                    //Checks if user's answers match the answers on the account
                    if (answer3.equalsIgnoreCase(data[i][2].toString()))
                    {
                        counter++;
                    }
                }
            }
        }

        /*Checks whether or not the user
        answered all three security questions correctly*/
        if (counter == 3)
        {
            String profileUpdateQuery = "UPDATE ProfileInformation SET Password = ?"
                + "WHERE Email = ?";

            try
            {
                PreparedStatement ps = myDbConn.prepareStatement(profileUpdateQuery);
                ps.setString(1, newPassword);
                ps.setString(2, email);
                ps.executeUpdate();
            }
            catch(SQLException se)
            {
                se.printStackTrace(System.err);
            }
            return true;
        }
        else
        {
            return false;
        }
    }
}
