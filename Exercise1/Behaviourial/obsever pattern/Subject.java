// Subject.java
import java.util.ArrayList;
import java.util.List;

public abstract class Subject {
    private List<Observer> observers = new ArrayList<>();

    public void attach(Observer observer) {
        observers.add(observer);
    }

    public void detach(Observer observer) {
        observers.remove(observer);
    }

    protected void notifyObservers(String course, boolean isPresent) {
        for (Observer observer : observers) {
            observer.update(course, isPresent);
        }
    }
}
