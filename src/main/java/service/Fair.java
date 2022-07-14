package service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Fair implements Manager{

    private boolean registrationStatus;

    private Double totalBalance;

    private final Set<Participant> participants = new HashSet<>();

    public Fair(boolean registrationStatus, Double totalBalance) {
        this.registrationStatus = registrationStatus;
        this.totalBalance = totalBalance;
    }

    public Set<Participant> getParticipants() {
        return participants;
    }

    /*
    * Returns true if any Participant object from 'participants' field collection has 'dish' field with Dish object that
    * has field 'name' equal to @param String 'dishname'
    */
    public boolean validateDish(String dishname) {
        return participants.stream().anyMatch(p -> p.getDish().name().equals(dishname));
    }

    /*
    * Fills collection 'ingredientsList' implemented from Manager interface with Ingredient objects from
    * 'recipe' field of Dish object from 'dish' field of each Participant object in 'participants' collection
    */
    @Override
    public void collectIngredients() {
        participants.forEach( p -> ingredientsList.addAll(p.getDish().recipe()));
    }

    /*
    * Sums up all values of 'price' field of Ingredient objects from 'ingredientsList' collection.
    * sum is deducted from 'totalBalance' field of Fair object.
    */
    @Override
    public void buyIngredients(){
        collectIngredients();
        double sum = ingredientsList.stream().mapToDouble(Ingredient::getFullPrice).sum();
        totalBalance = totalBalance - sum;
    }

    /*
    * Gets Ingredient objects from 'ingredientsList' collection if they are contained in 'recipe' field of 'dish' field of
    * Participant object from 'participants' collection.
    * Adds these Ingredient objects into 'requestedIngredients' collection of Participant object
    * Removes these Ingredient objects from ingredientList collection.
    */
    @Override
    public void divideIngredients(){

        for(Participant participant : participants){

            List<Ingredient> in =
                    ingredientsList.stream()
                            .filter(i -> participant
                                    .getDish()
                                    .recipe()
                                    .contains(i))
                            .toList();

            participant.getRequestedIngredient().addAll(in);
            ingredientsList.removeAll(in);

        }
    }
    
    @Override
    public String toString() {
        String a = "Fair balance: " + totalBalance + " " + participants;
        return  a.replaceAll("\\[", "").replaceAll("]", "");
    }
}

