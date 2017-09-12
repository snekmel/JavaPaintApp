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
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javafx.scene.*;
import javafx.scene.paint.*;
import javafx.scene.canvas.*;
import javafx.application.Application;
import javafx.stage.Stage;
import java.awt.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.beans.EventHandler;
import java.net.URL;
import java.util.ResourceBundle;

import static java.awt.Color.BLACK;
import static java.awt.Color.BLUE;

public class DrawingTool extends Application implements Initializable {
    @FXML
    private Spinner<Double> PTWidth;

    @FXML
    private Spinner<Double> PTHeight;

    @FXML
    private ComboBox<enums.Color> PTColor;

    @FXML
    private TextField PTContent;

    @FXML
    private TextField PTFont;

    @FXML
    private ComboBox<enums.Color> OvalColor;

    @FXML
    private Spinner<Double> OvalWeight;
    @FXML
    private Spinner<Double> OvalWidth;

    @FXML
    private Spinner<Double> OvalHeight;

    @FXML
    private Canvas drawingCanvas;

    @FXML
    private TabPane DrawingTypeTabPane;

    private JavaFxPaintable paintable;

    private Drawing drawing;


    public void Draw(){

        this.drawingCanvas.getGraphicsContext2D().clearRect(0,0, drawingCanvas.getWidth(), drawingCanvas.getHeight());
        for (int i = 0; i <  drawing.getItems().size(); i++) {

           if (drawing.getItems().get(i) instanceof Oval){
              paintable.Paint((Oval)drawing.getItems().get(i));
            }
            else if(drawing.getItems().get(i) instanceof PaintedText){
                paintable.Paint((PaintedText) drawing.getItems().get(i));
            }
            else if(drawing.getItems().get(i) instanceof Polygon){
                paintable.Paint((Polygon) drawing.getItems().get(i));
            }
            else if(drawing.getItems().get(i) instanceof Image){
                paintable.Paint((Image) drawing.getItems().get(i));
            }

        }
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
    this.drawing = new Drawing("test");


    this.OvalWidth.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0,1000,0));
    this.OvalHeight.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0,1000,0));
    this.OvalWeight.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0,1000,0));

    this.PTHeight.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0,1000,0));
    this.PTWidth.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0,1000,0));

    for (enums.Color c : enums.Color.values()){
        this.OvalColor.getItems().addAll(c);
    }





   //event handler
    drawingCanvas.setOnMouseClicked(new javafx.event.EventHandler<javafx.scene.input.MouseEvent>() {
        @Override
        public void handle(javafx.scene.input.MouseEvent event) {

            int Index = DrawingTypeTabPane.getSelectionModel().getSelectedIndex();
            Point p = new Point(event.getX(),event.getY());
            switch (Index){
                //Oval
                case 0:
                    Oval o = new Oval(BLACK, p, OvalWidth.getValue(), OvalHeight.getValue(), OvalWeight.getValue());
                    drawing.AddDrawingItem(o);
                break;
                //Text
                case 1:
                    PaintedText pt = new PaintedText(BLACK, PTContent.getText(), PTFont.getText(), p, PTWidth.getValue(), PTHeight.getValue());
                    System.out.println(pt);
                    drawing.AddDrawingItem(pt);
                break;
                //Polygon
                case 2:  Oval s = new Oval(BLACK, p, OvalWidth.getValue(), OvalHeight.getValue(), 100);
                break;
                //Image
                case 3:  Oval a = new Oval(BLACK, p, OvalWidth.getValue(), OvalHeight.getValue(), 100);
                break;
            }



        Draw();
        }
    });

    }



}
