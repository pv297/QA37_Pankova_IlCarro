package manager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HelperUser extends HelperBase {

    public HelperUser(WebDriver wd) {
        super(wd);
    }

    public void openLoginForm() {
        click(By.cssSelector("a[ng-reflect-router-link='login']"));
        //xpath - "//a[text()=' Log in ']"
    }

    public void fillLoginForm(String email, String password) {
        type(By.id("email"), email);
        type(By.xpath("//input[@id='password']"), password);
    }

    public void submitLogin() {
        click(By.xpath("//button[@type='submit']"));
    }


    public String getMassage() {
//
        //wait - option 1
        //pause(2000);
        //option 2
//        WebDriverWait wait = new WebDriverWait(wd, Duration.ofSeconds(5));
//        wait.until(ExpectedConditions.visibilityOf(wd.findElement(By.cssSelector(".dialog-container>h2"))));

        return wd.findElement(By.cssSelector(".dialog-container>h2")).getText();
    }
    public String getMassage1() {
        return wd.findElement(By.cssSelector(".dialog-container>h1")).getText();
    }

    public void clickOk() {
        click(By.xpath("//button[text()='Ok']"));
    }

    public boolean isLogged() {
        return isElementPresent(By.xpath("//a[text()=' Logout ']"));
    }

    public void logout() {
        click(By.xpath("//a[text()=' Logout ']"));
    }

    public boolean isError(By locator) {

       return isElementPresent(By.cssSelector(".error"));
    }
}
