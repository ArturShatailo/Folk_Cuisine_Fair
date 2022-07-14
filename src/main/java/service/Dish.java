package service;

import java.util.Set;

public record Dish(String name, Set<Ingredient> recipe) {

    @Override
    public String toString() {
        return name;
    }
}
