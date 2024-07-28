public class PremiumSubscription implements Subscription {
    @Override
    public String getDescription() {
        return "Premium Subscription: Access to all features.";
    }

    @Override
    public double getPrice() {
        return 14.99; // Monthly price in dollars
    }
}