package lambdasinaction.chap2;

import lambdasinaction.chap1.Apple;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static lambdasinaction.chap1.ExampleApples.*;
import static lambdasinaction.chap2.PrintingApples.prettyPrintApple;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("Printing")
public class ApplePrintingTest {
  private List<Apple> inventory;

  AppleFormatter colourAndWeight = new AppleFormatter() {
    @Override
    public String accept(Apple apple) {
      return String.format("Colour %s, weight %s", apple.getColour(), apple.getWeight());
    }
  };

  class AppleFancyFormatter implements AppleFormatter {
    public String accept(Apple apple) {
      String characteristic = apple.isHeavy() ? "heavy" : "light";
      return "A " + characteristic + " " + apple.getColour() + " apple";
    }
  }

  class AppleSimpleFormatter implements AppleFormatter {
    public String accept(Apple apple) {
      return "An apple of " + apple.getWeight() + "g";
    }
  }

  @BeforeEach
  public void setup() {
    inventory = Arrays.asList(APPLE_GREEN_80, APPLE_GREEN_155, APPLE_RED_120);
  }

  @Test
  @DisplayName("apples")
  public void printApplesTest() {
    assertAll("printing apples",
        () -> assertThat(prettyPrintApple(inventory, colourAndWeight), is(
            "Colour green, weight 80|Colour green, weight 155|Colour red, weight 120|"
            )
        ),
        () -> assertThat(prettyPrintApple(inventory, new AppleFancyFormatter()), is(
            "A light green apple|A heavy green apple|A light red apple|"
            )
        ),
        () -> assertThat(prettyPrintApple(inventory, new AppleSimpleFormatter()), is(
            "An apple of 80g|An apple of 155g|An apple of 120g|"
            )
        )
    );
  }
}