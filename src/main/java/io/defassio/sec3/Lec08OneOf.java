package io.defassio.sec3;

import io.defassio.proto.models.sec03.Credentials;
import io.defassio.proto.models.sec03.Email;
import io.defassio.proto.models.sec03.Phone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Lec08OneOf {

    private static final Logger log = LoggerFactory.getLogger(Lec08OneOf.class);

    public static void main(String[] args) {

        Email email = Email.newBuilder().setAddress("email@email.com").setPassword("12346").build();
        Phone phone = Phone.newBuilder().setCode(132544).setNumber(33335555).build();

        login(Credentials.newBuilder().setEmail(email).build());
        login(Credentials.newBuilder().setPhone(phone).build());
        login(Credentials.newBuilder().setEmail(email).setPhone(phone).build());

    }

    private static void login(Credentials credentials) {
        switch (credentials.getLoginTypeCase()) {
            case EMAIL -> log.info("login with email -> {}", credentials.getEmail());
            case PHONE -> log.info("login with phone -> {}", credentials.getPhone());
        }
    }
}
