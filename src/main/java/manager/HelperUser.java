package manager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HelperUser extends HelperBase{

    public HelperUser(WebDriver wd) {
        super(wd);
    }
    public void openLoginForm(){
        click(By.cssSelector("a[ng-reflect-router-link='login']"));
    }
    public void fillLoginForm(String email, String password){
        type(By.id("email"),email);
        type(By.xpath("//input[@id='password']"),password);
    }

    public void submitLogin(){
        click(By.xpath("//button[@type='submit']"));
    }

    public void submitLogin2(){
        click(By.xpath("//button[@type='button']"));
    }

    public boolean isLogged(){
        return isElementPresent(By.xpath("//a[normalize-space()='Logout']"));

    }
    public void logout() {
        click(By.xpath("//a[normalize-space()='Logout']"));
    }

}
