package classes;
import classes.*;
import classes.Point;
import classes.Polygon;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.stage.Stage;

import javafx.scene.*;
import javafx.scene.paint.*;
import javafx.scene.canvas.*;
import javafx.application.Application;
import javafx.stage.Stage;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.beans.EventHandler;
import java.net.URL;
import java.util.ResourceBundle;

import static java.awt.Color.BLACK;
import static java.awt.Color.BLUE;

public class DrawingTool extends Application implements Initializable {

    @FXML
    private Spinner<Double> OvalWidth;
    @FXML
    private Spinner<Double> OvalHeight;
    @FXML
    private Canvas drawingCanvas;
    private JavaFxPaintable paintable;
    private Drawing drawing;


    public void Draw(){
        System.out.println("Ran");
        paintable.Paint(new Oval(BLACK,new Point(100,100),100,100,100));
        paintable.Paint(new PaintedText(BLUE, "test", "Calibri", new Point(10, 10),OvalWidth.getValue(), OvalHeight.getValue()));
    }

    @Override
    //(Main)
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("../UserInterface.fxml"));
        Parent root = fxmlLoader.load();
        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("Paint 2.0");
        primaryStage.show();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    this.paintable = new JavaFxPaintable(this.drawingCanvas.getGraphicsContext2D());
    this.OvalWidth.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0,1000,0));
    this.OvalHeight.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0,1000,0));
    this.drawing.setName("test");

   //event handler


    }
}
