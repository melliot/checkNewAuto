package melliot.test.selenium;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.sql.SQLException;

public class SlackApi {

    static HttpClient httpClient = HttpClientBuilder.create().build();

    static String newAutoMessage = "https://slack.com/api/chat.postMessage?token=xoxp-5120310464-5120310474-10304425591-b9e683&channel=%23auto&text=New%20auto!%20Check%20it%20https%3A%2F%2Fauto.ria.com%2Fsearch%2F%3Fcategory_id%3D1%26state%3D10%26bodystyle%5B0%5D%3D5%23power_name%3D1%26bodystyle%5B1%5D%3D5%26bodystyle%5B3%5D%3D4%26bodystyle%5B4%5D%3D2%26category_id%3D1%26s_yers%5B0%5D%3D2007%26po_yers%5B0%5D%3D0%26state%5B0%5D%3D10%26search_near_states%3D1%26price_do%3D12000%26currency%3D1%26gearbox%5B1%5D%3D2%26top%3D8%26countpage%3D100%26damage%3D1%26auto_repairs%3D2%26seatsFrom%3D5%26seatsTo%3D9%26raceTo%3D160%26custom%3D1&pretty=1";

    static String deleteMessageUrl = "https://slack.com/api/chat.delete?token=xoxp-5120310464-5120310474-10304425591-b9e683&channel=C0A8YBDPH&ts=";

    public static String apiPost() throws IOException {

        HttpPost request = new HttpPost(newAutoMessage);

        HttpResponse response = httpClient.execute(request);

        String jsonResponse = EntityUtils.toString(response.getEntity());

        JsonParser parser = new JsonParser();

        JsonObject json = (JsonObject) parser.parse(jsonResponse);

        return String.valueOf(json.get("ts"));

    }

    public static void apiDeleteLastMessage() throws IOException, SQLException {

        String tsFromDb = MySqlConnect.getLastTs();

        System.out.println(tsFromDb);

        HttpDelete request = new HttpDelete(deleteMessageUrl + tsFromDb.replaceAll("\"", ""));

        httpClient.execute(request);
    }
}
