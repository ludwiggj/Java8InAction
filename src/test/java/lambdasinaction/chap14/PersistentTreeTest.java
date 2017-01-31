package lambdasinaction.chap14;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static lambdasinaction.chap14.PersistentTree.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class PersistentTreeTest {
  PersistentTree t;

  @BeforeEach
  public void setUp() {
    t = new PersistentTree("Mary", 22,
        new PersistentTree("Emily", 20,
            new PersistentTree("Alan", 50, null, null),
            new PersistentTree("Georgie", 23, null, null)
        ),
        new PersistentTree("Tian", 29,
            new PersistentTree("Raoul", 23, null, null),
            null
        )
    );
  }

  @Test
  public void canLookupNodes() {
    assertThat(lookup("Raoul", -1, t), is(23));
    assertThat(lookup("Jeff", -1, t), is(-1));
  }

  @Test
  public void updateTests() {
    PersistentTree f = fupdate("Jeff", 80, t);

    // Jeff is in f, but not in t
    assertThat(lookup("Jeff", -1, f), is(80));
    assertThat(lookup("Jeff", -1, t), is(-1));

    PersistentTree u = update("Jim", 40, t);

    // Jeff is in f, but not in t or u
    assertThat(lookup("Jeff", -1, f), is(80));
    assertThat(lookup("Jeff", -1, t), is(-1));
    assertThat(lookup("Jeff", -1, u), is(-1));

    // Jim is not in f, but is in t and u
    assertThat(lookup("Jim", -1, f), is(-1));
    assertThat(lookup("Jim", -1, t), is(40));
    assertThat(lookup("Jim", -1, u), is(40));

    PersistentTree f2 = fupdate("Jordan", 75, t);

    assertThat(lookup("Jordan", -1, f2), is(75));

    // f2 built from t altered by update() above, so Jim is still present = 40
    assertThat(lookup("Jim", -1, f2), is(40));
  }
}