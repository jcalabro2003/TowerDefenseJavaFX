package sample;

import sample.model.Map;
import sample.model.Settings;
import sample.view.*;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.shape.Rectangle;

public class Controller extends Application {
    private Scene mainScene;

    private BorderPane mainPane;
    private BorderPane headerPane;
    private Map bodyPane;
    private TilePane footerPane;
    private TilePane infoPane;
    private AnchorPane buttonsPane;

    private Button speedButton;
    private Button pauseButton;
    private Button startButton;



    private Game g = new Game();

    private void initMainPane() {
        headerPane = new BorderPane();
        bodyPane = Map.getInstance();
        footerPane = new TilePane();

        footerPane.setStyle("-fx-background-color: red;");
        footerPane.setPrefSize(950,50);

        mainPane.setTop(headerPane);
        mainPane.setCenter(bodyPane);
        mainPane.setBottom(footerPane);
    }

    private void initHeaderPane() {
        infoPane = new TilePane();
        buttonsPane = new AnchorPane();
        infoPane = InfoPane.getInstance();

        initButtonsPane();

        headerPane.setLeft(infoPane);
        headerPane.setRight(buttonsPane);
    }


    private void initButtonsPane() {
        speedButton = new Button("Speed");
        pauseButton = new Button("Pause");
        startButton = new Button("Start");

        //listeners pour les boutons
        speedButton.setOnMouseClicked(new SpeedButtonListener());
        startButton.setOnMouseClicked(new StartButtonListener(bodyPane));

        HBox hBoxButton= new HBox(Settings.SPACE_HBOX);
        hBoxButton.getChildren().addAll(startButton, speedButton, pauseButton);
        hBoxButton.setPadding(new Insets(5,5,5,5));

        buttonsPane.getChildren().add(hBoxButton);
        buttonsPane.setRightAnchor(hBoxButton, 10.0);
    }

    private void initBodyPane() {
        for (int i=0; i < bodyPane.getRectTowers().size(); i++) {
            Rectangle rec = bodyPane.getRectTowers().get(i);
            rec.setOnMouseClicked(new RectTowersListener(rec,bodyPane));
        }
    }

    private void initFooterPane() {

    }

    @Override
    public void start(Stage primaryStage) throws Exception{


        mainPane = new BorderPane();
        mainScene = new Scene(mainPane, 950, 550);
        primaryStage.setTitle("Tower Defense");
        primaryStage.setScene(mainScene);

        initMainPane();
        initHeaderPane();
        initBodyPane();
        initFooterPane();

        primaryStage.show();
    }


    public static void launchTowerDefense(String[] args) {
        launch(args);
    }
}
