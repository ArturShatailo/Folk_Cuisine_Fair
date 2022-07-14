package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import service.Ingredient;
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
    }

    /*
    * Checks if validateFields() method returns true and if yes, creates new Participant object with data from 'name',
    * 'surname' and 'dishname' text fields. The Set<Ingredient> ingredients collection is filled with methods chain started in
    * getIngredients() that returns Set<Ingredient> object.
    * Adds created Participant object into 'participants' field collection of Fair object
    * Clears fields by method clearRegisterFields()
    * Successful message is printed.
    */
    private void createParticipant() {
        if(validateFields()) {
            fair.getParticipants().add(new Participant(name.getText(), surname.getText(), dishname.getText(), getIngredients()));
            messageValue.setText("Successfully registered");
            clearRegisterFields();
        }
    }

    /*
     * Checks if both methods validateEmptiness() and validateDish() returns true;
     */
    private boolean validateFields() {
        return validateEmptiness() && validateDish();
    }

    /*
    * Checks if there is registered dish name as requested in 'dishname' textField. The method validateDish() of Fair object is
    * called for validation of all registered dishes. If validateDish() returns true, the error message will be shown and
    * false value returned.
    */
    private boolean validateDish() {
        if(fair.validateDish(dishname.getText())){
            messageValue.setText("This dish has been already registered");
            return false;
        }
        return true;
    }

    /*
    * Checks if input fields are filled. Empty fields are not allowed.
    */
    private boolean validateEmptiness() {
        if (name.getText().isEmpty() || surname.getText().isEmpty() ||
                dishname.getText().isEmpty() || ingredients.getText().isEmpty()){
            messageValue.setText("Empty fields are not allowed");
            return false;
        }
        return true;
    }

    /*
    * Get ingredients from String value created by toAddIngredient() method in 'ingredients' Label field.
    * Uses method ingredientsToArray() to split String into String array.
    * Creates HashSet object and adds Ingredient objects created in createNewIngredient() that receives each element
    * of String array as a String parameter and returns Ingredient object.
    */
    private Set<Ingredient> getIngredients() {

        String [] inA = ingredientsToArray();
        Set<Ingredient> inS = new HashSet<>();

        if(inA.length > 0) {
            Arrays.stream(inA).forEach( s -> inS.add(createNewIngredient(s)));
        }

        return inS;
    }

    private String [] ingredientsToArray() {
        String in = ingredients.getText().trim();
        return in.split(",");
    }

    /*
    * Receives String, trims it, and substrings from full String value Amount and Name of Ingredient by "(" and ")" markers.
    * Creates and returns new Ingredient object with values received by substring methods before. Price is randomized.
    */
    private Ingredient createNewIngredient(String s) {
        String a = s.substring(0, s.indexOf("(")).trim();
        String b = s.substring(s.indexOf("(")+1, s.indexOf(")")).trim();
        return new Ingredient(a, Double.parseDouble(b), Tech.getRandomDouble(1.0, 100.0));
    }

    /*
    * Adds information from fields 'ingredientName' and 'ingredientAmount' into 'ingredients' field
    */
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

    //Clears all fields related to Ingredient input
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

    /*
    * Calls buyIngredients() and divideIngredients() methods of Fair object and opens Poster view with full information about Fair
    */
    public void beginButton(ActionEvent a) {

        fair.buyIngredients();
        fair.divideIngredients();

        Tech.nextWindow(getClass().getResource("/view/Poster.fxml"), "Poster", getCurrentStage(a), 400, 540);
    }

    /*
    * Creates new Node object as a source of ActionEvent received from parameter 'a'
    * Returns Stage object as Window of Scene of created Node.
    */
    protected Stage getCurrentStage(ActionEvent a){
        Node node = (Node) a.getSource();
        return (Stage) node.getScene().getWindow();
    }

}