package tests;

import models.User;
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
    public void loginSuccess1(){

        User user = new User().setEmail("marym@gmail.com").setPassword("Mary2023@");
//        user.setEmail("noa@gmail.com");
//        user.setPassword("Nnoa12345$");

        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm(user);
        app.getHelperUser().submit();
        //Assert if element with text "Logged in success" is present
        Assert.assertEquals(app.getHelperUser().getMessage(),"Logged in success");
    }

    @Test
    public void loginSuccess() {
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("marym@gmail.com", "Mary2023@");
        app.getHelperUser().submit();
        // Assert if element with text "Logged in success" is present
        Assert.assertEquals(app.getHelperUser().getMessage(), "Logged in success");
    }

    @Test
    public void loginSuccessModel() {
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("marym@gmail.com", "Mary2023@");
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getMessage(), "Logged in success");
    }

    @Test
    public void loginWrongEmail() {
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("marym@gmail", "Mary2023@");
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getMessage1(), "Login failed");

    }

    @Test
    public void loginWrongEmail2() {
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("marymgmail.com", "Mary2023@");
        //Assert.assertTrue(app.getHelperUser().isError(By.cssSelector(".error")));
        Assert.assertEquals(app.getHelperUser().getErrorText(),"It'snot look like email");
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
    }

    @Test
    public void loginWrongPassword() {
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("marym@gmail.com", "Mary2023");
        app.getHelperUser().submit();
        //Assert.assertEquals(app.getHelperUser().getMessage1(), "Login failed");
        Assert.assertEquals(app.getHelperUser().getMessage(),"\"Login or Password incorrect\"");

    }

    @Test
    public void loginUnregisteredUser(){
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("metyblog@gmail.com", "Dr231010");
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getMessage1(), "Login failed");
    }

    @AfterMethod

    public void postCondition() {
        app.getHelperUser().clickOk();
    }

}
