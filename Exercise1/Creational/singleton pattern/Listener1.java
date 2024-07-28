// Sample implementation of an EventListener
public class Listener1 implements EventManager.EventListener {
    @Override
    public void onEvent(String event) {
        System.out.println("SampleListener received event: " + event);
    }
}
