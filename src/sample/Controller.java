package sample;
//
import sample.model.ChangeMapObserver;
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
import javafx.scene.image.ImageView;

public class Controller extends Application implements ChangeMapObserver {
    private static  String typeMapGlobal;
    private Game g;

    private Scene mainScene;

    private BorderPane mainPane;
    private BorderPane headerPane;
    public static Map bodyPane;
    private TilePane footerPane;
    private TilePane infoPane;
    private AnchorPane buttonsPane;

    private Button speedButton;
    private Button pauseButton;
    private Button startButton;
    private Button classicButton;
    private Button slowButton;
    private Button upgradeButton;
    private Button cancelButton;
    private Button changeMapButton;

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
        speedButton = new Button();
        pauseButton = new Button();
        startButton = new Button();
        changeMapButton = new Button("Change Map");

        ImageView imageStart = LoadingImage.loadImage("Play.png",25,25);
        startButton.setGraphic(imageStart);

        ImageView imageSpeed = LoadingImage.loadImage("boutonspeed.png",25,25);
        speedButton.setGraphic(imageSpeed);

        ImageView imagePause = LoadingImage.loadImage("pause.png",25,25);
        pauseButton.setGraphic(imagePause);

        HBox hBoxButton= new HBox(Settings.SPACE_HBOX);
        hBoxButton.getChildren().addAll(changeMapButton, startButton, speedButton, pauseButton);
        hBoxButton.setPadding(new Insets(10,5,5,5));

        buttonsPane.getChildren().add(hBoxButton);
        buttonsPane.setRightAnchor(hBoxButton, 10.0);

        //listeners pour les boutons
        speedButton.setOnMouseClicked(new SpeedButtonListener());
        startButton.setOnMouseClicked(new StartButtonListener());
        changeMapButton.setOnAction(new ChangeMapButtonListener(bodyPane));
    }

    private void initFooterPane() {

        footerPane.setStyle("-fx-background-color: black;");
        footerPane.setPrefSize(950,50);

        ImageView classico = LoadingImage.loadImage("towers.png");
        ImageView slow = LoadingImage.loadImage("kamek.png");
        ImageView upgrade = LoadingImage.loadImage("upgrade.png");
        ImageView cancel = LoadingImage.loadImage("cancel.png");

        classicButton = new Button("100", classico);
        slowButton = new Button("   150  ",slow);
        upgradeButton = new Button("Upgrade",upgrade);
        cancelButton = new Button ("Cancel",cancel);


        classicButton.setStyle("-fx-background-color: purple");
        slowButton.setStyle("-fx-background-color: indigo;");
        upgradeButton.setStyle("-fx-background-color: cyan;");
        cancelButton.setStyle("-fx-background-color: green;");


        HBox hBoxTowersButton = new HBox(Settings.SPACE_HBOX);
        hBoxTowersButton.getChildren().addAll(classicButton, slowButton,upgradeButton,cancelButton);
        hBoxTowersButton.setPadding(new Insets(12.5,5,5,10));

        HBox hBoxMessage  = new HBox(Settings.SPACE_HBOX);

        footerPane.getChildren().addAll(hBoxTowersButton, hBoxMessage);
        RectTowersListener.sethBoxMessage(hBoxMessage);
        classicButton.setOnMouseClicked(new ClassicListener());
        slowButton.setOnMouseClicked(new SlowListener());
        upgradeButton.setOnMouseClicked(new UpgradeListener());
        cancelButton.setOnMouseClicked(new CancelListener());
    }


    @Override
    public void start(Stage primaryStage) throws Exception{
        g = new Game();
        NewMapListener.setTypeMap("map1");



        mainPane = new BorderPane();
        mainScene = new Scene(mainPane, 950, 550);
        primaryStage.setTitle("Tower Defense");
        primaryStage.setScene(mainScene);

        initMainPane();
        initHeaderPane();
        initFooterPane();
        bodyPane.addObserver(this);

        primaryStage.show();
    }

    public static void launchTowerDefense(String[] args) {
        launch(args);
    }

    @Override
    public void changeMap() {
        bodyPane = Map.getInstance();
    }
}
