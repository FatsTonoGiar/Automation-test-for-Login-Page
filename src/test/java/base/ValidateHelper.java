package base;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import java.util.Arrays;

public class ValidateHelper {
    public WebDriver driver;
    public WebDriverWait wait;
    public Actions actions;
    public ValidateHelper(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
        actions = new Actions(driver);
    }

    public void SendText(WebElement element, String text){
        waitForLoad();
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.sendKeys(text);
    }

    public void ClickElement(WebElement element){
        waitForLoad();
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    public void waitForLoad(){
        ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>(){
            public Boolean apply(WebDriver driver){
                return ((JavascriptExecutor)driver).executeScript("return document.readyState").toString().equals("complete");
            }
        };
        try {
            Thread.sleep(1000);
            WebDriverWait wait = new WebDriverWait(driver, 30);
            wait.until(expectation);
        } catch (InterruptedException e) {
            System.out.println("Timeout" + Arrays.toString(e.getStackTrace()));
        }
    }

    public void getUrl(){
        waitForLoad();
        driver.getCurrentUrl();
    }

    public void getCurrentTitle(){
        waitForLoad();
        driver.getTitle();
    }

    public void ActionEnter(WebElement element){
        waitForLoad();
        wait.until(ExpectedConditions.elementToBeClickable(element));
        actions.sendKeys(Keys.ENTER).build().perform();
    }

    public boolean VerifyValue(WebElement element,String value, String ConfirmValue){
        waitForLoad();
        wait.until(ExpectedConditions.elementToBeClickable(element));
        Assert.assertEquals(element.getAttribute(value),ConfirmValue);
        return true;
    }

}
