package chatbot.exception;

/**
 * Exception thrown when an event command is missing either /from or /to.
 */
public class MissingFromToException extends AnoopException {

    /**
     * Constructs a MissingFromToException with a default error message.
     */
    public MissingFromToException() {
        super("An event must have both /from and /to times.");
    }
}
