package tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Collections;

public class LoginTests extends TestBase {

    @BeforeMethod
    public void preCondition() {
        if (app.getHelperUser().isLogged()) {
            app.getHelperUser().logout();
        }
    }

    @Test
    public void loginSuccess() {
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("marym@gmail.com", "Mary2023@");
        app.getHelperUser().submitLogin();
        // Assert if element with text "Logged in success" is present
        Assert.assertEquals(app.getHelperUser().getMassage(), "Logged in success");
    }

    @Test
    public void loginSuccessModel() {
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("marym@gmail.com", "Mary2023@");
        app.getHelperUser().submitLogin();
        Assert.assertEquals(app.getHelperUser().getMassage(), "Logged in success");
    }

    @Test
    public void loginWrongEmail() {
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("marym@gmail", "Mary2023@");
        app.getHelperUser().submitLogin();
        Assert.assertEquals(app.getHelperUser().getMassage1(), "Login failed");
    }

    @Test
    public void loginWrongEmail2() {
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("marymgmail.com", "Mary2023@");
        Assert.assertTrue(app.getHelperUser().isError(By.cssSelector(".error")));
    }

    @Test
    public void loginWrongPassword() {
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("marym@gmail.com", "Mary2023");
        app.getHelperUser().submitLogin();
        Assert.assertEquals(app.getHelperUser().getMassage1(), "Login failed");

    }

    @AfterMethod

    public void postCondition() {
        if(app.getHelperUser().isElementPresent(By.xpath("//button[text()='Ok']")))
        app.getHelperUser().clickOk();
    }


}
