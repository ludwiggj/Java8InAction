package lambdasinaction.chap9.ambiguous.quiz_9_3;

public interface B {
  default Integer getNumber() {
    return 42;
  }
}