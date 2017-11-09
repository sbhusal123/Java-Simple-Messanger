/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Messanger.RegisterUser;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class Model {

    private static Connection Conn = null;
    
   

    protected boolean checkUserExistence(String username) {
        try {
            
             Conn = DriverManager.getConnection("jdbc:mysql://localhost/jmessanger", "root", "");


            String query = "select * from login";

            Statement st = Conn.createStatement();

            st.executeQuery(query);

            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {

                String DBusername = rs.getString("username");

                if (DBusername.equals(username)) {
                    return true;
                }

            }

        } catch (Exception ex) {
            //JOptionPane.showMessageDialog(null,"Database Connection Error \n "
                //    + "Please Check Your Internet Connection", "Error ", JOptionPane.INFORMATION_MESSAGE);
        }

        return false;
    }

    protected boolean addUser(String username, String email, String password) {

        try {

            String query = "insert into login(username , email , password,accstat, PIN ) values( ? , ? , ? , ?, ?)";

            //Error 
            PreparedStatement preparedStatement = Conn.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, password);
            preparedStatement.setInt(4, 0);
            preparedStatement.setInt(5, 0);
            preparedStatement.executeUpdate();

            return true;

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null,"Database Connection Error \n "
                    + "Please Check Your Internet Connection", "Error ", JOptionPane.INFORMATION_MESSAGE);

        }

        return false;
    }

}

