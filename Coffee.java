public class Coffee extends MenuItem{

    private String size;
    private int add_ins;
    private int cups;
    private final double add_ins_cost = 0.30;

    public Coffee(String size, int add_ins, int cups){
        this.size = size;
        this.add_ins = add_ins;
        this.cups = cups;
    }

    public void add_add_ins(){
        this.add_ins++;

    }

    public void remove_add_ins(){
        if(this.add_ins != 0){
            this.add_ins--;
        }
    }

    @Override
    public double price() {

        double size_cost = switch (size){
            case "Short" -> 1.99;
            case "Tall" -> 2.49;
            case "Grande" -> 2.99;
            case "Venti" -> 3.49;

            default -> 0;
        };

        double total_add_ins = this.add_ins * add_ins_cost;
        return (size_cost + total_add_ins) * cups;
    }

}
