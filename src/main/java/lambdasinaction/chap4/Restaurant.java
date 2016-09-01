package lambdasinaction.chap4;

import java.util.Arrays;
import java.util.List;

public class Restaurant {
  public static final Dish PORK = new Dish("pork", false, 800, Dish.Type.MEAT);
  public static final Dish BEEF = new Dish("beef", false, 700, Dish.Type.MEAT);
  public static final Dish CHICKEN = new Dish("chicken", false, 400, Dish.Type.MEAT);
  public static final Dish FRENCH_FRIES = new Dish("french fries", true, 530, Dish.Type.OTHER);
  public static final Dish RICE = new Dish("rice", true, 350, Dish.Type.OTHER);
  public static final Dish SEASON_FRUIT = new Dish("season fruit", true, 120, Dish.Type.OTHER);
  public static final Dish PIZZA = new Dish("pizza", true, 550, Dish.Type.OTHER);
  public static final Dish PRAWNS = new Dish("prawns", false, 400, Dish.Type.FISH);
  public static final Dish SALMON = new Dish("salmon", false, 450, Dish.Type.FISH);

  public static final String MYSTERY_FISH = "mysteryFish";
  public static final boolean NOT_VEGETARIAN = false;

  public static final List<Dish> MENU = Arrays.asList(
      PORK, BEEF, CHICKEN, FRENCH_FRIES, RICE, SEASON_FRUIT, PIZZA, PRAWNS, SALMON);

  public static final List<Dish> EMPTY_MENU = Arrays.asList();
}
