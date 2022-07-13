package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import service.Ingredient;
import service.Manager;
import service.Participant;
import service.Tech;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class RegisterController implements Statics{

    public TextField name;
    public TextField surname;
    public TextField dishname;
    public TextField ingredientName;
    public TextField ingredientAmount;
    public Label messageValue;
    public Label ingredients;

    @FXML
    public void registerButton() {
        createParticipant();
        clearRegisterFields();
    }

    private void createParticipant() {

        fair.getParticipants().add(new Participant(name.getText(), surname.getText(), dishname.getText(), getIngredients()));
        messageValue.setText("Successfully registered");

    }

    private Set<Ingredient> getIngredients() {

        String [] inA = ingredientsToArray();
        Set<Ingredient> inS = new HashSet<>();

        if(inA.length > 0) {
            Arrays.stream(inA).forEach( s -> inS.add(createNewIngredient(s)));
        }

        return inS;
    }

    private String [] ingredientsToArray() {
        String in = ingredients.getText().replaceAll(" ", "");
        return in.split(",");
    }

    private Ingredient createNewIngredient(String s) {
        String a = s.substring(0, s.indexOf("("));
        String b = s.substring(s.indexOf("(")+1, s.indexOf(")"));
        return new Ingredient(a, Double.parseDouble(b), Tech.getRandomDouble(1.0, 100.0));
    }

    @FXML
    public void toAddIngredient() {
        ingredients.setText(
                ingredients.getText() +
                replaceSymbols(ingredientName.getText()) +
                "("+ replaceSymbols(ingredientAmount.getText()) + ")" + ", ");

        clearIngredientFields();
    }

    private String replaceSymbols(String text) {
        return text.replaceAll("\\(", "[" ).replaceAll("\\)", "]");
    }

    private void clearIngredientFields() {
        ingredientName.setText("");
        ingredientAmount.setText("");
    }

    private void clearRegisterFields() {
        name.setText("");
        surname.setText("");
        dishname.setText("");
        ingredientName.setText("");
        ingredientAmount.setText("");
        ingredients.setText("");
    }

    public void beginButton(ActionEvent a) {

        fair.buyIngredients();
        fair.divideIngredients();

        Tech.nextWindow(getClass().getResource("/view/Poster.fxml"), "Poster", getCurrentStage(a), 400, 540);
    }

    protected Stage getCurrentStage(ActionEvent a){
        Node node = (Node) a.getSource();
        return (Stage) node.getScene().getWindow();
    }

}