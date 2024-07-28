public class Laptop {
    private final LaptopState shutdownState;
    private final LaptopState restartState;
    private final LaptopState normalState;
    
    private LaptopState state;

    public Laptop() {
        shutdownState = new ShutdownState();
        restartState = new RestartState();
        normalState = new NormalState();
        
        state = shutdownState; // Initial state
    }

    public void setState(LaptopState state) {
        this.state = state;
    }

    public LaptopState getShutdownState() {
        return shutdownState;
    }

    public LaptopState getRestartState() {
        return restartState;
    }

    public LaptopState getNormalState() {
        return normalState;
    }

    public void pressPowerButton() {
        state.handlePowerButton(this);
    }

    public void openLid() {
        state.handleOpenLid(this);
    }
}