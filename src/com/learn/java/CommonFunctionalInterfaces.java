package com.learn.java;

import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class CommonFunctionalInterfaces {

    public static void main(String[] args) {

        int n = 10;

        boolean isEven = predicateNumber(n, (Integer i) -> (i % 2 == 0));
        System.out.println(isEven);
        // Type Interface - Without specifying the Datatype in lambda parameter
        boolean isOdd = predicateNumber(n, i -> (i % 2 != 0));
        System.out.println(isOdd);

        // Using local variables
        Predicate<Integer> integerPredicate = (Integer i) -> (i % 2 == 0);
        System.out.println(predicateNumber(n, integerPredicate));

//        consumeNumber(n, (Integer a) -> {
//            System.out.println(a + " " + (a + 1));
//        });

        // Method references
        consumeNumber(n, System.out::println);

        Object obj1 = supplyNumber(()->new Integer(n));
        System.out.println(obj1);

    }

    public static boolean predicateNumber(int n, Predicate<Integer> predicate) {
        return predicate.test(n);
    }

    public static void consumeNumber(int n, Consumer<Integer> consumer) {
        consumer.accept(n);
    }

    public static Object supplyNumber(Supplier<Object> supplier) {
        return supplier.get();
    }

    /**
     * BELOW ARE THE COMMON FUNCTIONAL INTERFACES IN JAVA 8
     * Predicate
     * Consumer
     * Function
     * Supplier
     * UnaryOperator
     * BinaryOperator
     * BiPredicate
     * BiConsumer
     * BiFunction
     */

}
