package com.k1rard.apiStream;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class StringWithStreams {
    public static void main(String[] args) {
        String[] names = {"Adam", "Daniel", "Martha", "Kevin", "Ben", "Joe", "Brad", "Susan"};
        Stream.of(names).filter(x -> x.startsWith("B")).forEach(System.out::println);

        List<String> namesList = new ArrayList<>(List.of(names));
        Stream<String> namesStream = namesList.stream();
        namesStream.forEach(System.out::println);
    }
}
