package chatbot.exception;

public class TaskListFullException extends AnoopException {
    public TaskListFullException() {
        super("chatbot.task.Task list is full. Cannot add more tasks.");
    }
}
