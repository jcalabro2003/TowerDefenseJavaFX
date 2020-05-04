package sample.model;


public interface Stop {
    void notifyObserver();
    void addObserver(StoppedObserver o);
}
