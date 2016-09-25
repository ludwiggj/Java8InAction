package lambdasinaction.chap9.ambiguous.quiz_9_3;

public interface A {
  default Number getNumber() {
    return 10;
  }
}