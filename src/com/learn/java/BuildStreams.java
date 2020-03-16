package com.learn.java;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.function.IntSupplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class BuildStreams {

    public static void main(String[] args) {

        // Streams from values
        Stream<String> stream = Stream.of("Java 8 ", "Lambdas ", "In ", "Action");
        stream.map(String::toUpperCase).forEach(System.out::println);

        // Empty Streams
        Stream<String> emptyStream = Stream.empty();

        // Streams from array
        int[] numbers = {2, 3, 5, 7, 11, 13};
        int sum = Arrays.stream(numbers).sum();
        System.out.println(sum);

        // Streams from files
        long uniqueWords = 0;
        try(Stream<String> lines = Files.lines(Paths.get("data.txt"), Charset.defaultCharset())) {
            uniqueWords = lines
                    .flatMap(line -> Arrays.stream(line.split(" ")))
                    .distinct()
                    .count();
            System.out.println(uniqueWords);
        }catch (IOException e){}

        /**
         * Streams from functions - creating infinite streams
         * iterate
         * generate
         */
        System.out.println(Stream.iterate(0, n -> n + 2).limit(10).collect(Collectors.toList()));
        Stream.generate(Math::random)
                .limit(5)
                .forEach(System.out::println);

        // Generating ones array
        IntStream ones = IntStream.generate(() -> 1);
        System.out.println(Arrays.toString(ones.limit(10).toArray()));

        // Generating twos array
        IntStream twos = IntStream.generate(new IntSupplier() {
            @Override
            public int getAsInt() {
                return 2;
            }
        });
        System.out.println(Arrays.toString(twos.limit(10).toArray()));

        // Generating n fibonacci series
        IntSupplier fib = new IntSupplier() {
            private int previous = 0;
            private int current = 1;
            @Override
            public int getAsInt() {
                int oldPrevious = this.previous;
                int nextValue = this.previous + this.current;
                this.previous = this.current;
                this.current = nextValue;
                return oldPrevious;
            }
        };
        System.out.println(Arrays.toString(IntStream.generate(fib).limit(10).toArray()));
    }

}
