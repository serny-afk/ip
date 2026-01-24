package chatbot.exception;

/**
 * Exception thrown when a task command is missing a description.
 */
public class EmptyDescriptionException extends AnoopException {

    /**
     * Constructs an EmptyDescriptionException for a specific task type.
     *
     * @param tasktype The type of task that is missing a description (e.g., "todo", "deadline", "event").
     */
    public EmptyDescriptionException(String tasktype) {
        super("The description of a " + tasktype + " cannot be empty.");
    }
}
