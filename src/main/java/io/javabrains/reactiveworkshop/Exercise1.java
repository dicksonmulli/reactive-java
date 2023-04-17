package io.javabrains.reactiveworkshop;

import java.util.Set;
import java.util.stream.Collectors;

import static io.javabrains.reactiveworkshop.StreamSources.intNumbersStream;
import static io.javabrains.reactiveworkshop.StreamSources.userStream;

public class Exercise1 {

    public static void main(String[] args) {

        // Use StreamSources.intNumbersStream() and StreamSources.userStream()

        // Print all numbers in the intNumbersStream stream
        intNumbersStream().forEach(n -> printANumber(n));
        printAString("***********");

        // Print numbers from intNumbersStream that are less than 5
        intNumbersStream()
                .filter(n -> n < 5)
                .forEach(n -> printANumber(n));
        printAString("***********");

        // Print the second and third numbers in intNumbersStream that's greater than 5
        intNumbersStream()
                .filter(n -> n > 5)
                .skip(1)
                .limit(2)
                .forEach(n -> printANumber(n));
        printAString("***********");

        //  Print the first number in intNumbersStream that's greater than 5.
        //  If nothing is found, print -1
        Integer value = intNumbersStream()
                .filter(n -> n > 5)
                .findFirst()
                .orElse(-1);
        printANumber(value);
        printAString("***********");

        // Print first names of all users in userStream
        // TODO: Write code here
        userStream().map(user -> user.getFirstName()).forEach(name -> printAString(name));

        // Print first names in userStream for users that have IDs from number stream
        Set<Integer> intNumbers = intNumbersStream().collect(Collectors.toSet());
        userStream()
                .filter(user -> intNumbers.contains(user.getId()))
                .map(user -> user.getFirstName())
                .forEach(Exercise1::printAString);

        // OR
        userStream()
                .filter(user -> intNumbersStream().anyMatch(id -> id == user.getId()))
                .map(user -> user.getFirstName())
                .forEach(Exercise1::printAString);

    }

    private static void printANumber(Integer n) {
        System.out.print(n);
    }

    private static void printAString(String n) {
        System.out.println(n);
    }

}
