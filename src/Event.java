import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Created by bogdannitescu on 09/12/2017.
 */
public class Event {

    private String eventName;
    private ArrayList<Ticket> tickets;

    public Event(String eventName, ArrayList<Ticket> tickets){
        this.eventName = eventName;
        this.tickets = tickets;
    }

    public String getEventName() {
        return eventName;
    }

    public ArrayList<Ticket> getTickets() {
        return tickets;
    }

    public boolean eventHasTickets() {
        return tickets.size() != 0;
    }
    
    public BigDecimal getCheapestTicketPrice() throws EventHasNoTicketsException {
        if (eventHasTickets()) {
            BigDecimal cheapestTicketPrice = tickets.get(0).getPrice();
            for (Ticket ticket : tickets) {
                if (ticketPriceIsLessThanActualCheapestTicketPrice(cheapestTicketPrice, ticket)) {
                    cheapestTicketPrice = ticket.getPrice();
                }
            }
            return cheapestTicketPrice;
        }
        else {
            throw new EventHasNoTicketsException();
        }
    }
    private boolean ticketPriceIsLessThanActualCheapestTicketPrice(BigDecimal cheapestTicketPrice, Ticket ticket) {
        return ticket.getPrice().compareTo(cheapestTicketPrice) == -1;
    }

    public void displayEventDetails() {
        System.out.print("Event " + eventName + " - $" + getCheapestTicketPrice());
    }

    public void displayEventDetailsWithAllTickets() {
        if (eventHasTickets()) {
            System.out.print("Event " + eventName + " - ");
            for (Ticket ticket : tickets) {
                ticket.displayPrice();
            }
            System.out.println();
        }
        else
        {
            System.out.println("Event " + eventName + " - No tickets available");
        }
    }
}
