package service;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Scanner;

public class Tech {


    /*
     * Updating Stage of the current Window with the new Scene object.
     * URL and title values are received from @param 'application' and @param 'title'.
     * New Stage object is received from @param 'stage'
     * @param x is width
     * @param y is height
     */
    public static void nextWindow(URL application, String title, Stage stage, int x, int y){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(application);
            Scene scene = new Scene(fxmlLoader.load(), x, y);
            stage.setTitle(title);
            stage.setScene(scene);
            stage.show();
        }
        catch (Exception exception){
            exception.printStackTrace();
        }
    }

    //Get random value from min to max
    public static int getRandom(int min, int max){
        return (int) (Math.random() * (max - min + 1)) + min;
    }

    public static Double getRandomDouble(double min, double max) {
        return roundMet((Math.random() * (max - min)) + min);
    }

    public static double roundMet(double a){
        double b = Math.pow(10, 2);
        return Math.ceil(a * b) / b;
    }

}
