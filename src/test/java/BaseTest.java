package test.java;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.github.bonigarcia.wdm.WebDriverManager;
import main.java.Pageobjects.HomePage;
import main.java.pageEvents.HomePageEvents;
import main.java.utils.Constants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.lang.reflect.Method;
import java.time.Duration;

public class BaseTest {
    public static WebDriver driver;
    public ExtentHtmlReporter htmlReporter;

    public static ExtentReports extent;
    public static ExtentTest test;



    @BeforeTest
    public void beforeTestMethod(){
        htmlReporter = new ExtentHtmlReporter("./Reports/AutomationReport.html");
        htmlReporter.config().setEncoding("utf-8");
        htmlReporter.config().setDocumentTitle("Automation Report");
        htmlReporter.config().setTheme(Theme.DARK);
        htmlReporter.config().setReportName("Automation Test Results");

        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        extent.setSystemInfo("Automation Tester", "Avinash");

    }

    @BeforeMethod
    @Parameters(value={"browserName"})
    public void  beforeMethodMethod(@Optional("chrome") String browserName, Method testMethod){
        test = extent.createTest(testMethod.getName());
        initializeBrowser(browserName);
        driver.manage().window().maximize();
        driver.get(Constants.url);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterMethod
    public void afterMethodMethod(ITestResult result){

        if(result.getStatus()==ITestResult.SUCCESS){
            String methodName = result.getMethod().getMethodName();
            String logtext = "Test Case:"+ methodName +" is Passed";
            Markup mark = MarkupHelper.createLabel(logtext, ExtentColor.GREEN);
            test.log(Status.PASS, mark);
        }else if(result.getStatus()==ITestResult.FAILURE){
            String methodName = result.getMethod().getMethodName();
            String logtext = "Test Case:"+ methodName +" is Failed";
            Markup mark = MarkupHelper.createLabel(logtext, ExtentColor.RED);
            test.log(Status.FAIL, mark);
        }else{
            String methodName = result.getMethod().getMethodName();
            String logtext = "Test Case:"+ methodName +" is Skipped";
            Markup mark = MarkupHelper.createLabel(logtext, ExtentColor.YELLOW);
            test.log(Status.SKIP, mark);
        }
        driver.quit();
    }


    @AfterTest
    public void afterTestMethod(){
        extent.flush();

    }

    public WebDriver initializeBrowser(String BrowserName){
        if(BrowserName.equalsIgnoreCase("chrome")){
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }else if (BrowserName.equalsIgnoreCase("firefox")){
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }else {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }
        return driver;
    }

    @Test
    public void searchTest(){

        HomePageEvents home = new HomePageEvents();
        home.searchKeyWord("Selenium");
        String title = driver.getTitle();
        assert title.contains("Selenium");
    }

    @Test
    public void ApiTest(){

    }

}
