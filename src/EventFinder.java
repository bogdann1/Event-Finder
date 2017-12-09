import java.util.ArrayList;

/**
 * Created by bogdannitescu on 09/12/2017.
 */
public class EventFinder {

    public static final int NUMBER_OF_EVENTS_TO_BE_DISPLAYED = 5;
    public static final int MAP_SIZE = Map.SQUARED_MAP_SIZE;
    public static final int MAP_UPPER_BOUND = MAP_SIZE;
    public static final int MAP_LOWER_BOUND = MAP_UPPER_BOUND * (-1);

    private Map map = new Map();
    private Location[][] mapOfLocations = map.getTwoDimensionalMapOfLocations();
    private int xCoordinate;
    private int yCoordinate;

    private ArrayList<Location> stack = new ArrayList<>();

    public EventFinder(int xCoordinate, int yCoordinate) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }

    // Breadth-first search from starting coordinates and expanding around
    // the starting point.

    public void searchEvents() {
        ArrayList<Location> visitedLocations = new ArrayList<>();
        Location searchLocation = mapOfLocations[xCoordinate+MAP_SIZE][yCoordinate+MAP_SIZE];
        int numberOfEventsFound = 0;
        stack.add(searchLocation);

        while (BFSConditionHolds(numberOfEventsFound)) {
            Location stackElement = stack.get(0);
            stack.remove(0);
            if (!visitedLocations.contains(stackElement)) {
                // Mark position as visited.
                visitedLocations.add(stackElement);

                // Display location event and cheapest price, if available.
                if (stackElement.hasTicketsAvailable()) {
                    stackElement.printEventCheapestTicket();
                    printDistance(stackElement);
                    numberOfEventsFound++;
                }

                addNeighboursToStack(stackElement);
            }
        }
    }


    public void addLocationToStack(Location location) {
        boolean added = false;

        // If stack is empty, add location to it.
        if (stack.size() == 0) {
            stack.add(location);
        }

        // Else, add the location such that the stack is ordered by
        // the distance to the input coordinates.
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

    private void addNeighboursToStack(Location stackElement) {
        int x = stackElement.getxCoordinate();
        int y = stackElement.getyCoordinate();

        // LEFT NEIGHBOUR
        if (!(x-1 < MAP_LOWER_BOUND)) {
            addLocationToStack(mapOfLocations[x-1+MAP_SIZE][y+MAP_SIZE]);
        }

        // RIGHT NEIGHBOUR
        if (!(x + 1 > MAP_UPPER_BOUND)) {
            addLocationToStack(mapOfLocations[x+1+MAP_SIZE][y+MAP_SIZE]);
        }

        // LOWER NEIGHBOUR
        if (!(y - 1 < MAP_LOWER_BOUND)) {
            addLocationToStack(mapOfLocations[x+MAP_SIZE][y-1+MAP_SIZE]);
        }

        // UPPER NEIGHBOUR
        if (!(y + 1 > MAP_UPPER_BOUND)) {
            addLocationToStack(mapOfLocations[x+MAP_SIZE][y+1+MAP_SIZE]);
        }

        // LOWER LEFT NEIGHBOUR
        if(!(x - 1 < MAP_LOWER_BOUND) && !(y - 1 < MAP_LOWER_BOUND)) {
            addLocationToStack(mapOfLocations[x-1+MAP_SIZE][y-1+MAP_SIZE]);
        }

        // UPPER LEFT NEIGHBOUR
        if(!(x - 1 < MAP_LOWER_BOUND) && !(y + 1 > MAP_UPPER_BOUND)) {
            addLocationToStack(mapOfLocations[x-1+MAP_SIZE][y+1+MAP_SIZE]);
        }

        // LOWER RIGHT NEIGHBOUR
        if(!(x + 1 > MAP_UPPER_BOUND) && !(y - 1 < MAP_LOWER_BOUND)) {
            addLocationToStack(mapOfLocations[x+1+MAP_SIZE][y-1+MAP_SIZE]);
        }

        // UPPER RIGHT NEIGHBOUR
        if(!(x + 1 > MAP_UPPER_BOUND) && !(y + 1 > MAP_UPPER_BOUND)) {
            addLocationToStack(mapOfLocations[x+1+MAP_SIZE][y+1+MAP_SIZE]);
        }
    }

    private boolean BFSConditionHolds(int numberOfEventsFound) {
        return stack.size() > 0 && numberOfEventsFound != NUMBER_OF_EVENTS_TO_BE_DISPLAYED;
    }

    private void printDistance(Location stackElement) {
        int distance = stackElement.distanceToCoordinates(xCoordinate,yCoordinate);
        System.out.println(", Distance "+ distance);
    }

    public void printMap() {
        for (int i = 0; i<=MAP_SIZE*2 ; i++) {
            for (int j = 0; j <= MAP_SIZE*2 ; j++) {
                mapOfLocations[i][j].printLocationDetails();
            }
        }
    }





}
