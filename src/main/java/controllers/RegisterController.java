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


    /**
     * Register button handler that calls createParticipant() method.
     */
    @FXML
    public void registerButton() {
        createParticipant();
    }

    /**
     * Checks if validateFields() method throws any exception with try-catch-finally construction,
     * creates new Participant object with data from 'name', 'surname' and 'dishname' text fields.
     * The Set<Ingredient> 'ingredients' collection is filled with methods chain started in
     * getIngredients() that returns Set<Ingredient> object with converted data from text fields into Ingredient objects.
     * Adds created Participant object into 'participants' field collection of Fair object
     * Clears fields by method clearRegisterFields() in finally block (in case of any scenario).
     * Successful message is printed only in case of exceptions absence.
     */
    private void createParticipant() {

        try{
            validateFields();
            fair.getParticipants().add(new Participant(name.getText(), surname.getText(), dishname.getText(), getIngredients()));
            messageValue.setText("Successfully registered");
        } catch (NullPointerException npe) {
            messageValue.setText("Empty fields are not allowed");
        } catch (Exception ex) {
            messageValue.setText("This dish has been already registered");
        } finally {
            clearRegisterFields();
        }
    }


    /**
     * Gets ingredients from String value in 'ingredients' Label field, created by toAddIngredient() method.
     * Uses method stringToArray() to split String into String array.
     * Creates HashSet object and adds Ingredient objects created in createNewIngredient() method that receives
     * each element of String array as a String parameter and returns Ingredient object.
     *
     * @return Set collection of Ingredient created objects.
     */
    private Set<Ingredient> getIngredients() {

        String [] inA = stringToArray(ingredients.getText());
        Set<Ingredient> inS = new HashSet<>();

        if(inA.length > 0) {
            Arrays.stream(inA).forEach( s -> inS.add(createNewIngredient(s)));
        }

        return inS;
    }

    /**
     * Converts received parameter String into String array with regex ','. If 's' parameter doesn't
     * contains ',' character, returns new String array with only one String that is equal to @param 's'.
     *
     * @param s String value to be converted into String array.
     * @return String array from @param 's' split by ',' regex.
     */
    private String [] stringToArray(String s) {
        String in = s.trim();
        if (!s.contains(",")) return new String[] {s};
        return in.split(",");
    }

    /**
     * Receives String, trims it, and substrings from full String value Amount and Name of Ingredient by "(" and ")" markers.
     * Creates and returns new Ingredient object with values received by substring methods before. Price is randomized.
     *
     * @param s String value received from getIngredients() with data of further created Ingredient object.
     * @return new created Ingredient object.
     */
    private Ingredient createNewIngredient(String s) {
        String a = s.substring(0, s.indexOf("(")).trim();
        String b = s.substring(s.indexOf("(")+1, s.indexOf(")")).trim();
        return new Ingredient(a, Double.parseDouble(b), Tech.getRandomDouble(1.0, 100.0));
    }

    /**
     * Add ingredient button handler.
     * Adds information from fields 'ingredientName' and 'ingredientAmount' into 'ingredients' field.
     * Checks if validateIngredients() doesn't throw any exception. In case of exceptions, shows accordingly message in
     * messageValue text field.
     * Clears all the fields in finally block in case of any scenario.
     */
    @FXML
    public void toAddIngredient() {

        try {
            validateIngredients();
            ingredients.setText(
                    ingredients.getText() +
                            replaceSymbols(ingredientName.getText()) +
                            "("+ replaceSymbols(ingredientAmount.getText()) + ")" + ", ");
            messageValue.setText("Ingredient has been successfully added");

        } catch (NullPointerException e) {
            messageValue.setText("Empty fields are not allowed");
        } catch (NumberFormatException e) {
            messageValue.setText("Ingredient amount should be a number");
        } finally {
            clearIngredientFields();
        }
    }

    private String replaceSymbols(String text) {
        return text.replaceAll("\\(", "[" ).replaceAll("\\)", "]");
    }

    /**
     * Calls for two methods of fields validation.
     * @throws Exception that can be NullPointerException in case of validateEmptiness() method and just Exception
     * in case of validateDish() method
     */
    private void validateFields() throws Exception{
        validateDish();
        validateEmptiness();
    }

    //Clears all fields related to Ingredient input
    private void clearIngredientFields() {
        ingredientName.setText("");
        ingredientAmount.setText("");
    }

    /**
     * Checks if fields of ingredients adding interface are correctly filled.
     *
     * @throws NullPointerException when any or both of two fields are empty
     * @throws NumberFormatException when ingredientAmount field is filled with non-numeric value.
     */
    private void validateIngredients() throws NullPointerException, NumberFormatException{

        if (ingredientAmount.getText().isEmpty() || ingredientName.getText().isEmpty())
            throw new NullPointerException();

        Double.parseDouble(ingredientAmount.getText());
    }

    /**
     * Checks if there is registered dish name as requested in 'dishname' textField. The method validateDish()
     * of Fair object is called for validation of all registered dishes. If validateDish() returns true, the method
     * throws an exception.
     * @throws Exception when requested in dishname field dish name is already used by any of participant objects.
     */
    private void validateDish() throws Exception{
        if(fair.validateDish(dishname.getText()))
            throw new Exception();
    }

    /**
     * Checks fields for emptiness
     * @throws NullPointerException when any or all fields are not filled with any value.
     */
    private void validateEmptiness() throws NullPointerException{
        if (name.getText().isEmpty() || surname.getText().isEmpty() ||
                dishname.getText().isEmpty() || ingredients.getText().isEmpty())
            throw new NullPointerException();
    }

    private void clearRegisterFields() {
        name.setText("");
        surname.setText("");
        dishname.setText("");
        ingredientName.setText("");
        ingredientAmount.setText("");
        ingredients.setText("");
    }

    /**
     * Calls buyIngredients() and divideIngredients() methods of Fair object and opens Poster view with
     * full information about Fair
     *
     * @param a actionEvent object is used to get the current Stage object.
     */
    public void beginButton(ActionEvent a) {

        fair.buyIngredients();
        fair.divideIngredients();

        Tech.nextWindow(getClass().getResource("/view/Poster.fxml"), "Poster", getCurrentStage(a), 400, 540);
    }

    /**
     * Creates new Node object as a source of ActionEvent received from parameter 'a'
     * Returns Stage object as Window of Scene of created Node.
     *
     * @param a actionEvent object is used to get the current Stage object.
     * @return object of the current open Stage
     */
    protected Stage getCurrentStage(ActionEvent a){
        Node node = (Node) a.getSource();
        return (Stage) node.getScene().getWindow();
    }

}