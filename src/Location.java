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

    public boolean hasEvent() {
        return this.event != null;
    }

    public boolean hasTicketsAvailable() {
        return hasEvent() && event.eventHasTickets();
    }

    public int distanceToCoordinates(int x, int y) {
        return Math.abs(x-xCoordinate) + Math.abs(y-yCoordinate);
    }

    public void printLocationDetails() {
        if (hasEvent()) {
            System.out.print("("+xCoordinate+","+yCoordinate+") - ");
            event.displayEventDetailsWithAllTickets();
        }
        else {
            System.out.print("("+xCoordinate+","+yCoordinate+") - ");
            System.out.println("Location does not host any event");
        }
    }
}
