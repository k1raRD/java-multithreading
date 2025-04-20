package com.k1rard.apiStream.streamWithCustomObject;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class App {
    public static void main(String[] args) {
        List<Book> books = new ArrayList<>();
        books.add(new Book("Being and Time", "Heidegger", 560, Type.PHILOSOPHY));
        books.add(new Book("The trial", "Franz Kafka", 240, Type.NOVEL));
        books.add(new Book("Death on the Nile", "Agatha Christie", 370, Type.THRILLER));
        books.add(new Book("Ancient Greece", "Robert F.", 435, Type.HISTORY));
        books.add(new Book("Ancient Rome", "Robert F.", 860, Type.HISTORY));
        books.add(new Book("Death of Virgil", "Hermann Broch", 590, Type.NOVEL));
        books.add(new Book("The Stranger", "Alber Camus", 560, Type.NOVEL));

//        List<Book> result = books.stream()
//                .filter(book -> book.getType() == Type.NOVEL)
//                .sorted(Comparator.comparing(Book::getPages))
//                .toList();
//        result.forEach(System.out::println);

        List<Book> result = books.stream()
                .filter(book -> book.getTitle().split(" ").length == 2)
                .sorted(Comparator.comparing(Book::getPages))
                .toList();
        result.forEach(System.out::println);
//
//        List<String> result = books.stream()
//                .filter(book -> book.getType() == Type.NOVEL)
//                .sorted(Comparator.comparing(Book::getPages))
//                .map(Book::getAuthor)
//                .toList();
//        result.forEach(System.out::println);

        // grouping by type
        Map<Type, List<Book>> booksByType = books.stream().collect(Collectors.groupingBy(Book::getType));
        booksByType.entrySet().stream().forEach(System.out::println);

        // finding 2 longest books (number of pages)
        List<String> longestBooks = books.stream()
                .filter(b -> b.getPages() > 500)
                .map(Book::getTitle)
                .limit(2)
                .toList();
        longestBooks.forEach(System.out::println);
    }
}
