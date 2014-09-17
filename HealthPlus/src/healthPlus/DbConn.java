/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package healthPlus;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.JOptionPane;

/**
 *
 * @author Navneet
 */
public class DbConn {
    
    // nav was here 

    private static Connection connection ;
    private static Statement statement;

    public static Connection connect() throws ClassNotFoundException, InstantiationException, IllegalAccessException{
        try{
            //DriverManager.registerDriver(new Driver() {});
        Class.forName("oracle.jdbc.OracleDriver").newInstance();
        connection = DriverManager.getConnection("jdbc:oracle:thin:@sage.business.unsw.edu.au:1521:orcl01", "z3417361", "creGuJA6");
        statement = connection.createStatement();
        
        } catch (SQLException e){
            e.printStackTrace();
        } return connection;
    }
    
   public static Connection getConnection() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException{
        if(connection !=null && !connection.isClosed())
            return connection;
            connect();
            return connection;
    }
}
    
    