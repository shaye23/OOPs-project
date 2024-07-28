public class StandardSubscription implements Subscription {
    @Override
    public String getDescription() {
        return "Standard Subscription: Access to standard features.";
    }

    @Override
    public double getPrice() {
        return 9.99; // Monthly price in dollars
    }
}