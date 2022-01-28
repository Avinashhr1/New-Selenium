package test.java;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.codec.binary.Base64;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v96.network.Network;
import org.openqa.selenium.devtools.v96.network.model.Headers;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;


public class firstTest {

    WebDriver driver;

    public static final String username = "lms.com";
    public static final String password = "Webteam!";



    @Test
    public void setup() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        DevTools devTools = ((ChromeDriver) driver).getDevTools();
        devTools.createSession();
        devTools.send(Network.enable(Optional.empty(),Optional.empty(),Optional.empty()));

        Map<String,Object> headers = new HashMap<>();
        String basicAuth = "Basic"+ new String(new Base64().encode(String.format("%s,%s",username,password).getBytes(StandardCharsets.UTF_8)));

        headers.put("Authorization",basicAuth);
        devTools.send(Network.setExtraHTTPHeaders(new Headers(headers)));

        driver.get("https://uat1.centrepointstores.com/ae/en/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));









    }

//    @Test
//    public void mainTest(){
//        String title = driver.getTitle();
//        System.out.println(title);
//        Assert.assertEquals(title,"OrangeHRM");
//
//    }
//
//    @AfterTest
//    public void teardown(){
//        driver.quit();
//    }
}
