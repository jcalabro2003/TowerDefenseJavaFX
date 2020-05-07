package sample.model;
//
public interface ChangeMap {
    void notifyObserver();
    void addObserver(ChangeMapObserver o);
}
