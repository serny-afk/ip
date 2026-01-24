package chatbot.exception;

/**
 * Exception thrown when attempting to add a task to a full TaskList.
 */
public class TaskListFullException extends AnoopException {

    /**
     * Constructs a TaskListFullException with a default error message.
     */
    public TaskListFullException() {
        super("chatbot.task.Task list is full. Cannot add more tasks.");
    }
}
