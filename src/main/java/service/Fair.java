package service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Fair implements Manager{

    private boolean registrationStatus;

    private Double totalBalance;

    private Set<Participant> participants = new HashSet<>();


    public Fair(boolean registrationStatus, Double totalBalance, Set<Participant> participants) {
        this.registrationStatus = registrationStatus;
        this.totalBalance = totalBalance;
        this.participants = participants;
    }

    public Fair(boolean registrationStatus, Double totalBalance) {
        this.registrationStatus = registrationStatus;
        this.totalBalance = totalBalance;
    }

    public Set<Participant> getParticipants() {
        return participants;
    }

    @Override
    public void collectIngredients() {
        participants.forEach( p -> ingredientsList.addAll(p.getDish().getRecipe()));
    }

    @Override
    public void buyIngredients(){
        collectIngredients();
        double sum = ingredientsList.stream().mapToDouble(Ingredient::getFullPrice).sum();
        totalBalance = totalBalance - sum;
    }

    @Override
    public void divideIngredients(){

        for(Participant participant : participants){

            List<Ingredient> in =
                    ingredientsList.stream()
                            .filter(i -> participant
                                    .getDish()
                                    .getRecipe()
                                    .contains(i))
                            .toList();

            participant.getRequestedIngredient().addAll(in);
            ingredientsList.removeAll(in);

        }
    }
    
    @Override
    public String toString() {
        return "Fair{" +
                "registrationStatus=" + registrationStatus +
                ", totalBalance=" + totalBalance +
                ", participants=" + participants +
                "}";
    }
}

