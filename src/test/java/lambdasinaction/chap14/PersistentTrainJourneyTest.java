package lambdasinaction.chap14;

import org.junit.jupiter.api.Test;

import static lambdasinaction.chap14.PersistentTrainJourney.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class PersistentTrainJourneyTest {

  @Test
  public void shouldLinkJourneysDestructively() {
    PersistentTrainJourney tj1 = new PersistentTrainJourney(10, "TJ1_1", new PersistentTrainJourney(30, "TJ1_2", null));
    PersistentTrainJourney tj2 = new PersistentTrainJourney(20, "TJ2_1", new PersistentTrainJourney(50, "TJ2_2", null));

    assertThat(tj1.intinerary(), is("TJ1_1/TJ1_2"));
    assertThat(tj1.totalPrice(), is(40));

    assertThat(tj2.intinerary(), is("TJ2_1/TJ2_2"));
    assertThat(tj2.totalPrice(), is(70));

    PersistentTrainJourney tj1To2 = PersistentTrainJourney.link(tj1, tj2);

    assertThat(tj1To2.intinerary(), is("TJ1_1/TJ1_2/TJ2_1/TJ2_2"));
    assertThat(tj1To2.totalPrice(), is(110));

    // tj2 is intact
    assertThat(tj2.intinerary(), is("TJ2_1/TJ2_2"));
    assertThat(tj2.totalPrice(), is(70));

    // BUT tj1 has been altered destructively, so it's now identical to tj1To2
    assertThat(tj1.intinerary(), is("TJ1_1/TJ1_2/TJ2_1/TJ2_2"));
    assertThat(tj1.totalPrice(), is(110));
  }

  @Test
  public void shouldAppendJourneysNonDestructively() {
    PersistentTrainJourney tj1 = new PersistentTrainJourney(10, "TJ1_1", new PersistentTrainJourney(30, "TJ1_2", null));
    PersistentTrainJourney tj2 = new PersistentTrainJourney(20, "TJ2_1", new PersistentTrainJourney(50, "TJ2_2", null));

    assertThat(tj1.intinerary(), is("TJ1_1/TJ1_2"));
    assertThat(tj1.totalPrice(), is(40));

    assertThat(tj2.intinerary(), is("TJ2_1/TJ2_2"));
    assertThat(tj2.totalPrice(), is(70));

    PersistentTrainJourney tj1To2 = append(tj1, tj2);

    assertThat(tj1To2.intinerary(), is("TJ1_1_copy/TJ1_2_copy/TJ2_1/TJ2_2"));
    assertThat(tj1To2.totalPrice(), is(110));

    // tj2 is intact
    assertThat(tj2.intinerary(), is("TJ2_1/TJ2_2"));
    assertThat(tj2.totalPrice(), is(70));

    // and so is t1
    assertThat(tj1.intinerary(), is("TJ1_1/TJ1_2"));
    assertThat(tj1.totalPrice(), is(40));
  }

  @Test
  public void anotherExample() {
    PersistentTrainJourney tj1 = new PersistentTrainJourney(10, "TJ1_1", new PersistentTrainJourney(30, "TJ1_2", null));
    PersistentTrainJourney tj2 = new PersistentTrainJourney(20, "TJ2_1", new PersistentTrainJourney(50, "TJ2_2", null));

    PersistentTrainJourney appended = append(tj1, tj2);
    visit(appended, tj -> System.out.print(tj.price + " - "));
    System.out.println();

    // A new TrainJourney is created without altering tj1 and tj2.
    PersistentTrainJourney appended2 = append(tj1, tj2);
    visit(appended2, tj -> System.out.print(tj.price + " - "));
    System.out.println();

    // tj1 is altered but it's still not visible in the results.
    PersistentTrainJourney linked = link(tj1, tj2);
    visit(linked, tj -> System.out.print(tj.price + " - "));
    System.out.println();

    // ... but here, if this code is uncommented, tj2 will be appended
    // at the end of the already altered tj1. This will cause a
    // StackOverflowError from the endless visit() recursive calls on
    // the tj2 part of the twice altered tj1.

//    PersistentTrainJourney linked2 = link(tj1, tj2);
//    visit(linked2, tj -> System.out.print(tj.price + " - "));
//    System.out.println();
  }
}