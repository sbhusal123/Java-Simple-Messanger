package Messanger.ChatWindow;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

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

        } catch (SQLException e) {
        }
        return false;
    }

    protected ResultSet getMessages() {

        ResultSet rs = null;

        try {

            Conn = DriverManager.getConnection("jdbc:mysql://localhost/jmessanger", "root", "");

            String query = "select * from messages";

            Statement st = Conn.createStatement();

            st.executeQuery(query);

            rs = st.executeQuery(query);

        } catch (SQLException e) {

        }

        return rs;
    }

}
