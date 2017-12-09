/**
 * Created by bogdannitescu on 09/12/2017.
 */

import java.util.Scanner;

public class Example {

    public static void main(String[] args)
    {
        Scanner inputScanner = new Scanner(System.in);
        System.out.println("Insert coordinates in the following format: x,y > ");
        String line = inputScanner.next();
        String[] storedCoordinates = line.split(",");
        int xInputCoordinate, yInputCoordinate;

        if(storedCoordinates.length < 2)
        {
            System.err.println("Error: Incorrect input coordinates");
            return;
        }

        try
        {
            xInputCoordinate = Integer.parseInt(storedCoordinates[0]);
            yInputCoordinate = Integer.parseInt(storedCoordinates[1]);
        }
        catch(NumberFormatException e)
        {
            System.err.println("Integer input required.");
            return;
        }

        if(xInputCoordinate > 10 || yInputCoordinate > 10 || xInputCoordinate < -10 || yInputCoordinate < -10)
        {
            System.err.println("Input coordinates range is from -10 to 10.");
            return;
        }

        EventFinder eventFinder = new EventFinder(xInputCoordinate,yInputCoordinate);
        eventFinder.searchEvents();

    }

}
