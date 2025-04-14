package io.defassio.sec3;

import io.defassio.proto.models.sec03.BodyStyle;
import io.defassio.proto.models.sec03.Car;
import io.defassio.proto.models.sec03.Dealer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Lec06Map {

    private static final Logger log = LoggerFactory.getLogger(Lec06Map.class);

    public static void main(String[] args) {

        Car car1 = Car.newBuilder().
                setYear(1990)
                .setName("corola")
                .setBodyStyle(BodyStyle.SUV)
                .build();

        Car car2 = Car.newBuilder().
                setYear(1991)
                .setName("civic")
                .setBodyStyle(BodyStyle.SEDAN)
                .build();


        Dealer inventory = Dealer.newBuilder()
                .putInventory(car1.getYear(), car1)
                .putInventory(car2.getYear(), car2)
                .build();

        log.info("{}", inventory);

    }
}
