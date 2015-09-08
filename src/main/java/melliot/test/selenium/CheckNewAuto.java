package melliot.test.selenium;

import java.io.IOException;
import java.sql.SQLException;

public class CheckNewAuto {
    public static void main(String[] args) throws IOException, SQLException {

        int newCounter = Integer.parseInt(GetNewAutoCounter.getNewAutoCount());

        int lastResult = MySqlConnect.getLastCounter();

        if (newCounter > lastResult) {

            GetNewAutoCounter.getScreenshot();

            SlackApi.apiDeleteLastMessage();

            String ts = SlackApi.apiPost();

            WriteToDb.writeCounterWithTs(newCounter, ts);

        } else {
            WriteToDb.writeCounter(newCounter);
        }
    }
}
