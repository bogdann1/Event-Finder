import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by bogdannitescu on 09/12/2017.
 */
public class EventFinder {

    private Map map = new Map();
    private ArrayList<Location> locations = map.getLocations();
    private int xCoordinate;
    private int yCoordinate;

    public EventFinder(int xCoordinate, int yCoordinate) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        filterLocations();
    }

    private void filterLocations() {
        for (Iterator<Location> iterator = locations.iterator(); iterator.hasNext();) {
            Location location = iterator.next();
            if (!location.hasTicketsAvailable()) {
                iterator.remove();
            }
        }
        for (Location location : locations) {
            location.printLocationDetails();
        }
    }

}
