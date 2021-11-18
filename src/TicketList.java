//TODO Remove TicketList and simply Initialise a list in TicketMachine (in constructor)
// this will be named AllAvailableTickets or AllAvailableTicketsList
// also all code needs to be refactored for this of course.

import java.util.ArrayList;
import java.util.List;

public class TicketList {
    final List<Ticket> allFilms;


    TicketList() {
        Ticket SW4 = new Ticket("StarWars 4", 12);
        Ticket IJ1 = new Ticket("Indiana Jones 1", 12);

        allFilms = new ArrayList<Ticket>();
        allFilms.add(SW4);
        allFilms.add(IJ1);
    }
}

