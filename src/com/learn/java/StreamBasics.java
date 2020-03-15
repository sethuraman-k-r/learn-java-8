package com.learn.java;

import com.learn.java.model.Dish;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamBasics {

    public static void main(String[] args) {
        List<Dish> menu = Arrays.asList(
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beaf", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH)
        );

        List<String> lowCaloricDishesName = menu.stream()
                .filter(d -> d.getCalories() < 400)
                .sorted(Comparator.comparing(Dish::getCalories))
                .map(Dish::getName)
                .collect(Collectors.toList());
        // menu.parallelStream() - to optimize the multi core CPU's
        System.out.println(lowCaloricDishesName);

        // Grouping elements
        Map<Dish.Type, List<Dish>> dishesByType = menu.stream().
                collect(Collectors.groupingBy(Dish::getType));
        System.out.println(dishesByType);

        List<String> highCaloricDishesName = menu.stream()
                .filter(d -> {
                    System.out.println("filtering " + d.getName());
                    return d.getCalories() > 300;
                })
                .map(Dish::getName)
                .limit(3)
                .collect(Collectors.toList());
        System.out.println(highCaloricDishesName);

        // Similar to Iterators, Streams are traversed exactly once
        List<String> titles = Arrays.asList("Hello", "World", "Again");
        Stream<String> s = titles.stream();
        s.forEach(System.out::println);
//        s.forEach(System.out::println); // java.lang.IllegalStateException: stream has already been operated upon or closed

        /**
         * Streams supports two kinds of operations
         * 1. Intermediate Operation - filter, map, limit, sorted, distinct
         * 2. Terminal Operation - forEach, count, collect
         */

    }

}
