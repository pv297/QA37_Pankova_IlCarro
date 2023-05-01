package manager;

import models.Car;
import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class DataProviderCar {

    @DataProvider
    public Iterator<Object[]> example(){
        List<Object[]> list = new ArrayList<>();

        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> carSuccess(){
        List<Object[]> list = new ArrayList<>();
        int i = new Random().nextInt(1000) + 1000;
        list.add(new Object[]{Car.builder()
                .location("Tel Aviv, Israel")
                .manufacture("Volvo")
                .model("100")
                .year("2021")
                .fuel("Gas")
                .seats(4)
                .carClass("C")
                .carRegNumber("678-901-" + i)
                .price(55.0)
                .build()});

        list.add(new Object[]{Car.builder()
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
                .build()});

        return list.iterator();
    }
}
