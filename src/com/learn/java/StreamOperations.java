package com.learn.java;

import com.learn.java.model.Dish;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamOperations {

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

        // Filtering with Predicate
        List<Dish> vegetarianMenu = menu.stream().filter(Dish::isVegetarian).collect(Collectors.toList());
        System.out.println(vegetarianMenu);

        // Filtering Unique Elements
        List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
        numbers.stream().distinct().forEach(System.out::println);

        // Truncating a Stream using limit
        List<Dish> dishes1 = menu.stream().filter(d -> d.getCalories() > 300)
                .limit(3)
                .collect(Collectors.toList());
        System.out.println(dishes1);

        // Skipping elements using skip
        List<Dish> dishes2 = menu.stream().filter(d -> d.getCalories() > 300)
                .skip(2)
                .collect(Collectors.toList());
        System.out.println(dishes2);

        // Mapping using Stream
        List<String> dishNames = menu.stream().map(Dish::getName).collect(Collectors.toList());
        System.out.println(dishNames);

        List<Integer> dishNamesLength = menu.stream()
                .map(Dish::getName)
                .map(String::length)
                .collect(Collectors.toList());
        System.out.println(dishNamesLength);

        String[] arrayOfWords = {"Goodbye", "World"};
        Stream<String> streamOfWords = Arrays.stream(arrayOfWords);
//        streamOfWords.forEach(System.out::println);      // Output - Goodbye \n World

        // Below code will not work to extract letters
//        List<String> uniqueLetters = streamOfWords
//                .map(w -> w.split(""))
//                .map(Arrays::stream)
//                .distinct()
//                .collect(Collectors.toList());

        // flatMap - to extract characters from a stream
        List<String> uniqueLetters = streamOfWords
                .map(w -> w.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .collect(Collectors.toList());
        System.out.println(uniqueLetters);

        // Checking to see if a predicate matches at least one element
        if (menu.stream().anyMatch(Dish::isVegetarian)){
            System.out.println("Menu has some vegetarian dishes");
        }

        // Checking to see if a predicate matches all elements
        boolean isHealthy1 = menu.stream().allMatch(d -> d.getCalories() > 1000);
        System.out.println(isHealthy1);

        // Checking to see if a predicate matches none elements
        boolean isHealthy2 = menu.stream().noneMatch(d -> d.getCalories() <= 1000);
        System.out.println(isHealthy2);

        // Finding an element
        Optional<Dish> dish1 = menu.stream().filter(Dish::isVegetarian).findAny();
        System.out.println(dish1);

        menu.stream().filter(Dish::isVegetarian).findAny().ifPresent(d -> {
            System.out.println(d.getName());
        });
    }

}
