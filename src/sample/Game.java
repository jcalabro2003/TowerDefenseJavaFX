package sample;

import sample.controller.*;
import sample.model.*;
import sample.view.InfoPane;
import sample.view.LoadingImage;
import sample.view.Map;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.paint.Color;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;

public class Game extends Application implements ChangeMapObserver {

    private Scene mainScene;
    private Player player = Player.getInstance();

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
    private Button bombButton;

    public static MenuBar menuBar;
    private MenuItem menuItem1;
    private MenuItem menuItem2;
    private Menu menu;

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
        buttonsPane = new AnchorPane();
        infoPane = InfoPane.getInstance();

        initButtonsPane();

        headerPane.setLeft(infoPane);
        headerPane.setRight(buttonsPane);
    }

    private void initButtonsPane() {
        menuBar = new MenuBar();
        menuItem1 = new MenuItem("Map 1");
        menuItem2 = new MenuItem("Map 2");
        menu = new Menu("Change Map");
        menu.getItems().addAll(menuItem1,menuItem2);
        menuBar.getMenus().add(menu);

        speedButton = new Button();
        pauseButton = new Button();
        startButton = new Button();

        ImageView imageStart = LoadingImage.loadImage("Play.png",25,25);
        startButton.setGraphic(imageStart);

        ImageView imageSpeed = LoadingImage.loadImage("boutonspeed.png",25,25);
        speedButton.setGraphic(imageSpeed);

        ImageView imagePause = LoadingImage.loadImage("pause.png",25,25);
        pauseButton.setGraphic(imagePause);

        HBox hBoxButton= new HBox(Settings.SPACE_HBOX);
        hBoxButton.getChildren().addAll(menuBar, startButton, speedButton, pauseButton);
        hBoxButton.setPadding(new Insets(10,5,5,5));

        buttonsPane.getChildren().add(hBoxButton);
        AnchorPane.setRightAnchor(hBoxButton, 10.0);

        //listeners pour les boutons
        speedButton.setOnMouseClicked(new SpeedButtonListener());
        startButton.setOnMouseClicked(new StartButtonListener());
        pauseButton.setOnMouseClicked(new PauseButtonListener());

        //listeners pour les items
        menuItem1.setOnAction(new NewMapListenerBtMap1("map1"));
        menuItem2.setOnAction(new NewMapListenerBtMap2("map2"));
    }

    private void initFooterPane() {

        footerPane.setStyle("-fx-background-color: black;");
        footerPane.setPrefSize(950,50);

        ImageView classic = LoadingImage.loadImage("towers.png");
        ImageView slow = LoadingImage.loadImage("kamek.png");
        ImageView upgrade = LoadingImage.loadImage("upgrade.png");
        ImageView bomb = LoadingImage.loadImage("bomb.png");

        classicButton = new Button("100", classic);
        slowButton = new Button("   150  ",slow);
        upgradeButton = new Button("Upgrade:100",upgrade);
        bombButton = new Button ("200",bomb);


        classicButton.setTextFill(Color.YELLOWGREEN);
        classicButton.setStyle("-fx-background-color: purple");
        slowButton.setStyle("-fx-background-color: indigo;");
        slowButton.setTextFill(Color.GREENYELLOW);
        upgradeButton.setStyle("-fx-background-color: cyan;");
        bombButton.setStyle("-fx-background-color: green;");


        HBox hBoxTowersButton = new HBox(Settings.SPACE_HBOX);
        hBoxTowersButton.getChildren().addAll(classicButton, slowButton,upgradeButton, bombButton);
        hBoxTowersButton.setPadding(new Insets(12.5,5,5,10));

        HBox hBoxMessage  = new HBox(Settings.SPACE_HBOX);

        footerPane.getChildren().addAll(hBoxTowersButton, hBoxMessage);
        RectTowersListener.sethBoxMessage(hBoxMessage);
        classicButton.setOnMouseClicked(new ClassicListener());
        slowButton.setOnMouseClicked(new SlowListener());
        upgradeButton.setOnMouseClicked(new UpgradeListener());
        bombButton.setOnMouseClicked(new CancelListener());
    }


    @Override
    public void start(Stage primaryStage) {

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
