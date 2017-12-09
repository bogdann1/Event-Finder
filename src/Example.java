/**
 * Created by bogdannitescu on 09/12/2017.
 */

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Random;

public class Example {

    public static void main(String[] args)
    {
        Random random = new Random();
        ArrayList<Integer> ints = new ArrayList<Integer>();
        int i = 10;
        while (i > 0) {
            if (random.nextBoolean()) {
                ints.add(1);
            }
            else
            {
                ints.add(0);
            }
            i--;
        }
        //for (int i1 : ints) System.out.println(i1);
        float randomPrice = (float) (0.05+random.nextFloat()*(100-0.05));
        BigDecimal bigDecimal = new BigDecimal(randomPrice).setScale(2,BigDecimal.ROUND_HALF_UP);
        //System.out.println(bigDecimal);

        Map map = new Map();
        map.printLocationsWithAvailableTickets();
    }

}
