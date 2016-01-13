package lambdasinaction.chap3;

import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Function;

public class MethodReferences {
  //  Quiz 3.6: Method references
  //  What are equivalent method references for the following lambda expressions?

  Function<String, Integer> stringToInteger = (String s) -> Integer.parseInt(s);

  // Equivalent
  Function<String, Integer> stringToIntegerRef = Integer::parseInt;

  BiPredicate<List<String>, String> contains = (list, element) -> list.contains(element);

  // Equivalent
  BiPredicate<List<String>, String> containsRef = List::contains;
}
