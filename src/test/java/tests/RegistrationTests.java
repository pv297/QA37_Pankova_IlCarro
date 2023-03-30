package tests;

import models.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

public class RegistrationTests extends TestBase{

    @BeforeMethod
    public void preCondition(){
        if(app.getHelperUser().isLogged()){
            app.getHelperUser().logout();
        }
    }

    @Test
    public void registrationSuccess(){
        Random random = new Random();
        int i = random.nextInt(1000);
        System.out.println(i);

        System.out.println(System.currentTimeMillis());
        int z =(int)(System.currentTimeMillis()/1000)%3600;
        User user = new User()
                .setFirstName("Mary")
                .setLastName("Merry")
                .setEmail("mary"+i+"@gmail.com")
                .setPassword("Mary12345$");

        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicy();
        app.getHelperUser().submit();

        Assert.assertEquals(app.getHelperUser().getMessage(),"You are logged in success");
    }
    @Test
    public void registrationWrongEmail(){
        User user = new User()
                .setFirstName("Ali")
                .setLastName("Alil")
                .setEmail("Alilgmail.com")
                .setPassword("Aa123456$");
        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        Assert.assertEquals(app.getHelperUser().getErrorText(),"Wrong email format\n" +
                "Wrong email format");
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
    }
    @Test
    public void registrationPasswordIsEmpty(){
        User user = new User()
                .setFirstName("Ali")
                .setLastName("Alil")
                .setEmail("alil@gmail.com")
                .setPassword("");
        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicy();

        Assert.assertEquals(app.getHelperUser().getErrorText(),"Password is required");
        //Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
    }
    @Test
    public void registrationWrongPassword(){  // correct length but not correct value
        User user = new User()
                .setFirstName("Ali")
                .setLastName("Alil")
                .setEmail("alil@gmail.com")
                .setPassword("Aa1234567");
        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicy();


        Assert.assertEquals(app.getHelperUser().getErrorText(),"Password must contain 1 uppercase letter, 1 lowercase letter, 1 number and one special symbol of [@$#^&*!]");
        //Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
    }
    @Test
    public void registrationWrongPassword1(){ // incorrect length and any value
        User user = new User()
                .setFirstName("Ali")
                .setLastName("Alil")
                .setEmail("alil@gmail.com")
                .setPassword("Aa1231");
        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicy();


        Assert.assertEquals(app.getHelperUser().getErrorText(),"Password must contain minimum 8 symbols\n" +
                "Password must contain 1 uppercase letter, 1 lowercase letter, 1 number and one special symbol of [@$#^&*!]");
        //Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
    }
    @Test
    public void registrationExistsUser(){
        User user = new User()
                .setFirstName("Mary")
                .setLastName("Merry")
                .setEmail("marym@gmail.com")
                .setPassword("Mary12345$");

        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicy();
        app.getHelperUser().submit();

        Assert.assertEquals(app.getHelperUser().getMessage1(),"Registration failed");

    }


    @AfterMethod
    public void postCondition(){
        app.getHelperUser().clickOk();


    }
}