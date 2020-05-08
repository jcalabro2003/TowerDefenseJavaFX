package sample.view;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;
import sample.model.*;

import java.util.Collection;

public class RectTowersListener implements EventHandler<MouseEvent>, ChangeMapObserver {
    private Rectangle rec;
    private Map map;
    private static HBox hBoxMessage;
    private static boolean classicReady = false;
    private static boolean slowReady = false;
    private static boolean upgradeReady = false;
    private static boolean bombReady = false;
    private static Text notEnoughMoney = new Text("not enough money");
    private Building building;

    public RectTowersListener(Rectangle rec, Map map){
        this.rec = rec;
        this.map = map;
        map.addObserver(this);
    }

    private void addHandler(){
        building.getImageView().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                upgrade();
            }
        });
    }
    private void printNoMoney(){
        notEnoughMoney.setFill(Color.RED);
        Timeline timer = new Timeline(new KeyFrame(Duration.millis(3000), new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                notEnoughMoney.setFill(Color.BLACK);
            }
        }));
        timer.play();
    }

    private void upgrade(){
        if (upgradeReady && Player.getGold() >= 100 && building instanceof Upgradable && ((Upgradable) building).getLevel() < 3) {
            System.out.println("hé ho");
            ((Upgradable) building).upgrade();
            Player.BuyBuilding(100);
            upgradeReady = false;
        } else if(upgradeReady && Player.getGold() < 100) printNoMoney();
    }
    public static void setClassicReady(boolean classicReady) {
        RectTowersListener.classicReady = classicReady;
    }

    public static void setSlowReady(boolean slowReady) {
        RectTowersListener.slowReady = slowReady;
    }

    public static void setUpgradeReady(boolean upgradeReady) {
        RectTowersListener.upgradeReady = upgradeReady;
    }

    public static void setBombReady(boolean bombReady) {
        RectTowersListener.bombReady = bombReady;
    }

    public static void sethBoxMessage(HBox hBoxMessage) {
        RectTowersListener.hBoxMessage = hBoxMessage;
        hBoxMessage.getChildren().add(notEnoughMoney);
    }

    @Override
    public void handle(MouseEvent event) {

        if (!map.isOccupied(rec.getX() + 12.5 , rec.getY() + 12.5) && (classicReady || slowReady || bombReady)){
            if (classicReady && Player.getGold() >= Settings.CLASSIC_TOWER_PRICE) {
                building = BuildingsFactory.getInstance("basic", rec.getX() + 12.5 , rec.getY() + 12.5);
                Player.BuyBuilding(Settings.CLASSIC_TOWER_PRICE);
            } else if (slowReady && Player.getGold() >= Settings.SLOW_TOWER_PRICE){
                building = BuildingsFactory.getInstance("slow", rec.getX() + 12.5 , rec.getY() + 12.5);
                Player.BuyBuilding(Settings.SLOW_TOWER_PRICE);
            } else if (bombReady && Player.getGold() >= Settings.BOMB_CREATOR){
                building = BuildingsFactory.getInstance("bomb",  rec.getX() + 12.5 , rec.getY() + 12.5);
                Player.BuyBuilding(Settings.BOMB_CREATOR);
            }
            else {
                printNoMoney();

            }if(building != null){
                addHandler();
                map.addObjectToMap(building);
                classicReady = false;
                slowReady = false;
                bombReady = false;
            }
        } else {
            upgrade();
        }
    }

    @Override
    public void changeMap() {
        Map.getInstance();
    }
}
