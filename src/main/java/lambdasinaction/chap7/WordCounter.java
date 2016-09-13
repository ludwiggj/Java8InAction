package lambdasinaction.chap7;

class WordCounter {
  private final int instanceCount;
  private final int counter;
  private final boolean lastSpace;

  public WordCounter() {
    this(1, 0, true);
  }

  public WordCounter(int instanceCount, int counter, boolean lastSpace) {
    this.instanceCount = instanceCount;
    this.counter = counter;
    this.lastSpace = lastSpace;
  }

  public WordCounter accumulate(Character c) {
    if (Character.isWhitespace(c)) {
      return lastSpace ? this : new WordCounter(instanceCount + 1, counter, true);
    } else {
      return lastSpace ? new WordCounter(instanceCount + 1, counter + 1, false) : this;
    }
  }

  public WordCounter combine(WordCounter wordCounter) {
    WordCounter combinedWordCounter = new WordCounter(
        instanceCount + wordCounter.instanceCount,
        counter + wordCounter.counter,
        wordCounter.lastSpace);

    System.out.println(String.format("COMBINING [%s] [%s] => [%s]", this, wordCounter, combinedWordCounter));

    return combinedWordCounter;
  }

  public int getCounter() {
    return counter;
  }

  @Override
  public String toString() {
    return "WC {" +
        "instanceCount=" + instanceCount +
        ", counter=" + counter +
        '}';
  }
}