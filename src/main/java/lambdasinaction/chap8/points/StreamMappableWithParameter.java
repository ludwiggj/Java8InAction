package lambdasinaction.chap8.points;

import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamMappableWithParameter<T> {
  private Stream<T> stream;

  public StreamMappableWithParameter(Stream<T> stream) {
    this.stream = stream;
  }

  // Example method to support mapping all stream elements with an input parameter
  // This is only to test out method references.
  // This method evaluates the original stream (by performing a terminal operation) before
  // converting it to a new stream, rather than streaming on the fly.
  public <U> Stream<T> mapWithParameter(BiFunction<? super T, U, ? extends T> mapper, U parameter) {
    List<T> originalList = stream.collect(Collectors.toList());

    List<T> mappedList =
        originalList.stream().map(t -> mapper.apply(t, parameter)).collect(Collectors.toList());

    return mappedList.stream();
  }
}