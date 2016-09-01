package lambdasinaction.chap6;

import java.util.*;
import java.util.function.*;

import static java.util.stream.Collectors.*;
import static lambdasinaction.chap6.Dish.menu;

public class Summarising {

    public static void main(String ... args) {
        System.out.println("The most caloric dish is: " + findMostCalorificDish(menu));
        System.out.println("The most caloric dish is: " + findMostCalorificDishUsingComparator());
        System.out.println("Total calories in MENU: " + calculateTotalCalories());
        System.out.println("Average calories in MENU: " + calculateAverageCalories());
        System.out.println("Menu statistics: " + calculateMenuStatistics());
        System.out.println("Short MENU: " + getShortMenu());
        System.out.println("Short MENU comma separated: " + getShortMenuCommaSeparated());
    }


    public static long howManyDishes() {
        return menu.stream().collect(counting());
    }

    public static long howManyDishesDirect() {
        return menu.stream().count();
    }

    public static Dish findMostCalorificDish(List<Dish> menu) {
        return menu.stream().collect(reducing((d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2)).get();
    }

    public static Dish findMostCalorificDish2() {
        BinaryOperator<Dish> moreCalorificOf = ((d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2);
        return menu.stream().collect(reducing(moreCalorificOf)).get();
    }

    public static Dish findMostCalorificDishUsingComparator() {
        Comparator<Dish> dishCaloriesComparator = Comparator.comparingInt(Dish::getCalories);
        BinaryOperator<Dish> moreCaloricOf = BinaryOperator.maxBy(dishCaloriesComparator);
        return menu.stream().collect(reducing(moreCaloricOf)).get();
    }

    public static Dish findMostCalorificDishUsingComparator2() {
        Comparator<Dish> dishCaloriesComparator = Comparator.comparingInt(Dish::getCalories);
        return menu.stream().collect((maxBy(dishCaloriesComparator))).get();
    }

    public static int calculateTotalCalories() {
        return menu.stream().collect(summingInt(Dish::getCalories));
    }

    public static Double calculateAverageCalories() {
        return menu.stream().collect(averagingInt(Dish::getCalories));
    }

    public static IntSummaryStatistics calculateMenuStatistics() {
        return menu.stream().collect(summarizingInt(Dish::getCalories));
    }

    private static String getShortMenu() {
        return menu.stream().map(Dish::getName).collect(joining());
    }

    private static String getShortMenuCommaSeparated() {
        return menu.stream().map(Dish::getName).collect(joining(", "));
    }
}
