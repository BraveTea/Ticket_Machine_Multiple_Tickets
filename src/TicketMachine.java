import org.jetbrains.annotations.NotNull;

import java.util.Scanner;
import java.util.ArrayList;

public class TicketMachine {

    private ArrayList<Ticket> availableTicketsList = new ArrayList<>();
    private ArrayList<Ticket> temporarilyReservedTicketsList = new ArrayList<>();
    private Ticket ticketChosen;
    private double balance;
    private String currency = "$";


    TicketMachine() {
        Ticket SW4 = new Ticket("StarWars 4", 12);
        Ticket IJ1 = new Ticket("Indiana Jones 1", 12);

        availableTicketsList.add(SW4);
        availableTicketsList.add(IJ1);
    }

    public void buyTicket(){
        welcomeMessage();
        showFilms();
        ticketChosen = chooseFilm();
        putTicketFromAvailableOnTempReserve(ticketChosen);
        paymentSystem();
        putTicketFromTempReserveOnAvailable(ticketChosen);

//        System.out.println(totalAmountOfChosenTickets(temporarilyReservedTicketsList)); //This line shows that if list is empty it will show 0.0 and still work
//        showTotalAmountOfChosenTickets(totalAmountOfChosenTickets(temporarilyReservedTicketsList)); //This line shows that it will correctly work with 1 film on it (not yet tested with multiple films because I haven't implemented that option yet.

    }

    private void welcomeMessage(){
        System.out.printf("Welcome to WS Theatres%n");
    }

    private void showTotalAmountOfChosenTickets(double totalAmount){
        System.out.printf("The current total amount of your chosen tickets is: %s%.2f%n", currency, totalAmount);
    }

    private double totalAmountOfChosenTickets(@NotNull ArrayList<Ticket> tempList){
        //shows a sum of all films on the passed list. This programme should mostly use the temporarilyReservedTicketsList
        double totalAmount = 0;
        for (int i = 0; i < tempList.size(); i++){
            totalAmount += tempList.get(i).getPrice();
        }
        return totalAmount;
    }

    void abort(){
        System.out.printf("thank you, byebye%n");
        System.exit(0);
    }

    private boolean enoughBalance(){
        return balance >= totalAmountOfChosenTickets(temporarilyReservedTicketsList);
    }

    private double payOutEntireBalance(double balance){
        return balance;
    }

    //When a client chooses a ticket their choice needs to be reserved until completion or cancellation of the process
    private void putTicketFromAvailableOnTempReserve(Ticket ticket){

        temporarilyReservedTicketsList.add(ticket);
        availableTicketsList.remove(ticket);
        //Lines below are for testing only
//        System.out.println("List of available films (availableTicketsList): "+availableTicketsList);
//        System.out.println("List of temporarily reserved films (temporaryReservedTicketsList): " + temporarilyReservedTicketsList);
    }

    //In case of procedure termination the ticket needs to be put back on the available list
    private void putTicketFromTempReserveOnAvailable(Ticket ticket){

        availableTicketsList.add(ticket);
        temporarilyReservedTicketsList.remove(ticket);
        //The lines below are test lines
//        System.out.println("List of temporarily reserved films (temporaryReservedTicketsList): " + temporarilyReservedTicketsList);
//        System.out.println("List of available films (availableTicketsList): "+availableTicketsList);
    }

    private void showFilms(){ //TODO Should I pass a ticketlist as a parameter here? in case of different film lists (not for now)
        //I want this to iterate through the list so that if I update the tickets it takes this into account and
        // chooseFilm doesn't have a problem with it. This merely goes if I stick to the same number of unique films
        // otherwise the switch statement doesn't work anymore. That is based on 2 films right now.

        System.out.println("Please choose a film by entering the corresponding number: "); //this is going to go later on. When I implement multiple tickets per session this needs to go

        for (int i = 0; i < availableTicketsList.size(); i++){
            System.out.printf("%d : %s%n", i+1, availableTicketsList.get(i).getName());
        }
    }

    private Ticket chooseFilm(){ //Ticket is the return type here. It is a Class Return and
        // must return an object of the specified class.
        //This method is used in buyTicket() and assigned to var ticketChosen to create a one time choice that is used
        // as an argument for other methods in class TicketMachine
        Scanner userInput = new Scanner(System.in);

        int choice = userInput.nextInt();
        switch (choice){
            case 1: //SW4, only during testing
                return availableTicketsList.get(0);
            case 2: //IJ1, only during testing
                return availableTicketsList.get(1);
            default:
                throw new IllegalStateException("Unexpected value: " + choice); //IDEA gave me this. I like it :)
        } //Can I write a try/catch for this throw? How does that work? TODO figure out "throw try catch exceptions"
    }

    private void userAddsToBalance(){
        Scanner userInput = new Scanner(System.in);
        addToBalance(userInput.nextDouble());
    }

    private void inputMoney(){
        System.out.printf("Please insert money%n");
        userAddsToBalance();
        showBalance();
    }

    private void paymentSystem(){
        while (!enoughBalance()) {
            System.out.printf("The current amount left to pay is: %.2f%n", remainderOfCosts());
            abortChoice();
            inputMoney();

        }
        printTicket();
        returnChange();
        resetBalanceToZero();
    }

    private void returnChange() {
        double change = getBalance() - totalAmountOfChosenTickets(temporarilyReservedTicketsList);
        System.out.printf("Here is your change back: %s%.2f%n", currency, change);
    }

    private void abortChoice() {
        System.out.printf("Do you wish to abort? Press Y/Yes or N/No%n"); //this should ask "do you wish to continue" but then the logic falls apart a little bit. User-wise I would ask continue
        Scanner userChoice = new Scanner(System.in);
        String choice = userChoice.nextLine();
        switch (choice){
            case "Y":
            case "Yes":
            case "y":
            case "yes":
                abort();
            case "N":
            case "No":
            case "n":
            case "no":
                break;
        }
    }

    private void showRemainderOfCosts(){
        System.out.println(remainderOfCosts());
    }

    private double remainderOfCosts(){
        return totalAmountOfChosenTickets(temporarilyReservedTicketsList) - getBalance();
    }

    //I specifically do not want a setter for balance, because a balance shouldn't be set, but added to
    private void resetBalanceToZero() {
        balance = 0;
    }

    private void addToBalance(double amount){
        balance += amount;
    }

    private double getBalance() {
        return balance;
    }

    public void showBalance(){
        System.out.println(getBalance());
    }

    private void printTicket(){ //A test method to see if chooseFilm() works
        System.out.printf("Enjoy %s%n", ticketChosen.getName());

    }
}



//    //TODO This method isn't finished yet. This is just an idea.
//    private void returnMoney(double balance){
//        System.out.printf("here is your %s%.2f back", currency, balance);
//    }

//This method is going to suck. I don't want to calculate balance here. Balance must be updated instead of
//    private double payOutAfterPayment(double balance, double totalAmount){
//        if (enoughBalance(balance, totalAmount)){
//            return balance - totalAmount;
//        }
//        else return 0;
//    }
