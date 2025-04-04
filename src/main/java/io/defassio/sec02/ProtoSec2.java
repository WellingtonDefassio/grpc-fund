package io.defassio.sec02;

import io.defassio.proto.models.sec02.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProtoSec2 {

    private static final Logger logger = LoggerFactory.getLogger(ProtoSec2.class);

    public static void main(String[] args) {

        Person person1 = createPerson();
        Person person2 = createPerson();

        logger.info("equals {}", person1.equals(person2));
        logger.info("== {}", person1 == person2);

        Person person3 = person1.toBuilder().setName("Jose").build();

        logger.info("{}", person3);
        logger.info("equals {}", person1.equals(person3));
        logger.info("== {}", person1 == person3);

        Person person4 = person1.toBuilder().clearName().build();

        logger.info("{}", person4);
        logger.info("equals {}", person1.equals(person4));
        logger.info("== {}", person1 == person4);
    }

    public static Person createPerson() {
        return Person
                .newBuilder()
                .setName("sam2")
                .setAge(202)
                .build();
    }

}
