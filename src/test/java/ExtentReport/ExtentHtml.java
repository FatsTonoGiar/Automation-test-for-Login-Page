package ExtentReport;

import base.BaseSetup;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.ExtentXReporter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import pages.RunPages;
import testcases.ExcellHelpers;

import java.io.IOException;

public class ExtentHtml {
    ExtentHtmlReporter htmlReporter;
    ExtentReports extent;
    public WebDriver driver;
    public RunPages runPages;
    public ExcellHelpers excel;
    @FindBy(xpath = "//div[@class='alert alert-danger']") private WebElement alert;
    @BeforeSuite

    public void SetUp() {
        driver = new BaseSetup().setDriver("chrome");
        driver.get(new BaseSetup().getUrl());
        excel = new ExcellHelpers();
        runPages = new RunPages(driver);
        PageFactory.initElements(this.driver,this);
        htmlReporter = new ExtentHtmlReporter("extent.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
    }

    @Test(priority = 1)
    public void test1() throws IOException {
        ExtentTest test = extent.createTest("Extent Report","Extent Report Test");
        test.log(Status.INFO, "This step shows usage of log(status, details)");
        test.info("This step shows usage of info(details)");
        //test.fail("details", MediaEntityBuilder.createScreenCaptureFromPath("test-output/failed.png").build());
        //test.addScreenCaptureFromPath("test-output/failed.png");
    }
    @Test(priority = 0)
    public void signInPage() throws Exception {
        ExtentTest testcase = extent.createTest("Login pages anhtester","Use Extent Report for testcase login pages");
        excel.setExcelFile("src/main/resources/Book1.xlsx", "Sheet1");
        testcase.info("This step set path for excel file");
        for (int i = 1; i < 6; i++) {
            runPages.signIn(excel.getCellData("username", i), excel.getCellData("password", i));
            testcase.info("This step read and get data from excel file such username and password for login pages");
            excel.setCellData("anhtester.com/login", i, 2);
            testcase.info("This step write text into excel file");
            if (alert.getText().contains("Xác thực Captcha thất bại. Hãy thử lại !!")) {
                excel.setCellData("FailSignIn", i, 4);
            } else {
                excel.setCellData("PassSignIn", i, 4);
            }
            testcase.info("This step write results in excel file");
        }

    }
    @AfterSuite
    public void tearDown(){
    extent.flush();
    }
}


