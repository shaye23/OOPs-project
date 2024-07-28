// Another implementation of an EventListener
public class Listener2 implements EventManager.EventListener {
    @Override
    public void onEvent(String event) {
        System.out.println("AnotherListener received event: " + event);
    }
}
