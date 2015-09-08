package melliot.test.selenium;

import java.io.IOException;
import java.sql.SQLException;

public class CheckNewAuto {
    public static void main(String[] args) throws IOException, SQLException {

        int newCounter = Integer.parseInt(GetNewAutoCounter.getNewAutoCount());

        int lastResult = MySqlConnect.getLastCounter();

        if (newCounter > lastResult) {

            SlackApi.apiDeleteLastMessage();

            String ts = SlackApi.apiPost();
            System.out.println(ts);

            WriteToDb.writeCounterWithTs(newCounter, ts);

        } else {
            WriteToDb.writeCounter(newCounter);
        }
    }
}
