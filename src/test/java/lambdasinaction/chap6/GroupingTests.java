package lambdasinaction.chap6;

import lambdasinaction.chap4.Dish;
import lambdasinaction.chap4.Dish.CaloricLevel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.*;

import static java.util.Arrays.asList;
import static lambdasinaction.chap4.Dish.*;
import static lambdasinaction.chap4.Dish.Type.*;
import static lambdasinaction.chap6.GroupingDishes.*;
import static lambdasinaction.chap4.Dish.CaloricLevel.*;
import static lambdasinaction.chap6.GroupingTransactions.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class GroupingTests {

  @Nested
  @DisplayName("Group dishes tests")
  class GroupDishes {

    @Test
    @DisplayName("Group dishes by type")
    public void shouldGroupDishesByType() {
      Map<Dish.Type, List<Dish>> expectedDishesByType = new HashMap<Dish.Type, List<Dish>>() {{
        put(FISH, asList(PRAWNS, SALMON));
        put(OTHER, asList(FRENCH_FRIES, RICE, SEASON_FRUIT, PIZZA));
        put(MEAT, asList(PORK, BEEF, CHICKEN));
      }};

      assertThat(groupDishesByType(), is(expectedDishesByType));
    }

    @Test
    @DisplayName("Group dishes by calorific level")
    public void shouldGroupDishesByCalorificLevel() {
      Map<CaloricLevel, List<Dish>> expectedDishesByType =
          new HashMap<CaloricLevel, List<Dish>>() {{
            put(DIET, asList(CHICKEN, RICE, SEASON_FRUIT, PRAWNS));
            put(NORMAL, asList(BEEF, FRENCH_FRIES, PIZZA, SALMON));
            put(FAT, asList(PORK));
          }};

      assertAll("Group dishes by calorific level",
          () -> assertThat(groupDishesByCalorificLevel(), is(expectedDishesByType)),
          () -> assertThat(groupDishesByCalorificLevelMethodRef(), is(expectedDishesByType))
      );
    }

    @Test
    @DisplayName("Group dishes by type and then by calorific level")
    public void shouldGroupDishesByTypeAndThenCalorificLevel() {
      Map<Dish.Type, Map<CaloricLevel, List<Dish>>> expectedDishesByTypeAndThenCalorificLevel =
          new HashMap<Dish.Type, Map<CaloricLevel, List<Dish>>>() {{
            put(FISH, new HashMap<CaloricLevel, List<Dish>>() {{
              put(DIET, asList(PRAWNS));
              put(NORMAL, asList(SALMON));
            }});
            put(OTHER, new HashMap<CaloricLevel, List<Dish>>() {{
              put(DIET, asList(RICE, SEASON_FRUIT));
              put(NORMAL, asList(FRENCH_FRIES, PIZZA));
            }});
            put(MEAT, new HashMap<CaloricLevel, List<Dish>>() {{
              put(DIET, asList(CHICKEN));
              put(NORMAL, asList(BEEF));
              put(FAT, asList(PORK));
            }});
          }};

      assertThat(groupDishedByTypeAndThenCalorificLevel(), is(expectedDishesByTypeAndThenCalorificLevel));
    }

    @Test
    @DisplayName("Count dishes by type")
    public void shouldCountDishesByType() {
      Map<Dish.Type, Long> expectedDishesCountedByType = new HashMap<Dish.Type, Long>() {{
        put(FISH, 2L);
        put(OTHER, 4L);
        put(MEAT, 3L);
      }};

      assertThat(countDishesInGroups(), is(expectedDishesCountedByType));
    }

    @Test
    @DisplayName("Most calorific dish by type")
    public void shouldGetMostCalorificDishesByType() {
      Map<Dish.Type, Optional<Dish>> expectedDishes = new HashMap<Dish.Type, Optional<Dish>>() {{
        put(FISH, Optional.of(SALMON));
        put(OTHER, Optional.of(PIZZA));
        put(MEAT, Optional.of(PORK));
      }};

      assertAll("Most calorific dish by type",
          () -> assertThat(mostCalorificDishesByType(), is(expectedDishes)),
          () -> assertThat(mostCalorificDishesByType2(), is(expectedDishes))
      );
    }

    @Test
    @DisplayName("Most calorific dish by type without optionals")
    public void shouldGetMostCalorificDishesByTypeWithoutOptionals() {
      Map<Dish.Type, Dish> expectedDishes = new HashMap<Dish.Type, Dish>() {{
        put(FISH, SALMON);
        put(OTHER, PIZZA);
        put(MEAT, PORK);
      }};

      assertThat(mostCaloricDishesByTypeWithoutOptionals(), is(expectedDishes));
    }

    @Test
    @DisplayName("Sum menu calories by dish type")
    public void shouldSumMenuCaloriesByDishType() {

      Map<Dish.Type, Integer> expectedDishCalorieSumsByType = new HashMap<Dish.Type, Integer>() {{
        put(FISH, 850);
        put(OTHER, 1550);
        put(MEAT, 1900);
      }};

      assertThat(sumCaloriesByType(), is(expectedDishCalorieSumsByType));
    }

    @Test
    @DisplayName("Menu caloric levels by dish type")
    public void shouldGetMenuCaloricLevelsByDishType() {

      Map<Dish.Type, Set<CaloricLevel>> expectedCaloricLevelsByType = new HashMap<Dish.Type, Set<CaloricLevel>>() {{
        put(FISH, new HashSet<CaloricLevel>() {{
          add(DIET);
          add(NORMAL);
        }});
        put(OTHER, new HashSet<CaloricLevel>() {{
          add(DIET);
          add(NORMAL);
        }});
        put(MEAT, new HashSet<CaloricLevel>() {{
          add(DIET);
          add(NORMAL);
          add(FAT);
        }});
      }};

      assertAll("Menu caloric levels by dish type",
          () -> assertThat(caloricLevelsByType(), is(expectedCaloricLevelsByType)),
          () -> assertThat(caloricLevelsByTypeWithExplicitSet(), is(expectedCaloricLevelsByType))
      );
    }
  }

  @Nested
  @DisplayName("Group transactions tests")
  class GroupTransactions {
    private final Map<GroupingTransactions.Currency, List<Transaction>> expectedGroupedTxs =
        new HashMap<GroupingTransactions.Currency, List<Transaction>>() {{
          put(GroupingTransactions.Currency.EUR, asList(TX_EUR_1, TX_EUR_2, TX_EUR_3, TX_EUR_4));
          put(GroupingTransactions.Currency.USD, asList(TX_USD_1, TX_USD_2, TX_USD_3));
          put(GroupingTransactions.Currency.GBP, asList(TX_GBP_1, TX_GBP_2));
          put(GroupingTransactions.Currency.JPY, asList(TX_JPY_1, TX_JPY_2));
          put(GroupingTransactions.Currency.CHF, asList(TX_CHF_1, TX_CHF_2));
        }};

    @Test
    public void shouldGroupTransactionsImperatively() {
      assertThat(groupTransactionsByCurrencyImperatively(), is(expectedGroupedTxs));
    }

    @Test
    public void shouldGroupTransactionsFunctionally() {
      assertThat(groupTransactionsByCurrencyFunctionally(), is(expectedGroupedTxs));
    }
  }
}