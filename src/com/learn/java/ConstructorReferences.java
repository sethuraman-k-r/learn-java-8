package com.learn.java;

import com.learn.java.model.Apple;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public class ConstructorReferences {

    public static void main(String[] args) {
        Supplier<Apple> c1 = Apple::new;
//        Supplier<Apple> c1 = () -> new Apple();
        Apple apple = c1.get();

        Function<Integer, Apple> c2 = Apple::new;
        // Function<Integer, Apple> c2 = (weight) -> new Apple(weight);
        Apple apple1 = c2.apply(10);

        BiFunction<String, Integer, Apple> c3 = Apple::new;
        Apple apple2 = c3.apply("Green", 30);

        List<Apple> apples = Arrays.asList(new Apple("green", 80),
                new Apple("green", 155),
                new Apple("red", 120));
        System.out.println(apples);
        apples.sort(Comparator.comparing(Apple::getWeight));
        System.out.println(apples);
        apples.sort(Comparator.comparing(Apple::getWeight).reversed());
        System.out.println(apples);
//        apples.sort(Comparator.comparing(Apple::getWeight).reversed().thenComparing(Apple::getColor));
//        System.out.println(apples);

    }

}
