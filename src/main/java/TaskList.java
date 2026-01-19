public class TaskList {
    private int size;
    private final Task[] tasks;
    private static final int MAX_SIZE = 100;

    public TaskList() {
        this.tasks = new Task[MAX_SIZE];
        size = 0;
    }

    public int getSize() {
        return this.size;
    }

    public void addTask(Task task) throws TaskListFullException {
        if (this.size >= MAX_SIZE) {
            throw new TaskListFullException();
        }
        tasks[this.size++] = task;
    }

    public Task getTask(int index) throws InvalidTaskNumberException {
        if (index < 0 || index >= size) {
            throw new InvalidTaskNumberException();
        }
        return this.tasks[index];
    }

    public Task[] getTasks() {
        Task[] currentTasks = new Task[this.size];
        for (int i = 0; i < this.size; i++) {
            currentTasks[i] = this.tasks[i];
        }
        return currentTasks;
    }

    @Override
    public String toString() {
        return "Now you have " + this.size + " tasks in the list.";
    }

}
