package sample.model;

import java.util.ArrayList;

public class Path {

    private ArrayList<Point> path;
    private String typeMap;

    public Path(String typeMap) {
        this.typeMap = typeMap;
        path =  getPoints();
    }

    public ArrayList<Point> getPoints() {
        ArrayList<Point> points = new ArrayList<>();

        switch (typeMap) {
            case ("map1") :
                points.add(new Point(225, 75));
                points.add(new Point(225, 175));
                points.add(new Point(475, 175));
                points.add(new Point(475, 50));
                points.add(new Point(950, 50));
                break;
            case ("map2") :
                points.add(new Point(225, 75));
                points.add(new Point(950, 75));
                /*
                points.add(new Point(175, 75));
                points.add(new Point(175, 325));
                points.add(new Point(775, 325));
                points.add(new Point(775, 75));
                points.add(new Point(950, 50));
                points.add(new Point(325, 75));
                points.add(new Point(325, 175));
                points.add(new Point(325, 525));
                points.add(new Point(725, 525));
                points.add(new Point(725, 50));
                 */
                break;
            default:
                break;
        }

        return points;
    }

    public boolean isPath(int x, int y) {
        boolean isPath = false;

        switch (typeMap) {
            case ("map1") :
                if ((y == 50 && x >= 0 && x <= 225) || (x==200 && y>=50 && y<=150) || (y == 150 && x >= 200 && x <= 450)
                        || (x==450 && y>=50 && y<=150) || (y == 50 && x >= 450 && x <= 950) ) {
                    isPath = true;
                }
                break;
            case ("map2") :
                if (y == 50)
                    isPath = true;
                break;
        }

        return isPath;
    }

    public ArrayList<Point> setPath(String typeMap) {
        this.typeMap = typeMap;

        return  this.getPoints();
    }
}