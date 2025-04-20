package com.k1rard.apiStream;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

public class FilesWIthStream {
    public static void main(String[] args) throws IOException {
        String path = "/home/k1rard/courses-code/Java-Multithreading/concurrency-multithreading-course/01-fundamentals/src/main/java/com/k1rard/apiStream/names.txt";
        Stream<String> namesStream = Files.lines(Paths.get(path));
        List<String> names = namesStream.filter(x -> x.startsWith("A")).toList();
        names.forEach(System.out::println);
    }
}
