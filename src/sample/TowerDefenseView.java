package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.stage.Stage;

public class TowerDefenseView extends Application {
    private Scene mainScene;

    private BorderPane mainPane;
    private BorderPane headerPane;
    private GridPane bodyPane;
    private TilePane footerPane;
    private TilePane infoPane;
    private TilePane buttonsPane;

    private Button speedButton;
    private Button pauseButton;

    private Text textHealthPoints;
    private Text textWaveNumber;
    private Text textMoneyAmount;

    private int healthPoints = 20;
    private int waveNumber = 0;
    private int moneyAmount = 1000;

    private void initMainPane() {
        headerPane = new BorderPane();
        bodyPane = new GridPane();
        footerPane = new TilePane();

        footerPane.setStyle("-fx-background-color: red;");
        footerPane.setPrefSize(950,50);

        mainPane.setTop(headerPane);
        mainPane.setCenter(bodyPane);
        mainPane.setBottom(footerPane);
    }

    private void initHeaderPane() {
        infoPane = new TilePane();
        buttonsPane = new TilePane();

        infoPane.setPrefSize(250,25);

        initInfoPane();
        initButtonsPane();

        headerPane.setLeft(infoPane);
        headerPane.setRight(buttonsPane);
    }

    private void initInfoPane() {
        Image labelHearth = new Image(Main.class.getResourceAsStream("hearth.png"));
        ImageView imageViewHearth = new ImageView(labelHearth);
        imageViewHearth.setFitHeight(15);
        imageViewHearth.setFitWidth(15);

        textHealthPoints = new Text();
        textHealthPoints.setText(Integer.toString(healthPoints));

        Image labelWave = new Image(Main.class.getResourceAsStream("wave.png"));
        ImageView imageViewWave = new ImageView(labelWave);
        imageViewWave.setFitHeight(15);
        imageViewWave.setFitWidth(15);

        textWaveNumber = new Text();
        textWaveNumber.setText(Integer.toString(waveNumber) + "/7");

        Image labelMoney = new Image(Main.class.getResourceAsStream("money.png"));
        ImageView imageViewMoney = new ImageView(labelMoney);
        imageViewMoney.setFitHeight(15);
        imageViewMoney.setFitWidth(15);

        textMoneyAmount = new Text();
        textMoneyAmount.setText(Integer.toString(moneyAmount));

        infoPane.getChildren().add(imageViewHearth);
        infoPane.getChildren().add(textHealthPoints);
        infoPane.getChildren().add(imageViewWave);
        infoPane.getChildren().add(textWaveNumber);
        infoPane.getChildren().add(imageViewMoney);
        infoPane.getChildren().add(textMoneyAmount);

    }

    private void initButtonsPane() {
        speedButton = new Button("Speed");
        pauseButton = new Button("Pause");

        buttonsPane.getChildren().add(speedButton);
        buttonsPane.getChildren().add(pauseButton);
    }

    private void initBodyPane() {

    }

    private void initFooterPane() {

    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        /*
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
         */
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


    public static void runTowerDefense() {
        launch();
    }
}
