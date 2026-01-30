package chatbot.ui;

import java.util.ArrayList;
import java.util.Scanner;

import chatbot.exception.AnoopException;
import chatbot.task.Task;
import chatbot.task.TaskList;

/**
 * Handles interaction with the user through the command line.
 * Responsible for printing messages, reading user input, and displaying tasks and errors.
 */
public class Ui {
    private static final String STRAIGHT_LINE = "____________________________________________________________";
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Prints a welcome message to the user.
     */
    public String userWelcome() {
        String output = "Hello, I'm Anoop. I'm taking 48 MCs this sem.\nWhat can I do for you?\n";
        System.out.println(output);
        return output;
    }

    /**
     * Reads a line of input from the user.
     *
     * @return The user's input as a string.
     */
    public String readCommand() {
        return this.scanner.nextLine();
    }

    /**
     * Prints a goodbye message to the user.
     */
    public String userGoodbye() {
        String output = "Bye! See you in France!\n";
        System.out.println(output);
        return output;
    }

    /**
     * Displays the list of tasks.
     *
     * @param tasklist The TaskList to display.
     */
    public String showTaskList(TaskList tasklist) {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the tasks in your list:\n");

        try {
            for (int i = 0; i < tasklist.getSize(); i++) {
                sb.append(i + 1).append(". ").append(tasklist.getTask(i)).append("\n");
            }
        } catch (AnoopException e) {
            sb.append(e.getMessage());
        }

        String output = sb.toString().trim();
        System.out.println(output);
        return output;
    }

    /**
     * Displays the task that has been added.
     *
     * @param task The task that was added.
     */
    public String showAddedTask(Task task) {
        String output = "Got it. I've added this task:\n" + task.toString();
        System.out.println(output);
        return output;
    }

    /**
     * Displays the task that has been marked as done.
     *
     * @param task The task that was marked done.
     */
    public String showMarkedTask(Task task) {
        String output = "Nice! I've marked this task as done:\n" + task.toString();
        System.out.println(output);
        return output;
    }

    /**
     * Displays the task that has been unmarked (not done).
     *
     * @param task The task that was unmarked.
     */
    public String showUnmarkedTask(Task task) {
        String output = "OK, I've marked this task as not done yet:\n" + task.toString();
        System.out.println(output);
        return output;
    }

    /**
     * Displays an error message to the user.
     *
     * @param errorMessage The error message to display.
     */
    public String showError(String errorMessage) {
        String output = "Error. " + errorMessage;
        System.out.println(output);
        return output;
    }

    /**
     * Displays the current count of tasks.
     *
     * @param tasklist The TaskList whose count is displayed.
     */
    public String showTaskCount(TaskList tasklist) {
        String output = tasklist.toString();
        System.out.println(output);
        return output;
    }

    /**
     * Displays the task that was deleted and the updated task list.
     *
     * @param task     The task that was deleted.
     * @param tasklist The updated TaskList after deletion.
     */
    public String showDeletedTask(Task task, TaskList tasklist) {
        String output = "Noted. I've removed this task:\n" + task.toString();
        System.out.println(output);
        return output;
    }

    /**
     * TODO add javadoc
     * @param matchingTasks show tasks
     */
    public String showMatchingTasks(ArrayList<Task> matchingTasks) {
        StringBuilder sb = new StringBuilder();

        if (matchingTasks.isEmpty()) {
            sb.append("No matching tasks found.\n");
        } else {
            sb.append("Here are the matching tasks found:\n");
            for (int i = 0; i < matchingTasks.size(); i++) {
                sb.append(i + 1).append(". ").append(matchingTasks.get(i)).append("\n");
            }
        }
        String output = sb.toString();
        System.out.println(output);
        return output;
    }
}
