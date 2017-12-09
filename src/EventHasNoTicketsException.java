/**
 * Created by bogdannitescu on 09/12/2017.
 */
public class EventHasNoTicketsException extends RuntimeException {
    public EventHasNoTicketsException() {
        super("Can't get cheapest ticket of an event without tickets");
    }
}
