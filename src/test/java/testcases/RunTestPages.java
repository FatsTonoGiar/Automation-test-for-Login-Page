package testcases;

import base.BaseSetup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.RunPages;
public class RunTestPages {
    public WebDriver driver;
    public RunPages runPages;
    public ExcellHelpers excel;
   @FindBy(xpath = "//div[@class='alert alert-danger']") private WebElement alert;
    @BeforeClass
    public void RunTestPage() {
        driver = new BaseSetup().setDriver("chrome");
        driver.get(new BaseSetup().getUrl());
        excel = new ExcellHelpers();
        runPages = new RunPages(driver);
        PageFactory.initElements(this.driver,this);
    }
    //@Test(priority = 0)
    //public void SearchBox(){
    //runPages = new RunPages(driver);
    //driver.get(new BaseSetup().getUrl());
    //excel = new ExcellHelpers();
    //runPages.SearchGoogle("seachains");
    //runPages.checkSearchTableByColumn(1,"Javier");
    //}

    @Test()
    public void signInPage() throws Exception {

        // Setup đường dẫn của file excel
        excel.setExcelFile("src/main/resources/Book1.xlsx", "Sheet1");

        // Đọc data từ file excel
        for (int i = 1; i < 6; i++) {
            runPages.signIn(excel.getCellData("username", i), excel.getCellData("password", i));
            excel.setCellData("anhtester.com/login", i, 2);
            if (alert.getText().contains("Xác thực Captcha thất bại. Hãy thử lại !!")) {
                excel.setCellData("FailSignIn", i, 4);
            } else {
                excel.setCellData("PassSignIn", i, 4);
            }

            // Chú ý: dòng và cột trong code nó hiểu bắt đầu từ 0
        }
    }
}
