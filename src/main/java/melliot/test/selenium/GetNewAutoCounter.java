package melliot.test.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class GetNewAutoCounter {
    static String targetId = ".//*[@id='resultsCount']/strong";
    static final String URL = "https://auto.ria.com/search/?category_id=1&state=10&bodystyle[0]=5#power_name=1&bodystyle[1]=5&bodystyle[3]=4&bodystyle[4]=2&category_id=1&s_yers[0]=2007&po_yers[0]=0&state[0]=10&search_near_states=1&price_do=12000&currency=1&gearbox[1]=2&top=8&countpage=100&damage=1&auto_repairs=2&seatsFrom=5&seatsTo=9&raceTo=160&custom=1";

    public static String getNewAutoCount() throws MalformedURLException {
        URL url = new URL("http://localhost:9515");
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability(CapabilityType.PAGE_LOAD_STRATEGY, "eager");
        WebDriver driver = new RemoteWebDriver(url, capabilities);

        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        WebDriverWait wait = new WebDriverWait(driver, 10);

        try {
            driver.get(URL);

            WebElement counter = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(targetId)));

            return counter.getText();

        } finally {
            driver.close();
        }
    }
}
