/**
 * Created by bogdannitescu on 09/12/2017.
 */
public class Location {

    private int xCoordinate;
    private int yCoordinate;
    private Event event = null;

    public Location(int xCoordinate, int yCoordinate)
    {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }

    public Location(int xCoordinate, int yCoordinate, Event event) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.event = event;
    }

    public boolean locationHasEvent() {
        return this.event != null;
    }

    public boolean locationHasTicketsAvailable() {
        return locationHasEvent() && event.eventHasTickets();
    }

    public void printLocationDetails() {
        if (locationHasEvent()) {
            System.out.print("("+xCoordinate+","+yCoordinate+") - ");
            event.displayEventDetailsWithAllTickets();
        }
        else {
            System.out.print("("+xCoordinate+","+yCoordinate+") - ");
            System.out.println("Location does not host any event");
        }
    }
}
