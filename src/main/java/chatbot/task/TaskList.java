package chatbot.task;

import chatbot.exception.InvalidTaskNumberException;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    public int getSize() {
        return this.tasks.size();
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public Task getTask(int index) throws InvalidTaskNumberException {
        if (index < 0 || index >= tasks.size()) {
            throw new InvalidTaskNumberException();
        }
        return this.tasks.get(index);
    }

    public void deleteTask(int index) throws InvalidTaskNumberException {
        if (index < 0 || index >= tasks.size()) {
            throw new InvalidTaskNumberException();
        }
        this.tasks.remove(index);
    }

    public ArrayList<Task> getTasks() {
        return new ArrayList<>(this.tasks);
    }

    @Override
    public String toString() {
        return "Now you have " + this.tasks.size() + " tasks in the list.";
    }

}
