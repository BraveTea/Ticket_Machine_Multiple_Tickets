public record Ticket(String name, double price) {

    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    @Override   //Override of toString to get a return on println(arrayList)
    public String toString() {
        return getName() + " " + String.valueOf(getPrice());
    }
}

/*

//this was the original code before I updated Ticket class to a Record as suggested by IDEA.
//  I have kept the code just in case
//Converting this into a record has, I believe, opened up the private String name, and private double price.
//  not sure yet that that is a good idea.
//No problems after converting it to a record. Everything still works.

public class Ticket {
    private final String name;
    private final double price;

    Ticket(String name, double price){
        this.name = name;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    @Override   //Override of toString to get a return on println(arrayList)
    public String toString(){
        return getName() + " " + String.valueOf(getPrice());
    }
}
 */