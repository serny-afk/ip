public class TaskListFullException extends AnoopException {
    public TaskListFullException() {
        super("Task list is full. Cannot add more tasks.");
    }
}
