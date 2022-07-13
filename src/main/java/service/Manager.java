package service;

import java.util.ArrayList;
import java.util.List;

public interface Manager {

    List<Ingredient> ingredientsList = new ArrayList<>();

    void buyIngredients();

    void divideIngredients();

    void collectIngredients();

}
