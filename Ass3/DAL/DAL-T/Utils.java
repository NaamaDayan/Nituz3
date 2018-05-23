import java.sql.Connection;
import java.sql.DriverManager;

public class Utils {
    public static Connection openConnection() {
        Connection conn = null;
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:transports.db");
            conn.createStatement().execute("PRAGMA foreign_keys = ON");
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
        return conn;
    }
}
