package service;

public class Ingredient {

    private final String name;

    private final double amount;

    private final double price;

    public Ingredient(String name, double amount, double price) {
        this.name = name;
        this.amount = amount;
        this.price = price;
    }

    public double getFullPrice(){
        return price * amount;
    }

    @Override
    public String toString() {
        return name + " (amount: " + amount + ", price: " + price + ")";
    }

//    @Override
//    public int hashCode() {
//        return 31 * name.hashCode() + (int) price;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//
//        if(this == o) return true;
//
//        if(o == null || this.getClass() != o.getClass()) return false;
//
//        Ingredient i = (Ingredient) o;
//
//        return name.equals(i.name) && price == i.price;
//
//    }

}
