import java.math.BigDecimal;

/**
 * Created by bogdannitescu on 09/12/2017.
 */
public class Ticket {

    private BigDecimal price;

    public Ticket(BigDecimal price) throws ZeroOrLessPriceTicketException {
        if (ticketPriceBiggerThanZero(price)) {
            this.price = price;
        }
        else {
            throw new ZeroOrLessPriceTicketException();
        }
    }

    private boolean ticketPriceBiggerThanZero(BigDecimal price) {
        return price.compareTo(new BigDecimal(0)) == 1;
    }

    public BigDecimal getPrice() {
        return price;
    }

}
