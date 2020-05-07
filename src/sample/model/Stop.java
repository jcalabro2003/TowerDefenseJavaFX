package sample.model;
//
public interface Stop {
    Object key = new Object();

    void notifyObserver();
    void addObserver(StoppedObserver o);
    static Object getKey(){
        return key;
    }
}
