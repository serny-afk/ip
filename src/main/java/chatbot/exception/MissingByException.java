package chatbot.exception;

/**
 * Exception thrown when a deadline command is missing a /by time.
 */
public class MissingByException extends AnoopException {

    /**
     * Constructs a MissingByException with a default error message.
     */
    public MissingByException() {
        super("A deadline must have a /by time.");
    }
}
