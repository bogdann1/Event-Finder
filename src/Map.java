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
    ArrayList<Location> locations = new ArrayList<>();
    public Map()
    {
        int eventNumber = 0;
        Location location = null;
        Random r = new Random();
        for (int x = X_COORDINATE_LOWER_BOUND; x <= X_COORDINATE_UPPER_BOUND; x++) {
            for (int y = Y_COORDINATE_LOWER_BOUND; y <= Y_COORDINATE_UPPER_BOUND; y++) {
                if (locationShouldHaveEvent(r)) {
                    eventNumber++;
                    String eventName = String.valueOf(eventNumber);
                    ArrayList<Ticket> eventTickets = new ArrayList<>();
                    if (eventShouldHaveTickets(r)) {
                        int randomNum = ThreadLocalRandom.current().nextInt(1, 4);
                        while (randomNum >= 1) {
                            float randomPrice = (float) (0.05 + r.nextFloat()*(100-0.05));
                            BigDecimal ticketPrice = new BigDecimal(randomPrice).setScale(2,BigDecimal.ROUND_HALF_UP);
                            Ticket ticket = new Ticket(ticketPrice);
                            eventTickets.add(ticket);
                            randomNum--;
                        }
                    }
                    Event event = new Event(eventName,eventTickets);
                    location = new Location(x,y,event);
                }
                else {
                    location = new Location(x,y);
                }

                locations.add(location);
            }
        }
    }

    private boolean eventShouldHaveTickets(Random r) {
        return r.nextBoolean();
    }

    private boolean locationShouldHaveEvent(Random r) {
        return r.nextBoolean();
    }

    public void printEntireMap() {
        for (Location location : locations) {
            location.printLocationDetails();
        }
    }

    public void printLocationsWithEvents() {
        for (Location location : locations) {
            if (location.locationHasEvent()) location.printLocationDetails();
        }
    }

    public void printLocationsWithAvailableTickets() {
        for (Location location : locations) {
            if (location.locationHasEvent()) {
                if (location.locationHasTicketsAvailable()) {
                    location.printLocationDetails();
                }
            }
        }
    }

}
