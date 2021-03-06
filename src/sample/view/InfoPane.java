package sample.view;

import sample.model.Settings;
import sample.model.*;

import javafx.geometry.Insets;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class InfoPane extends TilePane implements StoppedObserver {
    private static Text textHealthPoints;
    private static Text textWaveNumber;
    private static Text textMoneyAmount;
    private static Text textCountdown;

    private static int healthPoints;
    private static int waveNumber;
    private static int moneyAmount;
    private static int countdown;

    private static InfoPane instance = null;

    private InfoPane() {

        ImageView imageViewHearth = LoadingImage.loadImage("hearth.png");
        ImageView imageViewWave = LoadingImage.loadImage("wave.png");
        ImageView imageViewMoney = LoadingImage.loadImage("money.png");
        ImageView imageViewCountdown = LoadingImage.loadImage("clock.gif");
        healthPoints = Settings.HEALTH_POINTS;
        moneyAmount = Settings.MONEY_AMOUNT;
        waveNumber = Settings.WAVE_NUMBER;

        textHealthPoints = new Text();
        textHealthPoints.setText(Integer.toString(healthPoints));
        textHealthPoints.setFill(Color.BEIGE);

        textWaveNumber = new Text();
        textWaveNumber.setText(Integer.toString(waveNumber));
        textWaveNumber.setFill(Color.BEIGE);

        textMoneyAmount = new Text();
        textMoneyAmount.setText(Integer.toString(moneyAmount));
        textMoneyAmount.setFill(Color.BEIGE);

        textCountdown = new Text();
        textCountdown.setText(Integer.toString(countdown));
        textCountdown.setFill(Color.BEIGE);

        HBox hBoxHealthPoints = new HBox(Settings.SPACE_HBOX);
        hBoxHealthPoints.getChildren().addAll(imageViewHearth, textHealthPoints);
        hBoxHealthPoints.setPadding(new Insets(5,5,5,5));
        hBoxHealthPoints.setStyle("-fx-background-color: black;-fx-border-color: red;-fx-border-width: 2px;-fx-border-radius: 50px;");
        hBoxHealthPoints.setOpacity(0.7);

        HBox hBoxWave = new HBox(Settings.SPACE_HBOX);
        hBoxWave.getChildren().addAll(imageViewWave, textWaveNumber);
        hBoxWave.setPadding(new Insets(5,5,5,5));
        hBoxWave.setStyle("-fx-background-color: black;-fx-border-color: red;-fx-border-width: 2px;-fx-border-radius: 50px;");
        hBoxWave.setOpacity(0.7);

        HBox hBoxMoney = new HBox(Settings.SPACE_HBOX);
        hBoxMoney.getChildren().addAll(imageViewMoney, textMoneyAmount);
        hBoxMoney.setPadding(new Insets(5,5,5,5));
        hBoxMoney.setStyle("-fx-background-color: black;-fx-border-color: red;-fx-border-width: 2px;-fx-border-radius: 50px;");
        hBoxMoney.setOpacity(0.7);

        HBox hBoxCountdown = new HBox(Settings.SPACE_HBOX);
        hBoxCountdown.getChildren().addAll(imageViewCountdown, textCountdown);
        hBoxCountdown.setPadding(new Insets(5,5,5,5));
        hBoxCountdown.setStyle("-fx-background-color: black;-fx-border-color: red;-fx-border-width: 2px;-fx-border-radius: 50px;");
        hBoxCountdown.setOpacity(0.7);

        getChildren().addAll(hBoxHealthPoints, hBoxWave, hBoxMoney, hBoxCountdown);
        setPadding(new Insets(10,5,5,10));
        setHgap(10);
    }

    public static InfoPane getInstance(){
        if(InfoPane.instance == null){
            InfoPane.instance = new InfoPane();
        }
        return InfoPane.instance;
    }

    @Override
    public void react(GameObject o) {
        update();
    }

    public static void update(){
        if (healthPoints > 0) {
            waveNumber = Wave.getWaveNumber();
            textWaveNumber.setText(Integer.toString(waveNumber));
            healthPoints = Player.getHealthPoints();
            textHealthPoints.setText(Integer.toString(healthPoints));
            moneyAmount = Player.getGold();
            textMoneyAmount.setText(Integer.toString(moneyAmount));
        }
    }

    public static void init() {
        textWaveNumber.setText(Integer.toString(Settings.WAVE_NUMBER));
        textHealthPoints.setText(Integer.toString(Settings.HEALTH_POINTS));
        textMoneyAmount.setText(Integer.toString(Settings.MONEY_AMOUNT));
    }

    public static void countdown(int time){
        countdown = time;
        textCountdown.setText(Integer.toString(time));
    }

}
