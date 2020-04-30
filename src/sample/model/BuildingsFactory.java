package sample.model;

public class BuildingsFactory {

    public static Building getInstance(String type){
        Building res = null;
        switch (type){
            case "classic tower":
                res = new Tower(5, 10, 1000);
                break;
            case "slow tower":
                res = new Tower(0, 10, 1500);

            default:
                break;
        }
        return res;
    }
}
