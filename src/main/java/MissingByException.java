public class MissingByException extends AnoopException {
    public MissingByException() {
        super("A deadline must have a /by time.");
    }
}
