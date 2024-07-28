public class NormalState implements LaptopState {
    @Override
    public void handlePowerButton(Laptop laptop) {
        System.out.println("Laptop is shutting down.");
        laptop.setState(laptop.getShutdownState());
    }

    @Override
    public void handleOpenLid(Laptop laptop) {
        System.out.println("Laptop is waking up from sleep mode.");
    }
}