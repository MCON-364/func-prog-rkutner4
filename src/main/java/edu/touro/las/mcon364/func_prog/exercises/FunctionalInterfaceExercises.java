package edu.touro.las.mcon364.func_prog.exercises;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Functional Interface Practice
 *
 * In this assignment you will:
 *  - Create and return different functional interfaces
 *  - Apply them
 *  - Practice chaining where appropriate
 *
 * IMPORTANT:
 *  - Use lambdas
 *  - Do NOT use anonymous classes
 */
public class FunctionalInterfaceExercises {

    // =========================================================
    // PART 1 — SUPPLIERS
    // =========================================================

    /**
     * 1) Create a Supplier that returns the current year.
     *
     * Hint:
     * You can get the current date using:
     *     LocalDate.now()
     *
     * Then extract the year using:
     *     getYear()
     *
     * Example (not the solution):
     *
     */
    public static Supplier<Integer> currentYearSupplier() {
        Supplier <Integer> currentYear = () -> LocalDate.now().getYear();
        return currentYear;
    }

    /**
     * 2) Create a Supplier that generates a random number
     * between 1 and 100.
     */
    public static Supplier<Integer> randomScoreSupplier() {
        Supplier<Integer> randomNumber = () -> ThreadLocalRandom.current().nextInt(1, 100);
        return randomNumber;
    }

    // =========================================================
    // PART 2 — PREDICATES
    // =========================================================

    /**
     * 3) Create a Predicate that checks whether
     * a string is all uppercase.
     */
    public static Predicate<String> isAllUpperCase() {
        Predicate<String> isAllUpperCase = s -> s == s.toUpperCase();
        return isAllUpperCase;
    }

    /**
     * 4) Create a Predicate that checks whether
     * a number is positive AND divisible by 5.
     *
     * Hint: consider chaining.
     */
    public static Predicate<Integer> positiveAndDivisibleByFive() {
        Predicate<Integer> isPositive = n -> n > 0;
        Predicate<Integer> divisibleByFive = n -> n % 5 == 0;
        return isPositive.and(divisibleByFive);
    }

    // =========================================================
    // PART 3 — FUNCTIONS
    // =========================================================

    /**
     * 5) Create a Function that converts
     * a temperature in Celsius to Fahrenheit.
     *
     * Formula: F = C * 9/5 + 32
     */
    public static Function<Double, Double> celsiusToFahrenheit() {
        return x -> x * 9.0/5 + 32;
    }

    /**
     * 6) Create a Function that takes a String
     * and returns the number of vowels in it.
     *
     * Bonus: Make it case-insensitive.
     */
    public static Function<String, Integer> countVowels() {
        return str -> {
            if (str == null) return 0;

            int count = 0;
            String lower = str.toLowerCase();

            for(char c : lower.toCharArray()) {
                if ("aeiou".indexOf(c) != -1) {
                    count++;
                    }
            }
            return count;
        };
    }

    // =========================================================
    // PART 4 — CONSUMERS
    // =========================================================

    /**
     * 7) Create a Consumer that prints a value
     * surrounded by "***"
     *
     * Example output:
     * *** Hello ***
     */
    public static Consumer<String> starPrinter() {
        return str -> System.out.println("*** " + str + " ***");
    }

    /**
     * 8) Create a Consumer that prints the square
     * of an integer.
     */
    public static Consumer<Integer> printSquare() {
        return  x -> System.out.println(x*x);
    }

    // =========================================================
    // PART 5 — APPLYING FUNCTIONAL INTERFACES
    // =========================================================

    /**
     * 9) Apply:
     *  - A Predicate
     *  - A Function
     *  - A Consumer
     *
     * Process the list as follows:
     *  - Keep only strings longer than 3 characters
     *  - Convert them to lowercase
     *  - Print them
     */
    public static void processStrings(List<String> values) {
        Predicate<String> isLongEnough = s -> s.length() > 3;
        Function<String, String> convertToLowerCase = String::toLowerCase;
        Consumer<String> printer = s-> System.out.println(s);
        for(String value : values) {
            if(isLongEnough.test(value)) {
                value = convertToLowerCase.apply(value);
                printer.accept(value);
            }
        }
    }

    /**
     * 10) Apply:
     *  - A Supplier
     *  - A Predicate
     *  - A Consumer
     *
     * Generate 5 random scores.
     * Print only those above 70.
     */
    public static void generateAndFilterScores() {
        Supplier<Integer> scoreSupplier = () -> (int)(Math.random()*101);
        Predicate<Integer> isGreaterThan70 = n -> n > 70;
        Consumer<Integer> printer = n -> System.out.println(n);

        for (int i = 1; i <= 5; i++) {
            int s = scoreSupplier.get();
            if (isGreaterThan70.test(s)) {
                printer.accept(s);
            }

        }
    }
}
