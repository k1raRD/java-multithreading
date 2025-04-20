package com.k1rard.apiStream;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

public class ParallelStream3 {
    public static final String DIRECTORY = System.getProperty("user.dir") + "/test/";
    public static void main(String[] args) throws IOException {
        // create the directory
        Files.createDirectory(Paths.get(DIRECTORY));

        ParallelStream3 app = new ParallelStream3();

        // generate large number of Person objects
        List<Person> people = app.generatePeople(1000000);

        // sequential algorithm
        long start = System.currentTimeMillis();
        people.stream().forEach(ParallelStream3::save);
        System.out.println("Time taken (sequential): " + (System.currentTimeMillis() - start) + "ms");
        // parallel algorithm
        start = System.currentTimeMillis();
        people.parallelStream().forEach(ParallelStream3::save);
        System.out.println("Time taken (parallel): " + (System.currentTimeMillis() - start) + "ms");
    }

    private static void save(Person person) {
        try(FileOutputStream fos =
                    new FileOutputStream(new File(DIRECTORY + person.getPersonId() + ".txt"))) {

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Person> generatePeople(int num) {
        return Stream.iterate(0, n -> n + 1 )
                .limit(num)
                .map(Person::new)
                .toList();
    }
}
