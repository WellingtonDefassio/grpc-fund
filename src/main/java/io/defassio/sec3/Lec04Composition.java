package io.defassio.sec3;

import io.defassio.proto.models.sec03.Address;
import io.defassio.proto.models.sec03.School;
import io.defassio.proto.models.sec03.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Lec04Composition {

    private static final Logger log = LoggerFactory.getLogger(Lec04Composition.class);

    public static void main(String[] args) {
        //create student
        Address address = Address.newBuilder()
                .setStreet("123 street")
                .setCity("atlanta")
                .setState("GA")
                .build();

        Student student = Student.newBuilder()
                .setName("moiseis")
                .setAddress(address)
                .build();

        //create school

        School school = School.newBuilder()
                .setId(1)
                .setName("High school")
                .setAddress(address.toBuilder().setState("other street"))
                .build();

        log.info("school: {}", school);
        log.info("student: {}", student);

    }
}
