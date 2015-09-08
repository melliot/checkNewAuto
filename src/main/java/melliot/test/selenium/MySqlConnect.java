package melliot.test.selenium;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySqlConnect {
    static Connection connection;

    public static Connection getConnection() throws SQLException {

        String dbUrl = "jdbc:mysql://localhost/auto";
        String username = "root";
        String password = "";

        connection  = DriverManager.getConnection(dbUrl, username, password);

        return connection;
    }

    public int getCounter(int entityId) throws SQLException {

        connection = getConnection();

        ResultSet resultSet = connection.prepareStatement(
                "SELECT * From counters WHERE id=" + entityId + ";"
        ).executeQuery();

        if (resultSet.next()) {
            return resultSet.getInt("counter");
        } else return -1;
    }

    public static int getLastCounter() throws SQLException {
        ResultSet resultSet = getConnection().prepareStatement("SELECT * FROM counters").executeQuery();

        if(resultSet.last()) {
            resultSet.last();
            return resultSet.getInt("counter");
        }
        else return 0;
    }

    public static String getLastTs() throws SQLException {
        String ts;

        ResultSet resultSet = getConnection().prepareStatement("SELECT * FROM counters").executeQuery();

        resultSet.last();

        if((ts = resultSet.getString("ts")) != null) {
            return ts;
        } else {
            while ((resultSet.getString("ts")) == null) {
                resultSet.previous();
            }
            return resultSet.getString("ts");
        }
    }
}
