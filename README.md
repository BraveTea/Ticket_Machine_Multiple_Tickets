# Ticket_Machine_Multiple_Tickets

TicketMachine, the update, provides the user with a choice between two films which are stored in an ArrayList.
Once a choice is made, but before payment is handled, the ticket will be put on a reserved list where it will stay during the process of 
buying the ticket or until an abort is intiated. After an abort the film will be put back on the list of available tickets. After payment
the ticket will be printed and the film will be removed from the available list.
The methods all work on a choice made by the user which stores a specific object. 
#rantModeOn
By using a specific object it makes sure that other
"threads" (no clue if I am using that correctly here) can try and use it. Of course this would never happen in my programme, simply because
I am not good enough a programmer yet to write stuff like that, and also this is just a simple exercise, but it is nice to think about what
would happen if I had for example 2 ticketmachines trying to buy the same ticket.



/*
A rewrite of TicketMachine, now with the added functionality of choosing between multiple films. 
I have restructured almost all of the code, it now all runs on type Ticket.
It also uses an ArrayList to store the films/tickets
*/
