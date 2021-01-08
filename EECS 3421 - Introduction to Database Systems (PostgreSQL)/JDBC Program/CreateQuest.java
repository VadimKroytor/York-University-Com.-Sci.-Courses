
import java.util.*;
import java.net.*;
import java.text.*;
import java.lang.*;
import java.io.*;
import java.sql.*;

import pgpass.*;

public class CreateQuest {
    /*
    must use -cp command when running code on PRISM


    To test the code:
       javac -cp '/eecs/dept/course/2020-21/F/3421A/PG/*:/eecs/dept/course/2020-21/F/3421A/PG/:.:' CreateQuest.java
       java -cp '/eecs/dept/course/2020-21/F/3421A/PG/*:/eecs/dept/course/2020-21/F/3421A/PG/:.:' CreateQuest '2020-12-07' Buffalo 'Biscuit Party' 20000
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

       java -cp '/eecs/dept/course/2020-21/F/3421A/PG/*:/eecs/dept/course/2020-21/F/3421A/PG/:.:' CreateQuest (Followed by parameters)

                  java
                  -cp
                  '/eecs/dept/course/2020-21/F/3421A/PG/*:/eecs/dept/course/2020-21/F/3421A/PG/:.:'
                  CreateQuest
       args[0] <- day
       args[1] <- realm
       args[2] <- theme
       args[3] <- amount
       args[4] <- user
       args[5] <- seed

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    */


    private Connection conDB;        // Connection to the database system.
    private String url;              // URL: Which database?

    private String user = "kroytorv"; // Database user account
    private String theme;
    private String realm;
    private String day;
    private double amount;
    private double seed;


    //Constructor
    public CreateQuest(String[] args) {



        // Set up the DB connection.
        try {
            // Register the driver with DriverManager.
            Class.forName("org.postgresql.Driver").newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(0);
        } catch (InstantiationException e) {
            e.printStackTrace();
            System.exit(0);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            System.exit(0);
        }


        // URL: Which database?
        //url = "jdbc:postgresql://db:5432/<dbname>?currentSchema=yrb";
        url = "jdbc:postgresql://db:5432/";


        // fetch the PASSWD from <.pgpass>


        Properties props = new Properties();
        try {
            String passwd = PgPass.get("db", "*", user, user);
            props.setProperty("user", user);
            props.setProperty("password", passwd);
            // props.setProperty("ssl","true"); // NOT SUPPORTED on DB
        } catch (PgPassException e) {
            System.out.print("\nCould not obtain PASSWD from <.pgpass>.\n");
            System.out.println(e.toString());
            System.exit(0);
        }


        // Initialize the connection.
        try {
            // Connect with a fall-thru id & password
            //conDB = DriverManager.getConnection(url,"<username>","<password>");
            conDB = DriverManager.getConnection(url, props);
        } catch (SQLException e) {
            System.out.print("\nSQL: database connection error.\n");
            System.out.println(e.toString());
            System.exit(0);
        }

        // Let's have autocommit turned off.  No particular reason here.
        try {
            conDB.setAutoCommit(false);
        } catch (SQLException e) {
            System.out.print("\nFailed trying to turn autocommit off.\n");
            e.printStackTrace();
            System.exit(0);
        }

        if (args.length < 4) {
            throw new IllegalArgumentException("arguments in command line must be in the following format: \n" +
                    "java -cp '/eecs/dept/course/2020-21/F/3421A/PG/*:/eecs/dept/course/2020-21/F/3421A/PG/:.:' CreateQuest " +
                    "<day> <realm> <theme> <amount> [<user>] [seed] \n" +
                    "where user and seed are optional parameters.");
        }

        if (args.length >= 5 && args.length <= 6) {
            if (args.length == 6) {
                try {
                    seed = Double.parseDouble(args[5]);
                } catch (IllegalArgumentException e) {
                    System.out.println("\nUsage: java CreateQuest seed");
                    System.out.println("Provide a seed of type double for the seed parameter.");
                    System.exit(0);
                }
                if (seed < -1 || seed > 1) {
                    throw new IllegalArgumentException("Seed value must be a real number between -1 and 1");
                }
                setSeed();
            }
            user = args[4];
        }

        day =  args[0];
        realm = args[1];
        theme = args[2];

        try {
            amount = Double.parseDouble(args[3]);
        } catch (IllegalArgumentException e) {
            System.out.println("\nUsage: java CreateQuest amount");
            System.out.println("Provide an amount of type double for the amount parameter.");
            System.exit(0);
        }

        if (checkIfQuestAlreadyExists()) {
            throw new IllegalArgumentException("Inputted quest already exists. \n" +
                    "Define a new quest that does not yet exist.");
        }

        if (!checkIfRealmExists()) {
            throw new IllegalArgumentException("Inputted realm does not exist. \n" +
                    "Use a realm that exists in the database");
        }

        if (!checkIfDayIsInFuture()) {
            throw new IllegalArgumentException("Inputted day is in the present or past. \n" +
                    "Use a day that is in the future.");
        }

        if (checkIfAmountIsGreaterThanSumSQLValues()) {
            throw new IllegalArgumentException("Inputted amount is greater than sum of all treasure sql values. \n" +
                    "Input an amount that is less than or equal to sum of all treasure sql values");
        }

        AddQuest();

        // Commit.  Okay, here nothing to commit really, but why not...
        try {
            conDB.commit();
        } catch (SQLException e) {
            System.out.print("\nFailed trying to commit.\n");
            e.printStackTrace();
            System.exit(0);
        }
        // Close the connection.
        try {
            conDB.close();
        } catch (
                SQLException e) {
            System.out.print("\nFailed trying to close the connection.\n");
            e.printStackTrace();
            System.exit(0);
        }
    }

