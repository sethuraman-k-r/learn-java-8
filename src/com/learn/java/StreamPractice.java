package com.learn.java;

import com.learn.java.model.Trader;
import com.learn.java.model.Transaction;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamPractice {

    public static void main(String[] args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

        // all 2011 transactions and sort by value
        List<Transaction> tr2011 = transactions
                .stream()
                .filter(t -> t.getYear() == 2011)
                .sorted(Comparator.comparing(Transaction::getValue))
                .collect(Collectors.toList());
        System.out.println(tr2011);

        // unique cities of traders
        List<String> cities = transactions
                .stream()
                .map(t -> t.getTrader().getCity())
                .distinct()
                .collect(Collectors.toList());
        System.out.println(cities);

        // all traders from cambridge and sort by name
        List<Trader> traders = transactions
                .stream()
                .map(Transaction::getTrader)
                .filter(t -> t.getCity().contentEquals("Cambridge"))
                .distinct()
                .sorted(Comparator.comparing(Trader::getName)).collect(Collectors.toList());
        System.out.println(traders);

        // traders name - using reduce
        String traderStr1 = transactions
                .stream()
                .map(t -> t.getTrader().getName())
                .distinct()
                .sorted()
                .reduce("", (n1, n2) -> n1 + n2);
        System.out.println(traderStr1);

        // traders name - using collect
        String traderStr2 = transactions
                .stream()
                .map(t -> t.getTrader().getName())
                .distinct()
                .sorted()
                .collect(Collectors.joining());
        System.out.println(traderStr2);

        // any traders based in Milan
        boolean milanBased = transactions
                .stream()
                .anyMatch(t -> t.getTrader().getCity().equals("Milan"));
        System.out.println(milanBased);

        // all transactions value from cambridge traders
        transactions
                .stream()
                .filter(t -> t.getTrader().getCity().equals("Cambridge"))
                .map(Transaction::getValue)
                .forEach(System.out::println);

        // highest value in transactions
        Optional<Integer> highestTransactionAmount = transactions.stream().map(t -> t.getValue()).reduce(Integer::max);
        System.out.println(highestTransactionAmount);

        /**
         * Three primitive stream specializations
         * mapToInt - IntStream
         * mapToLong - LongStream
         * mapToDouble - DoubleStream
         */

        // sum of all transactions
        int sumOfAllTransactions = transactions.stream().mapToInt(Transaction::getValue).sum();
        System.out.println(sumOfAllTransactions);

        // Converting a Transaction Stream to Integer Stream
        IntStream intStream = transactions.stream().mapToInt(Transaction::getValue);
        System.out.println(intStream);

        // Converting a Integer Stream to Stream
        Stream<Integer> stream = intStream.boxed();
        System.out.println(stream);

        /**
         * OptionalInt
         * OptionalDouble
         * OptionalLong
         */

        // Assigning Default Values
        OptionalInt maxCalories = transactions.stream().mapToInt(Transaction::getValue).max();
        System.out.println(maxCalories);

        // Assigning else value if maxCalories is null
        int maxCalorie = maxCalories.orElse(1);

        // Numeric Ranges
        IntStream evenNumbers = IntStream.rangeClosed(1, 100)
                .filter(n -> n % 2 ==0);
//        evenNumbers.forEach(System.out::print);
        System.out.println(evenNumbers.count());
    }

}
