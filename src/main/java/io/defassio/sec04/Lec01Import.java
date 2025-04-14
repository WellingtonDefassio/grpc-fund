package io.defassio.sec04;

import io.defassio.proto.models.common.Address;
import io.defassio.proto.models.common.BodyStyle;
import io.defassio.proto.models.common.Car;
import io.defassio.proto.models.sec04.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Lec01Import {

    private static final Logger log = LoggerFactory.getLogger(Lec01Import.class);

    public static void main(String[] args) {
        var address = Address.newBuilder().setCity("atlanta").build();
        var car = Car.newBuilder().setBodyStyle(BodyStyle.SUV).build();

        var person = Person.newBuilder()
                .setName("person")
                .setCar(car)
                .setAddress(address)
                .build();

        log.info("{}", person);
    }

}
