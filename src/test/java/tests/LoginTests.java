package tests;

import manager.DataProviderUser;
import models.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;



public class LoginTests extends TestBase {

    @BeforeMethod
    public void preCondition() {
        if (app.getHelperUser().isLogged()) {
            app.getHelperUser().logout();
            logger.info("Before method finish logout");
        }
    }
    @Test
    public void loginSuccess1(){

        User user = new User().setEmail("marym@gmail.com").setPassword("Mary2023@");

        logger.info("Test start with test data --->" + user.toString());
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm(user);
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getMessage(),"Logged in success");
        logger.info("Assert check message 'Logged in success' present");
    }



//    @Test (dataProvider =  "loginData", dataProviderClass = DataProviderUser.class)
//    public void loginSuccess(String email,String password) {
//       // logger.info("Test start with test data --->/n" + "email : 'marym@gmail.com' & password : 'Mary2023@'");
//        logger.info("Test start with test data --->/n" + "email: " + email + " & password:  " + password); //- if we have dataProvider
//        app.getHelperUser().openLoginForm();
//        app.getHelperUser().fillLoginForm(email,password);
//        app.getHelperUser().submit();
//        // Assert if element with text "Logged in success" is present
//        Assert.assertEquals(app.getHelperUser().getMessage(), "Logged in success");
//        logger.info("Assert check message 'Logged in success' present");
//    }

    @Test (dataProvider =  "loginModels", dataProviderClass = DataProviderUser.class)
    public void loginSuccessModel(User user) {
        logger.info("Test start with test data --->/n" + user.toString());
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm(user);
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getMessage(), "Logged in success");
        logger.info("Assert check message 'Logged in success' present");
    }
    @Test (dataProvider =  "loginFile", dataProviderClass = DataProviderUser.class)
    public void loginSuccessModelF(User user) {
        logger.info("Test start with test data --->/n" + user.toString());
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm(user);
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getMessage(), "Logged in success");
        logger.info("Assert check message 'Logged in success' present");
    }

    @Test
    public void loginWrongEmail() {
        logger.info("Test negative check if it possible to login with wrong format email ");
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("marym@gmail", "Mary2023@");
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getMessage1(), "Login failed");
        logger.info("Assert check message 'Login failed' present");

    }

    @Test
    public void loginWrongEmail2() {
        logger.info("Test negative check if it possible to login with wrong format email ");
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("marymgmail.com", "Mary2023@");
        //Assert.assertTrue(app.getHelperUser().isError(By.cssSelector(".error")));
        Assert.assertEquals(app.getHelperUser().getErrorText(),"It'snot look like email");
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
        logger.info("Assert check  'It'snot look like email' present");
    }


    @Test
    public void loginWrongPassword() {
        logger.info("Test negative check if it possible to login with wrong format password ");
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("marym@gmail.com", "Mary2023");
        app.getHelperUser().submit();
        //Assert.assertEquals(app.getHelperUser().getMessage1(), "Login failed");
        Assert.assertEquals(app.getHelperUser().getMessage(),"\"Login or Password incorrect\"");
        logger.info("Assert check message 'Login or Password incorrect' present");

    }

    @Test
    public void loginUnregisteredUser(){
        logger.info("Test negative check if it possible to login with valid format data unregistered user ");
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("metyblog@gmail.com", "Dr231010");
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getMessage1(), "Login failed");
        logger.info("Assert check message 'Login failed' present");
    }

    @AfterMethod

    public void postCondition() {
        app.getHelperUser().clickOk();
    }

}
