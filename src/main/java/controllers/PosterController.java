package controllers;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import service.Manager;
import service.Participant;

public class PosterController implements Statics{

    public GridPane participants;

    public void initialize(){

        uploadParticipants();

        System.out.println(fair);
        System.out.println(Manager.ingredientsList);
    }

    private void uploadParticipants() {
        for (Participant p : fair.getParticipants()) {
            participants.add(addRecord(p), 0, participants.getRowCount()+1);
            participants.add(createLine(), 0, participants.getRowCount()+1);
        }
    }

    private GridPane addRecord(Participant p){

        GridPane gp = new GridPane();
        Label name = new Label(p.getName());
        name.setFont(new Font("Serif", 14));
        Label surname = new Label(p.getSurname());
        surname.setFont(new Font("Serif", 14));
        Label dish = new Label(p.getDish().getName());
        dish.setFont(new Font("Serif", 12));

        gp.add(name, 0, 0);
        gp.add(surname, 1, 0);
        gp.add(dish, 0, 2);

        gp.setAlignment(Pos.TOP_LEFT);
        gp.setHgap(5.0);
        gp.setVgap(5.0);

        return gp;
    }

    private Label createLine() {
        Label l = new Label();
        l.setText("_________________________");
        return l;
    }

}
