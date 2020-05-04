package sample;

import sample.model.LoadingImage;
import sample.model.Map;
import sample.model.Settings;
import sample.view.*;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.*;
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

    private Text textHealthPoints;
    private Text textWaveNumber;
    private Text textMoneyAmount;

    private int healthPoints;
    private int waveNumber;
    private int moneyAmount;

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

        initInfoPane();
        initButtonsPane();

        headerPane.setLeft(infoPane);
        headerPane.setRight(buttonsPane);
    }

    private void initInfoPane() {
        ImageView imageViewHearth = LoadingImage.loadImage("hearth.png");
        ImageView imageViewWave = LoadingImage.loadImage("wave.png");
        ImageView imageViewMoney = LoadingImage.loadImage("money.png");

        textHealthPoints = new Text();
        textHealthPoints.setText(Integer.toString(healthPoints));

        textWaveNumber = new Text();
        textWaveNumber.setText(Integer.toString(waveNumber) + "/7");

        textMoneyAmount = new Text();
        textMoneyAmount.setText(Integer.toString(moneyAmount));

        HBox hBoxHealthPoints = new HBox(Settings.SPACE_HBOX);
        hBoxHealthPoints.getChildren().addAll(imageViewHearth, textHealthPoints);
        hBoxHealthPoints.setPadding(new Insets(5,5,5,5));

        HBox hBoxWave = new HBox(Settings.SPACE_HBOX);
        hBoxWave.getChildren().addAll(imageViewWave, textWaveNumber);
        hBoxWave.setPadding(new Insets(5,5,5,5));

        HBox hBoxMoney = new HBox(Settings.SPACE_HBOX);
        hBoxMoney.getChildren().addAll(imageViewMoney, textMoneyAmount);
        hBoxMoney.setPadding(new Insets(5,5,5,5));

        infoPane.getChildren().addAll(hBoxHealthPoints, hBoxWave, hBoxMoney);
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
        healthPoints = Settings.HEALTH_POINTS;
        moneyAmount = Settings.MONEY_AMOUNT;
        waveNumber = 0;

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
