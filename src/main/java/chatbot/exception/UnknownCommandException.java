package chatbot.exception;

public class UnknownCommandException extends AnoopException {
    public UnknownCommandException() {
        super("Invalid command.");
    }
}
