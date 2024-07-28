public class ShutdownState implements LaptopState {
    @Override
    public void handlePowerButton(Laptop laptop) {
        System.out.println("Laptop is starting up.");
        laptop.setState(laptop.getNormalState());
    }

    @Override
    public void handleOpenLid(Laptop laptop) {
        System.out.println("Laptop is in shutdown state. Open lid does nothing.");
    }
}