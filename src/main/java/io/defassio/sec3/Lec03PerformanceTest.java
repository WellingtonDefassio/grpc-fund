package io.defassio.sec3;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.protobuf.InvalidProtocolBufferException;
import io.defassio.proto.models.sec03.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Lec03PerformanceTest {

    private static final Logger log = LoggerFactory.getLogger(Lec03PerformanceTest.class);
    private static final ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) {
        Person person = Person
                .newBuilder()
                .setName("sam")
                .setLastName("sam")
                .setAge(21)
                .setEmail("sam@gmail.com")
                .setEmployed(true)
                .setSalary(12000.50)
                .setBankAccountNumber(1234561144)
                .setBalance(-10000)
                .build();

        var jsonPerson = new JsonPerson(
                "sam",
                21L,
                "sam",
                "sam@gmail.com",
                true, 12000.50,
                1234561144L,
                -10000.5
        );

        for (int i = 0; i < 5; i++) {
            runTest("json", () -> json(jsonPerson));
            runTest("proto", () -> proto(person));
        }
    }

    public static void proto(Person person) {
        try {
            byte[] bytes = person.toByteArray();
            Person.parseFrom(bytes);
        } catch (InvalidProtocolBufferException e) {
            throw new RuntimeException(e);
        }
    }

    public static void json(JsonPerson jsonPerson) {
        try {
            byte[] bytes = mapper.writeValueAsBytes(jsonPerson);
            mapper.readValue(bytes, JsonPerson.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void runTest(String testName, Runnable runnable) {
        var start = System.currentTimeMillis();
        for (int i = 0; i < 5_000_000; i++) {
            runnable.run();
        }
        var end = System.currentTimeMillis();
        log.info("time taken for {} - {} ms", testName, (end - start));
    }

}
