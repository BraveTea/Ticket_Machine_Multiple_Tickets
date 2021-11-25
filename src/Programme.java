public class Programme {

    public static void main(String[] args) {
        System.out.printf("%s%n", "test");

        TicketMachine TM1 = new TicketMachine();


//        System.out.println(TM1.TL1.allFilms);
//        System.out.println((TM1.TL1.allFilms.get(0).getPrice()) + 12); //testing to see if this actually gets the double
//        System.out.println(TM1.TL1.allFilms.get(1).getName());


        TM1.buyTicket();

    }
}
