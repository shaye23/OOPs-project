

public class Main {
    public static void main(String[] args) {
        Laptop laptop = new Laptop();

        // Pressing power button from shutdown state
        laptop.pressPowerButton(); // Output: Laptop is starting up.

        // Opening lid in normal state
        laptop.openLid(); // Output: Laptop is waking up from sleep mode.

        // Pressing power button in normal state
        laptop.pressPowerButton(); // Output: Laptop is shutting down.

        // Opening lid in shutdown state
        laptop.openLid(); // Output: Laptop is in shutdown state. Open lid does nothing.
    }
}
