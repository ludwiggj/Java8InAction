package lambdasinaction.chap8.observer;

interface Subject {
    void registerObserver(Observer o);
    void notifyObservers(String tweet);
}