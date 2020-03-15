package com.learn.java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

@FunctionalInterface
interface UpdateString {
    String convertString(String str);
}

public class MethodReferences {

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 0);
        System.out.println(numbers);
        System.out.println(filterOddOrEven(numbers, n -> n % 2 == 0));
        System.out.println(filterOddOrEven(numbers, n -> n % 2 != 0));
        numbers.sort(MethodReferences::sortNumbers);
        System.out.println(numbers);

        String s1 = "John";
        String s2 = "Fernandas";
        UpdateString updateString = String::toUpperCase;
        System.out.println(updateString.convertString(s1));

    }

    public static int sortNumbers(int a, int b) {
        return a > b ? -1 : a < b ? 1 : 0;
    }

    public static List<Integer> filterOddOrEven(List<Integer> numbers, Predicate<Integer> numberPredicate) {
        List<Integer> filterNumber = new ArrayList<>();
        for (Integer n :
                numbers) {
            if(numberPredicate.test(n)){
                filterNumber.add(n);
            }
        }
        return filterNumber;
    }


}
