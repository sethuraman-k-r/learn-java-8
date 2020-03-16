package com.learn.java;

import com.learn.java.model.Dish;

import java.util.*;
import java.util.stream.Collectors;

public class CollectDataUsingStream {

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

        // Counting
        long howManyDishes = menu.stream().collect(Collectors.counting());
//        long howManyDishes = menu.stream().count();
        System.out.println(howManyDishes);

        // Find max and min
        Comparator<Dish> dishCaloriesComparator = Comparator.comparingInt(Dish::getCalories);
        Optional<Dish> mostCalorieDish = menu.stream().collect(Collectors.maxBy(dishCaloriesComparator));
        System.out.println(mostCalorieDish);

        // Summing
        int totalCalories = menu.stream().collect(Collectors.summingInt(Dish::getCalories));
        System.out.println(totalCalories);

        // Average
        double avgCalories = menu.stream().collect(Collectors.averagingInt(Dish::getCalories));
        System.out.println(avgCalories);

        // Statistics
        IntSummaryStatistics menuStatistics = menu.stream().collect(Collectors.summarizingInt(Dish::getCalories));
        System.out.println(menuStatistics);

        // Joining Strings
//        String shortMenu = menu.stream().map(Dish::getName).collect(Collectors.joining());
        String shortMenu = menu.stream().map(Dish::getName).collect(Collectors.joining(", "));
        System.out.println(shortMenu);

//        totalCalories = menu.stream().collect(Collectors.reducing(0, Dish::getCalories, (i ,j) -> i + j));
//        totalCalories = menu.stream().collect(Collectors.reducing(0, Dish::getCalories, Integer::sum));
//        totalCalories = menu.stream().map(Dish::getCalories).reduce(Integer::sum).get();
        totalCalories = menu.stream().mapToInt(Dish::getCalories).sum();
        System.out.println(totalCalories);

    }

}
