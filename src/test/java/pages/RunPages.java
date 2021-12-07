package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import base.ValidateHelper;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;
import java.util.Locale;

public class RunPages {
    public WebDriver driver;
    public ValidateHelper validatehelper;


    public RunPages(WebDriver driver) {
        this.driver=driver;
        validatehelper = new ValidateHelper(driver);
        PageFactory.initElements(this.driver,this);
    }
    @FindBy(xpath = "//input[@title='Tìm kiếm']") private WebElement SearchBox;
    @FindBy(xpath = " //caption[contains(text(),'Age table')]") private WebElement AgeTable;
    @FindBy(xpath = "//input[@placeholder='Email']") private WebElement emailFacebook;
    @FindBy(xpath = "//input[@placeholder='Password']") private WebElement passwordFacebook;
    @FindBy(xpath = "//button[@id='login']") private WebElement buttonLoginFB;
    public void checkSearchTableByColumn(int column, String value) {
        //Xác định số dòng của table sau khi search
        List<WebElement> row = driver.findElements(By.xpath("/html[1]/body[1]/div[3]/div[3]/div[5]/div[1]/table[1]/tbody[1]/tr"));
        int rowTotal = row.size(); //Lấy ra số dòng
        System.out.println("Số dòng tìm thấy: " + rowTotal);
        WebElement elementTH = driver.findElement(By.xpath("//table//tbody/tr[1]/th["+column+"]"));
        Assert.assertFalse(elementTH.getText().toUpperCase().contains(value.toUpperCase()));
        System.out.println("row:" + 1 + " " + "column:" + column +" "+elementTH.getText() +" "+ "Not Equal value" +" "+ value);
        for (int i = 2; i <= rowTotal; i++) {
            WebElement elementCheck = driver.findElement(By.xpath("//table//tbody/tr["+i+"]/td["+column+"]"));
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true);", AgeTable);
            //System.out.println(elementCheck.getText());
            //Assert.assertFalse(elementCheck.getText().toUpperCase().contains(value.toUpperCase(Locale.ROOT)));
           if(elementCheck.getText().toUpperCase().contains(value.toUpperCase())){
               System.out.println("ROW:" + i + " " + "COLUMN:" + column +" "+elementCheck.getText() +" "+ "EQUAL VALUE"  +" "+ value);
           }
           else{
               System.out.println("row:" + i + " " + "column:" + column +" "+elementCheck.getText() +" "+ "Not Equal value" +" "+ value);
           }
        }
    }
    public void SearchGoogle(String TextSearch){
        validatehelper.ClickElement(SearchBox);
        validatehelper.SendText(SearchBox,TextSearch);
        validatehelper.ActionEnter(SearchBox);
    }
    public void signIn(String usermane,String password){
        validatehelper.SendText(emailFacebook,usermane);
        validatehelper.SendText(passwordFacebook,password);
        validatehelper.ClickElement(buttonLoginFB);


    }

}
