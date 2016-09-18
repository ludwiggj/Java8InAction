package lambdasinaction.chap8.template;

class Database {
  static Customer getCustomerWithId(int id) {
    return new Customer(String.format("Bob_%d", id));
  }
}