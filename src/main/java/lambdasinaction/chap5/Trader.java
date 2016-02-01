package lambdasinaction.chap5;

import java.util.Objects;

public  class Trader{

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

	public void setCity(String newCity){
		this.city = newCity;
	}

	public String toString(){
		return "Trader:"+this.name + " in " + this.city;
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