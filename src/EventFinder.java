/**
 * Created by bogdannitescu on 09/12/2017.
 */
public class EventFinder {

    private Map map = new Map();
    Location[][] mapOfLocations = map.getMapOfLocations();
    private int xCoordinate;
    private int yCoordinate;

    public EventFinder(int xCoordinate, int yCoordinate) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }

    public void printMap() {
        for (int i = 0; i<=20 ; i++) {
            for (int j = 0; j <= 20 ; j++) {
                mapOfLocations[i][j].printLocationDetails();
            }
        }
    }


}
