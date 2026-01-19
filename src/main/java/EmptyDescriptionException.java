public class EmptyDescriptionException extends AnoopException {
    public EmptyDescriptionException(String tasktype) {
        super("The description of a " + tasktype +" cannot be empty.");
    }
}
