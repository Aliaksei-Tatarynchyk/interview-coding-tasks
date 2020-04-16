package com.interview.coding.tasks;

import java.util.Random;

/**
 * Need to write a lambda that generates 10 random numbers, filters odd numbers, squares them and print them in sorted order.
 * Optionally: print the result on each step
 *
 * This task came from interview to E-Trade (April 15, 2020)
 * @author Aliaksei Tatarynchyk
 */
public class StreamOfRandomNumbersAndFilterOddAndSquareAndPrint {
    public static void main(String[] args) {
        new Random().ints(10, 1, 100)
                .peek(i -> System.out.println("Number: " + i))
                .filter(i -> i % 2 == 1)
                .peek(i -> System.out.println("Odd: " + i))
                .map(i -> i * i)
                .peek(i -> System.out.println("Squared: " + i))
                .sorted()
                .forEach(System.out::println);
    }
}
