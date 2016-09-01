package lambdasinaction.chap5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static lambdasinaction.chap4.Restaurant.*;
import static lambdasinaction.chap5.Mapping.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class MappingTests {


  @Test
  @DisplayName("Get dish names")
  public void shouldGetDishNames() {
    List<String> expectedDishNames =
        Arrays.asList(
            PORK.getName(),
            BEEF.getName(),
            CHICKEN.getName(),
            FRENCH_FRIES.getName(),
            RICE.getName(),
            SEASON_FRUIT.getName(),
            PIZZA.getName(),
            PRAWNS.getName(),
            SALMON.getName()
        );

    assertThat(getDishNames(), is(expectedDishNames));
  }

  @Test
  @DisplayName("Get dish name lengths")
  public void shouldGetDishNameLengths() {
    assertThat(getDishNameLengths(), is(Arrays.asList(4, 4, 7, 12, 4, 12, 5, 6, 6)));
  }

  @Test
  @DisplayName("Get word lengths")
  public void shouldGetWordLengths() {
    assertAll("Word lenghts",
        () -> assertThat(getWordLengths(), is(Arrays.asList(2, 5))),
        () -> assertThat(getWordLengths2(), is(Arrays.asList(7, 5)))
    );
  }

  @Test
  @DisplayName("Failed attempt to find unique characters")
  public void shouldGetIncorrectUniqueCharacters() {
    String[][] expected = new String[][]{
        new String[]{"H", "e", "l", "l", "o"},
        new String[]{"W", "o", "r", "l", "d"}
    };

    assertThat(Arrays.deepEquals(incorrectUniqueCharacters().toArray(new String[][]{}), expected), is(true));
  }

  @Test
  @DisplayName("Get unique characters")
  public void shouldGetUniqueCharacters() {
    assertAll("Unique characters",
        () -> assertThat(uniqueCharacters(), is(Arrays.asList("H", "e", "l", "o", "W", "r", "d"))),
        () -> assertThat(uniqueCharacters2(), is(Arrays.asList("H", "e", "l", "o", "W", "r", "d")))
    );
  }

  @Test
  @DisplayName("Get squared numbers")
  public void shouldSquareListOfNumbers() {
    List<Integer> input = Arrays.asList(1, 2, 3, 4, 5);
    List<Integer> expected = Arrays.asList(1, 4, 9, 16, 25);

    assertThat(squareNumbers(input), is(expected));
  }

  @Test
  @DisplayName("Get all pairs")
  public void shouldGetAllPairs() {
    Integer[][] actual = getAllPairs(Arrays.asList(1, 2, 3), Arrays.asList(3, 4)).toArray(new Integer[][]{});
    Integer[][] expected = new Integer[][]{
        new Integer[]{1, 3}, new Integer[]{1, 4},
        new Integer[]{2, 3}, new Integer[]{2, 4},
        new Integer[]{3, 3}, new Integer[]{3, 4}
    };

    assertThat(actual, is(expected));
  }

  @Test
  @DisplayName("Get all pairs whose sum is divisible by 3")
  public void shouldGetAllPairsWhoseSumIsDivisibleByThree() {

    Integer[][] expected = new Integer[][]{
        new Integer[]{2, 4}, new Integer[]{3, 3}
    };

    assertAll("Divisible by 3",
        () -> assertThat(
            getAllPairsWhoseSumIsDivisibleByThree(Arrays.asList(1, 2, 3), Arrays.asList(3, 4)).toArray(new Integer[][]{}),
            is(expected)
        ),
        () -> assertThat(
            getAllPairsWhoseSumIsDivisibleByThree2(Arrays.asList(1, 2, 3), Arrays.asList(3, 4)).toArray(new Integer[][]{}),
            is(expected)
        )
    );
  }
}