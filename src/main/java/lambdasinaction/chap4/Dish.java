package lambdasinaction.chap4;

public class Dish {

  public static final Dish PORK = new Dish("pork", false, 800, Type.MEAT);
  public static final Dish BEEF = new Dish("beef", false, 700, Type.MEAT);
  public static final Dish CHICKEN = new Dish("chicken", false, 400, Type.MEAT);
  public static final Dish FRENCH_FRIES = new Dish("french fries", true, 530, Type.OTHER);
  public static final Dish RICE = new Dish("rice", true, 350, Type.OTHER);
  public static final Dish SEASON_FRUIT = new Dish("season fruit", true, 120, Type.OTHER);
  public static final Dish PIZZA = new Dish("pizza", true, 550, Type.OTHER);
  public static final Dish PRAWNS = new Dish("prawns", false, 400, Type.FISH);
  public static final Dish SALMON = new Dish("salmon", false, 450, Type.FISH);
  public static final String MYSTERY_FISH = "mysteryFish";
  public static final boolean NOT_VEGETARIAN = false;

  private final String name;
  private final boolean vegetarian;
  private final int calories;
  private final Type type;

  public enum Type {MEAT, FISH, OTHER;}

  public enum CaloricLevel {DIET, NORMAL, FAT;}

  public Dish(String name, boolean vegetarian, int calories, Type type) {
    this.name = name;
    this.vegetarian = vegetarian;
    this.calories = calories;
    this.type = type;
  }

  public String getName() {
    return name;
  }

  public boolean isVegetarian() {
    return vegetarian;
  }

  public int getCalories() {
    return calories;
  }

  public Type getType() {
    return type;
  }

  public CaloricLevel getCaloricLevel() {
    if (calories <= 400) return CaloricLevel.DIET;
    else if (calories <= 700) return CaloricLevel.NORMAL;
    else return CaloricLevel.FAT;
  }

  @Override
  public String toString() {
    return "Dish { " +
        "name='" + name + '\'' +
        ", vegetarian=" + vegetarian +
        ", calories=" + calories +
        ", type=" + type +
        " }";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Dish)) return false;

    Dish dish = (Dish) o;

    if (vegetarian != dish.vegetarian) return false;
    if (calories != dish.calories) return false;
    if (name != null ? !name.equals(dish.name) : dish.name != null) return false;
    return type == dish.type;
  }

  @Override
  public int hashCode() {
    int result = name != null ? name.hashCode() : 0;
    result = 31 * result + (vegetarian ? 1 : 0);
    result = 31 * result + calories;
    result = 31 * result + (type != null ? type.hashCode() : 0);
    return result;
  }
}