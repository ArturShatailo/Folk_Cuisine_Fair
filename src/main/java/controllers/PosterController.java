package controllers;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import service.Participant;

public class PosterController implements Statics{

    public GridPane participants;

    /**
     * Calls uploadParticipants() method and prints Fair object information into the console.
     */
    public void initialize(){
        uploadParticipants();
        System.out.println(fair);
    }

    /**
     * Adds into 'participants' GridPane a new Node elements created by addRecord() method with data of each Participant object from
     * 'participants' field collection of Fair object implemented from Statics interface.
     */
    private void uploadParticipants() {
        for (Participant p : fair.getParticipants()) {
            participants.add(addRecord(p), 0, participants.getRowCount()+1);
            participants.add(createLine(), 0, participants.getRowCount()+1);
        }
    }

    /**
     * Creates new GridPane Node with data from fields of Participant @param 'p'.
     *
     * @param p Participant object sent from 'participants' collection field of Fair object.
     * @return created Node GridPane element.
     */
    private GridPane addRecord(Participant p){

        GridPane gp = new GridPane();
        Label name = new Label(p.getName());
        name.setFont(new Font("Serif", 15));
        Label surname = new Label(p.getSurname());
        surname.setFont(new Font("Serif", 15));
        Label dish = new Label(p.getDish().name());
        dish.setFont(new Font("Serif", 12));

        gp.add(name, 0, 0);
        gp.add(surname, 1, 0);
        gp.add(dish, 0, 2);

        gp.setAlignment(Pos.TOP_LEFT);
        gp.setHgap(2.0);
        gp.setVgap(2.0);

        return gp;
    }

    //Creates and returns Label Node element filled with line (divider)
    private Label createLine() {
        Label l = new Label();
        l.setText("_________________________");
        return l;
    }

}
