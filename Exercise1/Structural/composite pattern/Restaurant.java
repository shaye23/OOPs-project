public class Restaurant {
    public static void main(String[] args) {
        // Create individual dishes
        MealItem burger = new Dish("Burger", 5.99, 800);
        MealItem fries = new Dish("Fries", 2.99, 300);
        MealItem soda = new Dish("Soda", 1.50, 150);

        // Create a combo meal
        ComboMeal combo1 = new ComboMeal("Combo 1");
        combo1.addItem(burger);
        combo1.addItem(fries);
        combo1.addItem(soda);

        // Create another combo meal that includes the first combo
        ComboMeal combo2 = new ComboMeal("Combo 2");
        combo2.addItem(combo1);
        combo2.addItem(new Dish("Salad", 3.99, 200));

        // Calculate total price and calories for combo2
        System.out.println("Combo 2 Price: $" + combo2.getPrice());
        System.out.println("Combo 2 Calories: " + combo2.getCalories());
    }
}