    public boolean checkIfAmountIsGreaterThanSumSQLValues() {
        String queryText = "";     // The SQL text.
        PreparedStatement querySt = null;   // The query handle.
        ResultSet answers = null;   // A cursor.

        boolean inDB = false;  // Return.

        queryText =
                "SELECT 'True' AS isAmountGreaterThanSumSQLValues   "
                    + "FROM Treasure                                "
                    + "HAVING ? > sum(sql)                          ";

        // Prepare the query.
        try {
            querySt = conDB.prepareStatement(queryText);
        } catch (SQLException e) {
            System.out.println("SQL#1 failed in prepare");
            System.out.println(e.toString());
            System.exit(0);
        }

        // Execute the query.
        try {
            querySt.setInt(1, (int) Math.floor(amount));
            answers = querySt.executeQuery();
        } catch (SQLException e) {
            System.out.println("SQL#1 failed in execute");
            System.out.println(e.toString());
            System.exit(0);
        }

        // Any answer?
        try {
            if (answers.next()) {
                inDB = true;
            } else {
                inDB = false;
            }
        } catch (SQLException e) {
            System.out.println("SQL#1 failed in cursor.");
            System.out.println(e.toString());
            System.exit(0);
        }

        // Close the cursor.
        try {
            answers.close();
        } catch (SQLException e) {
            System.out.print("SQL#1 failed closing cursor.\n");
            System.out.println(e.toString());
            System.exit(0);
        }

        // We're done with the handle.
        try {
            querySt.close();
        } catch (SQLException e) {
            System.out.print("SQL#1 failed closing the handle.\n");
            System.out.println(e.toString());
            System.exit(0);
        }
        return inDB;
    }

