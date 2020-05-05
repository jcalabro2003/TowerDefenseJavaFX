package sample;

import javafx.geometry.Insets;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Text;
import sample.model.*;

public class InfoPane extends TilePane implements StoppedObserver {
    private Text textHealthPoints;
    private static Text textWaveNumber;
    private Text textMoneyAmount;

    private int healthPoints;
    private static int waveNumber;
    private int moneyAmount;

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

        textWaveNumber = new Text();
        textWaveNumber.setText(Integer.toString(waveNumber));

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

        getChildren().addAll(hBoxHealthPoints, hBoxWave, hBoxMoney);
    }

    public static InfoPane getInstance(){
        if(InfoPane.instance == null){
            InfoPane.instance = new InfoPane();
        }
        return InfoPane.instance;
    }

    @Override
    public void react(GameObject o) {
        healthPoints = Player.getHealthPoints();
        textHealthPoints.setText(Integer.toString(healthPoints));
    }

    public static void updateWaveNumber(){
        waveNumber = Wave.getWaveNumber();
        textWaveNumber.setText(Integer.toString(waveNumber));
    }

    public  void  updateGold(){
        moneyAmount = Player.getGold();
        textMoneyAmount.setText(Integer.toString(moneyAmount));
    }
}
