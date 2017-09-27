package classes;

import interfaces.PersistencyMediator;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static java.awt.Color.BLACK;

public class DrawingTool extends Application implements Initializable {
    @FXML
    private Spinner<Double> PTWidth;

    @FXML
    private Spinner<Double> PTHeight;

    @FXML
    private TextField DrawingNameTb;

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

    @FXML
    private Spinner<Double> ImageWidth;

    @FXML
    private Spinner<Double> ImageHeight;

    @FXML
    private Button ImageUploadBtn;

    @FXML
    private Spinner<Double> PolygonWeight;

    @FXML
    private Spinner<Integer> PolygonPoints;

    @FXML
    private ComboBox<enums.Color> PolygonColor;

    @FXML
    private Button SaveDrawingBtn;

    @FXML
    private Button SaveXmlBtn;

    @FXML
    private ComboBox<Drawing> AllDrawingsCb;

    @FXML
    private Button loadDrawingBtn;

    @FXML
    private ComboBox<Drawing> AllDrawingsExports;

    @FXML
    private Button loadDrawingExportsBtn;

    @FXML
    private TableView<DrawingItem> ItemsTableView;

    private JavaFxPaintable paintable;

    private Drawing drawing;

    private File uploadeImage;

    private Polygon polygon;

    private PersistencyMediator pm;

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

        //Initialize fxml components
        this.paintable = new JavaFxPaintable(this.drawingCanvas.getGraphicsContext2D());
        this.drawing = new Drawing("");

        this.OvalWidth.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0, 1000, 0));
        this.OvalHeight.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0, 1000, 0));
        this.OvalWeight.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0, 1000, 0));

        this.PTHeight.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0, 1000, 0));
        this.PTWidth.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0, 1000, 0));

        this.ImageHeight.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0, 1000, 0));
        this.ImageWidth.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0, 1000, 0));

        this.PolygonPoints.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 50, 0));
        this.PolygonWeight.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0, 1000, 0));


        //Load data
        for (Drawing d: new DatabaseMediator().loadAll()
             ) {
            AllDrawingsCb.getItems().add(d);
        }

        for (Drawing d: new SerializationMediator().loadAll()
                ) {
            AllDrawingsExports.getItems().add(d);
        }

        for (enums.Color c : enums.Color.values()) {
            this.OvalColor.getItems().addAll(c);
        }


        //Event handlers

        ImageUploadBtn.setOnAction(event -> {
                    FileChooser fileChooser = new FileChooser();
                    this.uploadeImage = fileChooser.showOpenDialog(new Stage());
                }
        );

        this.SaveDrawingBtn.setOnAction(event -> {
            this.pm = new DatabaseMediator();
            this.drawing.setName(DrawingNameTb.getText());
            this.pm.save(this.drawing);
        });

        this.SaveXmlBtn.setOnAction(event -> {
            this.pm = new SerializationMediator();
            this.drawing.setName(DrawingNameTb.getText());
            this.pm.save(this.drawing);
            this.initialize(location, resources);

        });

        this.loadDrawingBtn.setOnAction(event -> {
           Drawing d = AllDrawingsCb.getSelectionModel().getSelectedItem();
           this.drawing.AddDrawingItem(d);
            System.out.println(d);
            Draw();
        });


        this.loadDrawingExportsBtn.setOnAction(event ->{
            Drawing d = AllDrawingsExports.getSelectionModel().getSelectedItem();
            this.drawing.AddDrawingItem(d);
            System.out.println(d);
            Draw();
        });



        drawingCanvas.setOnMouseClicked(new javafx.event.EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent event) {

                int Index = DrawingTypeTabPane.getSelectionModel().getSelectedIndex();
                Point p = new Point(event.getX(), event.getY());

                switch (Index) {
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
                    case 2:
                        //Wanneer er nog geen polygon is maak er een aan
                        if (polygon == null) {
                            polygon = new Polygon(BLACK, PolygonWeight.getValue(), PolygonPoints.getValue());
                            polygon.addVertices(p);
                        } else {
                            //Voeg point to aan bestaande polygon
                            if (polygon.getVertices().size() != (polygon.getAantalPoints())) {
                                polygon.addVertices(p);

                                //Wanneer het laatste point is toegevoegd, voeg drawing toe
                                if (polygon.getAantalPoints() == polygon.getVertices().size()) {
                                    drawing.AddDrawingItem(polygon);
                                    polygon = null;
                                }
                            }
                        }
                        break;
                    //Image
                    case 3:
                        Image img = new Image(BLACK, uploadeImage, p, ImageWidth.getValue(), ImageHeight.getValue());
                        drawing.AddDrawingItem(img);

                        break;
                }
                Draw();
            }
        });

    }

    public void Draw() {

        this.drawingCanvas.getGraphicsContext2D().clearRect(0, 0, drawingCanvas.getWidth(), drawingCanvas.getHeight());

        for (int i = 0; i < drawing.getItems().size(); i++) {

            if (drawing.getItems().get(i) instanceof Oval) {
                paintable.Paint((Oval) drawing.getItems().get(i));
            }
            else if (drawing.getItems().get(i) instanceof PaintedText) {
                paintable.Paint((PaintedText) drawing.getItems().get(i));
            }
            else if (drawing.getItems().get(i) instanceof Polygon) {
                paintable.Paint((Polygon) drawing.getItems().get(i));
            }

            else if (drawing.getItems().get(i) instanceof Drawing) {
              Drawing d = (Drawing) drawing.getItems().get(i);
                for (DrawingItem di: d.getItems()
                     ) {
                    if (di instanceof Oval) {
                        paintable.Paint((Oval) di);
                    }
                    else if (di instanceof PaintedText) {
                        paintable.Paint((PaintedText) di);
                    }
                }

            }
            else if (drawing.getItems().get(i) instanceof Image) {
                paintable.Paint((Image) drawing.getItems().get(i));
            }

        }
    }


    ObservableList<DrawingItem> getObservableList(){
        return FXCollections.observableList(this.drawing.getItems());
    }


}
