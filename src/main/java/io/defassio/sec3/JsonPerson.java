package io.defassio.sec3;

public record JsonPerson(
        String name,
        Long age,
        String lastName,
        String email,
        boolean employed,
        Double salary,
        Long bankAccountNumber,
        Double balance
){}