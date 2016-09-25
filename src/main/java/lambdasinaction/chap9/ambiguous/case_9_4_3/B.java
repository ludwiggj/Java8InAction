package lambdasinaction.chap9.ambiguous.case_9_4_3;

interface B {
  default void hello() {
    System.out.println("Hello from B");
  }
}
