package Messanger.Settings;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Model {

    private Connection conn = null;

    protected infoObject getUserInfo(String username) {

        infoObject obj = new infoObject();

        try {

            conn = DriverManager.getConnection("jdbc:mysql://localhost/jmessanger", "root", "");

            String query = "select * from login";

            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {

                if (rs.getString("username").equals(username)) {
                    obj.email = rs.getString("email");
                    obj.accountStatus = rs.getString("accstat");

                    return obj;
                }
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }
        return null;
    }

    protected void updateEmail(String Email, String Username) {
        try {

            conn = DriverManager.getConnection("jdbc:mysql://localhost/jmessanger", "root", "");

            String query = "update login set email=? where username=?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, Email);
            ps.setString(2, Username);
            ps.executeUpdate();
             JOptionPane.showMessageDialog(null, "Email has been updated", "Succesfull", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Database Connection Error \n "
                    + "Please Check Your Internet Connection", "Error ", JOptionPane.INFORMATION_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {

            }
        }

    }

    protected void updatePassword(String oldpassword, String password, String username) {
        boolean passCheck = false;
        String query;
        PreparedStatement statement;
        ResultSet rs;
        try {
            //Start a connection
            conn = DriverManager.getConnection("jdbc:mysql://localhost/jmessanger", "root", "");
            //what to do with database
            query = "select * from login where username=?";
            //submit a query to the database
            statement = conn.prepareStatement(query);
            statement.setString(1, username);
            rs = statement.executeQuery();

            if (rs.next()) {
                if (rs.getString("password").equals(oldpassword)) {
                    passCheck = true;
                }
            }
            if (passCheck) {
                query = "update login set password=? where username=?";
                statement = conn.prepareStatement(query);
                statement.setString(1, password);
                statement.setString(2, username);
                statement.executeUpdate();
                 JOptionPane.showMessageDialog(null, "Password has been updated.", "Succesfull", JOptionPane.INFORMATION_MESSAGE);
                
            } else {
                JOptionPane.showMessageDialog(null, "Invalid old password", "Error", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected class infoObject {

        String email;
        String accountStatus;
    }

}
