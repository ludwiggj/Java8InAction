package lambdasinaction.chap6;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;
import static lambdasinaction.chap6.PartitionPrimeNumbers.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class PartitionPrimeNumbersTest {

  @Test
  @DisplayName("Primes in range 1..100")
  public void getPrimesInRange1To100() {
    Map<Boolean, List<Integer>> expectedPrimes = new HashMap<Boolean, List<Integer>>() {{
      put(false, asList(
          4, 6, 8, 9, 10, 12, 14, 15, 16, 18, 20, 21, 22, 24, 25, 26, 27, 28, 30, 32, 33, 34, 35, 36, 38,
          39, 40, 42, 44, 45, 46, 48, 49, 50, 51, 52, 54, 55, 56, 57, 58, 60, 62, 63, 64, 65, 66, 68, 69,
          70, 72, 74, 75, 76, 77, 78, 80, 81, 82, 84, 85, 86, 87, 88, 90, 91, 92, 93, 94, 95, 96, 98, 99,
          100
      ));
      put(true, asList(
          2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97
      ));
    }};

    assertAll("Primes in range 1..100",
        () -> assertThat(partitionPrimesTake1(100), is(expectedPrimes)),
        () -> assertThat(partitionPrimesTake2(100), is(expectedPrimes)),
        () -> assertThat(partitionPrimesTake3(100), is(expectedPrimes))
    );
  }
}