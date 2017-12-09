import java.util.ArrayList;

/**
 * Created by bogdannitescu on 09/12/2017.
 */
public class EventFinder {

    private Map map = new Map();
    Location[][] mapOfLocations = map.getMapOfLocations();
    private int xCoordinate;
    private int yCoordinate;

    private ArrayList<Location> stack = new ArrayList<>();

    public EventFinder(int xCoordinate, int yCoordinate) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }

    public void addToStack(Location location) {
        boolean added = false;
        if (stack.size() == 0) {
            stack.add(location);
        }
        else
        {
            for (int i = 0; i<stack.size(); i++) {
                Location stackElement = stack.get(i);
                if (stackElement.distanceToCoordinates(xCoordinate,yCoordinate) >
                        location.distanceToCoordinates(xCoordinate,yCoordinate) &&
                        !added) {
                    stack.add(i,location);
                    added = true;
                }
            }
            if (!added) stack.add(location);
        }
    }

    public void searchEvents() {
        ArrayList<Location> visitedLocations = new ArrayList<>();
        Location searchLocation = mapOfLocations[xCoordinate+10][yCoordinate+10];
        int numberOfEventsFound = 0;
        stack.add(searchLocation);

        while (stack.size() > 0 && numberOfEventsFound != 5) {
            Location stackElement = stack.get(0);
            stack.remove(0);
            if (!visitedLocations.contains(stackElement)) {
                visitedLocations.add(stackElement);
                if (stackElement.hasTicketsAvailable()) {
                    stackElement.printEventCheapestTicket();
                    printDistance(stackElement);
                    numberOfEventsFound++;
                }

                int x = stackElement.getxCoordinate();
                int y = stackElement.getyCoordinate();

                if (!(x-1 < -10)) {
                    addToStack(mapOfLocations[x-1+10][y+10]);
                }

                if (!(x + 1 > 10)) {
                    addToStack(mapOfLocations[x+1+10][y+10]);
                }

                if (!(y - 1 < -10)) {
                    addToStack(mapOfLocations[x+10][y-1+10]);
                }

                if (!(y + 1 > 10)) {
                    addToStack(mapOfLocations[x+10][y+1+10]);
                }

                if(!(x - 1 < -10) && !(y - 1 < -10)) {
                    addToStack(mapOfLocations[x-1+10][y-1+10]);
                }

                if(!(x - 1 < -10) && !(y + 1 > 10)) {
                    addToStack(mapOfLocations[x-1+10][y+1+10]);
                }

                if(!(x + 1 > 10) && !(y - 1 < -10)) {
                    addToStack(mapOfLocations[x+1+10][y-1+10]);
                }

                if(!(x + 1 > 10) && !(y + 1 > 10)) {
                    addToStack(mapOfLocations[x+1+10][y+1+10]);
                }
            }
        }
    }

    private void printDistance(Location stackElement) {
        int distance = stackElement.distanceToCoordinates(xCoordinate,yCoordinate);
        System.out.println(", Distance "+ distance);
    }

    public void printMap() {
        for (int i = 0; i<=20 ; i++) {
            for (int j = 0; j <= 20 ; j++) {
                mapOfLocations[i][j].printLocationDetails();
            }
        }
    }





}
