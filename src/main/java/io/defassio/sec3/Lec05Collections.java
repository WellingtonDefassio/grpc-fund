package io.defassio.sec3;

import io.defassio.proto.models.sec03.Book;
import io.defassio.proto.models.sec03.Library;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class Lec05Collections {

    private static final Logger log = LoggerFactory.getLogger(Lec05Collections.class);

    public static void main(String[] args) {

        Book book1 = Book.newBuilder()
                .setTitle("book tittle 1")
                .setAuthor("author")
                .build();

        Book book2 = Book.newBuilder()
                .setTitle("book tittle 2")
                .setAuthor("author")
                .build();

        Library libary1 = Library.newBuilder()
                .addAllBooks(List.of(book1, book2))
                .setName("libary1")
                .build();


    }
}
