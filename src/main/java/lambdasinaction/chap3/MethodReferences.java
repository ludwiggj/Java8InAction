package lambdasinaction.chap3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.function.*;

public class MethodReferences {

  @FunctionalInterface
  public interface Executor {
    public void execute();
  }

  public void accept(Consumer<Integer> c) {
  }

  public static void consumer(Integer i) {
  }

  static void doWork() {
    String name = Thread.currentThread().getName();
    for (int i = 0; i < 5; i++) {
      System.out.printf("%s: %d%n", name, i);
      try {
        Thread.sleep((int) (Math.random() * 50));
      } catch (InterruptedException ie) {
      }
    }
  }

  @FunctionalInterface
  interface Formatter {
    String format(String fmtString, Object... arguments);
  }

  public static void forEach(Formatter formatter) {
    List<String> names = Arrays.asList("Charlie Brown", "Snoopy", "Lucy", "Linus", "Woodstock");

    for (String item : names) {
      System.out.print(formatter.format("%s%n", item));
    }
    System.out.println();
  }

  // There are 4 types of method reference

  // (1) Reference to a static method (unbound)
  //     i.e. className::staticMethodName
  //     e.g. String::valueOf
  public void staticMethodReferences() {
    Executor e = () -> Thread.currentThread().dumpStack();
    e.execute();

    // Unbound static method
    Executor f = Thread::dumpStack;
    f.execute();

    // Valid
    accept(MethodReferences::consumer);

    // Different ways of calling doWork
    new Thread(MethodReferences::doWork).start();
    new Thread(() -> doWork()).start();
    new Thread(new Runnable() {
      @Override
      public void run() {
        doWork();
      }
    }).start();

    // Example with String.format
    forEach((fmt, arg) -> String.format(fmt, arg));
    forEach(String::format);

    // Find numbers
    List<Integer> list = Arrays.asList(12, 5, 45, 18, 33, 24, 40);

    // Using an anonymous class
    System.out.println(Numbers.findNumbers(list, new BiPredicate<Integer, Integer>() {
      public boolean test(Integer i1, Integer i2) {
        return Numbers.isMoreThanFifty(i1, i2);
      }
    }));

    System.out.println(Numbers.findNumbers(list, (i1, i2) -> Numbers.isMoreThanFifty(i1, i2)));

    System.out.println(Numbers.findNumbers(list, Numbers::isMoreThanFifty));
  }

  public static void printIt(Supplier<String> supplier) {
    System.out.println(supplier.get());
  }

  class Car {
    private int id;
    private String color;

    public int getId() {
      return id;
    }
  }

  class Mechanic {
    public void fix(Car c) {
      System.out.println("Fixing car " + c.getId());
    }
  }

  public void execute(Car car, Consumer<Car> c) {
    c.accept(car);
  }

  // (2) Reference to a bound non-static method
  //     i.e. objectName::instanceMethodName
  //     e.g. s::toString
  public void boundNonStaticMethodReferences() {
    Consumer<String> h = s -> System.out.println(s);
    h.accept("Hello");

    // Bound method
    Consumer<String> i = System.out::println;
    i.accept("Hiya");

    // Another example
    String s = "method references are cool";

    printIt(() -> s.toString());
    printIt(s::toString);

    // Let's fix the car
    final Mechanic mechanic = new Mechanic();
    Car car = new Car();

    // Using an anonymous class
    execute(car, new Consumer<Car>() {
      public void accept(Car c) {
        mechanic.fix(c);
      }
    });

    // Using a lambda expression
    execute(car, c -> mechanic.fix(c));

    // Using a method reference
    execute(car, mechanic::fix);
  }

  public static void printItAgain(Function<String, String> function, String value) {
    System.out.println(function.apply(value));
  }

  class Shipment {
    public double calculateWeight() {
      double weight = 0;

      // Calculate weight
      return weight;
    }
  }

  public List<Double> calculateOnShipments(List<Shipment> l, Function<Shipment, Double> f) {
    List<Double> results = new ArrayList<>();

    for (Shipment s : l) {
      results.add(f.apply(s));
    }

    return results;
  }

  interface TriFunction<T, U, V, R> {
    R apply(T t, U u, V v);
  }

  class Sum {
    Integer doSum(String s1, String s2) {
      return Integer.parseInt(s1) + Integer.parseInt(s2);
    }
  }

  TriFunction<Sum, String, String, Integer> anon =
      new TriFunction<Sum, String, String, Integer>() {
        @Override
        public Integer apply(Sum s, String arg1, String arg2) {
          return s.doSum(arg1, arg2);
        }
      };

