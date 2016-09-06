package lambdasinaction.chap6;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class CollectVsReduceTests {

  @Test
  @DisplayName("List from stream via reduce")
  public void shouldConvertStreamToListViaReduce() {
    assertThat(CollectVsReduce.streamToListViaReduce(), is(Arrays.asList(1, 2, 3, 4, 5, 6)));
  }

  @Test
  @DisplayName("List from stream via collect")
  public void shouldConvertStreamToListViaCollect() {
    assertThat(CollectVsReduce.streamToListViaCollect(), is(Arrays.asList(1, 2, 3, 4, 5, 6)));
  }
}
