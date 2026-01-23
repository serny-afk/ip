package chatbot.exception;

public class InvalidTaskNumberException extends AnoopException {
    public InvalidTaskNumberException() {
        super("The task number provided is invalid.");
    }
}
