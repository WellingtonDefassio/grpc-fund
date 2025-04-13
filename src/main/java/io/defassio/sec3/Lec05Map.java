package io.defassio.sec3;

import io.defassio.proto.models.sec03.Book;
import io.defassio.proto.models.sec03.Car;
import io.defassio.proto.models.sec03.Dealer;
import io.defassio.proto.models.sec03.Library;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class Lec05Map {

    private static final Logger log = LoggerFactory.getLogger(Lec05Map.class);

    public static void main(String[] args) {

        Car car1 = Car.newBuilder().
                setYear(1990)
                .setName("corola")
                .build();

        Car car2 = Car.newBuilder().
                setYear(1991)
                .setName("civic")
                .build();


        Dealer inventory = Dealer.newBuilder()
                .putInventory(car1.getYear(), car1)
                .putInventory(car2.getYear(), car2)
                .build();

    }
}
