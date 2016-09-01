package lambdasinaction.chap5;

import lambdasinaction.chap4.*;

import java.util.*;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static lambdasinaction.chap4.Restaurant.MENU;

public class Mapping {

  public static List<String> getDishNames() {
    return MENU.stream()
        .map(Dish::getName)
        .collect(toList());
  }

  public static List<Integer> getDishNameLengths() {
    return MENU.stream()
        .map(Dish::getName)
        .map(String::length)
        .collect(toList());
  }

  public static List<Integer> getWordLengths() {
    return Arrays.asList("Hi", "World").stream()
        .map(String::length)
        .collect(toList());
  }

  public static List<Integer> getWordLengths2() {
    String[] arrayOfWords = {"Goodbye", "World"};
    Stream<String> streamOfWords = Arrays.stream(arrayOfWords);

    return streamOfWords.map(String::length).collect(toList());
  }

  public static List<String[]> incorrectUniqueCharacters() {
    List<String> words = Arrays.asList("Hello", "World");

    return words.stream()
        .map(word -> word.split(""))
        .distinct()
        .collect(toList());
  }

  public static List<Stream<String>> incorrectUniqueCharacters2() {
    List<String> words = Arrays.asList("Hello", "World");

    return words.stream()
        .map(word -> word.split(""))
        .map(Arrays::stream)
        .distinct()
        .collect(toList());
  }

  public static List<String> uniqueCharacters() {
    List<String> words = Arrays.asList("Hello", "World");

    return words.stream()
        .flatMap((String line) -> Arrays.stream(line.split("")))
        .distinct()
        .collect(toList());
  }

  public static List<String> uniqueCharacters2() {
    List<String> words = Arrays.asList("Hello", "World");

    return words.stream()
        .map(word -> word.split(""))
        .flatMap(Arrays::stream)
        .distinct()
        .collect(toList());
  }

  // Q 5.2.1: Given a list of numbers, how would you return a list of the square of each number?
  public static List<Integer> squareNumbers(List<Integer> numbers) {
    return numbers.stream()
        .map(a -> a * a)
        .collect(toList());
  }

  /*
  Q 5.2.2. Given two lists of numbers, how would you return all pairs of numbers? For example,
  given a list [1, 2, 3] and a list [3, 4] you should return [(1, 3), (1, 4), (2, 3), (2, 4),
  (3, 3), (3, 4)]. For simplicity, you can represent a pair as an array with two elements.
   */

  public static List<Integer[]> getAllPairs(List<Integer> firstList, List<Integer> secondList) {
    return firstList.stream()
        .flatMap(first ->
            secondList.stream()
                .map(second -> new Integer[]{first, second})
        ).collect(toList());
  }

  /*
  Q 5.2.3 3. How would you extend the previous example to return only pairs whose sum is
  divisible by 3? For example, (2, 4) and (3, 3) are valid.
   */

  public static List<Integer[]> getAllPairsWhoseSumIsDivisibleByThree(List<Integer> firstList, List<Integer> secondList) {
    return firstList.stream()
        .flatMap(first ->
            secondList.stream()
                .map(second -> new Integer[]{first, second})
        ).filter(candidate -> (candidate[0] + candidate[1]) % 3 == 0)
        .collect(toList());
  }

  public static List<Integer[]> getAllPairsWhoseSumIsDivisibleByThree2(List<Integer> firstList, List<Integer> secondList) {
    return firstList.stream()
        .flatMap(first ->
            secondList.stream()
                .filter(second -> (first + second) % 3 == 0)
                .map(second -> new Integer[]{first, second})
        ).collect(toList());
  }
}