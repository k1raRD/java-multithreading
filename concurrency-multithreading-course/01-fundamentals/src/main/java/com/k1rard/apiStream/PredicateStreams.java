package com.k1rard.apiStream;

import com.k1rard.apiStream.streamWithCustomObject.Book;
import com.k1rard.apiStream.streamWithCustomObject.Type;

import java.util.ArrayList;
import java.util.List;

public class PredicateStreams {
    public static void main(String[] args) {
        List<Book> books = new ArrayList<>();
        books.add(new Book("Being and Time", "Heidegger", 560, Type.PHILOSOPHY));
        books.add(new Book("The trial", "Franz Kafka", 240, Type.NOVEL));
        books.add(new Book("Death on the Nile", "Agatha Christie", 370, Type.THRILLER));
        books.add(new Book("Ancient Greece", "Robert F.", 435, Type.HISTORY));
        books.add(new Book("Ancient Rome", "Robert F.", 860, Type.HISTORY));
        books.add(new Book("Death of Virgil", "Hermann Broch", 590, Type.NOVEL));
        books.add(new Book("The Stranger", "Alber Camus", 560, Type.NOVEL));

        // allMatch() - checking if a Predicate matches all elements
        boolean allMatch = books.stream().allMatch(book -> book.getPages() > 100);
        System.out.println("allMatch = " + allMatch);

        // noneMatch() - opposite of allMatch
        // short-circuiting: some operations don't need to process the whole
        // stream to produce a result
        boolean noneMatch = books.stream().noneMatch(book -> book.getPages() > 100);
        System.out.println("noneMatch = " + noneMatch);

        // findAny - returns arbitrary element
        // parallelization
        // findFirst() - sequential
        // findAny() - with parallelization
        books.stream()
                .filter(b -> b.getType() == Type.HISTORY)
                .findFirst()
                .ifPresent(System.out::println);
    }
}