    public int sumSQL() {
        String queryText = "";     // The SQL text.
        PreparedStatement querySt = null;   // The query handle.
        ResultSet answers = null;   // A cursor.
        int sumsql = 0;

        queryText = "SELECT sum(sql)" +
                "   FROM Treasure";

        // Prepare the query.
        try {
            querySt = conDB.prepareStatement(queryText);
        } catch (SQLException e) {
            System.out.println("SQL#1 failed in prepare");
            System.out.println(e.toString());
            System.exit(0);
        }

        // Execute the query.
        try {
            answers = querySt.executeQuery();
        } catch (SQLException e) {
            System.out.println("SQL#1 failed in execute");
            System.out.println(e.toString());
            System.exit(0);
        }

        // Any answer?
        try {
            while (answers.next()) {
                sumsql = (answers.getInt(1));
            }

        } catch (SQLException e) {
            System.out.println("SQL#1 failed in cursor.");
            System.out.println(e.toString());
            System.exit(0);
        }

        // Close the cursor.
        try {
            answers.close();
        } catch (SQLException e) {
            System.out.print("SQL#1 failed closing cursor.\n");
            System.out.println(e.toString());
            System.exit(0);
        }

        // We're done with the handle.
        try {
            querySt.close();
        } catch(SQLException e) {
            System.out.print("SQL#1 failed closing the handle.\n");
            System.out.println(e.toString());
            System.exit(0);
        }
        return sumsql;
    }

    public void setSeed() {
        String queryText = "";     // The SQL text.
        PreparedStatement querySt = null;   // The query handle.
        queryText =
                "SELECT setseed(?), random()";
        // Prepare the query.
        try {
            querySt = conDB.prepareStatement(queryText);
        } catch (SQLException e) {
            System.out.println("SQL#1 failed in prepare");
            System.out.println(e.toString());
            System.exit(0);
        }

        // Execute the query.

        try {
            querySt.setDouble(1, seed);
            querySt.executeQuery();

        } catch (SQLException e) {
            System.out.println("SQL#1 failed in execute");
            System.out.println(e.toString());
            System.exit(0);
        }

        // We're done with the handle.
        try {
            querySt.close();
        } catch(SQLException e) {
            System.out.print("SQL#1 failed closing the handle.\n");
            System.out.println(e.toString());
            System.exit(0);
        }
    }


    public void AddLoot() {
        String queryText = "";     // The SQL text.
        PreparedStatement querySt = null;   // The query handle.
        ResultSet answers = null;   // A cursor.
        //rrayList<String> treasureArr = new ArrayList<String>();
        queryText = "SELECT * \n" +
                "FROM Treasure\n" +
                "ORDER BY random();";

        // Prepare the query.
        try {
            querySt = conDB.prepareStatement(queryText);
        } catch (SQLException e) {
            System.out.println("SQL#1 failed in prepare");
            System.out.println(e.toString());
            System.exit(0);
        }

        // Execute the query.
        try {
            answers = querySt.executeQuery();
        } catch (SQLException e) {


            System.out.println("ADDLOOT() FAILED");


            System.out.println("SQL#1 failed in execute");
            System.out.println(e.toString());
            System.exit(0);
        }

        // Any answer?
        try {
            boolean sqlCounterReachesAmount = false;
            int treasureCounter = 1;
            int sqlCounter = 0;
            //final int sumSQLVal = sumSQL();
            while (answers.next() && sqlCounter < amount) {

                AddLootHelper(treasureCounter, answers.getString(1));
                sqlCounter += answers.getInt(2);
                treasureCounter++;
            }


        } catch (SQLException e) {
            System.out.println("SQL#1 failed in cursor.");
            System.out.println(e.toString());
            System.exit(0);
        }

        // Close the cursor.
        try {
            answers.close();
        } catch (SQLException e) {
            System.out.print("SQL#1 failed closing cursor.\n");
            System.out.println(e.toString());
            System.exit(0);
        }

        // We're done with the handle.
        try {
            querySt.close();
        } catch(SQLException e) {
            System.out.print("SQL#1 failed closing the handle.\n");
            System.out.println(e.toString());
            System.exit(0);
        }
    }

