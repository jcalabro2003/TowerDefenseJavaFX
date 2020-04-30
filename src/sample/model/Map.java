package sample.model;

import java.util.ArrayList;

public class Map {
    private static Map instance = null;
    private ArrayList<GameObject> gameObjects = new ArrayList<>();
    private int width;
    private int height;
    private ArrayList<Path> paths = new ArrayList<>();

    private Map(int width, int height){
        this.width = width;
        this.height = height;
    }

    public static Map getInstance(int width, int height){
        if (Map.instance == null){
            Map.instance = new Map(width, height);
        }
        return Map.instance;
    }

    public void addGameObject(GameObject g){
        gameObjects.add(g);
    }

    public void setPaths(ArrayList<Path> paths) {
        this.paths = paths;
    }

    public ArrayList<Path> getPaths() {
        return paths;
    }
}
