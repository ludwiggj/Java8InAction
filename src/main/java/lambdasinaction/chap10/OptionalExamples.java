package lambdasinaction.chap10;

import java.util.Map;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.Properties;

import static java.util.Optional.empty;
import static java.util.Optional.of;

public class OptionalExamples {

  public static final Optional<Integer> max(Optional<Integer> i, Optional<Integer> j) {
    return i.flatMap(a -> j.map(b -> Math.max(a, b)));
  }

  public static Optional<String> get(Map<String, String> map, String key) {
    return Optional.ofNullable(map.get(key));
  }

  public static Optional<Integer> stringToInt(String s) {
    try {
      return of(Integer.parseInt(s));
    } catch (NumberFormatException e) {
      return empty();
    }
  }

  public static OptionalInt stringToInt2(String s) {
    try {
      return OptionalInt.of(Integer.parseInt(s));
    } catch (NumberFormatException e) {
      return OptionalInt.empty();
    }
  }

  public static int readDurationImperative(Properties props, String name) {
    String value = props.getProperty(name);
    if (value != null) {
      try {
        int i = Integer.parseInt(value);
        if (i > 0) {
          return i;
        }
      } catch (NumberFormatException nfe) {
      }
    }
    return 0;
  }

  public static int readDurationWithOptional(Properties props, String name) {
    return Optional.ofNullable(props.getProperty(name))
        .flatMap(OptionalExamples::stringToInt)
        // cannot use OptionalInt
        // .flatMap(OptionalExamples::stringToInt2)
        .filter(i -> i > 0)
        .orElse(0);
  }
}