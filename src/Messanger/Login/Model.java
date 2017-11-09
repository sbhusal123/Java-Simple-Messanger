package Messanger.Login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.fxml.FXML;
import javax.swing.JOptionPane;

public class Model {

    private static final Connection conn = null;

    @FXML
    protected boolean checkLogin(String username, String password) throws ClassNotFoundException, SQLException {

        try {

            Connection Conn = DriverManager.getConnection("jdbc:mysql://localhost/jmessanger", "root", "");

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