  // (3) Reference to an unbound non-static method
  //     i.e. className::instanceMethodName
  //     e.g. Object::toString
  public void unboundNonStaticMethodReferences() {
    printItAgain(s -> s.toString(), "some string to be printed");
    printItAgain(String::toString, "some string to be printed");

    // Shipments
    List<Shipment> l = new ArrayList<Shipment>();

    // Using an anonymous class
    calculateOnShipments(l, new Function<Shipment, Double>() {
      public Double apply(Shipment s) { // The object
        return s.calculateWeight(); // The method
      }
    });

    // Using a lambda expression
    calculateOnShipments(l, s -> s.calculateWeight());

    // Using a method reference
    calculateOnShipments(l, Shipment::calculateWeight);

    // Sums
    System.out.println(String.format("The answer is %d", anon.apply(new Sum(), "1", "4")));

    TriFunction<Sum, String, String, Integer> lambda = (Sum s, String arg1, String arg2) -> s.doSum(arg1, arg2);
    System.out.println(String.format("The answer is %d", lambda.apply(new Sum(), "10", "40")));

    TriFunction<Sum, String, String, Integer> mRef = Sum::doSum;
    System.out.println(String.format("The answer is %d", mRef.apply(new Sum(), "100", "400")));
  }

  class Employee {
    String name;
    Integer age;

    Employee(String name, Integer age) {
      this.name = name;
      this.age = age;
    }
  }

  @FunctionalInterface
  interface EmployeeProvider {
    Employee getEmployee(String name, Integer age);
  }

  public static Employee createEmployee(EmployeeProvider provider, String name, Integer age) {
    Employee e = provider.getEmployee(name, age);
    System.out.printf("Created Employee, Name: [%s] Age: [%s]%n", name, age);
    return e;
  }

  // (4) Reference to a constructor
  //     i.e. className::new
  //     e.g. String::new
  public void constructorReferences() {
    // A constructor reference consists of a qualifier, followed by the :: symbol, followed by
    // the keyword new. The qualifier is always a type, and the new keyword is the name of the
    // referenced constructor. The qualifier type must support the creation of instances; for
    // example, it cannot be the name of an abstract class or interface.

    IntFunction<Integer> intToInteger = Integer::new;
    Function<String, Integer> stringToInteger = Integer::new;

    System.out.println(intToInteger.apply(7));
    System.out.println(stringToInteger.apply("78"));

    createEmployee((name, age) -> new Employee(name, age), "John Doe", 47);
    createEmployee(Employee::new, "John Doe", 47);

    // If a constructor takes no arguments, a Supplier will do the job:

    // Using an anonymous class
    Supplier<List<String>> s1 = new Supplier() {
         public List<String> get() {
             return new ArrayList<String>();
         }
    };
    List<String> l1 = s1.get();

    // Using a lambda expression
    Supplier<List<String>> s2 = () -> new ArrayList<String>();
    List<String> l2 = s2.get();

    // Using a method reference
    Supplier<List<String>> s3 = ArrayList::new;
    List<String> l3 = s3.get();

    // If the constructor takes an argument, we can use the Function interface. For example:

    // Using an anonymous class
    Function<String, Integer> f1 =
         new Function<String, Integer>() {
             public Integer apply(String s) {
                 return new Integer(s);
             }
    };

    Integer i1 = f1.apply("100");

    // Using a lambda expression
    Function<String, Integer> f2 = s -> new Integer(s);
    Integer i2 = f2.apply("100");

    // Using a method reference
    Function<String, Integer> f3 = Integer::new;
    Integer i3 = f3.apply("100");

    // If the constructor takes two arguments, we use the BiFunction interface:

    // Using a anonymous class
    BiFunction<String, String, Locale> bf1 = new BiFunction<String, String, Locale>() {
         public Locale apply(String lang, String country) {
             return new Locale(lang, country);
         }
    };

    Locale loc1 = bf1.apply("en","UK");

    // Using a lambda expression
    BiFunction<String, String, Locale> bf2 = (lang, country) -> new Locale(lang, country);
    Locale loc2 = bf2.apply("en","UK");

    // Using a method reference
    BiFunction<String, String, Locale> bf3 = Locale::new;
    Locale loc3 = bf3.apply("en","UK");


  }

  // NOT ALLOWED - Reference to a bound static method
  // i.e. objectName::staticMethodName

  public void boundStaticMethodReferences() {
    // The method accept(Consumer<Integer>) is not applicable for the arguments (this::consumer)

    // accept(this::consumer);

    // Pop-up error says "Static method referenced through non-static qualifier"
    // Compilation error is: "java: invalid method reference, static bound method reference"

    // Same deal here... a bound non-static method
    // Executor g = Thread.currentThread()::dumpStack;
  }

  public static void main(String[] args) {
    MethodReferences mr = new MethodReferences();

    mr.staticMethodReferences();
    mr.boundNonStaticMethodReferences();
    mr.unboundNonStaticMethodReferences();
    mr.constructorReferences();
  }
}