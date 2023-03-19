package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginTests extends TestBase{

    @BeforeMethod
    public void preCondition(){

        if(app.getHelperUser().isLogged()){
            app.getHelperUser().logout();
        }
    }

    @Test
    public void loginSuccess(){
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("marym@gmail.com","Mary2023@");
        app.getHelperUser().submitLogin();
        app.getHelperUser().submitLogin2();

        Assert.assertTrue(app.getHelperUser().isLogged());

    }

    @Test
    public void loginSuccessModel(){

        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("marym@gmail.com","Mary2023@");
        app.getHelperUser().submitLogin();
        app.getHelperUser().submitLogin2();

        Assert.assertTrue(app.getHelperUser().isLogged());

    }

}
