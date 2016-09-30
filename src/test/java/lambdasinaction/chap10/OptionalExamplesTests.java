package lambdasinaction.chap10;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;

import static java.util.Optional.empty;
import static java.util.Optional.of;
import static lambdasinaction.chap10.OptionalExamples.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class OptionalExamplesTests {

  @DisplayName("Max tests")
  @Test
  public void shouldFindMax() {
    assertAll("Max tests",
        () -> assertThat(max(of(3), of(5)), is(Optional.of(5))),
        () -> assertThat(max(of(5), of(3)), is(Optional.of(5))),
        () -> assertThat(max(empty(), of(3)), is(empty())),
        () -> assertThat(max(of(3), empty()), is(empty()))
    );
  }

  @DisplayName("StringToInt")
  @Test
  public void shouldConvertStringToInt() throws Exception {
    assertAll("StringToInt",
        () -> assertThat(stringToInt("1"), is(Optional.of(1))),
        () -> assertThat(stringToInt("One"), is(Optional.empty()))
    );
  }

  @DisplayName("Map tests")
  @Nested
  public class MapTests {
    Map<String, String> map;

    @BeforeEach
    public void setUp() {
      map = new HashMap<String, String>() {{
        put("key", "value");
      }};
    }

    @DisplayName("Missing value")
    @Test
    public void shouldReturnEmptyForMissingValue() {
      assertThat(OptionalExamples.get(map, "notThere"), is(Optional.empty()));
    }

    @DisplayName("Present value")
    @Test
    public void shouldReturnValue() {
      assertThat(OptionalExamples.get(map, "key"), is(Optional.of("value")));
    }
  }

  @DisplayName("Test map")
  @Test
  public void testMap() {
    Properties props = new Properties();
    props.setProperty("a", "5");
    props.setProperty("b", "true");
    props.setProperty("c", "-3");

    assertAll("Test map",
        () -> assertThat(readDurationImperative(props, "a"), is(5)),
        () -> assertThat(readDurationImperative(props, "b"), is(0)),
        () -> assertThat(readDurationImperative(props, "c"), is(0)),
        () -> assertThat(readDurationImperative(props, "d"), is(0)),
        () -> assertThat(readDurationWithOptional(props, "a"), is(5)),
        () -> assertThat(readDurationWithOptional(props, "b"), is(0)),
        () -> assertThat(readDurationWithOptional(props, "c"), is(0)),
        () -> assertThat(readDurationWithOptional(props, "d"), is(0))
    );
  }
}