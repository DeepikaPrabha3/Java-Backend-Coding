package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnections
{
    public static Connection getConnection(){
        Connection conn=null;
        //1. Load Driver
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loaded");
            //2. Connect to driver => url,uname,pwd
            conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/gl2023","root","");
            System.out.println("Database Connected");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
