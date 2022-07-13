package service;

import java.util.HashSet;
import java.util.Set;

public class Participant {

    private final String name;

    private final String surname;

    private final Dish dish;

    private final Set<Ingredient> requestedIngredient = new HashSet<>();

    public Participant(String name, String surname, String dish, Set<Ingredient> ingredients){
        this.name = name;
        this.surname = surname;
        this.dish = new Dish(dish, ingredients);
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Set<Ingredient> getRequestedIngredient() {
        return requestedIngredient;
    }

    public Dish getDish() {
        return dish;
    }

    @Override
    public String toString() {
        return "Participant{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", dish=" + dish +
                ", requestedIngredient=" + requestedIngredient +
                "}\n";
    }
}
