public class BasicSubscription implements Subscription {
    @Override
    public String getDescription() {
        return "Basic Subscription: Access to basic features.";
    }

    @Override
    public double getPrice() {
        return 5.99; // Monthly price in dollars
    }
}