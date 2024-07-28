import java.util.ArrayList;
import java.util.List;

// Singleton EventManager class
public class EventManager {

    // Private static instance of the singleton class
    private static EventManager instance;

    // List to hold event listeners
    private List<EventListener> listeners;

    // Private constructor to prevent instantiation
    private EventManager() {
        listeners = new ArrayList<>();
    }

    // Public method to provide access to the instance
    public static synchronized EventManager getInstance() {
        if (instance == null) {
            instance = new EventManager();
        }
        return instance;
    }

    // Method to dispatch an event
    public void dispatchEvent(String event) {
        System.out.println("Event Dispatched: " + event);
        // Notify all registered listeners
        for (EventListener listener : listeners) {
            listener.onEvent(event);
        }
    }

    // Method to add an event listener
    public void addEventListener(EventListener listener) {
        listeners.add(listener);
        System.out.println("Listener added: " + listener.getClass().getName());
    }

    // Method to remove an event listener
    public void removeEventListener(EventListener listener) {
        listeners.remove(listener);
        System.out.println("Listener removed: " + listener.getClass().getName());
    }

    // Inner interface for event listeners
    public interface EventListener {
        void onEvent(String event);
    }
}
