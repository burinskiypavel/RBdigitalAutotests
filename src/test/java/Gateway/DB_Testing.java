package Gateway;

import org.testng.annotations.Test;

import java.sql.*;
import java.sql.Statement;
import  java.sql.Connection;
import  java.sql.ResultSet;
import  java.sql.DriverManager;
import  java.sql.SQLException;

public class DB_Testing {

    @Test
    public void DB_Test() throws ClassNotFoundException, SQLException {

        //Connection con = DriverManager.getConnection(dbUrl,username,password);
        String dbUrl = "jdbc:mysql://54.208.87.86:3306/RBDG";
        //Database Username
        String username = "php";
        String password = "jjOoan1UVZpDBA5p5YHY";
        //Querry to Execute
        String query = "select * from PATRON LIMIT 20;";

        Class.forName("com.mysql.jdbc.Driver");

        //Create Connection to DB
        Connection con = DriverManager.getConnection(dbUrl, username, password);

        //Create Statement Object
        Statement stmt = con.createStatement();

        // Execute the SQL Query. Store results in ResultSet
        ResultSet rs= stmt.executeQuery(query);

        // While Loop to iterate through all data and print results
        while (rs.next()){
            String myName = rs.getString(1);
            String myAge = rs.getString(2);
            System. out.println(myName+"  "+myAge);
        }
        // closing DB Connection
        con.close();
    }
}
