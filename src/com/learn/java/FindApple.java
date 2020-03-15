package com.learn.java;

import com.learn.java.model.Apple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

interface ApplePredicate {
    boolean compare(Apple apple);
}

/**
 * This is an example of behaviour parameterization
 */

public class FindApple {

    public static void main(String[] args) {
        List<Apple> apples = Arrays.asList(new Apple("green", 80),
                new Apple("green", 155),
                new Apple("red", 120));

        System.out.println(apples);

        List<Apple> filterApplesByWeight = filterApples(apples, new ApplePredicate() {
            @Override
            public boolean compare(Apple apple) {
                return apple.getWeight() > 80;
            }
        });
        System.out.println(filterApplesByWeight);

        List<Apple> filterApplesByColor = filterApples(apples, new ApplePredicate() {
            @Override
            public boolean compare(Apple apple) {
                return "red".equalsIgnoreCase(apple.getColor());
            }
        });
        System.out.println(filterApplesByColor);

    }

    public static List<Apple> filterApples(List<Apple> apples, ApplePredicate p){
        List<Apple> result = new ArrayList<>();
        for (Apple apple: apples) {
            if(p.compare(apple)){
                result.add(apple);
            }
        }
        return result;
    }
}
