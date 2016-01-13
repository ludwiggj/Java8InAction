package lambdasinaction.chap4;
import java.util.*;

public class Dish {

    private final String name;
    private final boolean vegetarian;
    private final int calories;
    private final Type type;

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

    public enum Type { MEAT, FISH, OTHER }

    @Override
    public String toString() {
        return "Dish{" +
            "name='" + name + '\'' +
            ", vegetarian=" + vegetarian +
            ", calories=" + calories +
            ", type=" + type +
            '}';
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

    public static final List<Dish> menu =
            Arrays.asList( new Dish("pork", false, 800, Dish.Type.MEAT),
                           new Dish("beef", false, 700, Dish.Type.MEAT),
                           new Dish("chicken", false, 400, Dish.Type.MEAT),
                           new Dish("french fries", true, 530, Dish.Type.OTHER),
                           new Dish("rice", true, 350, Dish.Type.OTHER),
                           new Dish("season fruit", true, 120, Dish.Type.OTHER),
                           new Dish("pizza", true, 550, Dish.Type.OTHER),
                           new Dish("prawns", false, 400, Dish.Type.FISH),
                           new Dish("salmon", false, 450, Dish.Type.FISH));
}