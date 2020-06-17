package sample.model;

import java.util.ArrayList;

public class Path {

    private ArrayList<Point> points;
    private String typeMap;
    private String typePath;

    public Path(String typeMap, String typePath) {
        this.typeMap = typeMap;
        this.typePath = typePath;
        points = getPoints();
    }

    public ArrayList<Point> getPoints() {
        ArrayList<Point> points = new ArrayList<>();

        switch (typeMap) {
            case ("map1"):
                points.add(new Point(225, 75));
                points.add(new Point(225, 175));
                points.add(new Point(475, 175));
                points.add(new Point(475, 50));
                points.add(new Point(950, 50));
                break;
            case ("map2"):
                switch (typePath) {
                    case ("path1"):
                        points.add(new Point(950, 75));
                        break;
                    case ("path2"):
                        points.add(new Point(225, 75));
                        points.add(new Point(225, 275));
                        points.add(new Point(325, 275));
                        points.add(new Point(325, 175));
                        points.add(new Point(625, 175));
                        points.add(new Point(625, 75));
                        points.add(new Point(950, 75));
                        break;
                }
                break;
        }

        return points;
    }

    public boolean isPath(int x, int y) {
        boolean isPath = false;

        switch (typeMap) {
            case ("map1"):
                if ((y == 50 && x >= 0 && x <= 225) || (x == 200 && y >= 50 && y <= 150) || (y == 150 && x >= 200 && x <= 450)
                        || (x == 450 && y >= 50 && y <= 150) || (y == 50 && x >= 450 && x <= 950)) {
                    isPath = true;
                }
                break;
            case ("map2"):
                if ((y == 50 && x >= 0 && x <= 950) || (x == 200 && y >= 50 && y <= 250) || (y == 250 && x >= 200 && x <= 300)
                        || (x == 300 && y >= 150 && y <= 250) || (y == 150 && x >= 300 && x <= 600) || (x == 600 && y >= 50 && y <= 150)) {
                    isPath = true;
                }
                break;
        }

        return isPath;
    }
}