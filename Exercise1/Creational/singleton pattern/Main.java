public class Main {
    public static void main(String[] args) {
        // Get the singleton instance of EventManager
        EventManager eventManager = EventManager.getInstance();

        // Create and add a sample listener
        Listener1 sampleListener = new Listener1();
        eventManager.addEventListener(sampleListener);

        // Create and add another listener
        Listener2 anotherListener = new Listener2();
        eventManager.addEventListener(anotherListener);

        // Dispatch an event
        eventManager.dispatchEvent("Test Event 1");

        // Remove the sample listener
        eventManager.removeEventListener(sampleListener);

        // Dispatch another event to show that the first listener has been removed
        eventManager.dispatchEvent("Test Event 2");

        // Remove the another listener
        eventManager.removeEventListener(anotherListener);

        // Dispatch another event to show that all listeners have been removed
        eventManager.dispatchEvent("Test Event 3");
    }
}
