package io.defassio.sec01;

import io.defassio.proto.models.sec01.PersonOuterClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleProtoDemo {

    private static final Logger logger = LoggerFactory.getLogger(SimpleProtoDemo.class);

    public static void main(String[] args) {

        PersonOuterClass.Person person = PersonOuterClass.Person
                .newBuilder()
                .setName("sam")
                .setAge(20)
                .build();

        logger.info("{}", person);

    }

}
