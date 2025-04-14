package io.defassio.sec3;

import io.defassio.proto.models.sec03.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class Lec07DefaultValues {

    private static final Logger log = LoggerFactory.getLogger(Lec07DefaultValues.class);

    public static void main(String[] args) {

        School schoolWithoutAddress = School.newBuilder()
                .build();

        School school = School.newBuilder()
                .setAddress(Address.newBuilder().build())
                .build();

        log.info("{}", school.getId());
        log.info("{}", school.getName());
        log.info("{}", school.getAddress().getCity());

        log.info("is default? : {}", school.getAddress().equals(Address.getDefaultInstance()));
        log.info("has address? : {}", school.hasAddress());
        log.info("has address? : {}", schoolWithoutAddress.hasAddress());

        Library libary1 = Library.newBuilder().build();
        log.info("{}", libary1.getBooksList());

        Dealer dealer = Dealer.newBuilder().build();
        log.info("{}", dealer.getInventoryMap());

        Car car = Car.newBuilder().build();
        log.info("{}", car.getBodyStyle());

    }
}
