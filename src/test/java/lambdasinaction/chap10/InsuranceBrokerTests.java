package lambdasinaction.chap10;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static lambdasinaction.chap10.Insurance.CHEAPO;
import static lambdasinaction.chap10.InsuranceBroker.findCheapestInsurance;
import static lambdasinaction.chap10.InsuranceBroker.findCheapestInsuranceIdiomatic;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;


public class InsuranceBrokerTests {

  public static final Car CAR = new Car(Optional.empty());
  public static final Person PERSON = new Person(Optional.of(CAR));

  @DisplayName("Cheapest insurance")
  @Test
  public void shouldReturnCheapestInsurance() {
    Insurance cheapo = new Insurance(CHEAPO);

    assertAll("Cheapest insurance",
        () -> assertThat(findCheapestInsurance(PERSON, CAR), is(cheapo)),
        () -> assertThat(findCheapestInsurance(Optional.of(PERSON), Optional.of(CAR)), is(Optional.of(cheapo))),
        () -> assertThat(findCheapestInsuranceIdiomatic(Optional.of(PERSON), Optional.of(CAR)), is(Optional.of(cheapo)))
    );
  }

  @DisplayName("Cannot find cheapest insurance")
  @Test
  public void cannotFindCheapestInsurance() {
    assertAll("Cannot find insurance",
        () -> assertThat(findCheapestInsurance(Optional.of(PERSON), Optional.empty()), is(Optional.empty())),
        () -> assertThat(findCheapestInsurance(Optional.empty(), Optional.of(CAR)), is(Optional.empty())),
        () -> assertThat(findCheapestInsurance(Optional.empty(), Optional.empty()), is(Optional.empty())),
        () -> assertThat(findCheapestInsuranceIdiomatic(Optional.of(PERSON), Optional.empty()), is(Optional.empty())),
        () -> assertThat(findCheapestInsuranceIdiomatic(Optional.empty(), Optional.of(CAR)), is(Optional.empty())),
        () -> assertThat(findCheapestInsuranceIdiomatic(Optional.empty(), Optional.empty()), is(Optional.empty()))
    );
  }
}