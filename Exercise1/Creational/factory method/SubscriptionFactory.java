// SubscriptionFactory.java
public class SubscriptionFactory {
    public static Subscription createSubscription(String subscriptionType) {
        switch (subscriptionType.toLowerCase()) {
            case "basic":
                return new BasicSubscription();
            case "standard":
                return new StandardSubscription();
            case "premium":
                return new PremiumSubscription();
            default:
                throw new IllegalArgumentException("Unknown subscription type: " + subscriptionType);
        }
    }
}