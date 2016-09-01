package lambdasinaction.chap5;

import lambdasinaction.chap4.Dish;

import java.util.List;
import java.util.Optional;

public class Reducing {

    public static Integer sumNumbersWithInitialValue(List<Integer> numbers) {
        return numbers.stream().reduce(0, (a, b) -> a + b);
    }

    public static Integer sumNumbersUsingMethodReferenceAndInitialValue(List<Integer> numbers) {
        return numbers.stream().reduce(0, Integer::sum);
    }

    public static Optional<Integer> sumNumbersUsingMethodReferenceButNoInitialValue(List<Integer> numbers) {
        return numbers.stream().reduce(Integer::sum);
    }

    public static Integer getMaxUsingMethodReferenceAndInitialValue(List<Integer> numbers) {
        return numbers.stream().reduce(0, Integer::max);
    }

    public static Optional<Integer> getMaxUsingMethodReferenceButNoInitialValue(List<Integer> numbers) {
        return numbers.stream().reduce(Integer::max);
    }

    public static Integer countDishes(List<Dish> menu) {
        return menu.stream().map(d -> 1).reduce(0, Integer::sum);
    }

    public static Integer sumMenuCalories(List<Dish> menu) {
        return menu.stream().map(Dish::getCalories).reduce(0, Integer::sum);
    }
}