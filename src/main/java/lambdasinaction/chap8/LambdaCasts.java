package lambdasinaction.chap8;

public class LambdaCasts {
  interface Task {
    public void execute();
  }

  public static void doSomething(Runnable r) {
    r.run();
  }

  public static void doSomething(Task t) {
    t.execute();
  }

  public static void main(String[] args) {
    doSomething(new Task() {
      public void execute() {
        System.out.println("Danger danger!!");
      }
    });

    doSomething(new Runnable() {
      @Override
      public void run() {
        System.out.println("Stranger danger!!");
      }
    });

    // Must cast the lambdas as they match both signatures
    doSomething((Task) () -> System.out.println("Ranger danger!!"));

    doSomething((Runnable) () -> System.out.println("Manger danger!!"));
  }
}