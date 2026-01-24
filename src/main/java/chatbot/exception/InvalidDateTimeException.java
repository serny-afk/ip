package chatbot.exception;

/**
 * Exception thrown when a date-time input cannot be parsed
 * because it does not match the expected format.
 */
public class InvalidDateTimeException extends AnoopException {

    /**
     * Constructs an InvalidDateTimeException with a default error message.
     */
    public InvalidDateTimeException() {
        super("Invalid date-time format. Use dd-MM-yyyy HH:mm");
    }
}
