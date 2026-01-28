package chatbot.task;

import java.util.ArrayList;

import chatbot.exception.InvalidTaskNumberException;

/**
 * Represents a list of tasks in the chatbot application.
 * <p>
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
     * @throws InvalidTaskNumberException If the index is out of bounds.
     */
    public Task getTask(int index) throws InvalidTaskNumberException {
        if (index < 0 || index >= tasks.size()) {
            throw new InvalidTaskNumberException();
        }
        return this.tasks.get(index);
    }

    /**
     * Deletes the task at the specified index.
     *
     * @param index Index of the task to delete (0-based).
     * @throws InvalidTaskNumberException If the index is out of bounds.
     */
    public void deleteTask(int index) throws InvalidTaskNumberException {
        if (index < 0 || index >= tasks.size()) {
            throw new InvalidTaskNumberException();
        }
        this.tasks.remove(index);
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
     * Returns a list of tasks matching query
     *
     * @param query String input of queried task
     * @return A new ArrayList with matching results
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
     * Returns a string summarizing the number of tasks in the list.
     *
     * @return Summary string of task count.
     */
    @Override
    public String toString() {
        return "Now you have " + this.tasks.size() + " tasks in the list.";
    }
}
