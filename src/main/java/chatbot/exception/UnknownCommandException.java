package chatbot.exception;

/**
 * Exception thrown when the user enters an unknown or invalid command.
 */
public class UnknownCommandException extends AnoopException {

    /**
     * Constructs an UnknownCommandException with a default error message.
     */
    public UnknownCommandException() {
        super("Invalid command.");
    }
}
