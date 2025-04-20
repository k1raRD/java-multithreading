package com.k1rard.apiStream;

import com.k1rard.apiStream.streamWithCustomObject.Book;
import com.k1rard.apiStream.streamWithCustomObject.Type;

import java.util.*;
import java.util.stream.Collectors;

public class ExternalAndInternalIteration {
    public static void main(String[] args) {
        List<Book> books = new ArrayList<>();
        books.add(new Book("Being and Time", "Heidegger", 560, Type.PHILOSOPHY));
        books.add(new Book("The trial", "Franz Kafka", 240, Type.NOVEL));
        books.add(new Book("Death on the Nile", "Agatha Christie", 370, Type.THRILLER));
        books.add(new Book("Ancient Greece", "Robert F.", 435, Type.HISTORY));
        books.add(new Book("Ancient Rome", "Robert F.", 860, Type.HISTORY));
        books.add(new Book("Death of Virgil", "Hermann Broch", 590, Type.NOVEL));
        books.add(new Book("The Stranger", "Alber Camus", 560, Type.NOVEL));

        // External iteration with (collections)
        List<String> titles = new ArrayList<>();

        Iterator<Book> iterator = books.iterator();

        // inherently sequential
        // [item1, item2, item3, item4]
        // No parallelization
        while (iterator.hasNext()) {
            titles.add(iterator.next().getTitle());
        }

        // Stream API - internal iteration
        // Parallel quite easily
        List<String> titles2 = books.stream().map(Book::getTitle).toList();
        titles2.forEach(System.out::println);
    }
}
