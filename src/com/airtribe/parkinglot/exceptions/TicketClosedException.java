package src.com.airtribe.parkinglot.exceptions;

public class TicketClosedException extends RuntimeException {
    public TicketClosedException(String message) {
        super(message);
    }
}