    public void AddLootHelper(int treasureCounter, String treasure) {
        String queryText = "";     // The SQL text.
        PreparedStatement querySt = null;   // The query handle.
        //ResultSet answers = null;   // A cursor.
        queryText = "INSERT INTO Loot (loot_id, treasure, theme, realm, day, login) VALUES" +
                "(?,     ?,          ?,    ?,  TO_DATE(?, 'YYYY/MM/DD'),   NULL)";

        // Prepare the query.
        try {
            querySt = conDB.prepareStatement(queryText);
        } catch (SQLException e) {
            System.out.println("SQL#1 failed in prepare");
            System.out.println(e.toString());
            System.exit(0);
        }

        // Execute the query.
        try {
            querySt.setInt(1, treasureCounter);
            querySt.setString(2, treasure);
            querySt.setString(3, theme);
            querySt.setString(4, realm);
            querySt.setString(5, day);


            /*answers = */ querySt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("SQL#1 failed in execute");
            System.out.println(e.toString());
            System.exit(0);
        }

        // Any answer?
        /*
        try {
            while (answers.next()) {

            }

        } catch (SQLException e) {
            System.out.println("SQL#1 failed in cursor.");
            System.out.println(e.toString());
            System.exit(0);
        }

        // Close the cursor.
        try {
            answers.close();
        } catch (SQLException e) {
            System.out.print("SQL#1 failed closing cursor.\n");
            System.out.println(e.toString());
            System.exit(0);
        }
        */


        // We're done with the handle.
        try {
            querySt.close();
        } catch(SQLException e) {
            System.out.print("SQL#1 failed closing the handle.\n");
            System.out.println(e.toString());
            System.exit(0);
        }
    }

    public void AddQuest() {
        String queryText = "";     // The SQL text.
        PreparedStatement querySt = null;   // The query handle.

        queryText =
                "INSERT INTO Quest (theme, realm, day, succeeded) VALUES  "
                        + "(?, ?, TO_DATE(?, 'YYYY/MM/DD'), NULL)";

        // Prepare the query.
        try {
            querySt = conDB.prepareStatement(queryText);
        } catch (SQLException e) {
            System.out.println("SQL#1 failed in prepare");
            System.out.println(e.toString());
            System.exit(0);
        }

        // Execute the query.
        try {

            querySt.setString(1, theme);
            querySt.setString(2, realm);
            querySt.setString(3, day);
            querySt.executeUpdate();

        } catch (SQLException e) {

            System.out.println("ADDQUEST() FAILED");


            System.out.println("SQL#1 failed in execute");
            System.out.println(e.toString());
            System.exit(0);
        }

        // We're done with the handle.
        try {
            querySt.close();
        } catch(SQLException e) {
            System.out.print("SQL#1 failed closing the handle.\n");
            System.out.println(e.toString());
            System.exit(0);
        }
        AddLoot();
    }

    public boolean checkIfQuestAlreadyExists() {
        String queryText = "";     // The SQL text.
        PreparedStatement querySt = null;   // The query handle.
        ResultSet answers = null;   // A cursor.

        boolean inDB = false;  // Return.

        queryText =
                "SELECT ?, ?, TO_DATE(?, 'YYYY/MM/DD')          "
                        + "FROM Quest                           "
                        + "WHERE realm = ?                      "
                        + "AND theme = ?                        "
                        + "AND day = TO_DATE(?, 'YYYY/MM/DD')   ";

        // Prepare the query.
        try {
            querySt = conDB.prepareStatement(queryText);
        } catch (SQLException e) {
            System.out.println("SQL#1 failed in prepare");
            System.out.println(e.toString());
            System.exit(0);
        }

        // Execute the query.
        try {
            querySt.setString(1, realm);
            querySt.setString(2, theme);
            querySt.setString(3, day);
            querySt.setString(4, realm);
            querySt.setString(5, theme);
            querySt.setString(6, day);
            answers = querySt.executeQuery();
        } catch (SQLException e) {
            System.out.println("SQL#1 failed in execute");
            System.out.println(e.toString());
            System.exit(0);
        }

        // Any answer?
        try {
            if (answers.next()) {
                inDB = true;
            } else {
                inDB = false;
            }
        } catch(SQLException e) {
            System.out.println("SQL#1 failed in cursor.");
            System.out.println(e.toString());
            System.exit(0);
        }

        // Close the cursor.
        try {
            answers.close();
        } catch(SQLException e) {
            System.out.print("SQL#1 failed closing cursor.\n");
            System.out.println(e.toString());
            System.exit(0);
        }

        // We're done with the handle.
        try {
            querySt.close();
        } catch(SQLException e) {
            System.out.print("SQL#1 failed closing the handle.\n");
            System.out.println(e.toString());
            System.exit(0);
        }


        return inDB;
    }


