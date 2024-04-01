import java.util.ArrayList;
import java.util.List;

public class Sandwich extends MenuItem{
    private String bread;
    private String protein;
    private List<String> add_ons;

    public Sandwich(String bread, String protein){
        this.bread = bread;
        this.protein = protein;
        this.add_ons = new ArrayList<>();
    }

    private void add_toppings(String topping){
        add_ons.add(topping);
    }

    private void remove_toppings(String topping){
        add_ons.remove(topping);
    }
    @Override
    public double price() {
        double total = switch (protein){
            case "Beef" -> 10.99;
            case "Chicken" -> 9.99;
            case "Fish" -> 9.99;
            default -> 0.00;
        };

        return total;
    }
}
