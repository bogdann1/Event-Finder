/**
 * Created by bogdannitescu on 09/12/2017.
 */
public class Location {

    private int xCoordinate;
    private int yCoordinate;
    private Event event = null;

    public Location(int xCoordinate, int yCoordinate, Event event) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.event = event;
    }

    public boolean locationHasEvent() {
        return this.event != null;
    }

}
