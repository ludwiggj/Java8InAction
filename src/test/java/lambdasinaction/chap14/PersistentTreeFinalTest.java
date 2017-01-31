package lambdasinaction.chap14;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static lambdasinaction.chap14.PersistentTreeFinal.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class PersistentTreeFinalTest {
  PersistentTreeFinal t;

  @BeforeEach
  public void setUp() {
    t = new PersistentTreeFinal("Mary", 22,
        new PersistentTreeFinal("Emily", 20,
            new PersistentTreeFinal("Alan", 50, null, null),
            new PersistentTreeFinal("Georgie", 23, null, null)
        ),
        new PersistentTreeFinal("Tian", 29,
            new PersistentTreeFinal("Raoul", 23, null, null),
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
    PersistentTreeFinal f = fupdate("Jeff", 80, t);

    // Jeff is in f, but not in t
    assertThat(lookup("Jeff", -1, f), is(80));
    assertThat(lookup("Jeff", -1, t), is(-1));

    PersistentTreeFinal u = fupdate("Jim", 40, t);

    // Jeff is in f, but not in t or u
    assertThat(lookup("Jeff", -1, f), is(80));
    assertThat(lookup("Jeff", -1, t), is(-1));
    assertThat(lookup("Jeff", -1, u), is(-1));

    // Jim is u, but not in t or f
    assertThat(lookup("Jim", -1, f), is(-1));
    assertThat(lookup("Jim", -1, t), is(-1));
    assertThat(lookup("Jim", -1, u), is(40));

    PersistentTreeFinal f2 = fupdate("Jordan", 75, t);

    assertThat(lookup("Jordan", -1, f2), is(75));
    assertThat(lookup("Jim", -1, f2), is(-1));
  }
}