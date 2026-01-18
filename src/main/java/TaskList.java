public class TaskList {
    private int size;
    private final Task[] tasks;

    public TaskList() {
        this.tasks = new Task[100];
        size = 0;
    }

    public int getSize() {
        return this.size;
    }

    public void addTask(String description) {
        if (size >= tasks.length) {
            throw new TaskListFullException("Task list has exceeded capacity.");
        }
        tasks[this.size++] = new Task(description);
    }

    public Task getTask(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid task index.");
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

}
