package service;

import controllers.MainApplication;

import java.util.HashSet;
import java.util.Set;

public class Main {

    public static void main(String[] args) {

        Set<Participant> participants = new HashSet<>();

        addParticipant(participants);

        Fair f = new Fair(true, 3000.0, participants);
        f.buyIngredients();
        f.divideIngredients();

        System.out.println(f);
        System.out.println(Manager.ingredientsList);
    }

    private static void addParticipant(Set<Participant> participants) {

        System.out.println("Input data of participant (enter 'exit' to complete or 'enter' to continue): ");

        if(!Tech.GetInputStringFunction().equals("exit")){
            System.out.println("Input name: ");
            String name = Tech.GetInputStringFunction();
            System.out.println("Input surname: ");
            String surname = Tech.GetInputStringFunction();
            System.out.println("Input dish name: ");
            String dishname = Tech.GetInputStringFunction();
            System.out.println("Now input ingredient (enter 'exit' to complete): ");
            Set<Ingredient> a = ingredientsInput(new HashSet<>());

            participants.add(new Participant(name, surname, dishname, a));

            addParticipant(participants);
        }

    }

    private static Set<Ingredient> ingredientsInput(Set<Ingredient> a) {
        System.out.println("Input ingredient name: ");
        String ingredientName = Tech.GetInputStringFunction();
        if(!ingredientName.equals("exit")){
            System.out.println("Input ingredient amount: ");
            String amount = Tech.GetInputStringFunction();
            System.out.println("Input ingredient price: ");
            String price = Tech.GetInputStringFunction();
            a.add(new Ingredient(ingredientName, Double.parseDouble(amount), Double.parseDouble(price)));
            ingredientsInput(a);
        }
        return a;
    }

}
