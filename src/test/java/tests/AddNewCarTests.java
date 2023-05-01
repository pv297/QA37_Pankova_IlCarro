package tests;

import manager.DataProviderCar;
import models.Car;
import models.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;

public class AddNewCarTests extends TestBase {
    @BeforeClass
    public void preCondition() {
        if (!app.getHelperUser().isLogged())
            app.getHelperUser().login(new User().setEmail("marym@gmail.com").setPassword("Mary2023@"));
        logger.info("Logout complete");
    }

    @Test
    public void addNewCarSuccessAll() {
        int i = new Random().nextInt(1000) + 1000;

        Car car = Car.builder()
                .location("Tel Aviv, Israel")
                .manufacture("Mazda")
                .model("M3")
                .year("2022")
                .fuel("Petrol")
                .seats(4)
                .carClass("C")
                .carRegNumber("678-900-" + i)
                .price(50.0)
                .about("Very nice car")
                .build();
        logger.info("Test start with test data --->" + car.toString());
        app.getHelperCar().openCarForm();
        app.getHelperCar().fillCarForm(car);
        //app.getHelperCar().attachPhoto();
        app.getHelperCar().submit();
        Assert.assertTrue(app.getHelperCar().getMessage().contains("added successful"));
       // Assert.assertEquals(app.getHelperCar().getMessage(),car.getManufacture()+" "+car.getModel()+"added successful");
        logger.info("Assert check massage 'added successful' present");
    }
    @Test (dataProvider =  "carSuccess", dataProviderClass = DataProviderCar.class)
    public void addNewCarSuccess(Car car) {
//        int i = new Random().nextInt(1000) + 1000;
//
//        Car car = Car.builder()
//                .location("Tel Aviv, Israel")
//                .manufacture("Volvo")
//                .model("100")
//                .year("2021")
//                .fuel("Gas")
//                .seats(4)
//                .carClass("C")
//                .carRegNumber("678-901-" + i)
//                .price(55.0)
//                .build();

        logger.info("Test start with test data --->" + car.toString());
        app.getHelperCar().openCarForm();
        app.getHelperCar().fillCarForm(car);
        app.getHelperCar().submit();
        Assert.assertTrue(app.getHelperCar().getMessage().contains("added successful"));
        // Assert.assertEquals(app.getHelperCar().getMessage(),car.getManufacture()+" "+car.getModel()+"added successful");
        logger.info("Assert check massage 'added successful' present");
    }
@AfterMethod
    public void posCondition(){
        app.getHelperCar().returnToHome();
}
}
