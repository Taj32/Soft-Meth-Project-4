public class Donut extends MenuItem{

    private String type;
    private String flavor;
    private int quantity;

    public Donut(String type, String flavor, int quantity){
        this.type = type;
        this.flavor = flavor;
        this.quantity = quantity;
    }
    @Override
    public double price() {
        double donut_price = switch(type){
            case "Yeast" -> 1.79;
            case "Cake" -> 1.89;
            case "Donut Hole" -> 0.39;
            default -> 0.00;
        };

        return donut_price * quantity;

    }
}
