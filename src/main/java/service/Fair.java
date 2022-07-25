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

    /**
     * Returns true if any Participant object from 'participants' field collection has 'dish' field with Dish object that
     * has field 'name' equal to @param String 'dishname'
     *
     * @param dishname String value of required for checking Dish object's 'name' field
     * @return Returns boolean value of statement about Dish name is registered or not.
     */
    public boolean validateDish(String dishname) {
        return participants.stream().anyMatch(p -> p.getDish().name().equals(dishname));
    }

    /**
     * Implemented from the Manager interface.
     * Calls collectIngredients() method first to fill out 'ingredientsList' collection implemented from Manager interface.
     * Sums up all the values of 'price' field of Ingredient objects from 'ingredientsList' collection.
     * The calculated sum is deducted from 'totalBalance' field of Fair object.
     */
    @Override
    public void buyIngredients(){
        collectIngredients();
        double sum = ingredientsList.stream().mapToDouble(Ingredient::getFullPrice).sum();
        totalBalance = totalBalance - sum;
    }

    /**
     * Implemented from the Manager interface.
     * Fills collection 'ingredientsList' implemented from Manager interface with Ingredient objects from
     * 'recipe' field of Dish object from 'dish' field of each Participant object in 'participants' field collection.
     */
    @Override
    public void collectIngredients() {
        participants.forEach( p -> ingredientsList.addAll(p.getDish().recipe()));
    }

    /**
     * Implemented from the Manager interface.
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

