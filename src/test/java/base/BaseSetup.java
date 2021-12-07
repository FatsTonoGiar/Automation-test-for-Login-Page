package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class BaseSetup {
    private WebDriver driver;
    static String driverPath = "src/main/resources/";
    public String url = "https://www.anhtester.com/login";
    public WebDriver getDriver() {
        return driver;
    }
    public String getUrl(){
        return url;
    }
    public WebDriver setDriver(String browser) {
        switch (browser) {
            case "chrome" -> initChrome();
            case "firefox" -> initFirefox();
            default -> {
                System.out.println("Not Find see browser so as");
                initChrome();

            }
        }
    return driver;
    }

    private void initFirefox() {

        System.setProperty("webdriver.gecko.driver",driverPath + "geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    private void initChrome() {
    System.setProperty("webdriver.chrome.driver",driverPath + "chromedriver.exe");
    driver = new ChromeDriver();
    driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
    driver.manage().window().maximize();
    }
@BeforeClass
    public void initBaseSetup(String browser) {
        try {
            setDriver(browser);
        }
        catch (Exception e){
            System.out.println("Error" + Arrays.toString(e.getStackTrace()));
        }
    }
@AfterClass
    public void TearDown() throws InterruptedException {
        Thread.sleep(1000);
        //driver.quit();
    }

}
