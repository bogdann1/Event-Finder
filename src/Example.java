/**
 * Created by bogdannitescu on 09/12/2017.
 */

import java.util.Scanner;

public class Example {

    public static void main(String[] args)
    {
        //Asking for coordinate input and parsing it
        Scanner in = new Scanner(System.in);
        System.out.println("Please input co-ordinates:");
        String line = in.next();
        String[] ords = line.split(",");

        //Checking input
        if(ords.length < 2)
        {
            System.err.println("Error: Incorrect input coordinates");
            return;
        }

        int x, y;
        try
        {
            x = Integer.parseInt(ords[0]);
            y = Integer.parseInt(ords[1]);
        }
        catch(NumberFormatException e)
        {
            System.err.println("Error: Enter Integer coordinates");
            return;
        }

        if(x > 10 || y > 10 || x < -10 || y < -10)
        {
            System.err.println("Error: Enter coordinates in the range 10 to -10");
            return;
        }

        EventFinder eventFinder = new EventFinder(x,y);

        eventFinder.searchEvents();

    }

}
