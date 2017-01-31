package lambdasinaction.chap14;

import java.util.function.Consumer;

public class PersistentTrainJourney {
  public int price;
  public String leg;
  public PersistentTrainJourney onward;

  public PersistentTrainJourney(int p, String l, PersistentTrainJourney t) {
    price = p;
    leg = l;
    onward = t;
  }

  public static PersistentTrainJourney link(PersistentTrainJourney a, PersistentTrainJourney b) {
    if (a == null) return b;
    PersistentTrainJourney t = a;
    while (t.onward != null) {
      t = t.onward;
    }
    t.onward = b;
    return a;
  }

  public int totalPrice() {
    int totalPrice = price;
    PersistentTrainJourney t = this;
    while (t.onward != null) {
      t = t.onward;
      totalPrice += t.price;
    }
    return totalPrice;
  }

  public String intinerary() {
    String journey = leg;
    PersistentTrainJourney t = this;
    while (t.onward != null) {
      t = t.onward;
      journey += "/" + t.leg;
    }
    return journey;
  }

  public static PersistentTrainJourney append(PersistentTrainJourney a, PersistentTrainJourney b) {
    return a == null ? b : new PersistentTrainJourney(a.price, a.leg + "_copy", append(a.onward, b));
  }

  public static void visit(PersistentTrainJourney journey, Consumer<PersistentTrainJourney> c) {
    if (journey != null) {
      c.accept(journey);
      visit(journey.onward, c);
    }
  }
}