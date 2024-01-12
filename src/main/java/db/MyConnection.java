package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnection {
    public static Connection connection;
    public static Connection getConnection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Project1?useSSL=false","root","Shit@1357");
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Connection is Started!!");
        return  connection;

    }
    public static void closeConnection(){
        if(connection != null){
            try{
                connection.close();
            }
            catch(SQLException ex){
                ex.printStackTrace();
            }
        }

    }
}
