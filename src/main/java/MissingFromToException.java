public class MissingFromToException extends AnoopException{
    public MissingFromToException() {
        super("An event must have both /from and /to times.");
    }
}
