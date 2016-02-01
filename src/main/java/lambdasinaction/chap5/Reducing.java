package lambdasinaction.chap5;
import lambdasinaction.chap4.*;

import java.util.*;

import static lambdasinaction.chap4.Restaurant.menu;

public class Reducing{

    public static void main(String...args){

        List<Integer> numbers = Arrays.asList(3,4,5,1,2);

        Optional<Integer> min = numbers.stream().reduce(Integer::min);
        min.ifPresent(System.out::println);

        int calories = menu.stream()
                           .map(Dish::getCalories)
                           .reduce(0, Integer::sum);
        System.out.println("Number of calories:" + calories);
    }

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
}
