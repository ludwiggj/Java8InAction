package lambdasinaction.chap9.ambiguous;

public class MostSpecific {

  interface A {
    default void hello() {
      System.out.println("Hello from A");
    }
  }

  interface B extends A {
    default void hello() {
      System.out.println("Hello from B");
    }
  }

  static class C implements B, A {
  }

  static class D implements A {
  }

  static class E extends D implements B, A {
  }

  static class F implements B, A {
    public void hello() {
      System.out.println("Hello from F");
    }
  }

  static class G extends F implements B, A {
  }

  public static void main(String... args) {
    // Hello from B
    new C().hello();

    // Hello from B
    new E().hello();

    // Hello from F
    new G().hello();
  }
}