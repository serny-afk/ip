package chatbot.exception;

public class InvalidDateTimeException extends AnoopException {
    public InvalidDateTimeException() {
        super("Invalid date-time format. Use dd-MM-yyyy HH:mm");
    }
}
