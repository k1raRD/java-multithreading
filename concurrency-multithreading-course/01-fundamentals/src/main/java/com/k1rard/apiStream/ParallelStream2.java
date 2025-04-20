package com.k1rard.apiStream;

import java.util.stream.IntStream;

public class ParallelStream2 {
    public static void main(String[] args) {
        System.out.println(System.getProperty("user.dir"));
        long start = System.currentTimeMillis();
        // sequential stream
        long numberOfPrimes = IntStream.rangeClosed(2, Integer.MAX_VALUE / 100)
                .filter(ParallelStream2::isPrime)
                .count();
        System.out.println("Num of primes : " + numberOfPrimes);
        System.out.println("Time taken (sequential): " + (System.currentTimeMillis() - start) + "ms");
        // parallel stream
        start = System.currentTimeMillis();
        numberOfPrimes = IntStream.rangeClosed(2, Integer.MAX_VALUE / 100)
                .parallel()
                .filter(ParallelStream2::isPrime)
                .count();
        System.out.println("Num of primes: " + numberOfPrimes);
        System.out.println("Time taken (parallel): " + (System.currentTimeMillis() - start) + "ms");
    }

    public static boolean isPrime(long num) {
        if(num <= 1) return false;
        if(num == 2) return true;
        if(num % 2 == 0) return false;

        // We can check the numbers in the range [0, sqrt(N)]
        long maxDivisor = (long) Math.sqrt(num);
        for (int i = 3; i <= maxDivisor ; i += 2) {
           if(num % i == 0)
               return false;
        }
        return true;
    }
}
