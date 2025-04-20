package com.k1rard.apiStream;

import com.k1rard.apiStream.streamWithCustomObject.Book;
import com.k1rard.apiStream.streamWithCustomObject.Type;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MapAndFlatMap {
    public static void main(String[] args) {
        List<Book> books = new ArrayList<>();
        books.add(new Book("Being and Time", "Heidegger", 560, Type.PHILOSOPHY));
        books.add(new Book("The trial", "Franz Kafka", 240, Type.NOVEL));
        books.add(new Book("Death on the Nile", "Agatha Christie", 370, Type.THRILLER));
        books.add(new Book("Ancient Greece", "Robert F.", 435, Type.HISTORY));
        books.add(new Book("Ancient Rome", "Robert F.", 860, Type.HISTORY));
        books.add(new Book("Death of Virgil", "Hermann Broch", 590, Type.NOVEL));
        books.add(new Book("The Stranger", "Alber Camus", 560, Type.NOVEL));

        // map() and flatMap() are similar to selecting a column in SQL
        // number of characters in every word
        List<String> words = Arrays.asList("Adam", "Ana", "Daniel");
        List<Integer> lengths = words.stream().map(String::length).toList();
//        lengths.stream().forEach(System.out::println);

        // create a list containing the squared values
        List<Integer> nums = Arrays.asList(1, 2, 3, 4);
        List<Integer> squaredNums = nums.stream().map(x -> x * x).toList();
//        squaredNums.stream().forEach(System.out::println);

        // flatMap()
        // "hello" "shell" - get all unique characters (h,e,l,e,s)
        String[] array = {"hello", "shell"};
        List<String> unique = Arrays.stream(array)
                .map(w -> w.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .toList();
//        unique.stream().forEach(System.out::println);

        List<Integer> nums1 = Arrays.asList(1, 2, 3);
        List<Integer> nums2 = Arrays.asList(4, 5);
        List<List<Integer>> pairs = nums1.stream()
                .flatMap(i -> nums2.stream().map(j -> Arrays.asList(i, j)))
                .toList();
        System.out.println(Arrays.toString(pairs.toArray()));
    }
}
