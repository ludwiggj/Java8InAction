package lambdasinaction.chap4;

import java.util.Arrays;
import java.util.List;

import static lambdasinaction.chap4.Dish.*;

public class Restaurant {

  public static final List<Dish> MENU = Arrays.asList(
      PORK, BEEF, CHICKEN, FRENCH_FRIES, RICE, SEASON_FRUIT, PIZZA, PRAWNS, SALMON);

  public static final List<Dish> EMPTY_MENU = Arrays.asList();
}
