import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by bogdannitescu on 09/12/2017.
 */
public class Map {
    static final int X_COORDINATE_LOWER_BOUND = -10;
    static final int X_COORDINATE_UPPER_BOUND = 10;
    static final int Y_COORDINATE_LOWER_BOUND = -10;
    static final int Y_COORDINATE_UPPER_BOUND = 10;
    public static final int MINIMUM_NUMBER_OF_TICKETS = 1;
    public static final int MAXIMUM_NUMBER_OF_TICKETS_FOR_EVENT = 4;
    private int eventNumber = 0;
    private ArrayList<Location> listOfLocations = new ArrayList<>();
    private Location[][] mapOfLocations = new Location[21][21];

    public Map()
    {
        populateMapLocations();
    }

    private void populateMapLocations() {
        Random r = new Random();
        for (int x = X_COORDINATE_LOWER_BOUND; x <= X_COORDINATE_UPPER_BOUND; x++) {
            for (int y = Y_COORDINATE_LOWER_BOUND; y <= Y_COORDINATE_UPPER_BOUND; y++)
                populateXYCoordinates(r, x, y);
        }
    }

    private void populateXYCoordinates(Random r, int x, int y) {
        Location location;
        if (locationShouldHaveEvent(r)) location = generateLocationWithRandomEvent(r, x, y);
        else {
            location = generateLocationWithoutEvent(x, y);
        }

        listOfLocations.add(location);
        mapOfLocations[x+10][y+10] = location;
    }

    private Location generateLocationWithoutEvent(int x, int y) {
        Location location;
        location = new Location(x,y);
        return location;
    }

    private Location generateLocationWithRandomEvent(Random r, int x, int y) {
        Location location;
        eventNumber++;
        String eventName = String.valueOf(eventNumber);
        ArrayList<Ticket> eventTickets = new ArrayList<>();
        if (eventShouldHaveTickets(r)) generateTicketsForEvent(r, eventTickets);
        Event event = new Event(eventName,eventTickets);
        location = new Location(x,y,event);
        return location;
    }

    private void generateTicketsForEvent(Random r, ArrayList<Ticket> eventTickets) {
        int numberOfTickets = generateRandomNumberOfTickets();
        while (numberOfTickets >= 1) {
            float randomPrice = (float) (0.05 + r.nextFloat()*(100-0.05));
            BigDecimal ticketPrice = new BigDecimal(randomPrice).setScale(2,BigDecimal.ROUND_HALF_UP);
            Ticket ticket = new Ticket(ticketPrice);
            eventTickets.add(ticket);
            numberOfTickets--;
        }
    }

    private int generateRandomNumberOfTickets() {
        return ThreadLocalRandom.current().nextInt(MINIMUM_NUMBER_OF_TICKETS, MAXIMUM_NUMBER_OF_TICKETS_FOR_EVENT+1);
    }

    private boolean eventShouldHaveTickets(Random r) {
        return r.nextBoolean();
    }

    private boolean locationShouldHaveEvent(Random r) {
        return r.nextBoolean();
    }

    public void printEntireMap() {
        for (Location location : listOfLocations) {
            location.printLocationDetails();
        }
    }

    public void printLocationsWithEvents() {
        for (Location location : listOfLocations) {
            if (location.hasEvent()) location.printLocationDetails();
        }
    }

    public void printLocationsWithAvailableTickets() {
        for (Location location : listOfLocations) {
            if (location.hasEvent()) {
                if (location.hasTicketsAvailable()) {
                    location.printLocationDetails();
                }
            }
        }
    }

    public ArrayList<Location> getListOfLocations() {
        return listOfLocations;
    }

    public Location[][] getMapOfLocations() {
        return mapOfLocations;
    }
}