    public boolean checkIfDayIsInFuture() {
        String queryText = "";     // The SQL text.
        PreparedStatement querySt = null;   // The query handle.
        ResultSet answers = null;   // A cursor.


        boolean inDB = false;  // Return.

        queryText =
                "SELECT TO_DATE(?, 'YYYY/MM/DD') AS day                 "
                        + "WHERE current_date < TO_DATE(?, 'YYYY/MM/DD')";

        // Prepare the query.
        try {
            querySt = conDB.prepareStatement(queryText);
        } catch (SQLException e) {
            System.out.println("SQL#1 failed in prepare");
            System.out.println(e.toString());
            System.exit(0);
        }

        // Execute the query.
        try {
            querySt.setString(1, day);
            querySt.setString(2, day);


            answers = querySt.executeQuery();
        } catch (SQLException e) {
            System.out.println("SQL#1 failed in execute");
            System.out.println(e.toString());
            System.exit(0);
        }

        // Any answer?
        try {
            if (answers.next()) {
                inDB = true;
            } else {
                inDB = false;
            }
        } catch(SQLException e) {
            System.out.println("SQL#1 failed in cursor.");
            System.out.println(e.toString());
            System.exit(0);
        }

        // Close the cursor.
        try {
            answers.close();
        } catch(SQLException e) {
            System.out.print("SQL#1 failed closing cursor.\n");
            System.out.println(e.toString());
            System.exit(0);
        }

        // We're done with the handle.
        try {
            querySt.close();
        } catch(SQLException e) {
            System.out.print("SQL#1 failed closing the handle.\n");
            System.out.println(e.toString());
            System.exit(0);
        }

        return inDB;
    }

    public boolean checkIfRealmExists() {
        String queryText = "";     // The SQL text.
        PreparedStatement querySt = null;   // The query handle.
        ResultSet answers = null;   // A cursor.

        boolean inDB = false;  // Return.

        queryText =
                "SELECT realm              "
                        + "FROM Realm      "
                        + "WHERE realm = ? ";

        // Prepare the query.
        try {
            querySt = conDB.prepareStatement(queryText);
        } catch (SQLException e) {
            System.out.println("SQL#1 failed in prepare");
            System.out.println(e.toString());
            System.exit(0);
        }

        // Execute the query.
        try {
            querySt.setString(1, realm);
            answers = querySt.executeQuery();
        } catch (SQLException e) {
            System.out.println("SQL#1 failed in execute");
            System.out.println(e.toString());
            System.exit(0);
        }

        // Any answer?
        try {
            if (answers.next()) {
                inDB = true;
            } else {
                inDB = false;
            }
        } catch (SQLException e) {
            System.out.println("SQL#1 failed in cursor.");
            System.out.println(e.toString());
            System.exit(0);
        }

        // Close the cursor.
        try {
            answers.close();
        } catch (SQLException e) {
            System.out.print("SQL#1 failed closing cursor.\n");
            System.out.println(e.toString());
            System.exit(0);
        }

        // We're done with the handle.
        try {
            querySt.close();
        } catch (SQLException e) {
            System.out.print("SQL#1 failed closing the handle.\n");
            System.out.println(e.toString());
            System.exit(0);
        }

        return inDB;
    }

    public static void main(String[] args) {

        CreateQuest cq = new CreateQuest(args);

    }
}
