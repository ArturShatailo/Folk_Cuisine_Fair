package service;

import java.util.Set;

public class Dish {

    private final String name;

    private final Set<Ingredient> recipe;

    public Dish(String name, Set<Ingredient> recipe) {
        this.name = name;
        this.recipe = recipe;
    }

    public String getName() {
        return name;
    }

    public Set<Ingredient> getRecipe() {
        return recipe;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "name='" + name + '\'' +
                ", recipe=" + recipe +
                '}';
    }
}
