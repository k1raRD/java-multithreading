package com.k1rard.apiStream;

import java.util.stream.IntStream;

public class IntWithStreams {
    public static void main(String[] args) {
        IntStream.range(0, 10)
                .filter(x -> x % 2 == 0)
                .forEach(x -> System.out.print(x + " "));
    }
}
