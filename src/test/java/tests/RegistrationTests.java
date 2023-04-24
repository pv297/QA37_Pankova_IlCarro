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

    @Test//(enabled = false)
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
        logger.info("Test start with test data --->" + user.toString());
        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicy();
        app.getHelperUser().submit();

        Assert.assertEquals(app.getHelperUser().getMessage(),"You are logged in success");
        logger.info("Assert check message 'You are logged in success' present");
    }

    @Test
    public void registrationEmptyName(){
        User user = new User()
                .setFirstName("")
                .setLastName("Alil")
                .setEmail("alil@gmail.com")
                .setPassword("Aa123456$");

        logger.info("Test start with test data --->" + user.toString());
        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicy();
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getErrorText(),"Name is required");
       // Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
        logger.info("Assert check error text 'Name is required' present");
    }

    @Test
    public void registrationEmptyLastName(){
        User user = new User()
                .setFirstName("Ali")
                .setLastName("")
                .setEmail("alil@gmail.com")
                .setPassword("Aa123456$");

        logger.info("Test start with test data --->" + user.toString());
        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicy();
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getErrorText(),"Last name is required");
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
        logger.info("Assert check error text 'Last name is required' present");
    }
    @Test//(enabled = false)
    public void registrationWrongEmail(){
        User user = new User()
                .setFirstName("Ali")
                .setLastName("Alil")
                .setEmail("alilgmail.com")
                .setPassword("Aa123456$");

        logger.info("Test start with test data --->" + user.toString());
        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicy();
        app.getHelperUser().submit();

        Assert.assertEquals(app.getHelperUser().getErrorText(),"Wrong email format\n" +
                "Wrong email format");
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
        logger.info("Assert check error text 'Wrong email format' present");
    }
    @Test//(enabled = false)
    public void registrationPasswordIsEmpty(){
        User user = new User()
                .setFirstName("Ali")
                .setLastName("Alil")
                .setEmail("alil@gmail.com")
                .setPassword("");

        logger.info("Test start with test data --->" + user.toString());
        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicy();
        app.getHelperUser().submit();

        Assert.assertEquals(app.getHelperUser().getErrorText(),"Password is required");
        //Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
        logger.info("Assert check error text 'Password is required' present");
    }
    @Test//(enabled = false)
    public void registrationWrongPassword(){  // correct length but not correct value
        User user = new User()
                .setFirstName("Ali")
                .setLastName("Alil")
                .setEmail("alil@gmail.com")
                .setPassword("Aa1234567");

        logger.info("Test start with test data --->" + user.toString());
        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicy();
        app.getHelperUser().submit();


        Assert.assertEquals(app.getHelperUser().getErrorText(),"Password must contain 1 uppercase letter, 1 lowercase letter, 1 number and one special symbol of [@$#^&*!]");
        //Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
        logger.info("Assert check error text 'Password must contain 1 uppercase letter, 1 lowercase letter, 1 number and one special symbol of [@$#^&*!]' present");
    }
    @Test//(enabled = false)
    public void registrationWrongPassword1(){ // incorrect length and any value
        User user = new User()
                .setFirstName("Ali")
                .setLastName("Alil")
                .setEmail("alil@gmail.com")
                .setPassword("Aa1231");

        logger.info("Test start with test data --->" + user.toString());
        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicy();
        app.getHelperUser().submit();


        Assert.assertEquals(app.getHelperUser().getErrorText(),"Password must contain minimum 8 symbols\n" +
                "Password must contain 1 uppercase letter, 1 lowercase letter, 1 number and one special symbol of [@$#^&*!]");
        //Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
    }
    @Test//(enabled = false)
    public void registrationExistsUser(){
        User user = new User()
                .setFirstName("Mary")
                .setLastName("Merry")
                .setEmail("marym@gmail.com")
                .setPassword("Mary12345$");

        logger.info("Test start --->" + user.toString());
        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicy();
        app.getHelperUser().submit();

        Assert.assertEquals(app.getHelperUser().getMessage1(),"Registration failed");
        logger.info("Assert check massage 'Registration failed' present");

    }

    @AfterMethod
    public void postCondition(){
        app.getHelperUser().clickOk();


    }
}