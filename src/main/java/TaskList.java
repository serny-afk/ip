public class TaskList {
    private int size;
    private final String[] tasks;

    public TaskList() {
        this.tasks = new String[100];
        size = 0;
    }

    public int getSize() {
        return this.size;
    }

    public void addTask(String task) {
        if (size >= tasks.length) {
            throw new TaskListFullException("Task list has exceeded capacity.");
        }
        tasks[this.size++] = task;
    }

    public String[] getTasks() {
        String[] currentTasks = new String[this.size];
        for (int i = 0; i < this.size; i++) {
            currentTasks[i] = this.tasks[i];
        }
        return currentTasks;
    }

}
