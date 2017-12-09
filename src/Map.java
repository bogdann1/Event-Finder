import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by bogdannitescu on 09/12/2017.
 */
public class Map {
    static final int SQUARED_MAP_SIZE = 10;
    static final int X_COORDINATE_UPPER_BOUND = SQUARED_MAP_SIZE;
    static final int Y_COORDINATE_UPPER_BOUND = X_COORDINATE_UPPER_BOUND;
    static final int X_COORDINATE_LOWER_BOUND = X_COORDINATE_UPPER_BOUND * (-1);
    static final int Y_COORDINATE_LOWER_BOUND = Y_COORDINATE_UPPER_BOUND * (-1);
    public static final int MINIMUM_NUMBER_OF_TICKETS_IF_AVAILABLE = 1;
    public static final int MAXIMUM_NUMBER_OF_TICKETS_FOR_EVENT = 50;
    public static final double MINIMUM_TICKET_PRICE = 10;
    public static final int MAXIMUM_TICKET_PRICE = 100;
    public static final int NUMBER_OF_X_AXIS_POINTS = Math.abs(X_COORDINATE_LOWER_BOUND) + Math.abs(X_COORDINATE_UPPER_BOUND) + 1;
    public static final int NUMBER_OF_Y_AXIS_POINTS = Math.abs(Y_COORDINATE_LOWER_BOUND) + Math.abs(Y_COORDINATE_UPPER_BOUND) + 1;
    public static final int TICKET_PRICE_NUMBER_OF_DECIMALS = 2;
    private int eventNumber = 0;
    private ArrayList<Location> listOfLocations = new ArrayList<>();
    private Location[][] twoDimensionalMapOfLocations = new Location[NUMBER_OF_X_AXIS_POINTS][NUMBER_OF_Y_AXIS_POINTS];

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
        twoDimensionalMapOfLocations[x+X_COORDINATE_UPPER_BOUND][y+Y_COORDINATE_UPPER_BOUND] = location;
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
            float randomPrice = (float) (MINIMUM_TICKET_PRICE + r.nextFloat()*(MAXIMUM_TICKET_PRICE - MINIMUM_TICKET_PRICE));
            BigDecimal ticketPrice = new BigDecimal(randomPrice).setScale(TICKET_PRICE_NUMBER_OF_DECIMALS, RoundingMode.HALF_UP);
            Ticket ticket = new Ticket(ticketPrice);
            eventTickets.add(ticket);
            numberOfTickets--;
        }
    }

    private int generateRandomNumberOfTickets() {
        return ThreadLocalRandom.current().nextInt(MINIMUM_NUMBER_OF_TICKETS_IF_AVAILABLE, MAXIMUM_NUMBER_OF_TICKETS_FOR_EVENT+1);
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

    public Location[][] getTwoDimensionalMapOfLocations() {
        return twoDimensionalMapOfLocations;
    }
}
