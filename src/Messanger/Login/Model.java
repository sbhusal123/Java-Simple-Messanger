package Messanger.Login;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.fxml.FXML;
import javax.swing.JOptionPane;
import Resources.DbParameters;

public class Model {
    

    private  Connection Conn = null;
    
    Model(){
        this.Conn = DbParameters.getConnectionInstance();
    }

    @FXML
    protected boolean checkLogin(String username, String password) throws ClassNotFoundException, SQLException {

        try {
            String query = "select * from login";

            Statement st = Conn.createStatement();

            st.executeQuery(query);

            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {

                String DBusername = rs.getString("username");
                String DBpassword = rs.getString("password");

                if (DBusername.equals(username) && DBpassword.equals(password)) {
                    return true;
                }
            }

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, "Database Connection Error \n "
                    + "Please Check Your Internet Connection", "Error ", JOptionPane.INFORMATION_MESSAGE);

        }

        return false;

    }

}
