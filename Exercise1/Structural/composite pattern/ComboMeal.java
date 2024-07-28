import java.util.ArrayList;
import java.util.List;

class ComboMeal implements MealItem {
    private String name;
    private List<MealItem> items;

    public ComboMeal(String name) {
        this.name = name;
        this.items = new ArrayList<>();
    }

    public void addItem(MealItem item) {
        items.add(item);
    }

    public void removeItem(MealItem item) {
        items.remove(item);
    }

    @Override
    public double getPrice() {
        return items.stream().mapToDouble(MealItem::getPrice).sum();
    }

    @Override
    public int getCalories() {
        return items.stream().mapToInt(MealItem::getCalories).sum();
    }
}
