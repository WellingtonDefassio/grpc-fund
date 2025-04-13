package io.defassio.sec3;

import io.defassio.proto.models.sec03.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Lec02Serialization {

    private static final Logger log = LoggerFactory.getLogger(Lec02Serialization.class);
    public static Path PATH = Path.of("person.out");

    public static void main(String[] args) throws IOException {
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

        serialize(person);
        log.info("{}", deserialize());
        log.info("equals: {}", person.equals(deserialize()));
        log.info("equals: {}", person == deserialize());
        log.info("bytes length: {}", person.toByteArray().length);
    }

    public static void serialize(Person person) throws IOException {
        try (var stream = Files.newOutputStream(PATH)) {
            person.writeTo(stream);
        }
    }

    public static Person deserialize() throws IOException {
        try (var stream = Files.newInputStream(PATH)) {
            return Person.parseFrom(stream);
        }
    }
}
