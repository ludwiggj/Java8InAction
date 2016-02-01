package chap5;

import lambdasinaction.chap4.Restaurant;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static lambdasinaction.chap5.Mapping.*;
import static lambdasinaction.chap5.Mapping.getDishNameLengths;
import static lambdasinaction.chap5.Mapping.getDishNames;
import static lambdasinaction.chap5.Mapping.getWordLengths;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class MappingTests {

  @Test
  public void shouldGetDishNames() {
    List<String> expectedDishNames =
        Arrays.asList(
            Restaurant.PORK.getName(),
            Restaurant.BEEF.getName(),
            Restaurant.CHICKEN.getName(),
            Restaurant.FRENCH_FRIES.getName(),
            Restaurant.RICE.getName(),
            Restaurant.SEASON_FRUIT.getName(),
            Restaurant.PIZZA.getName(),
            Restaurant.PRAWNS.getName(),
            Restaurant.SALMON.getName()
        );

    assertThat(getDishNames(), is(expectedDishNames));
  }

  @Test
  public void shouldGetDishNameLengths() throws Exception {
    assertThat(getDishNameLengths(), is(Arrays.asList(4, 4, 7, 12, 4, 12, 5, 6, 6)));
  }

  @Test
  public void shouldGetWordLengths() throws Exception {
    assertThat(getWordLengths(), is(Arrays.asList(2, 5)));
  }

  @Test
  public void shouldGetWordLengths2() throws Exception {
    assertThat(getWordLengths2(), is(Arrays.asList(7, 5)));
  }

  @Test
  public void shouldGetIncorrectUniqueCharacters() throws Exception {
    String[][] actual = incorrectUniqueCharacters().toArray(new String[][]{});
    String[][] expected = new String[][]{
        new String[]{"H", "e", "l", "l", "o"},
        new String[]{"W", "o", "r", "l", "d"}
    };

    assertThat(Arrays.deepEquals(actual, expected), is(true));
  }

  @Test
  public void shouldGetUniqueCharacters() throws Exception {
    assertThat(uniqueCharacters(), is(Arrays.asList("H", "e", "l", "o", "W", "r", "d")));
  }

  @Test
  public void shouldGetUniqueCharacters2() throws Exception {
    assertThat(uniqueCharacters2(), is(Arrays.asList("H", "e", "l", "o", "W", "r", "d")));
  }

  @Test
  public void shouldSquareListOfNumbers() throws Exception {
    List<Integer> input = Arrays.asList(1, 2, 3, 4, 5);
    List<Integer> expected = Arrays.asList(1, 4, 9, 16, 25);

    assertThat(squareNumbers(input), is(expected));
  }

  @Test
  public void shouldGetAllPairs() throws Exception {
    Integer[][] actual = getAllPairs(Arrays.asList(1, 2, 3), Arrays.asList(3, 4)).toArray(new Integer[][]{});
    Integer[][] expected = new Integer[][]{
        new Integer[]{1, 3}, new Integer[]{1, 4},
        new Integer[]{2, 3}, new Integer[]{2, 4},
        new Integer[]{3, 3}, new Integer[]{3, 4}
    };

    assertThat(actual, is(expected));
  }

  @Test
  public void shouldGetAllPairsWhoseSumIsDivisibleByThree() throws Exception {
    Integer[][] actual = getAllPairsWhoseSumIsDivisibleByThree(
        Arrays.asList(1, 2, 3), Arrays.asList(3, 4)).toArray(new Integer[][]{});
    Integer[][] expected = new Integer[][]{
        new Integer[]{2, 4}, new Integer[]{3, 3}
    };

    assertThat(actual, is(expected));
  }

  @Test
  public void shouldGetAllPairsWhoseSumIsDivisibleByThree2() throws Exception {
    Integer[][] actual = getAllPairsWhoseSumIsDivisibleByThree2(
        Arrays.asList(1, 2, 3), Arrays.asList(3, 4)).toArray(new Integer[][]{});
    Integer[][] expected = new Integer[][]{
        new Integer[]{2, 4}, new Integer[]{3, 3}
    };

    assertThat(actual, is(expected));
  }
}