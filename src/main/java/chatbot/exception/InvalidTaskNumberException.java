package chatbot.exception;

/**
 * Exception thrown when a task number provided by the user is invalid.
 */
public class InvalidTaskNumberException extends AnoopException {

    /**
     * Constructs an InvalidTaskNumberException with a default error message.
     */
    public InvalidTaskNumberException() {
        super("The task number provided is invalid.");
    }
}
