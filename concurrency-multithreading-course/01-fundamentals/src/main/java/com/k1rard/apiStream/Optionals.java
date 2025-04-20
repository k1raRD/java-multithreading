package com.k1rard.apiStream;

import com.k1rard.apiStream.streamWithCustomObject.Book;
import com.k1rard.apiStream.streamWithCustomObject.Type;

import java.util.*;

public class Optionals {
    public static void main(String[] args) {
        List<Book> books = new ArrayList<>();
        books.add(new Book("Being and Time", "Heidegger", 560, Type.PHILOSOPHY));
        books.add(new Book("The trial", "Franz Kafka", 240, Type.NOVEL));
        books.add(new Book("Death on the Nile", "Agatha Christie", 370, Type.THRILLER));
        books.add(new Book("Ancient Greece", "Robert F.", 435, Type.HISTORY));
        books.add(new Book("Ancient Rome", "Robert F.", 860, Type.HISTORY));
        books.add(new Book("Death of Virgil", "Hermann Broch", 590, Type.NOVEL));
        books.add(new Book("The Stranger", "Alber Camus", 560, Type.NOVEL));

        // total number of books
//        System.out.println(books.stream().count());

        List<Integer> nums = Arrays.asList(1, 2, 3, 4);
        // We do not want to get a NullPointerException
//        nums.stream().reduce(Integer::max).ifPresent(System.out::println);
        // The maximum number of pages
        books.stream()
                .map(Book::getPages)
                .reduce(Integer::max)
                .ifPresent(System.out::println);

        // We want to get the longest book
        Optional<Book> longestOptionalBook = books.stream().reduce((b1, b2) -> b1.getPages() > b2.getPages() ? b1 : b2);
        longestOptionalBook.ifPresent(System.out::println);

        // sum all pages of all books
        // We have to transform Stream<Integer> into an integer stream "list of integers"
        int totalPages = books.stream().mapToInt(Book::getPages).sum();
//        System.out.println(totalPages);

        OptionalInt optionalInt = books.stream().mapToInt(Book::getPages).max();
//        System.out.println(optionalInt.orElse(0));

    }
}
