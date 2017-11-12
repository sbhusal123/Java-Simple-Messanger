package Messanger.ChatWindow;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Model {
    
     private static Connection Conn = null;
    
    
    
     protected boolean addMessage(String username, String message) {
         
         

        try {
            Conn = DriverManager.getConnection("jdbc:mysql://localhost/jmessanger", "root", "");
            String query = "insert into messages(username , message ) values( ? , ? )";

            //Error 
            PreparedStatement preparedStatement = Conn.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, message);
            preparedStatement.executeUpdate();

            return true;

        } catch (Exception e) {

           e.printStackTrace();

        }
         return false;
     }
    
    
}
