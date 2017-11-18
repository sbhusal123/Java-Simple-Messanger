package Messanger.ChatWindow;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Model {

    private static Connection Conn = null;

    Model() throws SQLException {
        Conn = DriverManager.getConnection("jdbc:mysql://localhost/jmessanger", "root", "");
    }

    protected boolean addMessage(String username, String message) {

        try {

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

            String query = "select * from messages";

            Statement st = Conn.createStatement();

            st.executeQuery(query);

            rs = st.executeQuery(query);

        } catch (SQLException e) {

        }

        return rs;
    }

}
