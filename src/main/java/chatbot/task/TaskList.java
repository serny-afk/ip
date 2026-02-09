package chatbot.task;

import java.util.ArrayList;

import chatbot.exception.AnoopException;

/**
 * Represents a list of tasks in the chatbot application.
 * Provides methods to add, delete, and retrieve tasks.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList containing the provided tasks.
     *
     * @param tasks Initial list of tasks to include.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The size of the task list.
     */
    public int getSize() {
        return this.tasks.size();
    }

    /**
     * Adds a task to the list.
     *
     * @param task The task to add.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Retrieves the task at the specified index.
     *
     * @param index Index of the task to retrieve (0-based).
     * @return The Task object at the given index.
     * @throws AnoopException If the index is out of bounds.
     */
    public Task getTask(int index) throws AnoopException {
        checkIndexValid(index);
        return this.tasks.get(index);
    }

    /**
     * Deletes the task at the specified index from the list.
     *
     * @param index Index of the task to delete (0-based).
     * @return The task that was deleted.
     * @throws AnoopException If the index is out of bounds.
     */
    public Task deleteTask(int index) throws AnoopException {
        this.checkIndexValid(index);
        Task t = this.getTask(index);
        this.tasks.remove(index);
        return t;
    }

    /**
     * Returns a copy of the list of tasks.
     *
     * @return A new ArrayList containing all tasks.
     */
    public ArrayList<Task> getTasks() {
        return new ArrayList<>(this.tasks);
    }

    /**
     * Returns a list of tasks matching the query string.
     *
     * @param query String input of queried task.
     * @return A new ArrayList with tasks containing the query in their description.
     */
    public ArrayList<Task> findTasks(String query) {
        ArrayList<Task> matches = new ArrayList<>();
        String keyword = query.toLowerCase();

        for (Task task : this.tasks) {
            if (task.getDescription().toLowerCase().contains(keyword)) {
                matches.add(task);
            }
        }
        return matches;
    }

    /**
     * Marks the task at the specified index as done.
     *
     * @param index Index of the task to mark (0-based).
     * @return The task that was marked as done.
     * @throws AnoopException If the index is out of bounds.
     */
    public Task markTask(int index) throws AnoopException {
        checkIndexValid(index);
        Task t = tasks.get(index);
        t.markAsDone();
        return t;
    }

    /**
     * Marks the task at the specified index as not done.
     *
     * @param index Index of the task to unmark (0-based).
     * @return The task that was marked as not done.
     * @throws AnoopException If the index is out of bounds.
     */
    public Task unmarkTask(int index) throws AnoopException {
        checkIndexValid(index);
        Task t = tasks.get(index);
        t.markAsUndone();
        return t;
    }

    /**
     * Checks if the provided index is valid for the task list.
     *
     * @param index Index to check (0-based).
     * @throws AnoopException If the index is less than 0 or greater than or equal to the number of tasks.
     */
    private void checkIndexValid(int index) throws AnoopException {
        if (index < 0 || index >= tasks.size()) {
            throw new AnoopException("index out of bounds");
        }
    }

    /**
     * Returns a string summarizing the number of tasks in the list.
     *
     * @return Summary string of task count.
     */
    @Override
    public String toString() {
        return "Now you have " + this.tasks.size() + " tasks in the list.";
    }
}
