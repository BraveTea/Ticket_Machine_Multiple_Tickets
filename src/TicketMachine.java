import java.util.Scanner;
import java.util.ArrayList;

public class TicketMachine {

    TicketList TL1;
    ArrayList<Ticket> temporaryReservedTicketsList = new ArrayList<>();
    Ticket ticketChosen;
    double balance;
    String currency = "$";

    TicketMachine() {
        TL1 = new TicketList();
    }

    void buyTicket(){
        showFilms();
        ticketChosen = chooseFilm();
//        TESTprintTicket();
        putTicketFromAvailableOnTempReserve(ticketChosen);
        putTicketFromTempReserveOnAvailable(ticketChosen);
    }

    //TODO Pseudo code abort()
    void abort(){
        if (balance > 0){
            returnMoney(balance);
        }
    }

    //TODO This method isn't finished yet. This is just an idea.
    void returnMoney(double balance){
        System.out.printf("here is your %s%.2f back", currency, balance);
    }

    //When a client chooses a ticket their choice needs to be reserved until completion or cancellation of the process
    void putTicketFromAvailableOnTempReserve(Ticket ticket){
        int originalIndexOfTicket = TL1.allFilms.indexOf(ticket); //perhaps change var name to indexOfTicketAvailableList
        temporaryReservedTicketsList.add(ticket);
        TL1.allFilms.remove(originalIndexOfTicket);
        //Lines below are for testing only
        System.out.println("List of available films (TL1.allFilms): "+TL1.allFilms);
        System.out.println("List of temporarily reserved films (temporaryReservedTicketsList): " + temporaryReservedTicketsList);
    }

    //In case of procedure termination the ticket needs to be put back on the available list
    void putTicketFromTempReserveOnAvailable(Ticket ticket){

        int indexOfTicketReservedList = temporaryReservedTicketsList.indexOf(ticket);
        TL1.allFilms.add(ticket);
        temporaryReservedTicketsList.remove(ticket);
        //The lines below are test lines
        System.out.println("List of temporarily reserved films (temporaryReservedTicketsList): " + temporaryReservedTicketsList);
        System.out.println("List of available films (TL1.allFilms): "+TL1.allFilms);
    }

    void showFilms(){ //TODO Should I pass a ticketlist as a parameter here? in case of different film lists (not for now)
        //I want this to iterate through the list so that if I update the tickets it takes this into account and
        // chooseFilm doesn't have a problem with it. This merely goes if I stick to the same number of unique films
        // otherwise the switch statement doesn't work anymore. That is based on 2 films right now.

        System.out.println("Please choose a film by entering the corresponding number: ");

        for (int i = 0; i < TL1.allFilms.size(); i++){
            System.out.printf("%d : %s%n", i+1, TL1.allFilms.get(i).getName());
        }
    }

    Ticket chooseFilm(){ //Ticket is the return type here. It is a Class Return and
        // must return an object of the specified class.
        //This method is used in buyTicket() and assigned to var ticketChosen to create a one time choice that is used
        // as an argument for other methods in class TicketMachine
        Scanner userInput = new Scanner(System.in);

        int choice = userInput.nextInt();
        switch (choice){
            case 1: //SW4, only during testing
                return TL1.allFilms.get(0);
            case 2: //IJ1, only during testing
                return TL1.allFilms.get(1);
            default:
                throw new IllegalStateException("Unexpected value: " + choice); //IDEA gave me this. I like it :)
        } //Can I write a try/catch for this throw? How does that work? TODO figure out "throw try catch exceptions"
    }



    void TESTprintTicket(){ //A test method to see if chooseFilm() works
        System.out.println(ticketChosen);
        System.out.println("The line above is an Ticket object");
    }
}
