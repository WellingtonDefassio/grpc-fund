package io.defassio.sec3;

import io.defassio.proto.models.sec03.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Lec01Scalar {

    private static final Logger log = LoggerFactory.getLogger(Lec01Scalar.class);

    public static void main(String[] args) {
        Person person = Person
                .newBuilder()
                .setLastName("sam")
                .setAge(21)
                .setEmail("sam@gmail.com")
                .setEmployed(true)
                .setSalary(12000.50)
                .setBankAccountNumber(1234561144)
                .setBalance(-10000)
                .build();

        log.info("{}", person);
    }
}
