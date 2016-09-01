package lambdasinaction.chap5.finance;

import java.util.Objects;

class Trader {

	private String name;
	private String city;

	public Trader(String n, String c){
		this.name = n;
		this.city = c;
	}

	public String getName(){
		return this.name;
	}

	public String getCity(){
		return this.city;
	}

	public String toString(){
		return "Trader:" + this.name + " in " + this.city;
	}

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Trader)) return false;
    Trader trader = (Trader) o;
    return Objects.equals(name, trader.name) &&
        Objects.equals(city, trader.city);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, city);
  }
}