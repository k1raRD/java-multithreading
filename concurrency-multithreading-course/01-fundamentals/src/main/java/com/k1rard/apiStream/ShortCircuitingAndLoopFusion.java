package com.k1rard.apiStream;

import com.k1rard.apiStream.streamWithCustomObject.Book;
import com.k1rard.apiStream.streamWithCustomObject.Type;

import java.util.ArrayList;
import java.util.List;

public class ShortCircuitingAndLoopFusion {
    public static void main(String[] args) {
        List<Book> books = new ArrayList<>();
        books.add(new Book("Being and Time", "Heidegger", 560, Type.PHILOSOPHY));
        books.add(new Book("The trial", "Franz Kafka", 240, Type.NOVEL));
        books.add(new Book("Death on the Nile", "Agatha Christie", 370, Type.THRILLER));
        books.add(new Book("Ancient Greece", "Robert F.", 435, Type.HISTORY));
        books.add(new Book("Ancient Rome", "Robert F.", 860, Type.HISTORY));
        books.add(new Book("Death of Virgil", "Hermann Broch", 590, Type.NOVEL));
        books.add(new Book("The Stranger", "Alber Camus", 560, Type.NOVEL));

        // finding 2 longest books (number of pages)
        // short-circuiting and loop fusion
        // filter() and map() are different operations, they
        // are merged into the same pass (loop fusion)
        // short-circuiting: some operations don't need to process the whole
        // stream to produce a result
        // here we are looking for just 2 items - so the algorithm
        // terminates after finding 2 items !!!
        List<String> longestBooks = books.stream()
                .filter(b -> {
                    System.out.println(b.getTitle());
                    return b.getPages() > 500;
                })
                .map(b -> {
                    System.out.println("Mapping " + b.getAuthor());
                    return b.getTitle();
                })
                .limit(2)
                .toList();

        //longestBooks.forEach(System.out::println);
    }
}
