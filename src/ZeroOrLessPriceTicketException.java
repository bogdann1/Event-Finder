/**
 * Created by bogdannitescu on 09/12/2017.
 */
public class ZeroOrLessPriceTicketException extends RuntimeException {
    public ZeroOrLessPriceTicketException() {
        super("Ticket price should not be 0");
    }
}
