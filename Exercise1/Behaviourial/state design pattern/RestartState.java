public class RestartState implements LaptopState {
    @Override
    public void handlePowerButton(Laptop laptop) {
        System.out.println("Laptop is already restarting.");
    }

    @Override
    public void handleOpenLid(Laptop laptop) {
        System.out.println("Laptop is restarting. Please wait.");
    }
}