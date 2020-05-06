package sample;

import javafx.geometry.Insets;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import sample.model.*;

public class InfoPane extends TilePane implements StoppedObserver {
    private static Text textHealthPoints;
    private static Text textWaveNumber;
    private static Text textMoneyAmount;

    private static int healthPoints;
    private static int waveNumber;
    private static int moneyAmount;

    private Map bodyPane;

    private static InfoPane instance = null;

    private InfoPane() {

        ImageView imageViewHearth = LoadingImage.loadImage("hearth.png");
        ImageView imageViewWave = LoadingImage.loadImage("wave.png");
        ImageView imageViewMoney = LoadingImage.loadImage("money.png");
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


        getChildren().addAll(hBoxHealthPoints, hBoxWave, hBoxMoney);
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
        else if (healthPoints == 0) {
            ImageView gameover = LoadingImage.loadImage("gameover.png",950,500);
            Map.getInstance().getChildren().add(gameover);
        }
    }

}
