package melliot.test.selenium;

import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class WriteToDb {

    public static void writeCounter (int counter) throws SQLException {

        Date date = new java.sql.Date(new java.util.Date().getTime());

        String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);


        MySqlConnect.getConnection()
                .prepareStatement("INSERT INTO counters (date, counter) VALUES ('" + time +"'," + counter + ");")
                .executeUpdate();
    }

    public static void writeCounterWithTs (int counter, String ts) throws SQLException {

        Date date = new java.sql.Date(new java.util.Date().getTime());

        String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);


        MySqlConnect.getConnection()
                .prepareStatement("INSERT INTO counters (date, counter, ts) VALUES ('" + time +"'," + counter + ",'" + ts + "' );")
                .executeUpdate();
    }
}
