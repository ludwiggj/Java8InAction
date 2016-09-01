package lambdasinaction.chap6;

import static java.util.stream.Collectors.*;
import static lambdasinaction.chap6.Dish.menu;

public class Reducing {

    public static void main(String ... args) {
        System.out.println("Total calories in MENU: " + calculateTotalCalories());
        System.out.println("Total calories in MENU: " + calculateTotalCaloriesWithMethodReference());
        System.out.println("Total calories in MENU: " + calculateTotalCaloriesWithoutCollectors());
        System.out.println("Total calories in MENU: " + calculateTotalCaloriesUsingSum());
    }

    private static int calculateTotalCalories() {
        return menu.stream().collect(reducing(0, Dish::getCalories, (Integer i, Integer j) -> i + j));
    }

    private static int calculateTotalCaloriesWithMethodReference() {
        return menu.stream().collect(reducing(0, Dish::getCalories, Integer::sum));
    }

    private static int calculateTotalCaloriesWithoutCollectors() {
        return menu.stream().map(Dish::getCalories).reduce(Integer::sum).get();
    }

    private static int calculateTotalCaloriesUsingSum() {
        return menu.stream().mapToInt(Dish::getCalories).sum();
    }
}