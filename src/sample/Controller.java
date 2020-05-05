package sample;

import javafx.scene.image.ImageView;
import sample.model.LoadingImage;
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
    private Button classicButton;
    private Button slowButton;



    private Game g = new Game();

    private void initMainPane() {
        headerPane = new BorderPane();
        bodyPane = Map.getInstance();
        footerPane = new TilePane();

        mainPane.setTop(headerPane);
        mainPane.setCenter(bodyPane);
        mainPane.setBottom(footerPane);
    }

    private void initHeaderPane() {
        ImageView imageview =  LoadingImage.loadImage("BackgroundRainobw.png",950,50);
        headerPane.getChildren().add(imageview);
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
        for (int i=0; i < bodyPane.getRectPaths().size(); i++) {
            Rectangle rec = bodyPane.getRectPaths().get(i);
            ImageView imageView = LoadingImage.loadImage("Rainbow.png",50,50);
            imageView.setOpacity(0.8);
            imageView.setX(rec.getX());
            imageView.setY(rec.getY());
            bodyPane.getChildren().add(imageView);
        }
        ImageView imageviewArrivé = LoadingImage.loadImage("Arrivé.png",25,50);
        imageviewArrivé.setX(915);
        imageviewArrivé.setY(50);
        bodyPane.getChildren().add(imageviewArrivé);
    }

    private void initFooterPane() {

        footerPane.setStyle("-fx-background-color: black;");
        footerPane.setPrefSize(950,50);

        classicButton = new Button("Classic");
        slowButton = new Button("slow");

        HBox hBoxTowersButton = new HBox(Settings.SPACE_HBOX);
        hBoxTowersButton.getChildren().addAll(classicButton, slowButton);
        hBoxTowersButton.setPadding(new Insets(12.5,5,5,10));

        footerPane.getChildren().add(hBoxTowersButton);

        classicButton.setOnMouseClicked(new ClassicListener(bodyPane));
        slowButton.setOnMouseClicked(new SlowListener(bodyPane));
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
