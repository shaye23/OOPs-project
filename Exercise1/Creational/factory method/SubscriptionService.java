import java.util.Scanner;

public class SubscriptionService {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose a subscription plan (basic, standard, premium):");
        String userChoice = scanner.nextLine();

        try {
            Subscription subscription = SubscriptionFactory.createSubscription(userChoice);
            System.out.println("Subscription Created: " + subscription.getDescription());
            System.out.printf("Monthly Price: $%.2f%n", subscription.getPrice());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } finally {
            scanner.close();
        }
    }
}