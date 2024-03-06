package converter.datasource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MariaDbConnection {
    private static Connection conn = null;

    public static Connection getConnection() {
        try {
            if (conn == null || conn.isClosed()) {
                conn = DriverManager.getConnection(
                        "jdbc:mariadb://localhost:3306/converter?user=appuser&password=password");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static void terminate() {
        try {
            getConnection().close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
