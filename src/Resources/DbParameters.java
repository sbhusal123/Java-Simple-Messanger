package Resources;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DbParameters {
    
    public static String DbHost = "jdbc:mysql://localhost/jmessanger";
    public static String username = "root";
    public static String password = "";
    
    protected static Connection connect = null;
    
    public static Connection getConnectionInstance(){
        try {
            connect = DriverManager.getConnection("jdbc:mysql://localhost/jmessanger", "root", "");
        } catch (SQLException ex) {
            Logger.getLogger(DbParameters.class.getName()).log(Level.SEVERE, null, ex);
        }
        return connect;
    }
}
